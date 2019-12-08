package findroof.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import findroof.utilities.Role;

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
	private Role role;
	
	@Column(name="pers_isblacklist")
	private Boolean isBlacklisted;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_pers_id")
	private User user;

	public Person() {
		super();
		this.isBlacklisted = false;
	}

	
	public Person(String firstName, String lastName, int age, Role role, User user, Boolean isBlacklisted) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.role = role;
		this.user = user;
		this.isBlacklisted = isBlacklisted;
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Boolean getIsBlacklisted() {
		return isBlacklisted;
	}

	public void setIsBlacklisted(Boolean isBacklisted) {
		this.isBlacklisted = isBacklisted;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + ", role="
				+ role + ", isBacklisted=" + isBlacklisted + ", user=" + user + "]";
	}

}
