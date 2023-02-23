package quest.model;

public enum Civilite {
	M("Monsieur"), MME("Madame"), NB("Non Binaire");

	private final String label;

	private Civilite(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

}
