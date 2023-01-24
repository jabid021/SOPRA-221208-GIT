package quest.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "cursus")
public class Filiere {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "label", length = 255)
	private String libelle;
	@Column(name = "start")
	private LocalDate debut;
	@Column(name = "end")
	private LocalDate fin;
	@ManyToMany
	@JoinTable(name = "cursus_trainee", uniqueConstraints = @UniqueConstraint(columnNames = { "cursus_id",
			"trainee_id" }), joinColumns = @JoinColumn(name = "cursus_id"), inverseJoinColumns = @JoinColumn(name = "trainee_id"))
	private List<Stagiaire> stagiaires = new ArrayList<>();
	@ManyToOne
	@JoinColumn(name = "referent_id")
	private Formateur referent;
	@OneToMany(mappedBy = "filiere")
	private List<Matiere> matieres = new ArrayList<>();
	@OneToOne
	@JoinColumns({ @JoinColumn(name = "classroom_name", referencedColumnName = "name"),
			@JoinColumn(name = "classroom_floor", referencedColumnName = "floor") })
	private Salle salle;

	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "customer_name", referencedColumnName = "name"),
			@JoinColumn(name = "customer_type", referencedColumnName = "type") })
	private Client client;

	public Filiere() {
		super();
	}

	public Filiere(String libelle, LocalDate debut, LocalDate fin) {
		super();
		this.libelle = libelle;
		this.debut = debut;
		this.fin = fin;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public LocalDate getDebut() {
		return debut;
	}

	public void setDebut(LocalDate debut) {
		this.debut = debut;
	}

	public LocalDate getFin() {
		return fin;
	}

	public void setFin(LocalDate fin) {
		this.fin = fin;
	}

	public List<Stagiaire> getStagiaires() {
		return stagiaires;
	}

	public void setStagiaires(List<Stagiaire> stagiaires) {
		this.stagiaires = stagiaires;
	}

	public Formateur getReferent() {
		return referent;
	}

	public void setReferent(Formateur referent) {
		this.referent = referent;
	}

	public List<Matiere> getMatieres() {
		return matieres;
	}

	public void setMatieres(List<Matiere> matieres) {
		this.matieres = matieres;
	}

	public Salle getSalle() {
		return salle;
	}

	public void setSalle(Salle salle) {
		this.salle = salle;
	}

}
