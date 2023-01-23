package quest.model;

public class Formateur extends Personne {
	private boolean externe;
	private int experience;

	public Formateur() {
		super();
	}

	public boolean isExterne() {
		return externe;
	}

	public void setExterne(boolean externe) {
		this.externe = externe;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

}
