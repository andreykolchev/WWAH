package com.restfully.webapp.DAO;

/**
 *
 * @author 2015 Andrey Kolchev mailto: andreykolchev@gmail.com
 */
import com.restfully.webapp.model.Country;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.logging.Logger;

public class CountryDAO {

    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    private static final Logger log = Logger.getLogger(CountryDAO.class.getName());

    public List<Country> find(int country_id, int language_id) throws SQLException {
        List<Country> countryList = new ArrayList<Country>();
        String TextQuery;
        try {
            TextQuery = "";
            if (country_id != 0) {
                TextQuery = MySQL.addLine(TextQuery);
                TextQuery = TextQuery + "car_id = " + Integer.toString(country_id);
            }
            if (language_id != 0) {
                TextQuery = MySQL.addLine(TextQuery);
                TextQuery = TextQuery + "language_id = " + Integer.toString(language_id);
            }
            TextQuery = TextQuery + ";";
            String selectSQL = "SELECT country_id, language_id, name FROM country" + TextQuery;
            connection = MySQL.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(selectSQL);
            while (resultSet.next()) {
                int countryid = 0;
                int languageid = 0;
                if (country_id == 0) {
                    countryid = resultSet.getInt("country_id");
                }
                if (language_id == 0) {
                    languageid = resultSet.getInt("language_id");
                }
                String name = resultSet.getString("name");
                Country country = new Country(countryid, languageid, name);
                countryList.add(country);
            }
            return countryList;
        } catch (SQLException ex) {
            log.info(ex.getMessage());
        } finally {
            MySQL.closeConnection(connection, statement);
        }
        return null;
    }
}
