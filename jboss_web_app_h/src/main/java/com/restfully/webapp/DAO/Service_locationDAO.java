package com.restfully.webapp.DAO;

/**
 *
 * @author 2015 Andrey Kolchev mailto: andreykolchev@gmail.com
 */
import com.restfully.webapp.model.Service_location;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.logging.Logger;

public class Service_locationDAO {

    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    private static final Logger log = Logger.getLogger(Service_locationDAO.class.getName());

    public List<Service_location> find(int city_id, int language_id, int service_location_id) throws SQLException {
        List<Service_location> service_locationList = new ArrayList<Service_location>();
        String TextQuery;
        try {
            TextQuery = "";
            if (city_id != 0) {
                TextQuery = MySQL.addLine(TextQuery);
                TextQuery = TextQuery + "city_id = " + Integer.toString(city_id);
            }
            if (language_id != 0) {
                TextQuery = MySQL.addLine(TextQuery);
                TextQuery = TextQuery + "language_id = " + Integer.toString(language_id);
            }
            if (service_location_id != 0) {
                TextQuery = MySQL.addLine(TextQuery);
                TextQuery = TextQuery + "service_location_id = " + Integer.toString(service_location_id);
            }
            TextQuery = TextQuery + ";";
            String selectSQL = "SELECT service_location_id, language_id, service_location_id, name FROM service_location" + TextQuery;
            connection = MySQL.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(selectSQL);
            while (resultSet.next()) {
                int servicelocationid = 0;
                int languageid = 0;
                int cityid = 0;
                if (service_location_id == 0) {
                    servicelocationid = resultSet.getInt("service_location_id");
                }
                if (language_id == 0) {
                    languageid = resultSet.getInt("language_id");
                }
                if (city_id == 0) {
                    cityid = resultSet.getInt("city_id");
                }
                String name = resultSet.getString("name");
                Service_location service_location = new Service_location(servicelocationid, languageid, cityid, name);
                service_locationList.add(service_location);
            }
            return service_locationList;
        } catch (SQLException ex) {
            log.info(ex.getMessage());
        } finally {
            MySQL.closeConnection(connection, statement);
        }
        return null;
    }

}
