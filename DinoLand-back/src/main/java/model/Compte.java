package model;

public abstract class Compte {

	protected Integer id;
	protected String login;
	protected String password;
	protected String nom;
	protected String prenom;
	protected Adresse adresse;
	
	
	public Compte(Integer id, String login, String password, String nom, String prenom, Adresse adresse) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
	}


	public Compte(String login, String password, String nom, String prenom, Adresse adresse) {
		this.login = login;
		this.password = password;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
	}


	public Integer getId() {
		return id;
	}


	public String getLogin() {
		return login;
	}


	public String getPassword() {
		return password;
	}


	public String getNom() {
		return nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public Adresse getAdresse() {
		return adresse;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
	
	
	
}
