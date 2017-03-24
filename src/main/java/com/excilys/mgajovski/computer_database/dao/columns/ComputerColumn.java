package com.excilys.mgajovski.computer_database.dao.columns;

/**
 * @author Gajovski Maxime
 * @date 3 mars 2017
 */
public enum ComputerColumn {

    ID("id"),
    NAME("name"),
    INTRODUCED_DATE("introduced"),
    DISCONTINUED_DATE("discontinued"),
    ID_COMPANY("company_id"),
    NAME_COMPANY("company_name");

    private final String columnName;

    /**
     * Constructor for ComputerColumn with column name param.
     * @param columnName : the table column name.
     */
    ComputerColumn(String columnName) {
        this.columnName = columnName;
    }

    public String toString() {
        return columnName;
    }

    /**
     * This method can be used in order to ask for columns queries.
     * @param computerColumns : the Computer columns.
     * @return the concatenation of columns names with ',' as separator.
     */
    public static String arrayToString(ComputerColumn... computerColumns) {
        String string = "";
        for (ComputerColumn computerColumn : computerColumns) {
            string += computerColumn.toString() + ", ";
        }
        return string.substring(0, string.length() - 2);
    }

    /**
     * This method transform a string to a ComputerColumn instance.
     * @param column : the column name.
     * @return the corresponding ComputerColumn instance from string.
     */
    public static ComputerColumn from(String column) {
        if (column == null || column.isEmpty()) {
            return null;
        }
        for (ComputerColumn order : ComputerColumn.values()) {
            if (order.toString().equals(column.toLowerCase())) {
                return order;
            }
        }
        return null;
    }
}