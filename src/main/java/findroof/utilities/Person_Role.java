package findroof.utilities;

public enum Person_Role {
	Default("Default"),
	Owner("Propritaire"), 
	Holder("Locataire"),
	OwnerHolder("Propritaire/Locataire");
	
	private final String text;

    /**
     * @param text
     */
	Person_Role(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}


