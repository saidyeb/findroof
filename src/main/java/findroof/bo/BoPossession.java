package findroof.bo;

import findroof.model.Possession;

public class BoPossession {

	private Possession possession;
	
	private double priceDollar; 
	
	public BoPossession() {
		super();
		this.priceDollar = 0.0;
		this.possession = new Possession();
	}
	
	public BoPossession(Possession possession) {
		super();
		this.possession = possession;
	}
	
	public BoPossession(Possession possession, double priceDollar) {
		this.possession = possession;
		this.priceDollar = priceDollar;
	}

	public Possession getPossession() {
		return possession;
	}

	public void setPossession(Possession possession) {
		this.possession = possession;
	}

	public double getPriceDollar() {
		return priceDollar;
	}

	public void setPriceDollar(double priceDollar) {
		this.priceDollar = priceDollar;
	}
	
}
