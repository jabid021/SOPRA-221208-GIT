package quest.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "classroom")
@IdClass(SalleId.class)
public class Salle {
	@Id
	@Column(name = "name", length = 100)
	@JsonView(Views.ViewBase.class)
	private String nom;
	@Id
	@Column(name = "floor")
	@JsonView(Views.ViewBase.class)
	private int etage;
	@Version
	@JsonView(Views.ViewBase.class)
	private int version;
	@Column(name = "capacity")
	@JsonView(Views.ViewBase.class)
	private int capacite;
	@Column(name = "video_projector")
	@JsonView(Views.ViewBase.class)
	private boolean videoProjecteur;
	@Embedded
	@JsonView(Views.ViewBase.class)
	private Adresse adr;

	public Salle() {
		super();
	}

	public Salle(String nom, int etage, int capacite, boolean videoProjecteur) {
		super();
		this.nom = nom;
		this.etage = etage;
		this.capacite = capacite;
		this.videoProjecteur = videoProjecteur;
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

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getCapacite() {
		return capacite;
	}

	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}

	public boolean isVideoProjecteur() {
		return videoProjecteur;
	}

	public void setVideoProjecteur(boolean videoProjecteur) {
		this.videoProjecteur = videoProjecteur;
	}

	public Adresse getAdr() {
		return adr;
	}

	public void setAdr(Adresse adr) {
		this.adr = adr;
	}

}
