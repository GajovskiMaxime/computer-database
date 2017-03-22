package com.excilys.mgajovski.computer_database.pager;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.mgajovski.computer_database.exceptions.PageException;
import com.excilys.mgajovski.computer_database.exceptions.ServiceException;
import com.excilys.mgajovski.computer_database.services.Service;

/**
 * @author Gajovski Maxime
 * @date 22 mars 2017
 */

public abstract class PageServiceImpl<K> {

  public static final Logger LOGGER = LoggerFactory.getLogger(PageServiceImpl.class.getName());

  public static final int PAGE_INIT = 0;
  public static final int NB_OF_ELEMENTS_INIT = 10;
  public static final String FILTER_INIT = "";

  public Service<K> service;

  
  PageServiceImpl(Service<K> service) {
    this.service = service;
  }
  
  public FilteredPage<K> setElementsByPage(FilteredPage<K> filteredPage, int elementsByPage){
    try{
      filteredPage.setCurrentPage(PAGE_INIT);
      filteredPage.setElementsByPage(elementsByPage);
      filteredPage.setElements(service.findByPage(filteredPage));
      int sizeOfQuery = service.sizeOfFilteredQuery(filteredPage.getFilter());
      filteredPage.setResultFromQuery(sizeOfQuery);      
      filteredPage.setMaxPage(sizeOfQuery / elementsByPage);
    } catch (ServiceException serviceException) {
      LOGGER.error(serviceException.getMessage());
    }
    return filteredPage;
  }
  
  public FilteredPage<K> setFilter(FilteredPage<K> filteredPage, String filter){
    try{
      filteredPage.setCurrentPage(PAGE_INIT);
      filteredPage.setFilter(filter);
      filteredPage.setElements(service.findByPage(filteredPage));
      int sizeOfQuery = service.sizeOfFilteredQuery(filter);
      int elementsByPage = filteredPage.getElementsByPage();
      filteredPage.setResultFromQuery(sizeOfQuery);      
      filteredPage.setMaxPage(sizeOfQuery / elementsByPage);
    } catch (ServiceException serviceException) {
      LOGGER.error(serviceException.getMessage());
    }
    return filteredPage;
  }
  
  public Page<K> resetPage(Page<K> page) {
    try {
      page.setCurrentPage(PAGE_INIT);
      page.setElements(service.findByPage(page));
    } catch (ServiceException serviceException) {
      LOGGER.error(serviceException.getMessage(), serviceException);
    }
    return page;
  }

  public Page<K> create() {
    Page<K> page = new Page<>();
    try {
      page.setCurrentPage(PAGE_INIT);
      page.setElementsByPage(NB_OF_ELEMENTS_INIT);
      page.setElements(service.findByPage(page));
    } catch (ServiceException serviceException) {
      LOGGER.error(serviceException.getMessage(), serviceException);
    }
    return page;
  }


  public FilteredPage<K> createWithFilter() {
    FilteredPage<K> filteredPage = new FilteredPage<>();
    try {
      filteredPage.setFilter(FILTER_INIT);
      filteredPage.setCurrentPage(PAGE_INIT);
      filteredPage.setElementsByPage(NB_OF_ELEMENTS_INIT);
      filteredPage.setElements(service.findByPage(filteredPage));
      filteredPage.setResultFromQuery(service.sizeOfFilteredQuery(FILTER_INIT));
      filteredPage.setMaxPage(10);
    } catch (ServiceException serviceException) {
      LOGGER.error(serviceException.getMessage(), serviceException);
    }
    return filteredPage;
  }
  
  public Page<K> next(Page<K> page) {

    List<K> objects = page.getElements();

    try {
      page.setCurrentPage(page.getCurrentPage() + 1);
      page.setElements(service.findByPage(page));
    } catch (ServiceException serviceException) {
      LOGGER.warn(serviceException.getMessage());
      page.setCurrentPage(page.getCurrentPage() - 1);
      page.setElements(objects);
    }
    return page;
  }

  public FilteredPage<K> next(FilteredPage<K> page) {

    List<K> objects = page.getElements();

    try {
      page.setCurrentPage(page.getCurrentPage() + 1);
      page.setElements(service.findByPage(page));
    } catch (ServiceException serviceException) {
      LOGGER.warn(serviceException.getMessage());
      page.setCurrentPage(page.getCurrentPage() - 1);
      page.setElements(objects);
    }
    return page;
  }
 
  
  
  public Page<K> previous(Page<K> page) {
    if (page.getCurrentPage() - 1 < 0) {
      LOGGER.info(PageException.NEGATIVE_CURRENT_PAGE);
      return page;
    }
    
    try {
      page.setCurrentPage(page.getCurrentPage() - 1);
      page.setElements(service.findByPage(page));
    } catch (ServiceException serviceException) {
      LOGGER.warn(serviceException.getMessage());
    }
    return page;
  }
  

  
  public FilteredPage<K> previous(FilteredPage<K> page) {
    if (page.getCurrentPage() - 1 < 0) {
      LOGGER.info(PageException.NEGATIVE_CURRENT_PAGE);
      return page;
    }
    
    try {
      page.setCurrentPage(page.getCurrentPage() - 1);
      page.setElements(service.findByPage(page));
    } catch (ServiceException serviceException) {
      LOGGER.warn(serviceException.getMessage());
    }
    return page;
  }

}
