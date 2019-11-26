package findroof.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import findroof.model.Contract;
import findroof.model.Person;
import findroof.repository.ContractRepository;
import findroof.utilities.Person_Role;

@Service
public class ContractService {

	@Autowired
	private ContractRepository contractRepo;

	private Logger _logger = LoggerFactory.getLogger(this.getClass());
	
	public ContractService() {}
	
	
	public List<Contract> getPersonRequests(Person person) throws Exception
	{		
		try 
		{
			List<Contract> contracts = new ArrayList<Contract>();
			
			if(person.getRole() == Person_Role.Holder || person.getRole() == Person_Role.OwnerHolder)
			{
				Iterable<Contract> allContracts = contractRepo.findAll();
				for(Contract contract : allContracts)
				{
					if(contract.getHouseHolder().getId() == person.getId())
						contracts.add(contract);
				}
			}
			
			return contracts;
		}
		catch(Exception exception)
		{
			String msg = "Erreur lors de la récupération depuis la BD la listes des contracts demandés avec pour paramètres '"+person+"'";
			_logger.error(msg, exception);
			throw new Exception(msg, exception);
		}
	}
	
	public List<Contract> getPersonContracts(Person person) throws Exception
	{		
		try 
		{
			List<Contract> contracts = new ArrayList<Contract>();
			
			if(person.getRole() == Person_Role.Owner || person.getRole() == Person_Role.OwnerHolder)
			{
				Iterable<Contract> allContracts = contractRepo.findAll();
				for(Contract contract : allContracts)
				{
					if(contract.getHouseOwner().getId() == person.getId())
						contracts.add(contract);
				}			
			}
			
			return contracts;
		}
		catch(Exception exception)
		{
			String msg = "Erreur lors de la récupération depuis la BD la listes des contracts signés avec pour paramètres '"+person+"'";
			_logger.error(msg, exception);
			throw new Exception(msg, exception);
		}
	}
}
