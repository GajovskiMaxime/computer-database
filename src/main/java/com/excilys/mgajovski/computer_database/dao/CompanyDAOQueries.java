package com.excilys.mgajovski.computer_database.dao;

/**
 * @author Gajovski Maxime
 * @date 20 févr. 2017
 */
public final class CompanyDAOQueries {

    public static final String COMPANY_TABLE = "company";

    public static final String CREATE_COMPANY = "INSERT INTO " + COMPANY_TABLE + "(name) VALUES(?)";

    public static final  String SELECT_ALL_COMPANIES = "SELECT * FROM " + COMPANY_TABLE;

    public static final String SELECT_ALL_COMPANIES_NAMES = "SELECT name FROM " + COMPANY_TABLE;

    public static final String SELECT_COMPANY_WITH_ID = "SELECT * FROM " + COMPANY_TABLE + " WHERE id = ?";

    public static final String DELETE_COMPANY_AND_COMPUTERS_WHERE_ID = "DELETE FROM computer WHERE company_id=?; DELETE FROM company WHERE id=? ;";

    public static final String SELECT_NAMES_BY_PAGE = "SELECT name FROM " + COMPANY_TABLE + " LIMIT ? OFFSET ?";

    public static final String SELECT_ALL_BY_PAGE = "SELECT * FROM " + COMPANY_TABLE + " LIMIT ? OFFSET ?";

    public static final String UPDATE_WITH_ID = "UPDATE " + COMPANY_TABLE + "SET name = ? WHERE id = ?";

}
