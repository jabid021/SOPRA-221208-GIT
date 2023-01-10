package model;

public class Attraction extends Animation {
	
	private String nom;
	private boolean active;
	
	
	public Attraction(Integer id, int duree, int places, double prix,Parcelle parcelle, String nom, boolean active) {
		super(id, duree, places, prix,parcelle);
		this.nom = nom;
		this.active = active;
	}
	
	public Attraction(int duree, int places, double prix,Parcelle parcelle, String nom, boolean active) {
		super(duree, places, prix,parcelle);
		this.nom = nom;
		this.active = active;
	}

	public String getNom() {
		return nom;
	}

	public boolean isActive() {
		return active;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Attraction [id=" + id + ", duree=" + duree + ", places=" + places + ", prix=" + prix + ", parcelle="
				+ parcelle + ", nom=" + nom + ", active=" + active + "]";
	}


	
}
