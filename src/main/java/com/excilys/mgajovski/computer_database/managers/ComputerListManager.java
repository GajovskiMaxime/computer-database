package com.excilys.mgajovski.computer_database.managers;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.excilys.mgajovski.computer_database.entities.Computer;
import com.excilys.mgajovski.computer_database.exceptions.DAOException;
import com.excilys.mgajovski.computer_database.exceptions.PageException;
import com.excilys.mgajovski.computer_database.pager.ComputerPageService;
import com.excilys.mgajovski.computer_database.pager.FilteredPage;
import com.excilys.mgajovski.computer_database.services.Impl.ComputerServiceImpl;

/**
 * @author Gajovski Maxime
 * @date 28 févr. 2017
 */

@Component
@ManagedBean
public class ComputerListManager {

  @Autowired
  private ComputerPageService computerPageService;

  @Autowired
  private FilteredPage<Computer> computerFilteredPage;

  @Autowired
  private ComputerServiceImpl computerService;
  
  public int getMaxPage() {
    return computerFilteredPage.getMaxPage();
  }

  /**
   * This method returns the index of the last page.
   * 
   * @param maxPage
   *          : the number of page.
   */
  public void setMaxPage(int maxPage) {
    computerFilteredPage.setMaxPage(maxPage);
  }

  public String getFilter() {
    return computerFilteredPage.getFilter();
  }

  /**
   * This method set the filter of the FilteredPageDTO.
   * 
   * @param filter
   *          : the filter to set.
   */
  public void setFilter(String filter) {
    computerFilteredPage = computerPageService.setFilter(computerFilteredPage, filter); 
  }

  /**
   * Public constructor for ComputerListManager. This method call the initialize method who set the
   * default fields : currentPage, elementsByPage, filter to their default values.
   */
  public ComputerListManager() {
    initialize();
  }

  @PostConstruct
  public void initialize() {
    computerFilteredPage = computerPageService.createWithFilter();
  }

  public int getCurrentPage() {
    return computerFilteredPage.getCurrentPage();
  }

  public void nextPage() {
    computerFilteredPage = computerPageService.next(computerFilteredPage); 
  }

  public void previousPage() {
    computerFilteredPage = computerPageService.previous(computerFilteredPage); 
  }
  
  public int getElementsByPage() {
    return computerFilteredPage.getElementsByPage();
  }

  public void setElementsByPage(int elementsByPage) {
    computerFilteredPage = computerPageService.setElementsByPage(computerFilteredPage, elementsByPage);
  }


}
