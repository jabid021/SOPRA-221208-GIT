package model;

public class Stagiaire {

	private Integer id;
	private String nom;
	private String prenom;
	private String email;
	private Filiere filiere;
	
	public Stagiaire(Integer id, String nom, String prenom, String email, Filiere filiere) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.filiere = filiere;
	}

	public Stagiaire(String nom, String prenom, String email, Filiere filiere) {
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.filiere = filiere;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Filiere getFiliere() {
		return filiere;
	}

	public void setFiliere(Filiere filiere) {
		this.filiere = filiere;
	}

	@Override
	public String toString() {
		return "Stagiaire [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", filiere="
				+ filiere + "]";
	}
	
	
}
