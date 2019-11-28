package findroof.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import findroof.utilities.Person_Role;

@Entity
public class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="pers_id")
	private int id;
	
	@Column(name="pers_firstname")
	private String firstName;
	
	@Column(name="pers_lastname")
	private String lastName;
	
	@Column(name="pers_age")
	private int age;

	@Column(name="pers_role")
	private Person_Role role;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_pers_id")
	private Person person;

	public Person() {
		super();
	}

	
	public Person(String firstName, String lastName, int age, Person_Role role, Person person) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.role = role;
		this.person = person;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Person_Role getRole() {
		return role;
	}

	public void setRole(Person_Role role) {
		this.role = role;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}


	@Override
	public String toString() {
		return "Person [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + ", role="
				+ role + ", person=" + person + "]";
	}

}
