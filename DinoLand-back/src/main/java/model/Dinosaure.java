package model;

public class Dinosaure extends Animation{
	
	private int stress;
	private Espece espece;
	
	public Dinosaure(Integer id, int duree, int places, double prix,Parcelle parcelle, int stress, Espece espece) {
		super(id, duree, places, prix,parcelle);
		this.stress = stress;
		this.espece = espece;
	}
	
	public Dinosaure(int duree, int places, double prix, Parcelle parcelle,Espece espece) {
		super(duree, places, prix,parcelle);
		this.stress = 0;
		this.espece = espece;
	}

	public int getStress() {
		return stress;
	}

	public Espece getEspece() {
		return espece;
	}

	public void setStress(int stress) {
		this.stress = stress;
	}

	public void setEspece(Espece espece) {
		this.espece = espece;
	}

	@Override
	public String toString() {
		return "Dinosaure [id=" + id + ", duree=" + duree + ", places=" + places + ", prix=" + prix + ", parcelle="
				+ parcelle + ", stress=" + stress + ", espece=" + espece + "]";
	}

	

	
}
