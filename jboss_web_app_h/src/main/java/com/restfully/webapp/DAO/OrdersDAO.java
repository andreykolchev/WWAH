package com.restfully.webapp.DAO;

/**
 *
 * @author 2015 Andrey Kolchev mailto: andreykolchev@gmail.com
 */
import com.restfully.webapp.model.Orders;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class OrdersDAO {

    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    private static final Logger log = Logger.getLogger(OrdersDAO.class.getName());

    public List<Orders> find(int id, int order_number, String order_date, int account_id, int car_id, int get_service_location_id, String get_date_time, int put_service_location_id, String put_date_time) throws SQLException {
        List<Orders> ordersList = new ArrayList<Orders>();
        String TextQuery;
        try {
            TextQuery = "";
            if (id != 0) {
                TextQuery = MySQL.addLine(TextQuery);
                TextQuery = TextQuery + "id = " + Integer.toString(id);
            }
            if (order_number != 0) {
                TextQuery = MySQL.addLine(TextQuery);
                TextQuery = TextQuery + "order_number = " + Integer.toString(order_number);
            }
            if (!(order_date.equals(""))) {
                TextQuery = MySQL.addLine(TextQuery);
                TextQuery = TextQuery + "order_date = \"" + order_date + "\"";
            }
            if (account_id != 0) {
                TextQuery = MySQL.addLine(TextQuery);
                TextQuery = TextQuery + "account_id = " + Integer.toString(account_id);
            }
            if (car_id != 0) {
                TextQuery = MySQL.addLine(TextQuery);
                TextQuery = TextQuery + "car_id = " + Integer.toString(car_id);
            }
            if (get_service_location_id != 0) {
                TextQuery = MySQL.addLine(TextQuery);
                TextQuery = TextQuery + "get_service_location_id = " + Integer.toString(get_service_location_id);
            }
            if (!(get_date_time.equals(""))) {
                TextQuery = MySQL.addLine(TextQuery);
                TextQuery = TextQuery + "get_date_time = \"" + get_date_time + "\"";
            }
            if (put_service_location_id != 0) {
                TextQuery = MySQL.addLine(TextQuery);
                TextQuery = TextQuery + "put_service_location_id = " + Integer.toString(put_service_location_id);
            }
            if (!(put_date_time.equals(""))) {
                TextQuery = MySQL.addLine(TextQuery);
                TextQuery = TextQuery + "put_date_time = \"" + put_date_time + "\"";
            }
            TextQuery = TextQuery + ";";
            String selectSQL = "SELECT id, order_number, order_date, description, account_id, car_id, get_service_location_id, get_date_time, put_service_location_id, put_date_time FROM orders" + TextQuery;
            connection = MySQL.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(selectSQL);
            while (resultSet.next()) {
                int id_this = 0;
                int ordernumber = 0;
                String orderdate = "";
                int accountid = 0;
                int carid = 0;
                int getservicelocationid = 0;
                String getdatetime = "";
                int putservicelocationid = 0;
                String putdatetime = "";
                if (id == 0) {
                    id_this = resultSet.getInt("id");
                }
                if (order_number == 0) {
                    ordernumber = resultSet.getInt("order_number");
                }
                if (!(order_date.equals(""))) {
                    orderdate = resultSet.getString("order_date");
                }
                if (account_id == 0) {
                    accountid = resultSet.getInt("account_id");
                }
                if (car_id == 0) {
                    carid = resultSet.getInt("car_id");
                }
                if (get_service_location_id == 0) {
                    getservicelocationid = resultSet.getInt("get_service_location_id");
                }
                if (!(get_date_time.equals(""))) {
                    getdatetime = resultSet.getString("get_date_time");
                }
                if (put_service_location_id == 0) {
                    putservicelocationid = resultSet.getInt("put_service_location_id");
                }
                if (!(put_date_time.equals(""))) {
                    putdatetime = resultSet.getString("put_date_time");
                }
                String description = resultSet.getString("description");
                Orders orders = new Orders(id_this, ordernumber, orderdate, description, accountid, carid, getservicelocationid, getdatetime, putservicelocationid, putdatetime);
                ordersList.add(orders);
            }
            return ordersList;
        } catch (SQLException ex) {
            log.info(ex.getMessage());
        } finally {
            MySQL.closeConnection(connection, statement);
        }
        return null;
    }

    public Orders create(Orders orders) throws SQLException {
        String table_name = "orders";
        int up_key = 1;

        orders.id = MySQL.getNextId(table_name);
        orders.order_number = MySQL.getNextNumber(table_name);
        orders.order_date = MySQL.getFormatedDate(new Date());

        try {
            connection = MySQL.getConnection();
            statement = connection.createStatement();
            String insertSQL = "INSERT INTO orders (id,"
                    + " order_number,"
                    + " order_date,"
                    + " account_id,"
                    + " car_id,"
                    + " get_service_location_id,"
                    + " get_date_time,"
                    + " put_service_location_id,"
                    + " put_date_time,"
                    + " up_key) "
                    + "VALUES (" + Integer.toString(orders.id) + ", "
                    + "'" + Integer.toString(orders.order_number) + "', "
                    + "'" + orders.order_date + "', "
                    + Integer.toString(orders.account_id) + ", "
                    + Integer.toString(orders.car_id) + ", "
                    + Integer.toString(orders.get_service_location_id) + ", "
                    + "'" + orders.get_date_time + "', "
                    + Integer.toString(orders.put_service_location_id) + ", "
                    + "'" + orders.put_date_time + "', "
                    + Integer.toString(up_key) + ");";

            statement.executeUpdate(insertSQL);
        } catch (SQLException ex) {
            log.info(ex.getMessage());
        } finally {
            MySQL.closeConnection(connection, statement);
        }
        return orders;
    }
}
