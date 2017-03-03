package com.excilys.mgajovski.computer_database.dao.columns;

/**
 * @author	Gajovski Maxime
 * @date	3 mars 2017
 */
public enum ComputerColumns {
  
  NAME("name"), 
  INTRODUCED_DATE("introduced"), 
  DISCONTINUED_DATE("discontinued"), 
  ID_COMPANY("company_id"), 
  NAME_COMPANY("company_name");

  private final String columnName;

  ComputerColumns(String columnName) {
    this.columnName = columnName;
  }

  public String getColumnName() {
    return columnName;
  }

//  public static ComputerColumns from(String column) {
//    if (column == null || column.isEmpty()) {
//      return null;
//    }
//    for (Order order : Order.values()) {
//      if (order.queryName.equals(column.toLowerCase())) {
//        return order;
//      }
//    }
//    return null;
//  }
}