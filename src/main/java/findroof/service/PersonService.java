package findroof.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import findroof.bo.BoPossession;
import findroof.model.Contract;
import findroof.model.Person;
import findroof.model.User;
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
	
	public Person GetPersonByUser(User user) throws Exception  
	{ 
		try
		{
			return personRepo.findByUser(user); 
		}
		catch(Exception exception)
		{
			String msg = "Erreur lors de la récupération depuis la BD la person '"+user+"'";
			_logger.error(msg, exception);
			throw new Exception(msg, exception);
		}
	}
	
	public Person addPerson(Person person) throws Exception  
	{ 
		try
		{
			return personRepo.save(person); 
		}
		catch(Exception exception)
		{
			String msg = "Erreur lors du save de person dans la BD avec pour paramètre '"+person+"'";
			_logger.error(msg, exception);
			throw new Exception(msg, exception);
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
