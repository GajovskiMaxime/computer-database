package com.excilys.mgajovski.computer_database.dao.columns;

/**
 * @author	Gajovski Maxime
 * @date	20 mars 2017
 */
public enum CompanyColumn {

    ID("id"),
    NAME("name");

    private final String columnName;

    /**
     * Constructor for CompanyColumn with column name param.
     * @param columnName : the table column name.
     */
    CompanyColumn(String columnName) {
        this.columnName = columnName;
    }

    public String toString() {
        return columnName;
    }

    /**
     * This method can be used in order to ask for columns queries.
     * @param companyColumns : the Company columns.
     * @return the concatenation of columns names with ',' as separator.
     */
    public static String arrayToString(CompanyColumn... companyColumns) {
        String string = "";
        for (CompanyColumn companyColumn : companyColumns) {
            string += companyColumn.toString() + ", ";
        }
        return string.substring(0, string.length() - 2);
    }

    /**
     * This method transform a string to a CompanyColumn instance.
     * @param column : the column name.
     * @return the corresponding CompanyColumn instance from string.
     */
    public static CompanyColumn from(String column) {
        if (column == null || column.isEmpty()) {
            return null;
        }
        for (CompanyColumn order : CompanyColumn.values()) {
            if (order.toString().equals(column.toLowerCase())) {
                return order;
            }
        }
        return null;
    }
}
