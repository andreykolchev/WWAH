package com.restfully.webapp.DAO;

/**
 *
 * @author 2015 Dmitry Suvorov mailto: suvdima@gmail.com
 */
import com.restfully.webapp.model.Language;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.logging.Logger;

public class LanguageDAO {

    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    private static final Logger log = Logger.getLogger(LanguageDAO.class.getName());

    public List<Language> find(int id, String name) throws SQLException {
        List<Language> languageList = new ArrayList<Language>();
        String TextQuery;
        try {
            TextQuery = "";
            if (id != 0) {
                TextQuery = MySQL.addLine(TextQuery);
                TextQuery = TextQuery + "id = " + Integer.toString(id);
            }
            if (!(name.equals(""))) {
                TextQuery = MySQL.addLine(TextQuery);
                TextQuery = TextQuery + "name = \"" + name + "\"";
            }
            TextQuery = TextQuery + ";";
            String selectSQL = "SELECT id, name FROM language" + TextQuery;
            connection = MySQL.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(selectSQL);
            while (resultSet.next()) {
                int id_this = 0;
                String name_this = "";
                if (id == 0) {
                    id_this = resultSet.getInt("id");
                }
                if (!(name.equals(""))) {
                    name_this = resultSet.getString("name");
                }
                Language language = new Language(id_this, name_this);
                languageList.add(language);
            }
            if (languageList.isEmpty()) {
                int id_this = 1;
                String name_this = "EN";
                if (id == 0) {
                    id_this = resultSet.getInt("id");
                }
                if (!(name.equals(""))) {
                    name_this = resultSet.getString("name");
                }
                Language language = new Language(id_this, name_this);
                languageList.add(language);
            }
            return languageList;
        } catch (SQLException ex) {
            log.info(ex.getMessage());
        } finally {
            MySQL.closeConnection(connection, statement);
        }
        return null;
    }

}
