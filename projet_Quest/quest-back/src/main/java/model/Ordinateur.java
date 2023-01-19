package model;

public class Ordinateur {

	private Integer id;
	private String marque;
	private int ram;
	private Stagiaire stagiaire;
	
	
	public Ordinateur(Integer id, String marque, int ram, Stagiaire stagiaire) {
		this.id = id;
		this.marque = marque;
		this.ram = ram;
		this.stagiaire = stagiaire;
	}
	
	public Ordinateur(String marque, int ram, Stagiaire stagiaire) {
		this.marque = marque;
		this.ram = ram;
		this.stagiaire = stagiaire;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public int getRam() {
		return ram;
	}

	public void setRam(int ram) {
		this.ram = ram;
	}

	public Stagiaire getStagiaire() {
		return stagiaire;
	}

	public void setStagiaire(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
	}

	@Override
	public String toString() {
		return "Ordinateur [id=" + id + ", marque=" + marque + ", ram=" + ram + ", stagiaire=" + stagiaire + "]";
	}
	
	
	
}
