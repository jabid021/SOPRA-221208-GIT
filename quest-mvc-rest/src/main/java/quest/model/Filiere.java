package quest.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
import javax.persistence.Version;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "cursus")
public class Filiere {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(Views.ViewBase.class)
	private Integer id;
	@Version
	@JsonView(Views.ViewBase.class)
	private int version;
	@Column(name = "label", length = 255)
	@JsonView(Views.ViewBase.class)
	private String libelle;
	@Column(name = "start")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonView(Views.ViewBase.class)
	private LocalDate debut;
	@Column(name = "end")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonView(Views.ViewBase.class)
	private LocalDate fin;
	@ManyToMany
	@JoinTable(name = "cursus_trainee", uniqueConstraints = @UniqueConstraint(columnNames = { "cursus_id",
			"trainee_id" }), joinColumns = @JoinColumn(name = "cursus_id"), inverseJoinColumns = @JoinColumn(name = "trainee_id"))
	@JsonView(Views.ViewFiliereWithStagiaires.class)
	private Set<Stagiaire> stagiaires = new HashSet<>();
	@ManyToOne
	@JoinColumn(name = "referent_id")
	@JsonView(Views.ViewFiliere.class)
	private Formateur referent;
	@OneToMany(mappedBy = "filiere")
	private Set<Matiere> matieres = new HashSet<>();
	@OneToOne
	@JoinColumns({ @JoinColumn(name = "classroom_name", referencedColumnName = "name"),
			@JoinColumn(name = "classroom_floor", referencedColumnName = "floor") })
	private Salle salle;

	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "customer_name", referencedColumnName = "name"),
			@JoinColumn(name = "customer_type", referencedColumnName = "type") })
	@JsonIgnore
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

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
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

	public Set<Stagiaire> getStagiaires() {
		return stagiaires;
	}

	public void setStagiaires(Set<Stagiaire> stagiaires) {
		this.stagiaires = stagiaires;
	}

	public Formateur getReferent() {
		return referent;
	}

	public void setReferent(Formateur referent) {
		this.referent = referent;
	}

	public Set<Matiere> getMatieres() {
		return matieres;
	}

	public void setMatieres(Set<Matiere> matieres) {
		this.matieres = matieres;
	}

	public Salle getSalle() {
		return salle;
	}

	public void setSalle(Salle salle) {
		this.salle = salle;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Filiere other = (Filiere) obj;
		return Objects.equals(id, other.id);
	}

}
