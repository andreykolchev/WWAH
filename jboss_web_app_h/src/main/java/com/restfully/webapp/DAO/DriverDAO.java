package com.restfully.webapp.DAO;

/**
 *
 * @author 2015 Dmitry Suvorov mailto: suvdima@gmail.com
 */
import com.restfully.webapp.model.Driver;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.logging.Logger;

public class DriverDAO {

    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    private static final Logger log = Logger.getLogger(DriverDAO.class.getName());

    public List<Driver> find(int account_id, int id) throws SQLException {
        List<Driver> driverList = new ArrayList<Driver>();
        String TextQuery;
        try {
            TextQuery = "";
            if (account_id != 0) {
                TextQuery = MySQL.addLine(TextQuery);
                TextQuery = TextQuery + "account_id = " + Integer.toString(account_id);
            }
            if (id != 0) {
                TextQuery = MySQL.addLine(TextQuery);
                TextQuery = TextQuery + "id = " + Integer.toString(id);
            }
            TextQuery = TextQuery + ";";
            String selectSQL = "SELECT id, account_id, name, date_of_birth FROM driver" + TextQuery;
            connection = MySQL.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(selectSQL);
            while (resultSet.next()) {
                int id_this = 0;
                int accountid = 0;
                if (id == 0) {
                    id_this = resultSet.getInt("id");
                }
                if (account_id == 0) {
                    accountid = resultSet.getInt("account_id");
                }
                String name = resultSet.getString("name");
                String date_of_birth = resultSet.getString("date_of_birth");
                Driver driver = new Driver(id_this, accountid, name, date_of_birth);
                driverList.add(driver);
            }
            return driverList;
        } catch (SQLException ex) {
            log.info(ex.getMessage());
        } finally {
            MySQL.closeConnection(connection, statement);
        }
        return null;
    }

    public Driver create(Driver driver) throws SQLException {
        String table_name = "driver";
        int up_key = 1;

        driver.id = new MySQL().getNextId(table_name);

        try {
            connection = MySQL.getConnection();
            statement = connection.createStatement();
            String insertSQL = "INSERT INTO driver (id,"
                    + " name,"
                    + " date_of_birth,"
                    + " account_id,"
                    + " up_key) "
                    + "VALUES (" + Integer.toString(driver.id) + ", "
                    + "'" + driver.name + "', "
                    + "'" + driver.date_of_birth + "', "
                    + Integer.toString(driver.account_id) + ", "
                    + Integer.toString(up_key) + ");";

            statement.executeUpdate(insertSQL);
        } catch (SQLException ex) {
            log.info(ex.getMessage());
        } finally {
            MySQL.closeConnection(connection, statement);
        }
        return driver;
    }

}
