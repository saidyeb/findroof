package findroof.bo;

import java.io.Serializable;

import findroof.model.Address;

public class BoPossessionFilter implements Serializable {

	private int maxPerson;  
	private int minSurface; 
	private int maxSurface; 
	private int freePlaces;
	private Address address;
	
	
	public BoPossessionFilter() {
		super();
	}


	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}


	public int getMaxPerson() {
		return maxPerson;
	}


	public void setMaxPerson(int maxPerson) {
		this.maxPerson = maxPerson;
	}


	public int getMinSurface() {
		return minSurface;
	}


	public void setMinSurface(int minSurface) {
		this.minSurface = minSurface;
	}


	public int getMaxSurface() {
		return maxSurface;
	}


	public void setMaxSurface(int maxSurface) {
		this.maxSurface = maxSurface;
	}


	public int getFreePlaces() {
		return freePlaces;
	}


	public void setFreePlaces(int freePlaces) {
		this.freePlaces = freePlaces;
	} 
	
}
