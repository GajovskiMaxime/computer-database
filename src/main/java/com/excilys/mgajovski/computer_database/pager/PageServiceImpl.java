package com.excilys.mgajovski.computer_database.pager;

import java.util.List;
import java.util.Map;

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
  
  public static final String PAGE_CREATED_SUCCESSFULLY = "Page created successfully.\n"; 
  public static final String PAGE_CREATED_ERR = "Page wasn't created.\n"; 
  
  
  public static final int PAGE_INIT = 0;
  public static final int NB_OF_ELEMENTS_INIT = 10;
  public static final String FILTER_INIT = "";

  public Service<K> service;

  
  PageServiceImpl(Service<K> service) {
    this.service = service;
  }
  
  public FilteredPage<K> setElementsByPage(FilteredPage<K> filteredPage, int elementsByPage){
    
    try{
      Map<String, Object> map = service.findByPage(filteredPage);
      
      filteredPage.setCurrentPage(PAGE_INIT);
      filteredPage.setElementsByPage(elementsByPage);
      filteredPage.setElements((List<K>) map.get("list"));
      filteredPage.setResultFromQuery((Integer)map.get("size"));      
      filteredPage.setMaxPage((Integer)map.get("size") / elementsByPage);
    } catch (ServiceException serviceException) {
      LOGGER.error(serviceException.getMessage());
    }
    return filteredPage;
  }
  
  public FilteredPage<K> setFilter(FilteredPage<K> filteredPage, String filter){
    
    try{
      Map<String, Object> map = service.findByPage(filteredPage);

      filteredPage.setCurrentPage(PAGE_INIT);
      filteredPage.setFilter(filter);
      filteredPage.setElements((List<K>) map.get("list"));
      int elementsByPage = filteredPage.getElementsByPage();
      filteredPage.setResultFromQuery((Integer) map.get("size"));      
      filteredPage.setMaxPage((Integer) map.get("size")/ elementsByPage);
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
    LOGGER.info(page.toString());
    return page;
  }


  public FilteredPage<K> createWithFilter() {
    FilteredPage<K> filteredPage = new FilteredPage<>();
    Map<String, Object> map ;
    try {
      filteredPage.setFilter(FILTER_INIT);
      filteredPage.setCurrentPage(PAGE_INIT);
      filteredPage.setElementsByPage(NB_OF_ELEMENTS_INIT);
      map = service.findByPage(filteredPage);
      filteredPage.setElements((List<K>)map.get("list"));
      filteredPage.setResultFromQuery((Integer)map.get("size"));
      filteredPage.setMaxPage(filteredPage.getResultFromQuery() / NB_OF_ELEMENTS_INIT);
    } catch (ServiceException serviceException) {
      LOGGER.error(serviceException.getMessage(), serviceException);
    }
    LOGGER.info(filteredPage.toString());
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
      page.setElements((List<K>)service.findByPage(page).get("list"));
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
      page.setElements((List<K>)service.findByPage(page).get("list"));
    } catch (ServiceException serviceException) {
      LOGGER.warn(serviceException.getMessage());
    }
    return page;
  }

}
