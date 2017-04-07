package com.excilys.mgajovski.computer_database.dao;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author	Gajovski Maxime
 * @date	6 avr. 2017
 */
//@Converter(autoApply = true)
//public class LocalDateConverter implements AttributeConverter<LocalDate, TimeStamp> {
//  
//    @Override
//    public TimeStamp convertToDatabaseColumn(LocalDate locDate) {
//      return (locDate == null ? null : Timestamp.valueOf(locDate);
//    }
//
//    @Override
//    public LocalDate convertToEntityAttribute(Date sqlDate) {
//      return (sqlDate == null ? null : sqlDate.toLocalDate());
//    }
//}