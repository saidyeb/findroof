package findroof.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import findroof.model.Contract;
import findroof.model.Person;
import findroof.model.User;
import findroof.service.*;

@RestController
@RequestMapping(value = "/api")
public class FindRoofApiController {

	@Autowired
	private PersonService personService; 

	@Autowired
	private ContractService contractService; 
	
	@RequestMapping(value="/getperson", method = RequestMethod.GET)
	@ResponseBody
	public Person getPersonByUser(User user) 
	{
		try {
			return personService.GetPersonByUser(user);
		} 
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}
}
