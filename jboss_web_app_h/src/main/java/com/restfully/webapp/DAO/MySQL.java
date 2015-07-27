package com.restfully.webapp.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;

/**
 *
 * @author 2015 Andrey Kolchev mailto: andreykolchev@gmail.com
 */
public class MySQL {

    public static Connection connection = null;
    public static Statement statement = null;
    public static ResultSet resultSet = null;
    public static final Logger log = Logger.getLogger(MySQL.class.getName());
    public static final String USERNAME = "root";
    public static final String USERPASS = "root";
    public static final String CONNSTRING = "jdbc:mysql://localhost:3306/rentalcars";

    public static String addLine(String Text) {
        String NewText;
        if (Text.equals("")) {
            NewText = " WHERE ";
        } else {
            NewText = Text + " and ";
        }
        return NewText;
    }

    public static String getFormatedDate(java.util.Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }

    public static int getNextId(String table_name) throws SQLException {
        try {
            connection = getConnection();
            statement = connection.createStatement();
            String selectSQL = "SELECT MAX(id) FROM " + table_name + ";";
            resultSet = statement.executeQuery(selectSQL);
            if (resultSet.next()) {
                int max_id = resultSet.getInt("MAX(id)");
                return max_id + 1;
            }
        } catch (SQLException ex) {
            log.info(ex.getMessage());
        } finally {
            closeConnection(connection, statement);
        }
        return 1;
    }

    public static int getNextNumber(String table_name) throws SQLException {
        try {
            connection = getConnection();
            statement = connection.createStatement();
            String selectSQL = "SELECT MAX(order_number) FROM " + table_name + ";";
            resultSet = statement.executeQuery(selectSQL);
            if (resultSet.next()) {
                int order_number = resultSet.getInt("MAX(order_number)");
                return order_number + 1;
            }
        } catch (SQLException ex) {
            log.info(ex.getMessage());
        } finally {
            closeConnection(connection, statement);
        }
        return 1;
    }

    public static int getLanguageId(int id) throws SQLException {
        try {
            connection = getConnection();
            statement = connection.createStatement();
            String selectSQL = "SELECT id FROM language WHERE id = " + Integer.toString(id) + ";";
            resultSet = statement.executeQuery(selectSQL);
            if (resultSet.next()) {
                int newid = resultSet.getInt("id");
                return newid;
            }
        } catch (SQLException ex) {
            log.info(ex.getMessage());
        } finally {
            closeConnection(connection, statement);
        }
        return 1;
    }

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.err.println(ex);
        }
        try {
            connection = (Connection) DriverManager.getConnection(CONNSTRING, USERNAME, USERPASS);
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return connection;
    }

    public static void closeConnection(Connection connection, Statement statement) throws SQLException {
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

}
