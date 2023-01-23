package quest.model;

import java.time.LocalDate;

import javax.persistence.Transient;

public class Stagiaire extends Personne {

	private LocalDate dtNaissance;
	private NiveauEtude niveauEtude;
	@Transient
	private Filiere filiere;
	@Transient
	private Ordinateur ordinateur;

	public Stagiaire() {
		super();
	}

	public LocalDate getDtNaissance() {
		return dtNaissance;
	}

	public void setDtNaissance(LocalDate dtNaissance) {
		this.dtNaissance = dtNaissance;
	}

	public NiveauEtude getNiveauEtude() {
		return niveauEtude;
	}

	public void setNiveauEtude(NiveauEtude niveauEtude) {
		this.niveauEtude = niveauEtude;
	}

	public Filiere getFiliere() {
		return filiere;
	}

	public void setFiliere(Filiere filiere) {
		this.filiere = filiere;
	}

	public Ordinateur getOrdinateur() {
		return ordinateur;
	}

	public void setOrdinateur(Ordinateur ordinateur) {
		this.ordinateur = ordinateur;
	}

}
