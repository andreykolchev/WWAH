package com.restfully.webapp.DAO;

/**
 *
 * @author 2015 Andrey Kolchev mailto: andreykolchev@gmail.com
 */
import com.restfully.webapp.model.Car;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.logging.Logger;

public class CarDAO {

    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    private static final Logger log = Logger.getLogger(CarDAO.class.getName());

    public List<Car> find(int car_id, int language_id, int currency_id) throws SQLException {
        List<Car> carList = new ArrayList<Car>();
        String TextQuery;
        int currencyid = 1;
        try {
            TextQuery = "";
            if (car_id != 0) {
                TextQuery = MySQL.addLine(TextQuery);
                TextQuery = TextQuery + "rentalcars.car.car_id = " + Integer.toString(car_id);
            }
            if (language_id != 0) {
                TextQuery = MySQL.addLine(TextQuery);
                TextQuery = TextQuery + "rentalcars.car.language_id = " + Integer.toString(language_id);
            }
            if (currency_id != 0) {
                    currencyid = currency_id;
                }
            TextQuery = TextQuery + ";";
            String selectSQL = "SELECT rentalcars.car.*, rentalcars.model.img_path, rentalcars.car_prices.price_day/rentalcars.currency_exchange.course*rentalcars.currency_exchange.multiplicity AS price_day, rentalcars.car_prices.price_week/rentalcars.currency_exchange.course*rentalcars.currency_exchange.multiplicity AS price_week, rentalcars.car_prices.price_month/rentalcars.currency_exchange.course*rentalcars.currency_exchange.multiplicity AS price_month FROM rentalcars.car LEFT JOIN rentalcars.model ON (rentalcars.car.model_id = rentalcars.model.model_id and rentalcars.car.language_id = rentalcars.model.language_id) LEFT JOIN rentalcars.car_prices ON (rentalcars.car.car_id = rentalcars.car_prices.car_id) LEFT JOIN rentalcars.currency_exchange ON (rentalcars.car_prices.currency_id = rentalcars.currency_exchange.currency_id)" + TextQuery;
            connection = MySQL.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(selectSQL);
            while (resultSet.next()) {
                int carid = 0;
                int languageid = 0;
                if (car_id == 0) {
                    carid = resultSet.getInt("car_id");
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
                Car car = new Car(carid, languageid, name, description, img_path, price_day, price_week, price_month);
                carList.add(car);
            }
            return carList;
        } catch (SQLException ex) {
            log.info(ex.getMessage());
        } finally {
            MySQL.closeConnection(connection, statement);
        }
        return null;
    }

}
