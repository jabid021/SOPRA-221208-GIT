package model;

public class Employe extends Compte{

	private Metier metier;
	private Parcelle parcelle;

	public Employe(Integer id, String login, String password, String nom, String prenom, Adresse adresse,
			Metier metier,Parcelle parcelle) {
		super(id, login, password, nom, prenom, adresse);
		this.metier = metier;
		this.parcelle=parcelle;
	}

	public Employe(String login, String password, String nom, String prenom, Adresse adresse,
			Metier metier,Parcelle parcelle) {
		super(login, password, nom, prenom, adresse);
		this.metier = metier;
		this.parcelle=parcelle;
	}

	public Metier getMetier() {
		return metier;
	}

	public void setMetier(Metier metier) {
		this.metier = metier;
	}

	public Parcelle getParcelle() {
		return parcelle;
	}

	public void setParcelle(Parcelle parcelle) {
		this.parcelle = parcelle;
	}

	@Override
	public String toString() {
		return "Employe [id=" + id + ", login=" + login + ", password=" + password + ", nom=" + nom + ", prenom="
				+ prenom + ", adresse=" + adresse + ", metier=" + metier + ", parcelle=" + parcelle + "]";
	}


}
