package quest.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("stagiaire")
public class Stagiaire extends Personne {

	@Column(name = "birthdate")
	private LocalDate dtNaissance;
	@Column(name = "grade", length = 6)
	@Enumerated(EnumType.STRING)
	private NiveauEtude niveauEtude;
	@ManyToMany(mappedBy = "stagiaires")
	private List<Filiere> filieres = new ArrayList<>();
	@OneToOne
	@JoinColumn(name = "computer_id")
	private Ordinateur ordinateur;

	public Stagiaire() {
		super();
	}

	public Stagiaire(Civilite civilite, String nom, String prenom, String email, LocalDate dtNaissance, NiveauEtude niveauEtude) {
		super(civilite, nom, prenom, email);
		this.dtNaissance = dtNaissance;
		this.niveauEtude = niveauEtude;
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

	public List<Filiere> getFilieres() {
		return filieres;
	}

	public void setFilieres(List<Filiere> filieres) {
		this.filieres = filieres;
	}

	public Ordinateur getOrdinateur() {
		return ordinateur;
	}

	public void setOrdinateur(Ordinateur ordinateur) {
		this.ordinateur = ordinateur;
	}

}
