package findroof.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Contract {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="contr_id")
	private int id;
	
	@Column(name="contr_datestart")
	private Date dateStart;
	
	@Column(name="contr_dateend")
	private Date dateEnd;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pers_owner_id")
    private Person houseOwner;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pers_holder_id")
    private Person houseHolder;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pers_poss_id")
    private Possession possession;

	public Contract(Person houseOwner, Person houseHolder, Possession possession, Date dateStart, Date dateEnd) {
		super();
		this.houseOwner = houseOwner;
		this.houseHolder = houseHolder;
		this.possession = possession;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public Person getHouseOwner() {
		return houseOwner;
	}

	public void setHouseOwner(Person houseOwner) {
		this.houseOwner = houseOwner;
	}

	public Person getHouseHolder() {
		return houseHolder;
	}

	public void setHouseHolder(Person houseHolder) {
		this.houseHolder = houseHolder;
	}

	public Possession getPossession() {
		return possession;
	}

	public void setPossession(Possession possession) {
		this.possession = possession;
	}

	@Override
	public String toString() {
		return "Contract [id=" + id + ", dateStart=" + dateStart + ", dateEnd=" + dateEnd + ", houseOwner=" + houseOwner
				+ ", houseHolder=" + houseHolder + ", possession=" + possession + "]";
	}
	
	
}
