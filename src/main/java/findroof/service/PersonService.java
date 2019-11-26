package findroof.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import findroof.bo.BoPossession;
import findroof.model.Contract;
import findroof.model.Person;
import findroof.repository.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	private PersonRepository personRepo;
	
	@Autowired
	private PossessionService possessionService;

	@Autowired
	private ContractService contractService;
	
	private Logger _logger = LoggerFactory.getLogger(this.getClass());
	
	public PersonService() {}
	
	public Person GetPersonById(int id) throws Exception  
	{ 
		try
		{
			return personRepo.findById(id).get(); 
		}
		catch(Exception exception)
		{
			String msg = "Erreur lors de la récupération depuis la BD la person '"+id+"'";
			_logger.error(msg, exception);
			throw new Exception(msg, exception);
		}
		
	}
	
	public BoPossession getPersonPossessions(Person person) throws Exception
	{
		try 
		{
			return possessionService.getBoPossessionsByPerson(person);
		} 
		catch (Exception exception) 
		{
			throw exception;
		}
	}
	
	public List<Contract> getPersonRequests(Person person) throws Exception
	{
		try 
		{
			return contractService.getPersonRequests(person);
		} 
		catch (Exception exception) 
		{
			throw exception;
		}
	}
	
	public List<Contract> getPersonContracts(Person person) throws Exception
	{
		try 
		{
			return contractService.getPersonContracts(person);
		} 
		catch (Exception exception) 
		{
			throw exception;
		}	
	}
}
