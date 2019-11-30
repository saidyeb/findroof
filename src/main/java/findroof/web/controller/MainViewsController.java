package findroof.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import findroof.bo.BoPossession;
import findroof.controllers.FindRoofApiController;
import findroof.model.Contract;
import findroof.model.Person;
import findroof.model.Possession;
import findroof.model.User;
import findroof.service.UserService;

@Controller
public class MainViewsController {
	
	@Autowired
	private FindRoofApiController findRoofApiController; 
    
	@Autowired
	private UserService userService; 
	
	private Person currentPerson;
   	
	@ModelAttribute
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
		this.currentPerson = findRoofApiController.getPersonByUser(user);
	}	

	@RequestMapping(value="/posts", method = RequestMethod.GET)
	public ModelAndView viewPosts() {
		
		ModelAndView modelAndView = new ModelAndView("posts");
		
		List<Possession> possessions = findRoofApiController.getAllPosts();
		
		modelAndView.addObject("possessions", possessions);
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/possessions", method = RequestMethod.GET)
    public ModelAndView viewRequests() {
		
        ModelAndView modelAndView = new ModelAndView("possessions");
        BoPossession boPossession = findRoofApiController.getPersonPossessions(this.currentPerson.getId());
        
        modelAndView.addObject("possessionHolding", boPossession.getPossessionHolding());
        modelAndView.addObject("possessionOwning", boPossession.getPossessionOwning());
        
        return modelAndView;
    }

	
}