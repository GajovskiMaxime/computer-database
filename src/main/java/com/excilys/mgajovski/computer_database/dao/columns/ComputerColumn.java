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

    ComputerColumn(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }

    public static String arrayToString(ComputerColumn... comupterColumns) {
        String string = "";
        for (ComputerColumn computerColumn : comupterColumns) {
            string += computerColumn.getColumnName() + ", ";
        }
        /*string = string.substring(0, string.length() - 2);*/
        return string.substring(0, string.length() - 2);
    }

    public static ComputerColumn from(String column) {
        if (column == null || column.isEmpty()) {
            return null;
        }
        for (ComputerColumn  order : ComputerColumn.values()) {
            if (order.getColumnName().equals(column.toLowerCase())) {
                return order;
            }
        }
        return null;
    }
}