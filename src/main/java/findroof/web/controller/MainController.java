package findroof.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import findroof.controllers.FindRoofApiController;
import findroof.model.Contract;
import findroof.model.Person;
import findroof.model.User;
import findroof.service.UserService;

@Controller
@RequestMapping(value = "/view")
public class MainController {
	
	@Autowired
	private FindRoofApiController findRoofApiController; 
    
	@Autowired
	private UserService userService; 
	
	private Person currentPerson;
   	
	private void initCurrentPerson() {
		
		String username = "";
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
		  username = ((UserDetails)principal).getUsername();
		} 
		else {
		  username = principal.toString();
		}
		
		User user = userService.loadUserByUsername(username);
		currentPerson = findRoofApiController.getPersonByUser(user);
	}	

	@RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
	public String test() {
        //return "test test test"  +initCurrentPerson();
		return "";
	}

	
	@RequestMapping(value = "/request", method = RequestMethod.GET)
    public ModelAndView viewRequests() 
	{
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (!(auth instanceof AnonymousAuthenticationToken)) {
            return new ModelAndView("redirect:/");
        }
        
        ModelAndView modelAndView = new ModelAndView("request");
        List<Contract> requests = findRoofApiController.getPersonRequests();
        
        modelAndView.addObject("requests", requests);
        
        return modelAndView;
    }
	
}
