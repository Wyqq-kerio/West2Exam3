import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import dao.ICountryDao;
import dao.IDistrictDao;
import domain.Country;
import domain.District;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class Get {
    InputStream in;
    SqlSessionFactoryBuilder builder;
    SqlSessionFactory factory;
   static SqlSession session;
   static ICountryDao iCountryDao;
    IDistrictDao iDistrictDao;

    public static void main(String[] args) throws Exception {
        Get get=new Get();

        get.init();
        Country country=new Country();
        country.setAbbreviation("Test");
        System.out.println("保存前");
        iCountryDao.insertData(country);
        session.commit();
        System.out.println("保存后");

//        List<Country> lists=iCountryDao.findCountry();
//        for(Country country1:lists){
//            System.out.println(lists);
//        }

    }

    public void init() throws Exception {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        builder = new SqlSessionFactoryBuilder();
        factory = builder.build(in);
        session = factory.openSession();
        iCountryDao = session.getMapper(ICountryDao.class);
        iDistrictDao = session.getMapper(IDistrictDao.class);
    }



    /**
     *  以 Get 方法调用API
     */
    public static String doGet() throws IOException{
        OutputStreamWriter out = null;
        BufferedReader  br = null;
        String result =  "";
        try {
            //https://covid-api.mmediagroup.fr/v1/cases
            URL url = new URL("https://covid-api.mmediagroup.fr/v1/cases");
            //打开和 URL 之间的连接
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            //设置通用属性
            conn.setRequestProperty("accept", "*/*");
            InputStream is = (InputStream) conn.getInputStream();
            //构造一个字符缓冲流
            br = new BufferedReader(new InputStreamReader(is));
            String str = "";
            while((str=br.readLine())!=null) {
                result +=str;
            }
            return result;
            //关闭流

        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }finally {
            try {
                if(out != null) {
                    out.close();
                }
                if(br != null) {
                    br.close();
                }
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public  void insertIntoDatabase(String name) throws Exception {
        init();
        String url = "https://covid-api.mmediagroup.fr/v1/cases?country=" + name;

        try {

            /**
             * 网站不稳定
             * 选择读取保存的JSON
             */
//              String data=doGet();

            ReadJSON readJSON=new ReadJSON();
            String   data=readJSON.readJson(name);
            System.out.println(data);
            JSONObject jsonObject = JSON.parseObject(data);

            String[] countrys = new String[15];
            String[] districts = new String[8];

            //判断是否是国家信息
            boolean check = false;

            int iso = 0;

            for (Map.Entry<String, Object> stringObjectEntry : jsonObject.entrySet()) {

                String key = stringObjectEntry.getKey();

                JSONObject value = (JSONObject) stringObjectEntry.getValue();

                if (key == "All") {
                    check = true;
                }
                JSONObject insideJson = value;
                int count = 0;
                for (Map.Entry<String, Object> insideEntry : insideJson.entrySet()) {

                    Object getValue = insideEntry.getValue();

                    //存入数据
                    if (check) {
                        countrys[count++] = getValue.toString();
                    } else {
                        districts[count++] = getValue.toString();
                    }

                }
                if (check) {//是国家数据
                    iso = Integer.parseInt(countrys[2]);
                    check = false;
                    Country country=new Country();
                    country.setIso(iso);
                    country.setContinent(countrys[0]);
                    country.setCapital_city(countrys[3]);
                    country.setLife_expectancy(Double.parseDouble(countrys[4]));
                    country.setAbbreviation(countrys[5]);
                    country.setConfirmed(Integer.parseInt(countrys[6]));
                    country.setPopulation(Integer.parseInt(countrys[7]));
                    country.setSq_km_area(Integer.parseInt(countrys[8]));
                    country.setRecovered(Integer.parseInt(countrys[9]));
                    country.setElevation_in_meters(countrys[10]);
                    country.setLocation(countrys[11]);
                    country.setDeaths(Integer.parseInt(countrys[12]));
                    //存入数据库
                    iCountryDao.insertData(country);
                    //手动1提交事务
                    session.commit();

                } else {
                    //存储地区
                   District district=new District();
                    district.setIso_dis(iso);
                    district.setProvinceName(key);
                    district.setRecovered(Integer.parseInt(districts[0]));
                    district.setConfirmed(Integer.parseInt(districts[1]));
                    district.setUpdated(districts[2]);
                    district.setDeaths(Integer.parseInt(districts[5]));
                    //存入数据库
                    iDistrictDao.insertData(district);
                    session.commit();
                }

            }

            System.out.println(name+"成功。");

        } catch (Exception e) {

            System.out.println(e.getMessage());
            throw new RuntimeException(name+"失败。");

        }

    }
}
