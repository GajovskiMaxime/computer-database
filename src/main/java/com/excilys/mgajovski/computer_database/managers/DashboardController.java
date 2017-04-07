package com.excilys.mgajovski.computer_database.managers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.mgajovski.computer_database.dto.impl.CompanyDTO;
import com.excilys.mgajovski.computer_database.dto.impl.CompanyDTOImpl;
import com.excilys.mgajovski.computer_database.dto.impl.ComputerDTO;
import com.excilys.mgajovski.computer_database.dto.impl.ComputerDTOImpl;
import com.excilys.mgajovski.computer_database.dto.mappers.CompanyDTOMapperImpl;
import com.excilys.mgajovski.computer_database.dto.mappers.ComputerDTOMapperImpl;
import com.excilys.mgajovski.computer_database.entities.Company;
import com.excilys.mgajovski.computer_database.entities.Computer;
import com.excilys.mgajovski.computer_database.exceptions.ServiceException;
import com.excilys.mgajovski.computer_database.exceptions.mapping.DTOMapperException;
import com.excilys.mgajovski.computer_database.pager.ComputerPageService;
import com.excilys.mgajovski.computer_database.pager.FilteredPage;
import com.excilys.mgajovski.computer_database.services.CompanyService;
import com.excilys.mgajovski.computer_database.services.ComputerService;
import com.excilys.mgajovski.computer_database.validations.checkers.ComputerValidator;

/**
 * @author Gajovski Maxime
 * @date 23 mars 2017
 */
@Controller
@RequestMapping("/computers")
public class DashboardController {

  private static final Logger LOGGER = LoggerFactory.getLogger(DashboardController.class);
  
  @Autowired
  ComputerDTOImpl emptyComputer;
  
  @Autowired
  ComputerService computerService;

  @Autowired
  ComputerPageService computerPageService;


  @Autowired
  CompanyService companyService;
  
  @Autowired
  ComputerValidator computerValidation;
  
  @Autowired
  ComputerDTOMapperImpl computerMapper;
  
  @Autowired
  CompanyDTOMapperImpl companyMapper;
  
  FilteredPage<Computer> computerPage;

  @PostConstruct
  public void initialize() {
    computerPage = computerPageService.createWithFilter();
  }

  @RequestMapping(method = RequestMethod.GET)
  public String get(ModelMap model, @RequestParam MultiValueMap<String, String> parameters) {

    int currentPage = 0, elementsByPage;
    String filter;

    if (parameters.get("elementsByPage") == null) {
      elementsByPage = computerPage.getElementsByPage();
    } else {
      elementsByPage = Integer.parseUnsignedInt(parameters.get("elementsByPage").get(0));
    }

    try {
      if (parameters.get("currentPage") == null) {
        currentPage = 0;
      } else if (Integer.parseUnsignedInt(parameters.get("currentPage").get(0)) > computerPage
          .getMaxPage()) {
        model.put("errorMessage", "Too High");
      } else {
        currentPage = Integer.parseUnsignedInt(parameters.get("currentPage").get(0));
      }
    } catch (NumberFormatException numberFormatException) {
      currentPage = 0;
    }

    if (parameters.get("filter") == null) {
      filter = computerPage.getFilter();
    } else {
      filter = parameters.get("filter").get(0);
    }
    computerPage = computerPageService.getPageWithParams(computerPage, filter, currentPage,
        elementsByPage);

    this.setJspAttribute(model);
    return "dashboard";
  }

  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public String deleteComputer(ModelMap modelMap,
      @RequestParam MultiValueMap<String, String> parameters) {

    if (parameters.get("idsToDelete").get(0).isEmpty()) {
      return "redirect:/";
    }
    try {
      List<Long> idsToDelete = Arrays.asList(parameters.get("idsToDelete").get(0).split(","))
          .stream().map(Long::parseUnsignedLong).collect(Collectors.toList());

      computerService.delete(idsToDelete);
    } catch (ServiceException | NumberFormatException exception) {
      LOGGER.error(exception.getMessage(), exception);
    }
    return "redirect:/";
  }
  

  @RequestMapping(value="/add", method = RequestMethod.GET)
  public String getCompanies(ModelMap model) {
    loadCompanies(model);
    model.put("computerDTO", emptyComputer);
    return "addComputerView";
  }


  @RequestMapping(value="{id}/edit", method = RequestMethod.GET)
  public String getCompanies(ModelMap model, @PathVariable("id") String id) {
    
    long longId;
    Computer computer = null;
    try{
      longId = Long.parseUnsignedLong(id);
      computer = computerService.find(longId);
    } catch (ServiceException | NumberFormatException exception){
      LOGGER.error(exception.getMessage(), exception);
      return "redirect:/";
    }
    ComputerDTO computerDTO = computerMapper.transformToDTO(computer);
    
    loadCompanies(model);
    model.put("computer", computerDTO);
    return "addComputerView";
  }

  

  @RequestMapping(value="{id}/edit", method = RequestMethod.POST)
  public ModelAndView editComputer(
      @ModelAttribute("computer") @Validated ComputerDTOImpl computerDTO, 
      BindingResult result,
      ModelMap model) {
    
    loadCompanies(model);
    if (result.hasErrors()) {
      LOGGER.error(result.getAllErrors().toString());
      
      return new ModelAndView("addComputerView");
    }
    
    Company company = null;
    CompanyDTOImpl companyDTO = null;
    Computer computer = null;
    
    try {
      company = companyService.find(computerDTO.getCompany().getId());
      LOGGER.error("COMP");
    } catch (ServiceException serviceException) {
      LOGGER.warn(serviceException.getMessage(), serviceException);
    }

    if (company != null) {
      companyDTO = (CompanyDTOImpl) companyMapper.transformToDTO(company);
    }
    computerDTO.setCompany(companyDTO);
    LOGGER.error("COMP2");
    try {
      computer = computerMapper.transformFromDTO(computerDTO);
      computerService.update(computer);
    } catch (ServiceException serviceException) {
      LOGGER.error(serviceException.getMessage(), serviceException);
    }
    LOGGER.error("OKI");
    return new ModelAndView("redirect:/computers");
  }

  
  
  private void loadCompanies(ModelMap model) {
    List<Company> companies;
    try {
      companies = companyService.findAll();
      model.put("companies", companies);
    } catch (ServiceException e1) {
      LOGGER.error("Companies not found.");
    }
  }

  @InitBinder
  protected void initBinder(WebDataBinder binder) {
    binder.setValidator(computerValidation);
  }


  @RequestMapping(value="/add", method = RequestMethod.POST)
  public ModelAndView addComputer(
      @ModelAttribute("computer") @Validated ComputerDTOImpl computerDTO, BindingResult result,
      ModelMap model) {
    
    loadCompanies(model);
    if (result.hasErrors()) {
      return new ModelAndView("addComputerView");
    }
    
    Company company = null;
    CompanyDTOImpl companyDTO = null;
    Computer computer = null;

    try {
      company = companyService.find(computerDTO.getCompany().getId());
    } catch (ServiceException serviceException) {
      LOGGER.error(serviceException.getMessage(), serviceException);
    }

    if (company != null) {
      companyDTO = (CompanyDTOImpl) companyMapper.transformToDTO(company);
    }
    computerDTO.setCompany(companyDTO);

    try {
      computer = computerMapper.transformFromDTO(computerDTO);
      computerService.create(computer);
    } catch (ServiceException serviceException) {
      LOGGER.error(serviceException.getMessage(), serviceException);
    }

    return new ModelAndView("redirect:/computers");
  }
  
  private void setJspAttribute(ModelMap model) {

    model.put("requestSize", computerPage.getResultFromQuery());
    model.put("filter", computerPage.getFilter());
    model.put("computerList", computerPage.getElements());
    model.put("currentPage", computerPage.getCurrentPage());
    model.put("maxPage", computerPage.getMaxPage());
  }
}