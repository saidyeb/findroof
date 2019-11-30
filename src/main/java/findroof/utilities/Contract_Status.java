package findroof.utilities;

public enum Contract_Status {
	Valid("Valider"),
	Refuse("Refusé"),
	InProgress("En cours");
	
    private final String text;

	Contract_Status(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
