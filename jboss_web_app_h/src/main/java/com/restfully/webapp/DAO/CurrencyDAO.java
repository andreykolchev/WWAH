package com.restfully.webapp.DAO;

/**
 *
 * @author 2015 Dmitry Suvorov mailto: suvdima@gmail.com
 */
import com.restfully.webapp.model.Currency;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.logging.Logger;

public class CurrencyDAO {

    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    private static final Logger log = Logger.getLogger(CurrencyDAO.class.getName());

    public List<Currency> find(int currency_id, int language_id) throws SQLException {
        List<Currency> currencyList = new ArrayList<Currency>();
        String TextQuery;
        try {
            TextQuery = "";
            if (currency_id != 0) {
                TextQuery = MySQL.addLine(TextQuery);
                TextQuery = TextQuery + "currency_id = " + Integer.toString(currency_id);
            }
            if (language_id != 0) {
                TextQuery = MySQL.addLine(TextQuery);
                TextQuery = TextQuery + "language_id = " + Integer.toString(language_id);
            }
            TextQuery = TextQuery + ";";
            String selectSQL = "SELECT currency_id, language_id, name FROM currency" + TextQuery;
            connection = MySQL.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(selectSQL);
            while (resultSet.next()) {
                int currencyid = 0;
                int languageid = 0;
                if (currency_id == 0) {
                    currencyid = resultSet.getInt("currency_id");
                }
                if (language_id == 0) {
                    languageid = resultSet.getInt("language_id");
                }
                String name = resultSet.getString("name");
                Currency currency = new Currency(currencyid, languageid, name);
                currencyList.add(currency);
            }
            return currencyList;
        } catch (SQLException ex) {
            log.info(ex.getMessage());
        } finally {
            MySQL.closeConnection(connection, statement);
        }
        return null;
    }

}
