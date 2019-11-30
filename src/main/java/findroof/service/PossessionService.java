package findroof.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import findroof.bo.BoPossession;
import findroof.model.Person;
import findroof.model.Possession;
import findroof.repository.PossessionRepository;
import findroof.utilities.Person_Role;

@Service
public class PossessionService {

	@Autowired
	private PossessionRepository possessionRepo;
	
	private Logger _logger = LoggerFactory.getLogger(this.getClass());
	
	public PossessionService() {}
	
	public BoPossession getBoPossessionsByPerson(Person person) throws Exception
	{
		try
		{
			BoPossession boPossession = new BoPossession();
			
			Iterable<Possession> allPossession =  possessionRepo.findAll();
			for (Possession possession : allPossession)
			{
				if(person.getRole() == Person_Role.Holder || person.getRole() == Person_Role.OwnerHolder)
				{
					Person possPers = possession.getHouseHolders().stream() 
							.filter(p -> p.getId() == person.getId())
							.findFirst()
							.get();
					
					if (possPers != null)
						boPossession.getPossessionHolding().add(possession);
				}
				else if (person.getRole() == Person_Role.Owner || person.getRole() == Person_Role.OwnerHolder)
				{
				    if (possession.getHouseOwner().getId() == person.getId())
				    	 boPossession.getPossessionHolding().add(possession);
				}
				
			}
			
			return boPossession;
		}
		catch(Exception exception)
		{
			String msg = "Erreur lors de la récupération depuis la BD la listes des biens loués avec pour paramètres '"+person+"'";
			_logger.error(msg, exception);
			throw new Exception(msg, exception);
		}
	}
	
	
	public Possession addPossession(Possession possession, Person person) throws Exception
	{
		try
		{
			if(person != null && (person.getRole() == Person_Role.Owner || person.getRole() == Person_Role.OwnerHolder))
			{
				possession.setHouseOwner(person);
				return possessionRepo.save(possession);
			}
			else
			{
				return null;
			}
		}
		catch(Exception exception)
		{
			String msg = "Erreur lors de sauvegarde dans la BD du nouveau bien avec pour paramètres '"+possession+"'";
			_logger.error(msg, exception);
			throw new Exception(msg, exception);
		}
	}
	
	public Possession addHouseHolderPossession(int possessionId, Person person) throws Exception
	{
		try
		{
			Possession newPossession = null;
			
			if(person != null && possessionId > 0)
			{
				Possession possession = possessionRepo.findById(possessionId).get();
				if(possession.getMaxPerson() > possession.getHouseHolders().size())
				{
					possession.getHouseHolders().add(person);	
					newPossession = possessionRepo.save(possession);
				}
			}
			
			return newPossession;
			
		}
		catch(Exception exception)
		{
			String msg = "Erreur lors de sauvegarde dans la BD du nouveau householder avec pour paramètres possessionId='"+possessionId+"' '"+person.toString()+"'";
			_logger.error(msg, exception);
			throw new Exception(msg, exception);
		}
	}

	public List<Possession> getAllPosts() throws Exception
	{
		try
		{
			Iterable<Possession> possessions= possessionRepo.findAll();
			return (List<Possession>) possessions; 
		}
		catch(Exception exception)
		{
			String msg = "Erreur lors de la récupération de la liste des biens.";
			_logger.error(msg, exception);
			throw new Exception(msg, exception);
		}		
	}
}
