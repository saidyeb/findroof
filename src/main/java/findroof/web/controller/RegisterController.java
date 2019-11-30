package findroof.web.controller;


import java.util.Collections;
import java.util.List;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import findroof.model.User;
import findroof.repository.UserRepository;
import findroof.utilities.Person_Role;
import findroof.utilities.User_Role;

@Controller
public class RegisterController {
	  
	@Autowired private UserRepository userRepository;
	
	@RequestMapping(value="/register",method = RequestMethod.GET)
	public ModelAndView test() {
		ModelAndView model = new ModelAndView("register");
		model.addObject("user", new User());
		return model;
	}
	  
	@RequestMapping(value="/register",method = RequestMethod.POST)
	public String save(@ModelAttribute("user") @Valid User user, BindingResult result) {
		
		List<Person_Role> personRole= Collections.singletonList(Person_Role.Owner);
		
		if (user.getRole()=="Owner") {
			personRole = Collections.singletonList(Person_Role.Owner);
		}
		else if (user.getRole()=="Holder") {
			personRole = Collections.singletonList(Person_Role.Holder);
		}
		else if (user.getRole()=="OwnerHolder") {
			personRole = Collections.singletonList(Person_Role.OwnerHolder);
		}
		
		user.setRoles(personRole);
		userRepository.save(user);
		return "redirect:/register?success";
	}
		  
}
