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

import findroof.model.Person;
import findroof.model.User;
import findroof.repository.PersonRepository;
import findroof.repository.UserRepository;
import findroof.utilities.Role;

@Controller
public class RegisterController {
	  
	@Autowired 
	private UserRepository userRepository;
	
	@Autowired 
	private PersonRepository personRepository;
	
	
	@RequestMapping(value="/register",method = RequestMethod.GET)
	public ModelAndView viewRegister() {
		ModelAndView model = new ModelAndView("register");
		model.addObject("user", new User());
		return model;
	}
	  
	@RequestMapping(value="/register",method = RequestMethod.POST)
	public String save(@ModelAttribute("user") @Valid User user, BindingResult result) {
		
		String role=user.getRole();
		
		Person person = new Person();
		person.setFirstName(user.getFirstName());
		person.setLastName(user.getLastName());
		person.setUser(user);
		
		if (role.equals("Owner")) {
			List<Role> userRole = Collections.singletonList(Role.Owner);
			user.setRoles(userRole);
			person.setRole(Role.Owner);
		}
		else if (role.equals("Holder")) {
			List<Role> userRole = Collections.singletonList(Role.Holder);
			user.setRoles(userRole);
			person.setRole(Role.Holder);
		}
		else if (role.equals("OwnerHolder")) {
			List<Role> userRole = Collections.singletonList(Role.OwnerHolder);
			user.setRoles(userRole);
			person.setRole(Role.OwnerHolder);
		}
		
		userRepository.save(user);
		personRepository.save(person);
		
		return "redirect:/register?success";
	}
		  
}
