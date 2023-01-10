package model;

public class Admin extends Compte {

	public Admin(Integer id, String login, String password, String nom, String prenom, Adresse adresse) {
		super(id, login, password, nom, prenom, adresse);
	}

	public Admin(String login, String password, String nom, String prenom, Adresse adresse) {
		super(login, password, nom, prenom, adresse);
	}

	@Override
	public String toString() {
		return "Admin [id=" + id + ", login=" + login + ", password=" + password + ", nom=" + nom + ", prenom=" + prenom
				+ ", adresse=" + adresse + "]";
	}


}
