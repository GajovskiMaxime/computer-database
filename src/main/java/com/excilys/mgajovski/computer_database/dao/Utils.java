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
    public static final String ENTITY_NOT_FOUND = "\nEntity not found on the database.\n";
    public static final String EMPTY_TABLE = "\nTable seems to be empty.\n";
    public static final String REACH_LAST_PAGE = "\nIt seems that you already reach the last page.\n";
    public static final String PREPARED_STATEMENT_ERR = "\nPrepared Statement err. Mistakes into queries ?!\n";
    public static final String CREATE_STATEMENT_ERR = "\nImpossible to create statement!\n";
    public static final String RESULT_SET_TO_LIST_ERR = "\nMapping : ResultSet to List err!\n";
    public static final String INIT_DAO = "\nDAO Initialization.\n";
    public static final String ENTITY_NULL = "\nentity must not be null\n";
    public static final String NEGATIVE_OR_NULL_ID = "\nWrong given id (negative or zero).\n";
    public static final String ENTITY_SUCCESS = "\nEntity created successfully.\n";
    public static final String ENTITY_NULL_OR_ALREADY_EXIST = "\nnull or already persisted object\n";
    public static final String BAD_QUERY = "\nBAD QUERY!\n";

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
            int columns = md.getColumnCount();
            while (rs.next()) {
                HashMap<String, Object> row = new HashMap<String, Object>(columns);
                for (int i = 1; i <= columns; ++i) {
                    row.put(md.getColumnLabel(i), rs.getObject(i));
                }
                list.add(row);
            }
        } catch (SQLException e) {
            LOGGER.warning(RESULT_SET_TO_LIST_ERR);
            throw e;
        }
        return list;
    }

    /**
     * 
     * @param rows
     * @return
     */
    public static List<String> getNamesFromResultSet(List<HashMap<String, Object>> rows) {

        List<String> list = new ArrayList<String>();

        for (int i = 0; i < rows.size(); i++) {
            HashMap<String, Object> row = rows.get(i);
            list.add((String) row.get("name"));
        }
        return list;
    }

}
