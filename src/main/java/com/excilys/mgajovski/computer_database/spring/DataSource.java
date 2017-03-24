package com.excilys.mgajovski.computer_database.spring;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import com.excilys.mgajovski.computer_database.dao.DatabaseProperties;
import com.excilys.mgajovski.computer_database.dao.DatabaseTags;


/**
 * @author	Gajovski Maxime
 * @date	23 mars 2017
 */
@Repository
@Scope("singleton")
public class DataSource extends DriverManagerDataSource {
    
    public static final Logger LOGGER = LoggerFactory.getLogger(DataSource.class.getName());
        
    /**
     * Configures data source.
     */
    public DataSource() {
        DatabaseProperties databaseProperties = new DatabaseProperties(DataSource.class);
        this.setDriverClassName(databaseProperties.getDriverClassName());
        this.setUrl(databaseProperties.getUrl());
        this.setUsername(databaseProperties.getUserName());
        this.setPassword(databaseProperties.getPassword());        
    }
}