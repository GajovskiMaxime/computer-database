package com.excilys.mgajovski.computer_database.managers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;

import com.excilys.mgajovski.computer_database.dao.DAO;
import com.excilys.mgajovski.computer_database.dao.IComputerDAO;
import com.excilys.mgajovski.computer_database.dto.page.FilteredPageDTO;
import com.excilys.mgajovski.computer_database.entities.Computer;

/**
 * @author Gajovski Maxime
 * @date 28 févr. 2017
 */
@ManagedBean
public class ComputerListManager {

    private FilteredPageDTO<Computer> computerFilteredPage;
    private IComputerDAO computerDAO;
    private int numberOfElementsFromFilteredRequest;

    private static final String FILTER_INI = "";
    private static final int CURRENT_PAGE_INI = 0;
    private static final int ELEMENTS_BY_PAGE_INI = 10;

    public int getMaxPage() {
        return computerFilteredPage.getMaxPage();
    }

    /**
     * This method returns the index of the last page.
     * @param maxPage : the number of page.
     */
    public void setMaxPage(int maxPage) {
        computerFilteredPage.setMaxPage(maxPage);
    }

    public String getFilter() {
        return computerFilteredPage.getFilter();
    }

    /**
     * This method set the filter of the FilteredPageDTO.
     * @param filter : the filter to set.
     */
    public void setFilter(String filter) {
      computerFilteredPage.setFilter(filter);
      updateNumberOfElementsFromFilteredRequest();
      computerFilteredPage.refreshMaxPage(numberOfElementsFromFilteredRequest);
      updateElements();
    }

    /**
     * Public constructor for ComputerListManager.
     * This method call the initialize method who set the default fields :
     * currentPage, elementsByPage, filter to their default values.
     */
    public ComputerListManager() {
      initialize();
    }

    @PostConstruct
    public void initialize() {
        computerDAO = DAO.COMPUTER;
        computerFilteredPage = new FilteredPageDTO<>();
        computerFilteredPage.setCurrentPage(CURRENT_PAGE_INI);
        computerFilteredPage.setElementsByPage(ELEMENTS_BY_PAGE_INI);
        computerFilteredPage.setFilter(FILTER_INI);
        numberOfElementsFromFilteredRequest = computerDAO.size(computerFilteredPage.getFilter());
        computerFilteredPage.refreshMaxPage(numberOfElementsFromFilteredRequest);
        updateElements();
    }

    public int getComputerRows() {
        return computerDAO.size("");
    }

    public int getCurrentPage() {
        return computerFilteredPage.getCurrentPage();
    }

    /**
     * This method set the currentPage fields of the PageDTO.
     * @param currentPage : the currentPage
     */
    public void setCurrentPage(int currentPage) {
      computerFilteredPage.setCurrentPage(currentPage);
      updateElements();
    }

    public int getElementsByPage() {
        return computerFilteredPage.getElementsByPage();
    }

    /**
     * This method set the number of elements for each page.
     * Then, it will refresh the PageDTO maxPage field.
     * @param elementsByPage : the number of elements to display.
     */
    public void setElementsByPage(int elementsByPage) {
      computerFilteredPage.setElementsByPage(elementsByPage);
      computerFilteredPage.refreshMaxPage(numberOfElementsFromFilteredRequest);
      updateElements();
    }

    /**
     * This method update the number of elements from the filtered request.
     */
    public void updateNumberOfElementsFromFilteredRequest() {
        numberOfElementsFromFilteredRequest = computerDAO.size(computerFilteredPage.getFilter());
    }

    public int getNumberOfElementsFromFilteredRequest() {
      return numberOfElementsFromFilteredRequest;
    }

    /**
     * This method set the computers for the currentPage of the FilteredPageDTO.
     */
    private void updateElements() {
      Optional<List<Computer>> optComputers =
            computerDAO.findWhereNameContainsSequenceWithPagination(
                computerFilteredPage.getFilter(),
                computerFilteredPage.getCurrentPage(),
            computerFilteredPage.getElementsByPage());

        if (optComputers.isPresent()) {
          computerFilteredPage.setElements(optComputers);
        } else {
          computerFilteredPage.setElements(Optional.empty());
        }
    }


    public List<Computer> getElements() {
      Optional<List<Computer>> optComputers = computerFilteredPage.getElements();
      if (optComputers.isPresent()) {
        return computerFilteredPage.getElements().get();
      } else {
        return new ArrayList<>();
      }
    }
}
