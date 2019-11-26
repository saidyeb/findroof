package findroof.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Possession {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="poss_id")
	private int id;
	
	@Column(name="poss_name")
	private String name;
	
	@Column(name="poss_type")
	private String type;	
	
	@Column(name="poss_maxperson")
	private int maxPerson;
	
	@Column(name="poss_price")
	private double price;
	
	@Column(name="poss_surface")
	private int surface;
	
	@Column(name="poss_description")
	private int description;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "addr_id")
	private Address address;
    
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pers_owner_id")
    private Person houseOwner;
    
    @OneToMany
    @JoinColumn(name ="pers_holders_id")
    private Set<Person> houseHolders;

	public Possession(String name, String type, int maxPerson, double price, int surface, int description,
			Address address, Person houseOwner, Set<Person> houseHolders) {
		super();
		this.name = name;
		this.type = type;
		this.maxPerson = maxPerson;
		this.price = price;
		this.surface = surface;
		this.description = description;
		this.address = address;
		this.houseOwner = houseOwner;
		this.houseHolders = houseHolders;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getMaxPerson() {
		return maxPerson;
	}

	public void setMaxPerson(int maxPerson) {
		this.maxPerson = maxPerson;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getSurface() {
		return surface;
	}

	public void setSurface(int surface) {
		this.surface = surface;
	}

	public int getDescription() {
		return description;
	}

	public void setDescription(int description) {
		this.description = description;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Person getHouseOwner() {
		return houseOwner;
	}

	public void setHouseOwner(Person houseOwner) {
		this.houseOwner = houseOwner;
	}

	public Set<Person> getHouseHolders() {
		return houseHolders;
	}

	public void setHouseHolders(Set<Person> houseHolders) {
		this.houseHolders = houseHolders;
	}

	@Override
	public String toString() {
		return "Possession [id=" + id + ", name=" + name + ", type=" + type + ", maxPerson=" + maxPerson + ", price="
				+ price + ", surface=" + surface + ", description=" + description + ", address=" + address
				+ ", houseOwner=" + houseOwner + ", houseHolders=" + houseHolders + "]";
	}
    
    
}
