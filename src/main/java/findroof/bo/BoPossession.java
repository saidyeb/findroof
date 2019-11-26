package findroof.bo;

import java.util.ArrayList;
import java.util.List;

import findroof.model.Possession;

public class BoPossession {

			     
	private List<Possession> possessionHolding;
	private List<Possession> possessionOwning;
	
	public BoPossession() {
		super();
		this.possessionHolding = new ArrayList<>();
		this.possessionOwning = new ArrayList<>();
	}

	public List<Possession> getPossessionHolding() {
		return possessionHolding;
	}

	
	public void setPossessionHolding(List<Possession> possessionHolding) {
		this.possessionHolding = possessionHolding;
	}

	public List<Possession> getPossessionOwning() {
		return possessionOwning;
	}

	public void setPossessionOwning(List<Possession> possessionOwning) {
		this.possessionOwning = possessionOwning;
	}
	
}
