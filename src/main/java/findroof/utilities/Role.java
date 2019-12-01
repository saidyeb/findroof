package findroof.utilities;

public enum Role {
	Owner("Propritaire"), 
	Holder("Locataire"),
	OwnerHolder("Propritaire/Locataire");
	
	private final String text;

    /**
     * @param text
     */
	Role(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
