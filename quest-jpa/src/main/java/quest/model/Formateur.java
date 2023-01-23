package quest.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("formateur")
public class Formateur extends Personne {
	@Column(name = "extern")
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
