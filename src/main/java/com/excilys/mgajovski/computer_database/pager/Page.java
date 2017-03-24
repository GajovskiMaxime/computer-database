package com.excilys.mgajovski.computer_database.pager;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * @author Gajovski Maxime
 * @date 2 mars 2017
 */
@Component
public class Page<K> {

  private static final String CURRENT_PAGE_UPDATED = "Current page set to : ";
  private static final String ELEMENTS_BY_PAGE_UPDATED = "Number of elements by page set to : ";
  protected Logger LOGGER = LoggerFactory.getLogger(Page.class);
  
  protected int currentPage;
  protected int elementsByPage;
  protected int maxPage;
  private int resultFromQuery;

  protected List<K> elements;

  /**
   * Public constructor for PageDTO.
   */
  public Page() {

  }

  public List<K> getElements() {
    return elements;
  }


  public void setElements(List<K> elements) {
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
    LOGGER.info(ELEMENTS_BY_PAGE_UPDATED + this.elementsByPage);
    this.elementsByPage = elementsByPage;
  }

  public int getMaxPage() {
    return maxPage;
  }

  public void setMaxPage(int maxPage) {
    this.maxPage = maxPage;
  }
  

  public int getResultFromQuery() {
    return resultFromQuery;
  }

  public void setResultFromQuery(int resultFromQuery) {
    this.resultFromQuery = resultFromQuery;
  }
  
  @Override
  public String toString(){
    return  "class : " + getClass().getSimpleName() + " \n" + 
            "currentPage : " + currentPage + " \n" + 
            "elementsByPage : " + elementsByPage + " \n" + 
            "maxPage : " + maxPage + " \n";
    
  }
}
