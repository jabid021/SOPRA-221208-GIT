package quest.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "classroom")
@IdClass(SalleId.class)
public class Salle {
	@Id
	@Column(name = "name", length = 100)
	private String nom;
	@Id
	@Column(name = "floor")
	private int etage;
	@Column(name = "capacity")
	private int capacite;
	@Column(name = "video_projector")
	private boolean videoProjecteur;
	@Embedded
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
