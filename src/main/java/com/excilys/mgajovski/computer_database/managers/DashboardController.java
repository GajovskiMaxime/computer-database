package com.excilys.mgajovski.computer_database.managers;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.mgajovski.computer_database.entities.Computer;
import com.excilys.mgajovski.computer_database.pager.ComputerPageService;
import com.excilys.mgajovski.computer_database.pager.FilteredPage;
import com.excilys.mgajovski.computer_database.pager.Page;
import com.excilys.mgajovski.computer_database.services.ComputerService;

/**
 * @author	Gajovski Maxime
 * @date	23 mars 2017
 */
@Controller
@RequestMapping("/")
public class DashboardController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(DashboardController.class);
    
    @Autowired
    ComputerService computerService;
    
    @Autowired
    ComputerPageService computerPageService;
    
    FilteredPage<Computer> computerPage;
    
    @PostConstruct
    public void initialize(){
      computerPage = computerPageService.createWithFilter();
    }
    
    
    @RequestMapping(method = RequestMethod.GET)
    public String get(
            ModelMap model) {
        LOGGER.debug("Dashboard Get:");
        
//        PageListComputerDTO dtolistComputer = computerService.getComputerDTOList(search, pageSize, pageNumber, orderBy);
        this.setJspAttribute(model);
        
        return "dashboard";
    }
//    
//    @RequestMapping(value="/dashboard", method = RequestMethod.GET)
//    public ModelAndView getRedirect(ModelMap model) {
//        return new ModelAndView("redirect:/");
//    }
    

    @RequestMapping(value ="currentPage", method = RequestMethod.POST)
    public String post(ModelMap model) {
        LOGGER.debug("Dashboard delete selection:");
        
        return "dashboard";
    }
    
    
    private void setJspAttribute(ModelMap model) {
//      LOGGER.warn("wolowo" + computerPage.getElements().size());
        model.addAttribute("computerList", computerPage.getElements());
//        LOGGER.warn("" + computerPage.getElements().size());
        model.addAttribute("currentPage", computerPage.getCurrentPage());
        model.addAttribute("maxPage", computerPage.getMaxPage());
        //        model.addAttribute("pageNumber", computerPage);
//        model.addAttribute("pageNumber", computerPage);
        
//        LOGGER.error("max : " + computerPage.getMaxPage() + " - current : " +  computerPage.getCurrentPage());
        //        model.addAttribute("search", dtolistComputer.getFilter());
//        model.addAttribute("totalRowNumber", dtolistComputer.getTotalRow());
//        model.addAttribute("orderBy", dtolistComputer.getOrderBy());
    }
//    
//    private boolean deleteSelection(String selected) {
////        String[] idsStr = selected.split(",");
////        
////
////        boolean result = computerService.removeComputers(idsStr);
////
////        return result;
//    }
}