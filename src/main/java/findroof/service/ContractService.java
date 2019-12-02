package findroof.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import findroof.bo.BoContract;
import findroof.model.Contract;
import findroof.model.Person;
import findroof.model.Possession;
import findroof.repository.ContractRepository;
import findroof.repository.PersonRepository;
import findroof.repository.PossessionRepository;
import findroof.utilities.Contract_Status;
import findroof.utilities.Role;

@Service
public class ContractService {

	@Autowired
	private ContractRepository contractRepo;
	
	@Autowired
	private PersonRepository personRepo;
	
	@Autowired
	private PossessionRepository possessionRepo;

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
				if(person.getRole() == Role.Holder || person.getRole() == Role.OwnerHolder)
				{
					if(contract.getHouseHolder().getId() == person.getId())
						boContract.getSendRequests().add(contract);
				}
				
				if(person.getRole() == Role.Owner || person.getRole() == Role.OwnerHolder)
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

	public Boolean updateContractStatus(int contractId, String status) throws Exception
	{		
		try 
		{
			if(contractId > 0 && status != null)
			{
				Contract contract = this.contractRepo.findById(contractId).get();
				Possession possession = this.possessionRepo.findById(contract.getPossession().getId()).get();
				
				if(status.equalsIgnoreCase("Valid")) {
					contract.setStatus(Contract_Status.Valid);
					possession.getHouseHolders().add(contract.getHouseHolder());
				}
				else if(status.equalsIgnoreCase("Refuse"))
					contract.setStatus(Contract_Status.Refuse);	
					
				this.contractRepo.save(contract);
				this.possessionRepo.save(possession);
				
				return true;
			}
			
			return false;
		}
		catch(Exception exception)
		{
			String msg = "Erreur lors de la mise à jour dans la BD du contract avec pour paramètres contractId:'"+contractId+"'";
			_logger.error(msg, exception);
			throw new Exception(msg, exception);
		}
	}
	
	
}
