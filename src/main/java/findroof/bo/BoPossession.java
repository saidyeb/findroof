package findroof.bo;

import findroof.model.Possession;

public class BoPossession {

	private Possession possession;
	
	public BoPossession() {
		super();
		this.possession = new Possession();
	}
	
	public BoPossession(Possession possession) {
		super();
		this.possession = possession;
	}

	public Possession getPossession() {
		return possession;
	}

	public void setPossession(Possession possession) {
		this.possession = possession;
	}
}
