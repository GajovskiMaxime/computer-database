package com.excilys.mgajovski.computer_database.dao;

/**
 * @author Gajovski Maxime
 * @date 20 févr. 2017
 */
public class ComputerDAOQueries {

    public static final String COMPUTER_TABLE = "computer";

    public static final String CREATE_COMPUTER = "INSERT INTO " + COMPUTER_TABLE
            + "(name, introduced, discontinued, company_id) VALUES(?, ?, ?, ?)";

    public static final String SELECT_ALL_COMPUTERS = "SELECT * FROM " + COMPUTER_TABLE;

    public static final String SELECT_ALL_COMPUTERS_NAMES = "SELECT name FROM " + COMPUTER_TABLE;
    
    public static final String SELECT_ALL_COMPUTERS_BY_COLUMN = "SELECT ? FROM computer";

    public static final String SELECT_COMPUTER_WITH_ID = "SELECT * FROM " + COMPUTER_TABLE + " WHERE id = ?";

    public static final String DELETE_COMPUTER_WITH_ID = "DELETE FROM " + COMPUTER_TABLE + " WHERE id=?";

    public static final String SELECT_NAMES_BY_PAGE = "SELECT name FROM " + COMPUTER_TABLE + " LIMIT ? OFFSET ?";

    public static final String SELECT_WHERE_NAME_CONTAINS_SEQUENCE = "SELECT * FROM " + COMPUTER_TABLE + " c where c.name like ?";
    
    public static final String SELECT_WHERE_NAME_CONTAINS_SEQUENCE_BY_PAGE = "SELECT c.id, c.name, c.introduced, c.discontinued, c.company_id,  comp.name as company_name  FROM computer c "
        + "LEFT JOIN company comp ON c.company_id = comp.id where c.name like ? limit ? offset ?";
    
    public static final String COUNT_FILTERED_ROWS = "SELECT COUNT(*) FROM computer c "
        + "LEFT JOIN company comp ON c.company_id = comp.id where c.name like ?";
    
   //public static final String SELECT_ALL_BY_PAGE = "SELECT * FROM " + COMPUTER_TABLE + " LIMIT ? OFFSET ?";

    public static final String UPDATE_COMPUTER = "UPDATE " + COMPUTER_TABLE + " SET "
            + "name = ?, introduced = ?, discontinued = ?, company_id = ? " + " WHERE id = ?";

    public static final String LAST_ROW_INDEX = "SELECT id FROM " + COMPUTER_TABLE + " ORDER BY id DESC LIMIT 1";

    public static final String RIGHT_JOIN = "SELECT c.id, c.name, c.introduced, c.discontinued, c.company_id,  comp.name as company_name  FROM computer c "
            + "LEFT JOIN company comp ON c.company_id = comp.id ";

    public static final String SELECT_ALL_BY_PAGE = "SELECT c.id, c.name, c.introduced, c.discontinued, c.company_id,  comp.name as company_name  FROM computer c "
            + "LEFT JOIN company comp ON c.company_id = comp.id limit ? offset ?";


    public static final String RIGHT_JOIN_WITH_ID = "SELECT c.id, c.name, c.introduced, c.discontinued, c.company_id,  comp.name as company_name  FROM computer c "
            + "LEFT JOIN company comp  ON c.company_id = comp.id WHERE c.id = ?";

}
