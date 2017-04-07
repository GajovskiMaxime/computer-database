package com.excilys.mgajovski.computer_database.managers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Gajovski Maxime
 * @date 3 avr. 2017
 */
@Controller
@RequestMapping("/")
public class DefaultController {
  
  @RequestMapping(method = RequestMethod.GET)
  public ModelAndView goToDashboard() {
    return new ModelAndView("redirect:computers");
  }
}