package test;

import java.time.LocalDate;
import java.time.LocalTime;

import model.Admin;
import model.Adresse;
import model.Attraction;
import model.Client;
import model.Dinosaure;
import model.Employe;
import model.Espece;
import model.Metier;
import model.Parcelle;
import model.Reservation;
import model.Zone;

public class Test {

	public static void main(String[] args) {
		
		
		
		Adresse a1  = new Adresse("1bis","rue de paris","Paris","75009");
		Adresse a2  = new Adresse("12bis","rue de paris","Paris","75009");
		
		Parcelle parcelle1 = new Parcelle(5000,Zone.Voliere);
		
		
		Parcelle parcelle2 = new Parcelle(2000,Zone.Foret);
		
		Client client1 = new Client("client","client","Doe","John",a1);
		Admin admin = new Admin("admin","admin","nom","prenom",null);
		Employe emp1 = new Employe("emp","emp","Abid","Jordan",a2,Metier.Entretien,parcelle1);
		Employe emp2 = new Employe("emp","emp","Abid","Jeremy",a2,Metier.Ouvrier,parcelle1);
		Employe emp3 = new Employe("emp","emp","Toto","Titi",a2,Metier.Entretien,parcelle2);

		
		
		Attraction attraction1 = new Attraction(20,14,15.99,parcelle2,"SSJ2",true);
		
		//Pas besoin de parametre "stress" dans le constructeur, on init à 0
		Dinosaure dino1 = new Dinosaure(12,25,12.55,parcelle1,Espece.Pteranodon);
		
	
		Reservation r1 = new Reservation(4,LocalDate.parse("2023-01-02"),LocalTime.parse("15:00"),client1,attraction1);
		Reservation r2 = new Reservation(3,LocalDate.parse("2023-01-02"),LocalTime.parse("16:00"),client1,dino1);

	}

}
