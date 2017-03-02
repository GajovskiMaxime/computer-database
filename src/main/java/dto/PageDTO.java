package dto;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author Gajovski Maxime
 * @date 2 mars 2017
 */
public class PageDTO<K> {

  private static final String CURRENT_PAGE_UPDATED = "Current page set to : ";
  private static final String ELEMENTS_BY_PAGE_UPDATED = "Number of elements by page set to : ";

  private Logger LOGGER = LoggerFactory.getLogger(PageDTO.class);
  protected int currentPage;
  protected int elementsByPage;
  protected int maxPage;
  protected Optional<List<K>> elements;

  /**
   * Public constructor for PageDTO.
   */
  public PageDTO() {

  }

  public Optional<List<K>> getElements() {
    return elements;
  }


  public void setElements(Optional<List<K>> elements) {
    this.elements = elements;
  }

  public int getCurrentPage() {
    return currentPage;
  }

  /**
   * This method updates the currentPage.
   * The PageDTO logger will inform the update.
   * @param currentPage : the new currentPage.
   */
  public void setCurrentPage(int currentPage) {
    LOGGER .info(CURRENT_PAGE_UPDATED + currentPage);
    this.currentPage = currentPage;
  }

  public int getElementsByPage() {
    return elementsByPage;
  }

  /**
   * This method updates the number of elements by page.
   * The PageDTO logger will inform the update.
   * @param elementsByPage : the new elementsByPage value.
   */

  public void setElementsByPage(int elementsByPage) {
    LOGGER .info(ELEMENTS_BY_PAGE_UPDATED + currentPage);
    this.elementsByPage = elementsByPage;
  }

  public int getMaxPage() {
    return maxPage;
  }

  public void setMaxPage(int maxPage) {
    this.maxPage = maxPage;
  }

  public void refreshMaxPage(int elementsToDisplay) {
    maxPage = elementsToDisplay / elementsByPage;
    currentPage = 0;
  }

}
