package findroof.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import findroof.bo.BoPossession;
import findroof.bo.BoPossessionFilter;
import findroof.model.Contract;
import findroof.model.Person;
import findroof.model.Possession;
import findroof.repository.ContractRepository;
import findroof.repository.PersonRepository;
import findroof.repository.PossessionRepository;
import findroof.utilities.Role;

@Service
public class PossessionService {

	@Autowired
	private PossessionRepository possessionRepo;
	
	@Autowired
	private ContractRepository contractRepo;
	
	@Autowired 
	private PersonRepository personRepo;
	
	private Logger _logger = LoggerFactory.getLogger(this.getClass());
	
	public PossessionService() {}
	
	public List<BoPossession> getBoPossessionsByPerson(int personId, Role typePossession) throws Exception
	{
		try
		{
			List<BoPossession> boPossessions = new ArrayList<BoPossession>();			
			Person person = personRepo.findById(personId).get(); 
			
			if(typePossession == Role.Holder && (person.getRole() == Role.Holder || person.getRole() == Role.OwnerHolder) ) 
			{
				List<Possession> possessionHolder = possessionRepo.findByHouseHolders(person);
				for(Possession possession : possessionHolder)
				{
					boPossessions.add(new BoPossession(possession));
				}
			}
			else if (typePossession == Role.Owner && (person.getRole() == Role.Owner || person.getRole() == Role.OwnerHolder) )
			{
				List<Possession> possessionOwners = possessionRepo.findByHouseOwner(person);
				for(Possession possession : possessionOwners)
				{
					boPossessions.add(new BoPossession(possession));
				}			
			}
			
			return boPossessions;
		}
		catch(Exception exception)
		{
			String msg = "Erreur lors de la récupération depuis la BD la listes des biens loués avec pour paramètres '"+personId+"'";
			_logger.error(msg, exception);
			throw new Exception(msg, exception);
		}
	}
	
	
	public Possession addPossession(Possession possession, Person houseOwner) throws Exception
	{
		try
		{
			if(houseOwner != null && (houseOwner.getRole() == Role.Owner || houseOwner.getRole() == Role.OwnerHolder))
			{
				possession.setHouseOwner(houseOwner);
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

	public List<Possession> getAllPosts(Person person) throws Exception
	{
		try
		{
			List<Possession> allPossessions= (List<Possession>) possessionRepo.findAll();
			List<Contract> holderContracts = contractRepo.findByHouseHolder(person);
			Iterable<Contract> ownerContracts = contractRepo.findByHouseOwner(person);
			
			// remove if the person has already the possession 
			allPossessions.removeIf(possession -> possession.getHouseHolders().contains(person));
			
			// remove if the person own the possession 
			allPossessions.removeIf(possession -> possession.getHouseOwner().getId() == person.getId());
			
			// remove if the person has already <b>reserve</b> the possession 
			for(Contract holderContract : holderContracts)
			{
				allPossessions.removeIf(possession -> possession.getId() == holderContract.getPossession().getId());
			}
			
			for(Contract ownerContract : ownerContracts)
			{
				allPossessions.removeIf(possession -> possession.getId() == ownerContract.getPossession().getId());
			}
		
			return allPossessions; 
		}
		catch(Exception exception)
		{
			String msg = "Erreur lors de la récupération de la liste des biens.";
			_logger.error(msg, exception);
			throw new Exception(msg, exception);
		}		
	}
	
	public List<Possession> getAllPostByFilterPerson(BoPossessionFilter boFilter, Person person) throws Exception
	{
		try
		{
			List<Possession> allPossessions= (List<Possession>) possessionRepo.findAll();
			List<Contract> holderContracts = contractRepo.findByHouseHolder(person);
			Iterable<Contract> ownerContracts = contractRepo.findByHouseHolder(person);
			
			allPossessions.removeIf(possession -> possession.getHouseHolders().contains(person));
			
			
			allPossessions.removeIf(possession -> 
				
			       !(possession.getAddress().getCity() == boFilter.getAddress().getCity())  
				&& !(possession.getAddress().getStreet() == boFilter.getAddress().getStreet())
				&& !(possession.getAddress().getZipCode() == boFilter.getAddress().getZipCode())
				&& !(possession.getAddress().getCountry() == boFilter.getAddress().getCountry())
				
				&& !(possession.getSurface() >= boFilter.getMinSurface() && possession.getSurface() <= boFilter.getMaxSurface()) 
				
				&& !(possession.getMaxPerson() <= boFilter.getMaxPerson())
			
				&& !(possession.getHouseHolders().size() <= boFilter.getFreePlaces())
			);
			
			for(Contract holderContract : holderContracts) 
				allPossessions.removeIf(possession -> possession.getId() == holderContract.getPossession().getId());
			
			for(Contract ownerContract : ownerContracts)
				allPossessions.removeIf(possession -> possession.getId() == ownerContract.getPossession().getId());
		
			return allPossessions; 
		}
		catch(Exception exception)
		{
			String msg = "Erreur lors de la récupération de la liste des biens. avec les filtes"+boFilter;
			_logger.error(msg, exception);
			throw new Exception(msg, exception);
		}		
	}
}
