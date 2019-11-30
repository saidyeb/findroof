package findroof.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="addr_id")
	private int id;
	
	@Column(name="addr_street")
	private String street;
	
	@Column(name="addr_city")
	private String city; 
	
	@Column(name="addr_country")
	private String country;
	
	@Column(name="addr_zipcode")
	private int zipCode;
	
	public Address() {}
	
	public Address(String street, String city, String country, int zipCode) {
		super();
		this.street = street;
		this.city = city;
		this.country = country;
		this.zipCode = zipCode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", street=" + street + ", city=" + city + ", country=" + country + ", zipCode="
				+ zipCode + "]";
	}
		
}
