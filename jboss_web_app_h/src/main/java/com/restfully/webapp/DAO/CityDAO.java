package com.restfully.webapp.DAO;

/**
 *
 * @author 2015 Andrey Kolchev mailto: andreykolchev@gmail.com
 */
import com.restfully.webapp.model.City;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.logging.Logger;

public class CityDAO {

    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    private static final Logger log = Logger.getLogger(CityDAO.class.getName());

    public List<City> find(int country_id, int language_id, int city_id) throws SQLException {
        List<City> cityList = new ArrayList<City>();
        String TextQuery;
        try {
            TextQuery = "";
            if (country_id != 0) {
                TextQuery = MySQL.addLine(TextQuery);
                TextQuery = TextQuery + "country_id = " + Integer.toString(country_id);
            }
            if (language_id != 0) {
                TextQuery = MySQL.addLine(TextQuery);
                TextQuery = TextQuery + "language_id = " + Integer.toString(language_id);
            }
            if (city_id != 0) {
                TextQuery = MySQL.addLine(TextQuery);
                TextQuery = TextQuery + "city_id = " + Integer.toString(city_id);
            }
            TextQuery = TextQuery + ";";
            String selectSQL = "SELECT city_id, language_id, country_id, name FROM city" + TextQuery;
            connection = MySQL.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(selectSQL);
            while (resultSet.next()) {
                int cityid = 0;
                int languageid = 0;
                int countryid = 0;
                if (city_id == 0) {
                    cityid = resultSet.getInt("city_id");
                }
                if (language_id == 0) {
                    languageid = resultSet.getInt("language_id");
                }
                if (country_id == 0) {
                    countryid = resultSet.getInt("country_id");
                }
                String name = resultSet.getString("name");
                City city = new City(cityid, languageid, countryid, name);
                cityList.add(city);
            }
            return cityList;
        } catch (SQLException ex) {
            log.info(ex.getMessage());
        } finally {
            MySQL.closeConnection(connection, statement);
        }
        return null;
    }

}
