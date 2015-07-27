package com.restfully.webapp.DAO;

import com.restfully.webapp.model.InterfaceItem;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author 2015 Andrey Kolchev mailto: andreykolchev@gmail.com
 */
public class InterfaceItemDAO {

    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    private static final Logger log = Logger.getLogger(CountryDAO.class.getName());

    public List<InterfaceItem> findByLanguageId(int language_id) throws SQLException {
        List<InterfaceItem> interfaceItemList = new ArrayList<InterfaceItem>();
        try {
            connection = MySQL.getConnection();
            statement = connection.createStatement();
            String selectTableSQL = "SELECT * FROM interface WHERE language_id = " + language_id + ";";
            resultSet = statement.executeQuery(selectTableSQL);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("interface_name");
                String value = resultSet.getString("name");
                InterfaceItem interfaceItem = new InterfaceItem(id, language_id, name, value);
                interfaceItemList.add(interfaceItem);
            }
            return interfaceItemList;
        } catch (SQLException ex) {
            log.info(ex.getMessage());
        } finally {
            MySQL.closeConnection(connection, statement);
        }
        return null;
    }
}
