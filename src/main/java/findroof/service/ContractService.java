package findroof.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import findroof.bo.BoContract;
import findroof.model.Contract;
import findroof.model.Person;
import findroof.repository.ContractRepository;
import findroof.repository.PersonRepository;
import findroof.utilities.Contract_Status;
import findroof.utilities.Person_Role;

@Service
public class ContractService {

	@Autowired
	private ContractRepository contractRepo;
	
	@Autowired
	private PersonRepository personRepo;

	private Logger _logger = LoggerFactory.getLogger(this.getClass());
	
	public ContractService() {}
	
	
	public BoContract getPersonRequests(int personId) throws Exception
	{		
		try 
		{
			BoContract boContract = new BoContract();
			Person person = personRepo.findById(personId).get();
			Iterable<Contract> allContracts = contractRepo.findAll();
			
			for(Contract contract : allContracts)
			{
				if(person.getRole() == Person_Role.Holder || person.getRole() == Person_Role.OwnerHolder)
				{
					if(contract.getHouseHolder().getId() == person.getId())
						boContract.getSendRequests().add(contract);
				}
				else if(person.getRole() == Person_Role.Owner || person.getRole() == Person_Role.OwnerHolder)
				{
					if(contract.getHouseOwner().getId() == person.getId())
						boContract.getReceiveRequests().add(contract);					
				}
			}
			
			return boContract;
		}
		catch(Exception exception)
		{
			String msg = "Erreur lors de la récupération depuis la BD la listes des contracts demandés/signés avec pour paramètres '"+personId+"'";
			_logger.error(msg, exception);
			throw new Exception(msg, exception);
		}
	}
	
	public Contract addContract(Contract contract) throws Exception
	{		
		try 
		{
			if(contract != null)
			{
				return this.contractRepo.save(contract);
			}
			
			return null;
		}
		catch(Exception exception)
		{
			String msg = "Erreur lors de sauvegarde dans la BD du nouveau contract avec pour paramètres'"+contract+"'";
			_logger.error(msg, exception);
			throw new Exception(msg, exception);
		}
	}

	public Contract updateContractStatus(int contractId, Contract_Status status) throws Exception
	{		
		try 
		{
			Contract contract = null; 
			
			if(contractId > 0 && status != null)
			{
				contract = this.contractRepo.findById(contractId).get();
				contract.setStatus(status);
				contract = this.contractRepo.save(contract);
			}
			
			return contract;
		}
		catch(Exception exception)
		{
			String msg = "Erreur lors de la mise à jour dans la BD du contract avec pour paramètres contractId:'"+contractId+"'";
			_logger.error(msg, exception);
			throw new Exception(msg, exception);
		}
	}
	
	
}
