package com.restfully.webapp.DAO;

/**
 *
 * @author 2015 Dmitry Suvorov mailto: suvdima@gmail.com
 */
import com.restfully.webapp.model.Payment_cards;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.logging.Logger;

public class Payment_cardsDAO {

    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    private static final Logger log = Logger.getLogger(Payment_cardsDAO.class.getName());

    public List<Payment_cards> find(int id, int account_id, String holder_name, int number, int end_month, int end_year) throws SQLException {
        List<Payment_cards> payment_cardsList = new ArrayList<Payment_cards>();
        String TextQuery;
        try {
            TextQuery = "";
            if (id != 0) {
                TextQuery = MySQL.addLine(TextQuery);
                TextQuery = TextQuery + "id = " + Integer.toString(id);
            }
            if (account_id != 0) {
                TextQuery = MySQL.addLine(TextQuery);
                TextQuery = TextQuery + "account_id = " + Integer.toString(account_id);
            }
            if (holder_name != null) {
                TextQuery = MySQL.addLine(TextQuery);
                TextQuery = TextQuery + "holder_name = \"" + holder_name + "\"";
            }
            if (number != 0) {
                TextQuery = MySQL.addLine(TextQuery);
                TextQuery = TextQuery + "number = " + Integer.toString(number);
            }
            if (end_month != 0) {
                TextQuery = MySQL.addLine(TextQuery);
                TextQuery = TextQuery + "end_month = " + Integer.toString(end_month);
            }
            if (end_year != 0) {
                TextQuery = MySQL.addLine(TextQuery);
                TextQuery = TextQuery + "end_year = " + Integer.toString(end_year);
            }
            TextQuery = TextQuery + ";";
            String selectSQL = "SELECT id, account_id, holder_name, number, end_month, end_year FROM payment_cards" + TextQuery;
            connection = MySQL.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(selectSQL);
            while (resultSet.next()) {
                int id_this = 0;
                int accountid = 0;
                String holdername = "";
                int number_this = 0;
                int endmonth = 0;
                int endyear = 0;
                if (id == 0) {
                    id_this = resultSet.getInt("id");
                }
                if (account_id == 0) {
                    accountid = resultSet.getInt("account_id");
                }
                if (!(holder_name.equals(""))) {
                    holdername = resultSet.getString("holder_name");
                }
                if (number == 0) {
                    number_this = resultSet.getInt("number");
                }
                if (end_month == 0) {
                    endmonth = resultSet.getInt("end_month");
                }
                if (end_year == 0) {
                    endyear = resultSet.getInt("end_year");
                }
                Payment_cards payment_cards = new Payment_cards(id_this, accountid, holdername, number_this, endmonth, endyear);
                payment_cardsList.add(payment_cards);
            }
            return payment_cardsList;
        } catch (SQLException ex) {
            log.info(ex.getMessage());
        } finally {
            MySQL.closeConnection(connection, statement);
        }
        return null;
    }

    public Payment_cards create(Payment_cards payment_cards) throws SQLException {
        String table_name = "payment_cards";
        int up_key = 1;

        payment_cards.id = new MySQL().getNextId(table_name);

        try {
            connection = MySQL.getConnection();
            statement = connection.createStatement();
            String insertSQL = "INSERT INTO payment_cards (id,"
                    + " account_id,"
                    + " holder_name,"
                    + " number,"
                    + " end_month,"
                    + " end_year,"
                    + " up_key) "
                    + "VALUES (" + Integer.toString(payment_cards.id) + ", "
                    + Integer.toString(payment_cards.account_id) + ", "
                    + "'" + payment_cards.holder_name + "', "
                    + Integer.toString(payment_cards.number) + ", "
                    + Integer.toString(payment_cards.end_month) + ", "
                    + Integer.toString(payment_cards.end_year) + ", "
                    + Integer.toString(up_key) + ");";

            statement.executeUpdate(insertSQL);
        } catch (SQLException ex) {
            log.info(ex.getMessage());
        } finally {
            MySQL.closeConnection(connection, statement);
        }
        return payment_cards;
    }

}
