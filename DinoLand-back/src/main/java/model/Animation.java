package model;

public abstract class Animation {

	protected Integer id;
	protected int duree;
	protected int  places;
	protected double prix;
	protected Parcelle parcelle;
	
	public Animation(Integer id, int duree, int places, double prix,Parcelle parcelle) {
		this.id = id;
		this.duree = duree;
		this.places = places;
		this.prix = prix;
		this.parcelle=parcelle;
	}
	
	public Animation(int duree, int places, double prix,Parcelle parcelle) {
		this.duree = duree;
		this.places = places;
		this.prix = prix;
		this.parcelle=parcelle;
	}
	public Integer getId() {
		return id;
	}
	public int getDuree() {
		return duree;
	}
	public int getPlaces() {
		return places;
	}
	public double getPrix() {
		return prix;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setDuree(int duree) {
		this.duree = duree;
	}
	public void setPlaces(int places) {
		this.places = places;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}

	public Parcelle getParcelle() {
		return parcelle;
	}

	public void setParcelle(Parcelle parcelle) {
		this.parcelle = parcelle;
	}
	
	
}
