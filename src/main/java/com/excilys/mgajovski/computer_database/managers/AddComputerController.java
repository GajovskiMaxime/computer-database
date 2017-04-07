//package com.excilys.mgajovski.computer_database.managers;
//
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.WebDataBinder;
//import org.springframework.web.bind.annotation.InitBinder;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.excilys.mgajovski.computer_database.dto.impl.CompanyDTOImpl;
//import com.excilys.mgajovski.computer_database.dto.impl.ComputerDTOImpl;
//import com.excilys.mgajovski.computer_database.dto.mappers.CompanyMapper;
//import com.excilys.mgajovski.computer_database.dto.mappers.ComputerMapper;
//import com.excilys.mgajovski.computer_database.entities.Company;
//import com.excilys.mgajovski.computer_database.entities.Computer;
//import com.excilys.mgajovski.computer_database.exceptions.ServiceException;
//import com.excilys.mgajovski.computer_database.exceptions.mapping.DTOMapperException;
//import com.excilys.mgajovski.computer_database.services.CompanyService;
//import com.excilys.mgajovski.computer_database.services.ComputerService;
//import com.excilys.mgajovski.computer_database.validations.checkers.ComputerValidator;
//
///**
// * @author Gajovski Maxime
// * @date 3 avr. 2017
// */
//@Controller
//@RequestMapping("/add/computer")
//public class AddComputerController {
//
//  Logger LOGGER = LoggerFactory.getLogger(AddComputerController.class.getName());
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
//  @RequestMapping(method = RequestMethod.GET)
//  public String getCompanies(ModelMap model) {
//    loadCompanies(model);
//    model.put("computer", new ComputerDTOImpl());
//    return "addComputerView";
//  }
//
//  private void loadCompanies(ModelMap model) {
//    List<Company> companies;
//    try {
//      companies = companyService.findAll();
//      model.put("companies", companies);
//    } catch (ServiceException e1) {
//      LOGGER.error("Companies not found.");
//    }
//  }
//
//  @InitBinder
//  protected void initBinder(WebDataBinder binder) {
//    binder.setValidator(computerValidation);
//  }
//
//  @RequestMapping(method = RequestMethod.POST)
//  public ModelAndView addComputer(
//      @ModelAttribute("computer") @Validated ComputerDTOImpl computerDTO, BindingResult result,
//      ModelMap model) {
//    
//    loadCompanies(model);
//    if (result.hasErrors()) {
//      model.put("error", true);
//
//      return new ModelAndView("addComputerView");
//    }
//
//    Company company = null;
//    CompanyDTOImpl companyDTO = null;
//    Computer computer = null;
//
//    try {
//      company = companyService.find(computerDTO.getCompany().getId());
//    } catch (ServiceException serviceException) {
//      LOGGER.error(serviceException.getMessage(), serviceException);
//    }
//
//    if (company != null) {
//      companyDTO = CompanyMapper.transformToDTO(company);
//    }
//    computerDTO.setCompany(companyDTO);
//
//    try {
//      computer = ComputerMapper.transformFromDTOWithIdInit(computerDTO, false);
//      computerService.create(computer);
//    } catch (DTOMapperException dtoMapperException) {
//      LOGGER.error(dtoMapperException.getMessage(), dtoMapperException);
//    } catch (ServiceException serviceException) {
//      LOGGER.error(serviceException.getMessage(), serviceException);
//    }
//
//    return new ModelAndView("redirect:/computers");
//  }
//
//}