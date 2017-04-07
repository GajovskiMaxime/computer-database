package com.excilys.mgajovski.computer_database.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.blazebit.persistence.Criteria;
import com.blazebit.persistence.CriteriaBuilder;
import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.InsertCriteriaBuilder;
import com.blazebit.persistence.UpdateCriteriaBuilder;
import com.blazebit.persistence.spi.CriteriaBuilderConfiguration;
import com.excilys.mgajovski.computer_database.dao.ComputerDAOQueries;
import com.excilys.mgajovski.computer_database.dao.Utils;
import com.excilys.mgajovski.computer_database.dao.columns.ComputerColumn;
import com.excilys.mgajovski.computer_database.dao.interfaces.ComputerDAO;
import com.excilys.mgajovski.computer_database.dao.mappers.ComputerMapper;
import com.excilys.mgajovski.computer_database.entities.Company;
import com.excilys.mgajovski.computer_database.entities.Computer;
import com.excilys.mgajovski.computer_database.exceptions.DAOException;
import com.excilys.mgajovski.computer_database.exceptions.PageException;
import com.excilys.mgajovski.computer_database.exceptions.SQLMappingException;
import com.excilys.mgajovski.computer_database.pager.FilteredPage;
import com.excilys.mgajovski.computer_database.pager.Page;

/**
 * @author Gajovski Maxime
 * @date 20 févr. 2017
 */
@Repository
@Scope("singleton")
public class ComputerDAOImpl implements ComputerDAO {

  private static final Logger LOGGER = LoggerFactory.getLogger(ComputerDAOImpl.class);

  @Autowired
  EntityManager em;

  @Autowired
  CriteriaBuilderFactory cbf;

  /**
   * Private constructor for ComputerDAO singleton.
   */
  ComputerDAOImpl() {

  }

  @PostConstruct
  public void init() {
  }

  @Override
  @Transactional
  public Computer create(Connection connection, Computer computer) throws DAOException {

    if (computer == null || computer.getId() > 0) {
      throw new DAOException(DAOException.ENTITY_NULL_OR_ALREADY_EXIST);
    }

    LOGGER.error(computer.toString());
//    em.getTransaction().begin();

    em.persist(computer);
    //    em.getTransaction().commit();
//    cbf.insert(em, Computer.class).from(Computer.class, "computer")
//        .setParameter(ComputerColumn.NAME.toString(), computer.getName())
//        .setParameter(ComputerColumn.INTRODUCED_DATE.toString(), computer.getIntroduced())
//        .setParameter(ComputerColumn.DISCONTINUED_DATE.toString(), computer.getDiscontinued());
//        .setParameter(ComputerColumn.ID_COMPANY.toString());
    LOGGER.error(computer.toString());
    return computer;

    //
    // try (PreparedStatement create =
    // connection.prepareStatement(ComputerDAOQueries.CREATE_COMPUTER,
    // Statement.RETURN_GENERATED_KEYS);) {
    // if (ComputerMapper.insertComputerIntoDatabaseWithUpdate(create, computer, false)) {
    // try (ResultSet resultSet = create.getGeneratedKeys();) {
    // resultSet.next();
    // computer.setId(resultSet.getLong(1));
    // LOGGER.info(Utils.ENTITY_CREATED_SUCCESS);
    //
    // }
    // }
    // return computer;
    // } catch (SQLException | SQLMappingException e) {
    // throw new DAOException(e.getMessage(), e);
    // }

  }

  @Override
  public Computer find(Connection connection, long id) throws DAOException {

    if (id <= 0) {
      throw new DAOException(DAOException.NEGATIVE_OR_NULL_ID);
    }

    return cbf.create(em, Computer.class).from(Computer.class, "computer").where("computer.id")
        .eq(id).getSingleResult();
  }

  @Override
  public List<Computer> findAll(Connection connection) throws DAOException {
    return cbf.create(em, Computer.class).getResultList();

  }

  @Override
  public List<Computer> findByFilter(Connection connection, String filter) throws DAOException {
    try (PreparedStatement findByFilter = connection
        .prepareStatement(ComputerDAOQueries.SELECT_WHERE_NAME_CONTAINS_SEQUENCE);) {

      findByFilter.setString(1, '%' + filter + '%');
      try (ResultSet result = findByFilter.executeQuery()) {
        List<Computer> computers = ComputerMapper
            .getComputerListFromResultSet(Utils.convertResultSetToList(result));
        if (computers.isEmpty()) {
          throw new DAOException(DAOException.NO_MATCH_FOR_FILTER);
        }
        return computers;
      }
    } catch (SQLException e) {
      throw new DAOException(e.getMessage(), e);
    }
  }

  @Override
  public List<Computer> findByPage(Connection connection, FilteredPage<Computer> page)
      throws PageException, DAOException {

    if (page == null) {
      throw new PageException(PageException.PAGE_NULL);
    }
    if (page.getCurrentPage() < 0) {
      throw new PageException(PageException.NEGATIVE_CURRENT_PAGE + page.getCurrentPage());
    }
    if (page.getElementsByPage() < 0) {
      throw new PageException(
          PageException.NEGATIVE_NUMBERS_OF_ELEMENTS + page.getElementsByPage());
    }

    LOGGER.error(page.toString());
    List<Computer> computerByPage = cbf.create(em, Computer.class).from(Computer.class, "computer")
//         .where("computer.name").like()..value(page.getFilter()).noEscape()
        .whereExpression("computer.name LIKE '%" + page.getFilter() + "%'")
        .setFirstResult(page.getCurrentPage())
        .setMaxResults(page.getElementsByPage())
        .getResultList();
    LOGGER.error("Computer size : " + computerByPage.size());
    return computerByPage;
  }

  @Override
  public List<Computer> findByPage(Connection connection, Page<Computer> page)
      throws PageException, DAOException {

    if (page == null) {
      throw new PageException(PageException.PAGE_NULL);
    }
    if (page.getCurrentPage() < 0) {
      throw new PageException(PageException.NEGATIVE_CURRENT_PAGE + page.getCurrentPage());
    }
    if (page.getElementsByPage() < 0) {
      throw new PageException(
          PageException.NEGATIVE_NUMBERS_OF_ELEMENTS + page.getElementsByPage());
    }

    try (PreparedStatement findByPage = connection
        .prepareStatement(ComputerDAOQueries.SELECT_ALL_BY_PAGE);) {
      findByPage.setInt(1, page.getElementsByPage());
      findByPage.setInt(2, page.getElementsByPage() * page.getCurrentPage());
      try (ResultSet result = findByPage.executeQuery()) {
        List<Computer> computers = ComputerMapper
            .getComputerListFromResultSet(Utils.convertResultSetToList(result));
        if (computers.isEmpty()) {
          throw new PageException(PageException.EMPTY_SET);
        }
        return computers;
      }
    } catch (SQLException e) {
      throw new DAOException(e.getMessage(), e);
    }
  }

  @Override
  public void delete(Connection connection, long id) throws DAOException {
    if (id <= 0) {
      throw new DAOException(DAOException.NEGATIVE_OR_NULL_ID);
    }

    cbf.delete(em, Computer.class, "computer").where("computer.id").eq(id).executeUpdate();

    // String deleteComputerQuery = "delete from computer where id=" + id;
    // try (Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
    // ResultSet.CONCUR_UPDATABLE)) {
    // boolean isDeleted = statement.executeUpdate(deleteComputerQuery) == 1;
    // LOGGER.info(
    // "Computer with id :" + id + (isDeleted ? " deleted successfuly." : " not deleted."));
    // } catch (SQLException e) {
    // throw new DAOException(e.getMessage(), e);
    // }
  }

  @Override
  public void delete(Connection connection, Computer computer) throws DAOException {
    if (computer == null) {
      throw new DAOException(DAOException.ENTITY_NULL_OR_ALREADY_EXIST);
    }
    this.delete(connection, computer.getId());
  }

  @Override
  public Computer update(Connection connection, Computer computer) throws DAOException {
    if (computer == null || computer.getId() == 0) {
      throw new DAOException(DAOException.ENTITY_NULL_OR_NOT_IN_DB);
    }
    
//    em.find(arg0, arg1)
    em.getTransaction().begin();
    em.merge(computer);
    
    em.getTransaction().commit();
//    UpdateCriteriaBuilder<Computer> cb = cbf.update(em, Computer.class, "computer")
//        .set(ComputerColumn.NAME.toString(), computer.getName())
//        .set(ComputerColumn.INTRODUCED_DATE.toString(), computer.getIntroduced())
//        .set(ComputerColumn.DISCONTINUED_DATE.toString(), computer.getDiscontinued())
//        .where(ComputerColumn.ID.toString()).eq(computer.getId());
//    cb.executeUpdate();
    return computer;

    // from(Person.class, "p").where("p").eqExpression("cat.owner")
    // .select("CONCAT(p.name, '''s cat')").end().where("name").isNull();

    // try (PreparedStatement create = connection
    // .prepareStatement(ComputerDAOQueries.UPDATE_COMPUTER);) {
    //
    // boolean rowIsUpdated = ComputerMapper.insertComputerIntoDatabaseWithUpdate(create, computer,
    // true);
    // LOGGER.info("Row with " + computer.getId() + " updated"
    // + (rowIsUpdated ? " successfully" : " failed"));
    // return computer;
    //
    // } catch (SQLException | SQLMappingException e) {
    // LOGGER.error(e.getMessage());
    // throw new DAOException(e.getMessage(), e);
    // }
  }

  @Override
  public int sizeOfFilteredQuery(Connection connection, String filter) throws DAOException {
    try (PreparedStatement sizeOfFilteredQuery = connection
        .prepareStatement(ComputerDAOQueries.COUNT_FILTERED_ROWS);) {
      sizeOfFilteredQuery.setString(1, '%' + filter + '%');
      try (ResultSet rs = sizeOfFilteredQuery.executeQuery()) {

        if (!rs.isBeforeFirst()) {
          throw new DAOException();
        }
        rs.next();
        return rs.getInt(1);
      }
    } catch (SQLException e) {
      throw new DAOException(e.getMessage(), e);
    }
  }

  public void unreferenceOrRemoveCompanyForeignKey(Connection connection, long id, boolean remove)
      throws DAOException {

    if (id <= 0) {
      throw new DAOException(DAOException.NEGATIVE_OR_NULL_ID);
    }

    String computersToDeleteQuery = "select * from computer where company_id=" + id;

    try (ResultSet computersToDeleteRs = connection
        .createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)
        .executeQuery(computersToDeleteQuery);) {

      while (computersToDeleteRs.next()) {
        LOGGER.info("Computer with id :" + computersToDeleteRs.getLong(ComputerColumn.ID.toString())
            + " updated successfuly.");
        computersToDeleteRs.updateNull(ComputerColumn.ID_COMPANY.toString());
        computersToDeleteRs.updateRow();
        if (remove) {
          LOGGER
              .info("Computer with id :" + computersToDeleteRs.getLong(ComputerColumn.ID.toString())
                  + " deleted successfully");
          computersToDeleteRs.deleteRow();
        }
      }
    } catch (SQLException sqlException) {
      throw new DAOException(sqlException.getMessage(), sqlException);
    }
  }

  @Override
  public void unreferenceOrRemoveCompanyForeignKey(Connection connection, Company company,
      boolean remove) throws DAOException {
    if (company == null) {
      throw new DAOException(DAOException.ENTITY_NULL_OR_ALREADY_EXIST);
    }
    this.unreferenceOrRemoveCompanyForeignKey(connection, company.getId(), remove);
  }

}