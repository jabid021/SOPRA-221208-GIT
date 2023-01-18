package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import context.Singleton;
import dao.IDAOCompte;
import dao.IDAOPatient;
import dao.IDAOVisite;
import model.Compte;
import model.Medecin;
import model.Patient;
import model.Secretaire;
import model.Visite;

public class App {

	static IDAOCompte daoC = Singleton.getInstance().getDaoCompte();
	static IDAOVisite daoV = Singleton.getInstance().getDaoVisite();
	static IDAOPatient daoP = Singleton.getInstance().getDaoPatient();
	static LinkedList<Patient> fileAttente = Singleton.getInstance().getFileAttente();

	static boolean enPause=false;
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
		System.out.println("\nAccueil :");
		System.out.println("1- Se connecter");
		System.out.println("2- Stop");

		int choix = saisieInt("Choisir un menu");
		switch(choix) 
		{
		case 1 : seConnecter();break;
		case 2 : System.exit(0);break;
		}

		menuPrincipal();
	}


	public static void seConnecter() {

		String login = saisieString("Saisir login");
		String password = saisieString("Saisir password");

		connected = daoC.findByLoginAndPassword(login, password);

		if(connected == null) 
		{
			System.out.println("Identifiants invalides");
		}
		else if(connected instanceof Medecin) 
		{
			int salle = saisieInt("Quelle salle ?");
			((Medecin) connected).setSalle(salle);
			menuMedecin();
		}
		else if(connected instanceof Secretaire)
		{
			if(enPause) 
			{
				menuSecretairePause();
			}
			else 
			{
				menuSecretaire();
			}
		}
	}

	public static void menuSecretaire() 
	{
		System.out.println("\nMenu Secretaire :");
		System.out.println("1- Recevoir un patient");
		System.out.println("2- Afficher la file d'attente");
		System.out.println("3- Afficher les visites d'un patient");
		System.out.println("4- Partir en pause");
		System.out.println("5- Se deconnecter");

		int choix = saisieInt("Choisir un menu");
		switch(choix) 
		{
		case 1 : recevoirPatient();break;
		case 2 : afficherFileAttente();break;
		case 3 : afficherVisitesPatient();break;
		case 4 : partirEnPause();break;
		case 5 : menuPrincipal();break;
		}

		menuSecretaire();
	}

	public static void menuSecretairePause() 
	{
		System.out.println("\nMenu Secretaire (Pause) :");
		System.out.println("1- Revenir de pause");
		System.out.println("2- Se deconnecter");

		int choix = saisieInt("Choisir un menu");
		switch(choix) 
		{
		case 1 : revenirDePause();break;
		case 2 : menuPrincipal();break;
		}

		menuSecretairePause();
	}


	public static void partirEnPause() {
		
		File f = new File("patients.txt");
		
		try {
		FileOutputStream fos = new FileOutputStream(f);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		oos.writeObject(fileAttente);
		fileAttente.clear();
		enPause=true;
		oos.close();
		fos.close();
		}catch(Exception e) {e.printStackTrace();}
		
		menuSecretairePause();
	}

	public static void revenirDePause() {
		File f = new File("patients.txt");
		
		try {
		FileInputStream fis = new FileInputStream(f);
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		fileAttente=(LinkedList<Patient>) ois.readObject();
		enPause=false;
		ois.close();
		fis.close();
		}catch(Exception e) {e.printStackTrace();}
		menuSecretaire();
	}

	public static void afficherVisitesPatient() {

		int idPatient = saisieInt("Saisir l'id du patient");
		List<Visite> visites = daoV.findAllByPatient(idPatient);

		if(visites.isEmpty()) 
		{
			System.out.println("Le patient "+idPatient+" n'a pas encore de visite sur l'appli");
		}

		for(Visite v : visites) 
		{
			System.out.println(v);
		}

	}

	public static void afficherFileAttente() {
		if(fileAttente.isEmpty()) 
		{
			System.out.println("Aucun patient dans la file d'attente");
		}

		for(Patient p : fileAttente) 
		{
			System.out.println(p);
		}
	}

	public static void recevoirPatient() {
		int idPatient = saisieInt("Saisir l'id du patient");

		Patient p = daoP.findById(idPatient);

		if(p==null) {
			String nom = saisieString("Saisir votre nom");
			String prenom = saisieString("Saisir votre prenom");

			p = new Patient(idPatient, nom, prenom);
			daoP.insert(p);
		}

		fileAttente.add(p);
		System.out.println("Le patient a rejoint la file d'attente");
	}



	public static void menuMedecin() 
	{
		System.out.println("\nMenu Medecin :");
		System.out.println("1- Faire une consultation");
		System.out.println("2- Afficher la file d'attente");
		System.out.println("3- Sauvegarder mes visites");
		System.out.println("4- Se deconnecter");

		int choix = saisieInt("Choisir un menu");
		switch(choix) 
		{
		case 1 : consultation();break;
		case 2 : afficherFileAttente();break;
		case 3 : sauvegarderVisites();break;
		case 4 : menuPrincipal();break;
		}

		menuMedecin();
	}

	public static void sauvegarderVisites() {
		Medecin m = (Medecin) connected;

		if(m.getVisites().isEmpty()) 
		{
			System.out.println("Pas de visite à save...");
		}

		else {
			for(Visite v : m.getVisites()) 
			{
				daoV.insert(v);
			}
			m.getVisites().clear();
		}
	}

	public static void consultation() {
		Medecin m = (Medecin) connected;
		if(fileAttente.isEmpty()) 
		{
			System.out.println("Aucun patient dans la file d'attente");
		}
		else 
		{
			Patient suivant = fileAttente.poll();
			System.out.println("Prochain patient : "+suivant);
			Visite v = new Visite(suivant, m);
			m.getVisites().add(v);
			if(m.getVisites().size()>=10) 
			{
				sauvegarderVisites();
			}
		}

	}

	public static void main(String[] args) {
		menuPrincipal();
	}
}
