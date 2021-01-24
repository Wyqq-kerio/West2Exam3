import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.io.*;

/**
 * 为了避免获取第三方网站的数据因为网络问题导致错误，事先将数据存入data.json文件中
 */

public class ReadJSON {

    @Test
    public static String readJson(String name){

        String jsonStr = "";
        try {
            File jsonFile = new File("src/main/java/data.json");
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile),"utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            JSONObject jsonObject=JSONObject.parseObject(jsonStr);
            String result=jsonObject.getString(name);

            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


    }

    public static void main(String[] args) {
        System.out.println(readJson("China"));
    }
}
