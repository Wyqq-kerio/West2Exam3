package dao;

import domain.Country;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ICountryDao {


    @Select("select * from country")
    List<Country> findCountry();

    @Insert("insert into country\n" +
            "        (  iso,continent,capital_city,life_expectancy,abbreviation,confirmed, population,sq_km_area,recovered,elevation_in_meters,location,deaths)\n" +
            "        values\n" +
            "        ( #{iso},#{continent},#{capital_city},#{life_expectancy},\n" +
            "        #{abbreviation},#{confirmed}, #{population},#{sq_km_area},#{recovered},\n" +
            "        #{elevation_in_meters},#{location},#{deaths} )")
    void insertData(Country country);
}
