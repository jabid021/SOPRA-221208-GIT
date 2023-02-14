package hopital.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Secretaire")
public class Secretaire extends Compte {

	public Secretaire() {
	}

	public Secretaire(String mail, String password) {
		super(mail, password);

	}


}
