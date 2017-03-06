package com.excilys.mgajovski.computer_database.dao;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Gajovski Maxime
 * @date 22 févr. 2017
 */
public class Utils {

    public static final Logger LOGGER = Logger.getLogger(Utils.class.getName());
//    public static final String PREPARED_STATEMENT_ERR = "Prepared Statement err. Mistakes into queries ?!";
//    public static final String CREATE_STATEMENT_ERR = "Impossible to create statement!";
    public static final String RESULT_SET_TO_LIST_ERR = "Mapping : ResultSet to List err!";
//    public static final String INIT_DAO = "DAO Initialization.";
    public static final String ENTITY_CREATED_SUCCESS = "Entity created successfully.";
    public static final String ENTITY_UPDATED_SUCCESS = "Entity updated successfully.";
//    public static final String ENTITY_NULL_OR_ALREADY_EXIST = "Null or already persisted object";
//    public static final String BAD_QUERY = "BAD QUERY!";
//    public static final String CONNECTION_CLOSED = "Connection closed succesfully!";
//

    /**
     * This method convert a ResultSet into a List<HashMap<String, Object>>.
     * For each entity a HashMap<String, Object> is created.
     * @param rs : the ResultSet
     * @return a List<HashMap<String, Object>>
     * @throws SQLException if there's something wrong with the mapping.
     */
    public static List<HashMap<String, Object>> convertResultSetToList(ResultSet rs) throws SQLException {

        List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        try {
            ResultSetMetaData md = rs.getMetaData();
            System.out.println(md);
            int columns = md.getColumnCount();
            while (rs.next()) {
                HashMap<String, Object> row = new HashMap<String, Object>(columns);
                for (int i = 1; i <= columns; ++i) {
                    row.put(md.getColumnLabel(i), rs.getObject(i));
                    System.out.println(md.getColumnLabel(i)+ " " + rs.getObject(i));
                }
                list.add(row);
            }
        } catch (SQLException e) {
            LOGGER.warning(RESULT_SET_TO_LIST_ERR);
            throw e;
        }
        return list;
    }

    public static List<String> hashMapListToString(List<HashMap<String, Object>> rows) {

        List<String> list = new ArrayList<String>();

        for (int i = 0; i < rows.size(); i++) {
            HashMap<String, Object> row = rows.get(i);
            String string = "";
            for(String currentKey : row.keySet()){
                string += row.get(currentKey) + " - ";
            }
            list.add(string.substring(0, string.length() - 2));
        }
        return list;
    }

}
