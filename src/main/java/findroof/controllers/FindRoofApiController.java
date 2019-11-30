package findroof.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import findroof.bo.BoContract;
import findroof.bo.BoPossession;
import findroof.model.Contract;
import findroof.model.Person;
import findroof.model.Possession;
import findroof.model.User;
import findroof.service.*;

@RestController
@RequestMapping(value = "/api")
public class FindRoofApiController {

	@Autowired
	private PersonService personService; 
	
	@Autowired
	private PossessionService possessionService; 

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
	
	@RequestMapping(value="/getallposts", method = RequestMethod.GET)
	@ResponseBody
	public List<Possession> getAllPosts() 
	{
		try {
			return possessionService.getAllPosts();
		} 
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}
	
	@RequestMapping(value="/getPersonPossessions", method = RequestMethod.GET)
	@ResponseBody
	public BoPossession getPersonPossessions(int personId) 
	{
		try 
		{
			return possessionService.getBoPossessionsByPerson(personId);
		} 
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}
	
	@RequestMapping(value="/getPersonRequests", method = RequestMethod.GET)
	@ResponseBody
	public BoContract getPersonRequests(int personId) 
	{
		try 
		{
			return contractService.getPersonRequests(personId);
		} 
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}
	
	

}
