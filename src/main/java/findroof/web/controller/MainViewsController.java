package findroof.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import findroof.bo.BoContract;
import findroof.bo.BoPossession;
import findroof.bo.BoPossessionFilter;
import findroof.controllers.FindRoofApiController;
import findroof.model.Person;
import findroof.model.Possession;
import findroof.model.User;
import findroof.service.UserService;
import findroof.utilities.Role;

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
		
		List<Possession> possessions = findRoofApiController.getAllPosts(this.currentPerson);
		
		modelAndView.addObject("possessions", possessions);
		modelAndView.addObject("person", this.currentPerson);
		modelAndView.addObject("boPossessionFilter", new BoPossessionFilter()); 
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/possessions", method = RequestMethod.GET)
    public ModelAndView viewPossessions() {
		
        List<BoPossession> boPossessionHolding = findRoofApiController.getPersonPossessions(this.currentPerson.getId(), Role.Holder);
        List<BoPossession> boPossessionOwning  = findRoofApiController.getPersonPossessions(this.currentPerson.getId(), Role.Owner);
       
        ModelAndView modelAndView = new ModelAndView("possessions");
        modelAndView.addObject("person", this.currentPerson);
        modelAndView.addObject("possessionsHolding", boPossessionHolding);
        modelAndView.addObject("possessionsOwning", boPossessionOwning);
        
        return modelAndView;
    }
	
	@RequestMapping(value = "/requests", method = RequestMethod.GET)
    public ModelAndView viewRequests() {
		
        ModelAndView modelAndView = new ModelAndView("requests");
        modelAndView.addObject("person", this.currentPerson);
        
        BoContract boContract = findRoofApiController.getPersonRequests(this.currentPerson.getId());
        modelAndView.addObject("receiveRequests", boContract.getReceiveRequests());
        modelAndView.addObject("sendRequests", boContract.getSendRequests());
        
        return modelAndView;
    }
	
	@RequestMapping(value="/postsFiltered", method = RequestMethod.POST)
	public ModelAndView viewFilteredPosts(@Valid BoPossessionFilter boFilter, BindingResult result) {
		
		ModelAndView modelAndView = new ModelAndView("posts");
		
		List<Possession> possessions = findRoofApiController.applyPossessionFilter(boFilter, this.currentPerson);
		
		modelAndView.addObject("possessions", possessions);
		modelAndView.addObject("person", this.currentPerson);
		modelAndView.addObject("boPossessionFilter", new BoPossessionFilter()); 
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/addpossessions", method = RequestMethod.GET)
    public ModelAndView viewAddPossession() {
		ModelAndView model = new ModelAndView("addpossessions");
		model.addObject("possession", new Possession());
		return model;
        
    }
	
	@RequestMapping(value="/addpossessions",method = RequestMethod.POST)
	public String save(@ModelAttribute("possession") @Valid Possession possession, BindingResult result) {
		findRoofApiController.addPossession(possession, this.currentPerson);		
		return "redirect:/posts";
	}
	
}
