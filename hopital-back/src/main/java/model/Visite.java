package model;

import java.time.LocalDate;

public class Visite {

	
	private Integer id;
	private Patient patient;
	private Medecin medecin;
	private double prix=20;
	private int salle;
	private LocalDate dateVisite;
	
	
	
	
	public Visite(Integer id, Patient patient, Medecin medecin, double prix, int salle, LocalDate dateVisite) {
		this.id = id;
		this.patient = patient;
		this.medecin = medecin;
		this.prix = prix;
		this.salle = salle;
		this.dateVisite = dateVisite;
	}

	public Visite(Patient patient, Medecin medecin) {
		this.patient = patient;
		this.medecin = medecin;
		this.salle=medecin.getSalle();
		this.dateVisite=LocalDate.now();	
	}



	public Integer getId() {
		return id;
	}




	public Patient getPatient() {
		return patient;
	}




	public Medecin getMedecin() {
		return medecin;
	}




	public double getPrix() {
		return prix;
	}




	public int getSalle() {
		return salle;
	}




	public LocalDate getDateVisite() {
		return dateVisite;
	}




	public void setId(Integer id) {
		this.id = id;
	}




	public void setPatient(Patient patient) {
		this.patient = patient;
	}




	public void setMedecin(Medecin medecin) {
		this.medecin = medecin;
	}




	public void setPrix(double prix) {
		this.prix = prix;
	}




	public void setSalle(int salle) {
		this.salle = salle;
	}




	public void setDateVisite(LocalDate dateVisite) {
		this.dateVisite = dateVisite;
	}




	@Override
	public String toString() {
		return "Visite [id=" + id + ", patient=" + patient + ", medecin=" + medecin.getId() + ", prix=" + prix + ", salle="
				+ salle + ", dateVisite=" + dateVisite + "]";
	}
	
	
	
}
