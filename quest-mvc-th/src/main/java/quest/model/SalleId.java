package quest.model;

import java.io.Serializable;
import java.util.Objects;

public class SalleId implements Serializable {
	private static final long serialVersionUID = 1L;
	private String nom;
	private int etage;

	public SalleId() {
		super();
	}

	public SalleId(String nom, int etage) {
		super();
		this.nom = nom;
		this.etage = etage;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getEtage() {
		return etage;
	}

	public void setEtage(int etage) {
		this.etage = etage;
	}

	@Override
	public int hashCode() {
		return Objects.hash(etage, nom);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SalleId other = (SalleId) obj;
		return etage == other.etage && Objects.equals(nom, other.nom);
	}

}
