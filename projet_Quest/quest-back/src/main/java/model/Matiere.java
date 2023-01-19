package model;

public class Matiere {

	private Integer id;
	private String libelle;
	private int quest;
	
	public Matiere(Integer id, String libelle, int quest) {
		this.id = id;
		this.libelle = libelle;
		this.quest = quest;
	}
	
	
	public Matiere(String libelle, int quest) {
		this.libelle = libelle;
		this.quest = quest;
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


	public int getQuest() {
		return quest;
	}


	public void setQuest(int quest) {
		this.quest = quest;
	}


	@Override
	public String toString() {
		return "Matiere [id=" + id + ", libelle=" + libelle + ", quest=" + quest + "]";
	}
	
	
	
}
