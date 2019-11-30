package findroof.bo;

import java.util.ArrayList;
import java.util.List;

import findroof.model.Contract;


public class BoContract {

	private List<Contract> receiveRequests;
	private List<Contract> sendRequests;
	
	
	public BoContract() {
		this.receiveRequests = new ArrayList<>();
		this.sendRequests = new ArrayList<>();
	}


	public BoContract(List<Contract> receiveRequests, List<Contract> sendRequests) {
		super();
		this.receiveRequests = receiveRequests;
		this.sendRequests = sendRequests;
	}


	public List<Contract> getReceiveRequests() {
		return receiveRequests;
	}


	public void setReceiveRequests(List<Contract> receiveRequests) {
		this.receiveRequests = receiveRequests;
	}


	public List<Contract> getSendRequests() {
		return sendRequests;
	}


	public void setSendRequests(List<Contract> sendRequests) {
		this.sendRequests = sendRequests;
	}
	
	
	
	

}
