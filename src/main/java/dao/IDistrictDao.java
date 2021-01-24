package dao;

import domain.Country;
import domain.District;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IDistrictDao {
    @Select("select * from district")
    List<District> findDistrict();

    @Insert(" insert into district (iso_dis,provinceName,recovered,confirmed,updated,deaths)\n" +
            "        values(#{iso_dis},#{provinceName},#{recovered},#{confirmed},#{updated},#{deaths})\n")
    void insertData(District district);
}
