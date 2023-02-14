package hopital.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("Medecin")
public class Medecin extends Compte {

	@OneToMany(mappedBy = "medecin")
	private List<Visite> visites = new ArrayList<>();

	private transient int salle;

	public Medecin() {
	}

	public Medecin(String mail, String password) {
		super(mail, password);

	}

	public List<Visite> getVisites() {
		return visites;
	}

	public void setVisites(List<Visite> visites) {
		this.visites = visites;
	}

	public int getSalle() {
		return salle;
	}

	public void setSalle(int salle) {
		this.salle = salle;
	}

}
