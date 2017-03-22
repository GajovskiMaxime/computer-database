package com.excilys.mgajovski.computer_database.pager;


/**
 * @author Gajovski Maxime
 * @date 2 mars 2017
 * This class is a PageDTO child with filter attribute.
 */
public class FilteredPage<K> extends Page<K> {

  private static final String FILTER_UPDATED = "Filter set to : ";

  private String filter;
  
  /**
   * Public constructor for FilteredPageDTO.
   * @see PageDTO.
   */
  public FilteredPage() {
    super();
  }

  public String getFilter() {
    return filter;
  }

  /**
   * This method set the filter attribute.
   * @param filter : the filter to update.
   */
  public void setFilter(String filter) {
    LOGGER.info(FILTER_UPDATED + "'" + filter + "'");
    this.filter = filter;
  }

}
