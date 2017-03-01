package com.excilys.mgajovski.computer_database.managers;

import java.util.ArrayList;
import java.util.List;

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

    private IComputerDAO computerDAO;
    private int currentPage;
    private int numberOfRows;
    private String sequence;
    private int pageMax;



    /**
     * @return the pageMax
     */
    public int getPageMax() {
        return pageMax;
    }

    /**
     * @param pageMax the pageMax to set
     */
    public void setPageMax(int pageMax) {
        this.pageMax = pageMax;
    }

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
        currentPage = 0;
        numberOfRows = 10;
        sequence = "";
        pageMax = getNumberOfComputersFromRequest() / numberOfRows;

    }

    public int getComputerRows() {
        return computerDAO.size("");
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;

    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public void setNumberOfRows(int rows) {
        this.numberOfRows = rows;
        this.currentPage = 0;
        pageMax = getNumberOfComputersFromRequest() / numberOfRows;
    }

    public int getNumberOfComputersFromRequest() {
        return computerDAO.size(sequence);
    }

    public List<Computer> getDisplayedComputers() {
        if(computerDAO.findWhereNameContainsSequenceWithPagination(sequence, currentPage, numberOfRows).isPresent())
                return computerDAO.findWhereNameContainsSequenceWithPagination(sequence, currentPage, numberOfRows).get();
        return new ArrayList<>();
    }

    @Override
    public String toString() {
        /*
         * StringBuilder builder = new StringBuilder();
         * builder.append("ListManager [mPage="); builder.append(mPage);
         * builder.append(", mSize="); builder.append(mSize);
         * builder.append(", mSearch="); builder.append(mSearch);
         * builder.append(", mDisplayedComputers=");
         * builder.append(mDisplayedComputers);
         * builder.append(", mComputerDao="); builder.append(mComputerDao);
         * builder.append("]"); return builder.toString();
         */
        return null;
    }

}
