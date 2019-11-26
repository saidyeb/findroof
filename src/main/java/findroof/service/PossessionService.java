package findroof.service;

import java.util.ArrayList;
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

	
}
