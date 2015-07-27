package com.restfully.webapp.DAO;

/**
 *
 * @author 2015 Dmitry Suvorov mailto: suvdima@gmail.com
 */
import com.restfully.webapp.model.Order_details;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.logging.Logger;

public class Order_detailsDAO {

    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    private static final Logger log = Logger.getLogger(Order_detailsDAO.class.getName());

    public List<Order_details> find(int id, int orders_id, int additional_service_id) throws SQLException {
        List<Order_details> order_detailsList = new ArrayList<Order_details>();
        String TextQuery;
        try {
            TextQuery = "";
            if (id != 0) {
                TextQuery = MySQL.addLine(TextQuery);
                TextQuery = TextQuery + "id = " + Integer.toString(id);
            }
            if (orders_id != 0) {
                TextQuery = MySQL.addLine(TextQuery);
                TextQuery = TextQuery + "orders_id = " + Integer.toString(orders_id);
            }
            if (additional_service_id != 0) {
                TextQuery = MySQL.addLine(TextQuery);
                TextQuery = TextQuery + "additional_service_id = " + Integer.toString(additional_service_id);
            }
            TextQuery = TextQuery + ";";
            String selectSQL = "SELECT id, orders_id, additional_service_id, number, price FROM order_details" + TextQuery;
            connection = MySQL.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(selectSQL);
            while (resultSet.next()) {
                int id_this = 0;
                int ordersid = 0;
                int additional_serviceid = 0;
                if (id == 0) {
                    id_this = resultSet.getInt("id");
                }
                if (orders_id == 0) {
                    ordersid = resultSet.getInt("orders_id");
                }
                if (additional_service_id == 0) {
                    additional_serviceid = resultSet.getInt("additional_service_id");
                }
                int number = resultSet.getInt("number");
                int price = resultSet.getInt("price");
                Order_details order_details = new Order_details(id_this, orders_id, additional_service_id, number, price);
                order_detailsList.add(order_details);
            }
            return order_detailsList;
        } catch (SQLException ex) {
            log.info(ex.getMessage());
        } finally {
            MySQL.closeConnection(connection, statement);
        }
        return null;
    }

    public Order_details create(Order_details order_details) throws SQLException {
        String table_name = "order_details";
        int up_key = 1;

        order_details.id = new MySQL().getNextId(table_name);

        try {
            connection = MySQL.getConnection();
            statement = connection.createStatement();
            String insertSQL = "INSERT INTO order_details (id,"
                    + " orders_id,"
                    + " additional_service_id,"
                    + " number,"
                    + " price,"
                    + " up_key) "
                    + "VALUES (" + Integer.toString(order_details.id) + ", "
                    + Integer.toString(order_details.orders_id) + ", "
                    + Integer.toString(order_details.additional_service_id) + ", "
                    + Integer.toString(order_details.number) + ", "
                    + Integer.toString(order_details.price) + ", "
                    + Integer.toString(up_key) + ");";

            statement.executeUpdate(insertSQL);
        } catch (SQLException ex) {
            log.info(ex.getMessage());
        } finally {
            MySQL.closeConnection(connection, statement);
        }
        return order_details;
    }

}
