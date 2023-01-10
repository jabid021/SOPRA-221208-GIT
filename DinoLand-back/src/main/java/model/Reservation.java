package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Reservation {

	private Integer id;
	private int qte;
	private double prixTotal;
	private LocalDate jour;
	private LocalTime heure;
	private Client client;
	private Animation animation;
	
	
	public Reservation(Integer id, int qte, double prixTotal, LocalDate jour, LocalTime heure, Client client,
			Animation animation) {
		this.id = id;
		this.qte = qte;
		this.prixTotal = prixTotal;
		this.jour = jour;
		this.heure = heure;
		this.client = client;
		this.animation = animation;
	}


	public Reservation(int qte, LocalDate jour, LocalTime heure, Client client, Animation animation) {
		this.qte = qte;
		this.prixTotal=animation.getPrix()*qte;
		this.jour = jour;
		this.heure = heure;
		this.client = client;
		this.animation = animation;
	}


	public Integer getId() {
		return id;
	}


	public int getQte() {
		return qte;
	}


	public double getPrixTotal() {
		return prixTotal;
	}


	public LocalDate getJour() {
		return jour;
	}


	public LocalTime getHeure() {
		return heure;
	}


	public Client getClient() {
		return client;
	}


	public Animation getAnimation() {
		return animation;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public void setQte(int qte) {
		this.qte = qte;
	}


	public void setPrixTotal(double prixTotal) {
		this.prixTotal = prixTotal;
	}


	public void setJour(LocalDate jour) {
		this.jour = jour;
	}


	public void setHeure(LocalTime heure) {
		this.heure = heure;
	}


	public void setClient(Client client) {
		this.client = client;
	}


	public void setAnimation(Animation animation) {
		this.animation = animation;
	}


	@Override
	public String toString() {
		return "Reservation [id=" + id + ", qte=" + qte + ", prixTotal=" + prixTotal + ", jour=" + jour + ", heure="
				+ heure + ", client=" + client + ", animation=" + animation + "]";
	}
	
	
	
	
	
	
}
