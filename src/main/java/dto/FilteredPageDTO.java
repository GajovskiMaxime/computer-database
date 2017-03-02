package dto;

/**
 * @author Gajovski Maxime
 * @date 2 mars 2017
 */
public class FilteredPageDTO<K> extends PageDTO<K> {

  private String filter;

  /**
   * Public constructor for FilteredPageDTO.
   * @see PageDTO.
   */
  public FilteredPageDTO() {
    super();
  }

  public String getFilter() {
    return filter;
  }

  public void setFilter(String filter) {
    this.filter = filter;
  }

}
