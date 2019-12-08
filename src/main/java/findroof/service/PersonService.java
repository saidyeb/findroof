package findroof.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import findroof.model.Person;
import findroof.model.User;
import findroof.repository.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	private PersonRepository personRepo;
	
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
			person.setIsBlacklisted(false);
			return personRepo.save(person); 
		}
		catch(Exception exception)
		{
			String msg = "Erreur lors du save de person dans la BD avec pour paramètre '"+person+"'";
			_logger.error(msg, exception);
			throw new Exception(msg, exception);
		}
	}
}
