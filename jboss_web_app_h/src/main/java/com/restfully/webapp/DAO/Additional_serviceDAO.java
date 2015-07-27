package com.restfully.webapp.DAO;

/**
 *
 * @author 2015 Dmitry Suvorov mailto: suvdima@gmail.com
 */
import com.restfully.webapp.model.Additional_service;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.logging.Logger;

public class Additional_serviceDAO {

    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    private static final Logger log = Logger.getLogger(Additional_serviceDAO.class.getName());

    public List<Additional_service> find(int additional_service_id, int language_id, int currency_id) throws SQLException {
        List<Additional_service> additional_serviceList = new ArrayList<Additional_service>();
        String TextQuery;
        int currencyid = 1;
        try {
            TextQuery = "";
            if (additional_service_id != 0) {
                TextQuery = MySQL.addLine(TextQuery);
                TextQuery = TextQuery + "additional_service_id = " + Integer.toString(additional_service_id);
            }
            if (language_id != 0) {
                TextQuery = MySQL.addLine(TextQuery);
                TextQuery = TextQuery + "language_id = " + Integer.toString(language_id);
            }
            if (currency_id != 0) {
                    currencyid = currency_id;
                }
            TextQuery = TextQuery + ";";
            String selectSQL = "SELECT rentalcars.additional_service.*, rentalcars.additional_service_prices.price_day/rentalcars.currency_exchange.course*rentalcars.currency_exchange.multiplicity AS price_day, rentalcars.additional_service_prices.price_week/rentalcars.currency_exchange.course*rentalcars.currency_exchange.multiplicity AS price_week, rentalcars.additional_service_prices.price_month/rentalcars.currency_exchange.course*rentalcars.currency_exchange.multiplicity AS price_month FROM rentalcars.additional_service LEFT JOIN rentalcars.additional_service_prices ON (rentalcars.additional_service.additional_service_id = rentalcars.additional_service_prices.additional_service_id) LEFT JOIN rentalcars.currency_exchange ON (rentalcars.additional_service_prices.currency_id = rentalcars.currency_exchange.currency_id)" + TextQuery;
            connection = MySQL.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(selectSQL);
            while (resultSet.next()) {
                int additional_serviceid = 0;
                int languageid = 0;
                if (additional_service_id == 0) {
                    additional_serviceid = resultSet.getInt("additional_service_id");
                }
                if (language_id == 0) {
                    languageid = resultSet.getInt("language_id");
                }
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                String img_path = resultSet.getString("img_path");
                int price_day = resultSet.getInt("price_day");
                int price_week = resultSet.getInt("price_week");
                int price_month = resultSet.getInt("price_month");
                Additional_service additional_service = new Additional_service(additional_serviceid, languageid, name, description, img_path, price_day, price_week, price_month);
                additional_serviceList.add(additional_service);
            }
            return additional_serviceList;
        } catch (SQLException ex) {
            log.info(ex.getMessage());
        } finally {
            MySQL.closeConnection(connection, statement);
        }
        return null;
    }

}
