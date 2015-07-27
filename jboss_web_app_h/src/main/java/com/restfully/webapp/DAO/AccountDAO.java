package com.restfully.webapp.DAO;

/**
 *
 * @author 2015 Dmitry Suvorov mailto: suvdima@gmail.com
 */
import com.restfully.webapp.model.Account;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.logging.Logger;

public class AccountDAO {

    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    private static final Logger log = Logger.getLogger(AccountDAO.class.getName());

    public List<Account> find(String user_name) throws SQLException {
        List<Account> accountList = new ArrayList<Account>();
        String TextQuery;
        try {
            TextQuery = "";
            if (!(user_name.equals(""))) {
                TextQuery = MySQL.addLine(TextQuery);
                TextQuery = TextQuery + "user_name = \"" + user_name + "\"";
            }
            TextQuery = TextQuery + ";";
            String selectSQL = "SELECT id, user_name, pass, e_mail, currency_id, date_of_birth FROM account" + TextQuery;
            connection = MySQL.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(selectSQL);
            while (resultSet.next()) {
                String username = "";
                int id = resultSet.getInt("id");
                if (!(user_name.equals(""))) {
                    username = resultSet.getString("user_name");
                }
                String pass = resultSet.getString("pass");
                String e_mail = resultSet.getString("e_mail");
                int currency_id = resultSet.getInt("currency_id");
                String date_of_birth = resultSet.getString("date_of_birth");
                Account account = new Account(id, username, pass, e_mail, currency_id, date_of_birth);
                accountList.add(account);
            }
            return accountList;
        } catch (SQLException ex) {
            log.info(ex.getMessage());
        } finally {
            MySQL.closeConnection(connection, statement);
        }
        return null;
    }

    public Account create(Account account) throws SQLException {
        String table_name = "account";
        int up_key = 1;

        account.id = new MySQL().getNextId(table_name);

        try {
            connection = MySQL.getConnection();
            statement = connection.createStatement();
            String insertSQL = "INSERT INTO account (id,"
                    + " user_name,"
                    + " pass,"
                    + " e_mail,"
                    + " currency_id,"
                    + " date_of_birth,"
                    + " up_key) "
                    + "VALUES (" + Integer.toString(account.id) + ", "
                    + "'" + account.user_name + "', "
                    + "'" + account.pass + "', "
                    + "'" + account.e_mail + "', "
                    + Integer.toString(account.currency_id) + ", "
                    + "'" + account.date_of_birth + "' "
                    + Integer.toString(up_key) + ");";

            statement.executeUpdate(insertSQL);
        } catch (SQLException ex) {
            log.info(ex.getMessage());
        } finally {
            MySQL.closeConnection(connection, statement);
        }
        return account;
    }

}
