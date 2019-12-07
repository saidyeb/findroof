package findroof.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import findroof.bo.BoContract;
import findroof.bo.BoPossession;
import findroof.bo.BoPossessionFilter;
import findroof.model.Contract;
import findroof.model.Person;
import findroof.model.Possession;
import findroof.model.User;
import findroof.service.*;
import findroof.utilities.Role;

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
	public List<Possession> getAllPosts(Person person) 
	{
		try {
			return possessionService.getAllPosts(person);
		} 
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}
	
	@RequestMapping(value="/getPersonPossessions", method = RequestMethod.GET)
	@ResponseBody
	public List<BoPossession> getPersonPossessions(int personId, Role typePossession) 
	{
		try 
		{
			return possessionService.getBoPossessionsByPerson(personId, typePossession);
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
	
	@RequestMapping(value="/contractUpdateStatus", method = RequestMethod.GET)
	@ResponseBody
	public Boolean contractUpdateStatus(int contractId, String status) 
	{
		try 
		{
			return contractService.updateContractStatus(contractId, status);
		} 
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}	
	}
	
	
	@RequestMapping(value="/createRequest", method = RequestMethod.GET)
	@ResponseBody
	public Contract createRequest(int possessionId, int holderId) 
	{
		try 
		{
			return contractService.addContract(possessionId, holderId);
		} 
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}
	
	@RequestMapping(value="/applyPossessionFilter", method = RequestMethod.GET)
	@ResponseBody
	public List<Possession> applyPossessionFilter(BoPossessionFilter boFilter, Person person) 
	{
		try 
		{
			return possessionService.getAllPostByFilterPerson(boFilter,person);
		} 
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}
	
	
	@RequestMapping(value="/addpossession", method = RequestMethod.GET)
	@ResponseBody
	public Possession addPossession(Possession possession, Person houseOwner) 
	{
		try 
		{
			return possessionService.addPossession(possession, houseOwner);
		} 
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}
	
	
	
	
}
