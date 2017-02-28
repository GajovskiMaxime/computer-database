package com.excilys.mgajovski.computer_database.managers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.mgajovski.computer_database.dao.IComputerDAO;
import com.excilys.mgajovski.computer_database.dao.impl.ComputerDAO;
import com.excilys.mgajovski.computer_database.entities.Computer;


/**
 * @author Gajovski Maxime
 * @date 28 févr. 2017
 */
@ManagedBean
public class ComputerManager {
  
  private Logger LOGGER = LoggerFactory.getLogger(ComputerManager.class);
  
  private List<Computer> displayedComputers;
  private IComputerDAO computerDAO;
  private int currentPage;
  private int numberOfRows;
  private String sequence;
  
  public String getSequence() {
    return sequence;
  }

  public void setSequence(String sequence) {
    this.sequence = sequence;
  }

  public ComputerManager() {
    init();
  }

  @PostConstruct
  public void init() {
    computerDAO = ComputerDAO.INSTANCE;
    currentPage = 1;
    numberOfRows = 10;
    sequence = "";
    
  }

  //===========================================================
  // Méthodes controleurs
  //===========================================================
  
  public int getComputerRows() {
    return computerDAO.size();
  }
  
  public int getCurrentPage() {
    return currentPage;
  }

  public void setCurrentPage(int currentPage) {
    LOGGER.info("page changed to " + currentPage);
    this.currentPage = currentPage;
//    displayedComputers = computerDAO.findByPage(currentPage, rows).get();
  }

  public int getNumberOfRows() {
    return numberOfRows;
  }
//
  public void setNumberOfRows(int rows) {
    LOGGER.info("size changed to " + rows);
    this.numberOfRows = rows;
//    displayedComputers = computerDAO.findByPage(currentPage, rows).get();
  }
  
//  public String getSearch() {
//    return mSearch;
//  }
//
//  public void setSearch(String pSearch) {
//    mSearch = pSearch;
//  }

//  public void update() {
//    if (LOGGER.isDebugEnabled()) {
//      LOGGER.debug("ListManager#update");
//      LOGGER.debug("");
//    }
//    
//    if (LOGGER.isDebugEnabled()) {
//      LOGGER.debug(this);
//    }
//    
//    if (mPage == 0) {
//      mPage = 1;
//    }
//    
//    if (mSearch != null && !mSearch.isEmpty()) {
//      QueryParameter parameter = QueryParameter.with(ComputerDao.Parameters.FILTER_NAME, mSearch);
//      parameter.and(ComputerDao.Parameters.SIZE, mSize)
//        .and(ComputerDao.Parameters.START, (mPage - 1)*mSize);
//      mDisplayedComputers = computerDAO.findWithNamedQuery(ComputerDao.NamedQueries.SEARCH, parameter.parameters());
//    } else {
//      mDisplayedComputers = computerDAO.findAll((mPage-1)*mSize, mSize);
//    }
//  }


  public int getNumberOfComputersFromRequest() {
    return computerDAO.size(sequence);
  }

  
  public List<Computer> getDisplayedComputers() {
    
    if(computerDAO.findWhereNameContainsSequenceWithPagination(sequence, currentPage, numberOfRows).isPresent())
      return computerDAO.findWhereNameContainsSequenceWithPagination(sequence, currentPage, numberOfRows).get();
    return new ArrayList<>();
   
  }
//  
//  public List<Computer> getDisplayedComputers() {
//    if(computerDAO.findByPage(currentPage, numberOfRows).isPresent())
//      return computerDAO.findByPage(currentPage, numberOfRows).get();
//    return new ArrayList<>();
//   
//  }

//  public boolean delete(String pIntegers) {
//    String[] indexes = pIntegers.split(",");
//
//    // TODO à optimiser
//    for (String index : indexes) {
//      int i = Integer.parseInt(index);
//      if (!computerDAO.delete(i)) {
//        return false;
//      }
//    }
//    return true;
//  }
  //===========================================================
  // Méthodes - Object
  //=========================================================== 
  
  @Override
  public String toString() {
    /*StringBuilder builder = new StringBuilder();
    builder.append("ListManager [mPage=");
    builder.append(mPage);
    builder.append(", mSize=");
    builder.append(mSize);
    builder.append(", mSearch=");
    builder.append(mSearch);
    builder.append(", mDisplayedComputers=");
    builder.append(mDisplayedComputers);
    builder.append(", mComputerDao=");
    builder.append(mComputerDao);
    builder.append("]");
    return builder.toString();*/
    return null;
  }



}
