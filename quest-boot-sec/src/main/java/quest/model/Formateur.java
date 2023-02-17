package quest.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@DiscriminatorValue("formateur")
public class Formateur extends Personne {
	@Column(name = "extern")
	@JsonView(Views.ViewBase.class)
	private boolean externe;
	@JsonView(Views.ViewBase.class)
	private int experience;

	public Formateur() {
		super();
	}
	
	

	public Formateur(Civilite civilite, String nom, String prenom, String email, boolean externe, int experience) {
		super(civilite, nom, prenom, email);
		this.externe = externe;
		this.experience = experience;
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
