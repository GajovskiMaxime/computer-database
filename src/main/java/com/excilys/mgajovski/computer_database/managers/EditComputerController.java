//package com.excilys.mgajovski.computer_database.managers;
//
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import com.excilys.mgajovski.computer_database.dto.impl.ComputerDTO;
//import com.excilys.mgajovski.computer_database.dto.impl.ComputerDTOImpl;
//import com.excilys.mgajovski.computer_database.dto.mappers.CompanyDTOMapperImpl;
//import com.excilys.mgajovski.computer_database.dto.mappers.ComputerDTOMapperImpl;
//import com.excilys.mgajovski.computer_database.entities.Company;
//import com.excilys.mgajovski.computer_database.entities.Computer;
//import com.excilys.mgajovski.computer_database.exceptions.ServiceException;
//import com.excilys.mgajovski.computer_database.services.CompanyService;
//import com.excilys.mgajovski.computer_database.services.ComputerService;
//import com.excilys.mgajovski.computer_database.validations.checkers.ComputerValidator;
//
///**
// * @author	Gajovski Maxime
// * @date	5 avr. 2017
// */
//@Controller
//@RequestMapping("/edit/computer/{id}")
//public class EditComputerController {
//
//  Logger LOGGER = LoggerFactory.getLogger(EditComputerController.class.getName());
//
//  @Autowired
//  CompanyService companyService;
//
//  @Autowired
//  ComputerService computerService;
//
//  @Autowired
//  ComputerValidator computerValidation;
//  
//  @Autowired
//  ComputerDTOMapperImpl computerMapper;
//  
//  @Autowired
//  CompanyDTOMapperImpl companyMapper;
//  
//  
//  private void loadCompanies(ModelMap model) {
//    List<Company> companies;
//    try {
//      companies = companyService.findAll();
//      model.put("companies", companies);
//    } catch (ServiceException serviceException) {
//      LOGGER.error(serviceException.getMessage(), serviceException);
//    }
//  }
//  
//  @RequestMapping(method = RequestMethod.GET)
//  public String getCompanies(ModelMap model, @PathVariable("id") long id) {
//    Computer computer = null;
//    try {
//      computer = computerService.find(id);
//    } catch (ServiceException serviceException) {
//     LOGGER.error(serviceException.getMessage(), serviceException);
//    }
//    
//    ComputerDTO computerDTO = computerMapper.transformToDTO(computer);
//    loadCompanies(model);
//    model.put("computer", computerDTO);
//    return "editComputerView";
//  }
//}