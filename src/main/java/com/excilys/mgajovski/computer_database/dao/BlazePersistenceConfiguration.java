package com.excilys.mgajovski.computer_database.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.blazebit.persistence.Criteria;
import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.spi.CriteriaBuilderConfiguration;

/**
 * @author	Gajovski Maxime
 * @date	5 avr. 2017
 */
@Configuration
public class BlazePersistenceConfiguration {

    @PersistenceUnit(unitName="cdb")
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("cdb");

    @Bean    
    public CriteriaBuilderFactory createCriteriaBuilderFactory() {
        CriteriaBuilderConfiguration config = Criteria.getDefault();
        return config.createCriteriaBuilderFactory(entityManagerFactory);
    }

    @Bean
    public EntityManager getEntityManager(){
      return entityManagerFactory.createEntityManager();
    }
    
}