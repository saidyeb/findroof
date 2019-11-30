package findroof.utilities;

public enum Possession_Type {
	Flat("Appartement"), 
	House("Maison");
	
    private final String text;

	Possession_Type(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
