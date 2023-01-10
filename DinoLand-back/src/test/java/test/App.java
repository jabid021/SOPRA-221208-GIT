package test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import dao.DAOAnimation;
import dao.DAOCompte;
import dao.DAOParcelle;
import dao.DAOReservation;
import model.Admin;
import model.Adresse;
import model.Animation;
import model.Attraction;
import model.Client;
import model.Compte;
import model.Dinosaure;
import model.Employe;
import model.Espece;
import model.Metier;
import model.Parcelle;
import model.Reservation;
import model.Zone;

public class App {
	

	
	//----------------------MENUS---------------------//


	static DAOCompte daoC = new DAOCompte();
	static DAOAnimation daoA = new DAOAnimation();
	static DAOParcelle daoP = new DAOParcelle();
	static DAOReservation daoR = new DAOReservation();
	static Compte connected;



	public static String saisieString(String msg) 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println(msg); 
		return sc.nextLine();
	}

	public static int saisieInt(String msg) 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println(msg); 
		return sc.nextInt();
	}

	public static double saisieDouble(String msg) 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println(msg); 
		return sc.nextDouble();
	}


	public static boolean saisieBoolean(String msg) 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println(msg); 
		return sc.nextBoolean();
	}






	public static void menuPrincipal() {
		System.out.println("\nAppli DINO-LAND");
		System.out.println("1- Se connecter");
		System.out.println("2- Inscription");
		System.out.println("3- Stop");
		int choix = saisieInt("Choisir un menu :\n");

		switch(choix) 
		{
		case 1 : seConnecter();break;
		case 2 : inscription();break;
		case 3 : System.exit(0);break;
		}
		menuPrincipal(); 
	}



	public static void seConnecter() {
		String login=saisieString("Saisir votre login");
		String password = saisieString("Saisir votre password");

		connected=daoC.findByLoginAndPassword(login,password);

		if(connected instanceof Admin) 
		{
			menuAdmin();
		}
		else if(connected instanceof Client ) 
		{
			menuClient();
		}
		else if(connected instanceof Employe) 
		{
			menuEmploye();
		}
		else 
		{
			System.out.println("Identifiants invalides");
		}

	}





	public static void inscription() {
		//Faire saisir les informations du compte : login,password,nom,prenom
		//Demander si Admin / Client / Employe
		//Si Admin => adresse = null
		//Si Client => saisir adresse
		//Si Employe => saisir adresse + saisier metier + afficher liste des parcelles, saisir l'id de la parcelle 
		
		Compte compte;

		String login = saisieString("Saisir login");
		String password = saisieString("Saisir password");
		String nom = saisieString("Saisir nom");
		String prenom = saisieString("Saisir prenom");
		
		String type = saisieString("Type compte ? Admin / Client / Employe");
		
		if(type.equals("Admin")) 
		{
			compte = new Admin(login, password, nom, prenom, null);
		}
		else if(type.equals("Client"))
		{
			String numero = saisieString("Saisir numero");
			String voie = saisieString("Saisir voie");
			String ville = saisieString("Saisir ville");
			String cp = saisieString("Saisir cp");
			Adresse adresse = new Adresse(numero,voie,ville,cp);
			compte = new Client(login, password, nom, prenom, adresse);
		}
		else 
		{
			String numero = saisieString("Saisir numero");
			String voie = saisieString("Saisir voie");
			String ville = saisieString("Saisir ville");
			String cp = saisieString("Saisir cp");
			
			String choix = saisieString("Saisir un metier : "+Arrays.toString(Metier.values()));
			
			afficherParcelles();

			int idParcelle = saisieInt("Saisir l'id de la parcelle");
			Parcelle parcelle = daoP.findById(idParcelle);

			
			Adresse adresse = new Adresse(numero,voie,ville,cp);
			
			
			compte = new Employe(login, password, nom, prenom, adresse,Metier.valueOf(choix),parcelle);
		}
		

		
		daoC.insert(compte);

	}



	public static void menuAdmin() {
		System.out.println("\nMenu Admin :");
		System.out.println("1- Gestion des Comptes");
		System.out.println("2- Gestion des Parcelles");
		System.out.println("3- Gestion des Animations");
		System.out.println("4- Voir toutes les Reservations");
		System.out.println("5- Se deconnecter");

		int choix = saisieInt("Choisir menu");

		switch(choix) 
		{
		case 1 : menuComptes();break;
		case 2 : menuParcelles();break;
		case 3 : menuAnimations();break;
		case 4 : afficherReservations();break;
		case 5 : menuPrincipal();break;
		}
		menuAdmin();
	}


	public static void menuAnimations() {
		System.out.println("\nMenu Gestion des Animations :");
		System.out.println("1- Afficher les Animations");
		System.out.println("2- Ajouter une Animation");
		System.out.println("3- Retour menu Admin");

		int choix = saisieInt("Choisir menu");

		switch(choix) 
		{
		case 1 : afficherAnimations();break;
		case 2 : ajouterAnimation();break;
		case 3 : menuAdmin();break;
		}

		menuAnimations();
	}

	public static void afficherAnimations() {
		List<Animation> animations = daoA.findAll();
		if(animations.isEmpty()) 
		{
			System.out.println("Pas d'animations.");
		}

		for(Animation a : animations) 
		{
			System.out.println(a);
		}

	}



	public static void ajouterAnimation() {
		//Afficher liste des parcelles, saisir l'id de la parcelle 
		//Faire Saisir duree,places,prix, type animation (Attraction / Dinosaure)
		//Si Attraction => Saisir nom,active
		//Si Dinosaure => Saisir espece;

		Animation animation;
		
		afficherParcelles();

		int idParcelle = saisieInt("Saisir l'id de la parcelle");
		Parcelle parcelle = daoP.findById(idParcelle);

		int duree = saisieInt("Saisir duree");
		int places = saisieInt("Nombre de place max ?");
		double prix = saisieDouble("Saisir prix");
		String type = saisieString("Type animation ? Dinosaure / Attraction");
		
		if(type.equals("Dinosaure")) 
		{
			String choix = saisieString("Choisir espece : "+Arrays.toString(Espece.values()));
			animation = new Dinosaure(duree, places, prix, parcelle, Espece.valueOf(choix));
		}
		else 
		{
			String nom = saisieString("Saisir le nom");
			boolean active = saisieBoolean("Attraction active ?");
			animation = new Attraction(duree, places, prix, parcelle, nom, active);
		}
		

		daoA.insert(animation);

	}




	public static void menuParcelles() {
		System.out.println("\nMenu Gestion des Parcelles :");
		System.out.println("1- Afficher les Parcelles");
		System.out.println("2- Ajouter une Parcelle");
		System.out.println("3- Modifier une Parcelle");
		System.out.println("4- Retour menu Admin");

		int choix = saisieInt("Choisir menu");

		switch(choix) 
		{
		case 1 : afficherParcelles();break;
		case 2 : ajouterParcelle();break;
		case 3 : modifierParcelle();break;
		case 4 : menuAdmin();break;
		}

		menuParcelles();
	}



	public static void ajouterParcelle() {
		//Faire saisir superficie + zone
		int superficie = saisieInt("Saisir superficie");
		String choix = saisieString("Choisir zone : "+Arrays.toString(Zone.values()));
		
		Parcelle parcelle = new Parcelle(superficie, Zone.valueOf(choix));

		daoP.insert(parcelle);

	}

	public static void modifierParcelle() {
		afficherParcelles();
		int idParcelle = saisieInt("Saisir l'id de la parcelle à modifier ?");

		int superficie = saisieInt("Saisir superficie");
		String choix = saisieString("Choisir zone : "+Arrays.toString(Zone.values()));

		Parcelle parcelle = new Parcelle(idParcelle,superficie,Zone.valueOf(choix));

		daoP.update(parcelle);

	}


	public static void afficherParcelles() {
		List<Parcelle> parcelles = daoP.findAll();

		if(parcelles.isEmpty()) 
		{
			System.out.println("Pas de parcelle.");
		}

		for(Parcelle p : parcelles) 
		{
			System.out.println(p);
		}
	}



	public static void menuComptes() {
		System.out.println("\nMenu Gestion des Comptes :");
		System.out.println("1- Afficher les Comptes");
		System.out.println("2- Ajouter un Compte");
		System.out.println("3- Supprimer un Compte");
		System.out.println("4- Retour menu Admin");

		int choix = saisieInt("Choisir menu");

		switch(choix) 
		{
		case 1 : afficherComptes();break;
		case 2 : inscription();break;
		case 3 : supprimerCompte();break;
		case 4 : menuAdmin();break;
		}

		menuComptes();
	}


	public static void supprimerCompte() {
		afficherComptes();
		int idCompte = saisieInt("Saisir l'id du compte");

		daoC.delete(idCompte);

	}



	public static void afficherComptes() {
		List<Compte> comptes = daoC.findAll();
		if(comptes.isEmpty())
		{
			System.out.println("Pas de compte.");
		}

		for(Compte c : comptes) 
		{
			System.out.println(c);
		}
	}

	public static void afficherReservations() {
		List<Reservation> reservations = daoR.findAll();
		if(reservations.isEmpty()) 
		{
			System.out.println("Pas de reservation.");
		}

		for(Reservation r : reservations) 
		{
			System.out.println(r);
		}
	}

	public static void menuClient() {
		System.out.println("\nMenu Client :");
		System.out.println("1- Faire une reservation");
		System.out.println("2- Consulter mes reservations");
		System.out.println("3- Se deconnecter");

		int choix = saisieInt("Choisir menu");

		switch(choix) 
		{
		case 1 : faireReservation();break;
		case 2 : afficherMesReservations();break;
		case 3: menuPrincipal();break;
		}
		menuClient();

	}

	public static void afficherMesReservations() {
		List<Reservation> reservations = daoR.findAllByClient(connected.getId());
		if(reservations.isEmpty()) 
		{
			System.out.println("Vous n'avez pas de reservation");
		}

		for(Reservation r : reservations) 
		{
			System.out.println(r);
		}

	}


	public static void faireReservation() {
		afficherAnimations();
		int idAnimation = saisieInt("Choisir une animation");

		Animation animation = daoA.findById(idAnimation);

		int qte= saisieInt("Saisir le nombre de personne");
		String date = saisieString("Saisir la date de reservation");
		String heure = saisieString("Saisir l'heure");



		Reservation r1 = new Reservation(qte,LocalDate.parse(date),LocalTime.parse(heure),(Client) connected,animation);

		daoR.insert(r1);

	}

	public static void menuEmploye() {
		System.out.println("\nMenu Employe (EN COURS DE DEV....) :");
		System.out.println("1- Se deconnecter");

		int choix = saisieInt("Choisir menu");

		switch(choix) 
		{
		case 1 : menuPrincipal();break;
		}
		menuEmploye();
	}


	public static void main(String[] args) {
		menuPrincipal();
	}
}
