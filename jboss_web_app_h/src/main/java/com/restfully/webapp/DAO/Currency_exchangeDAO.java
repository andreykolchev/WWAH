package com.restfully.webapp.DAO;

/**
 *
 * @author 2015 Dmitry Suvorov mailto: suvdima@gmail.com
 */
import com.restfully.webapp.model.Currency_exchange;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.logging.Logger;

public class Currency_exchangeDAO {

    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    private static final Logger log = Logger.getLogger(Currency_exchangeDAO.class.getName());

    public List<Currency_exchange> find(int currency_id) throws SQLException {
        List<Currency_exchange> currency_exchangeList = new ArrayList<Currency_exchange>();
        String TextQuery;
        try {
            TextQuery = "";
            if (currency_id != 0) {
                TextQuery = MySQL.addLine(TextQuery);
                TextQuery = TextQuery + "currency_id = " + Integer.toString(currency_id);
            }
            TextQuery = TextQuery + ";";
            String selectSQL = "SELECT currency_id, course, multiplicity FROM currency_exchange" + TextQuery;
            connection = MySQL.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(selectSQL);
            while (resultSet.next()) {
                int currencyid = 0;
                if (currency_id == 0) {
                    currencyid = resultSet.getInt("currency_id");
                }
                int course = resultSet.getInt("course");
                int multiplicity = resultSet.getInt("multiplicity");
                Currency_exchange currency_exchange = new Currency_exchange(currencyid, course, multiplicity);
                currency_exchangeList.add(currency_exchange);
            }
            return currency_exchangeList;
        } catch (SQLException ex) {
            log.info(ex.getMessage());
        } finally {
            MySQL.closeConnection(connection, statement);
        }
        return null;
    }

}
