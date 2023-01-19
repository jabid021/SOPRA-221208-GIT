package test;

import dao.DAOFiliere;
import dao.DAOMatiere;
import dao.DAOOrdinateur;
import dao.DAOStagiaire;

public class App {

	public static void main(String[] args) {

		DAOMatiere daoM=new DAOMatiere();
		DAOStagiaire daoS=new DAOStagiaire();
		DAOOrdinateur daoO=new DAOOrdinateur();
		DAOFiliere daoF=new DAOFiliere();
		
		/*Filiere f1 = new Filiere("DIS-400-SOPRA-JAVA",LocalDate.parse("2022-01-01"),LocalDate.parse("2020-05-01"));
		//daoF.insert(f1);
		f1=daoF.findById(1);
		
		Matiere m1 = new Matiere("UML",7829);
		//daoM.insert(m1);
		m1=daoM.findById(1);
		
		Stagiaire s1 = new Stagiaire("Titi","Toto","toto@gmail.com",f1);
		//daoS.insert(s1);
		s1=daoS.findById(1);
		
		
		Ordinateur o1 = new Ordinateur("Apple", 32, s1);
		//daoO.insert(o1);
		o1=daoO.findById(1);
		
		
		System.out.println("////INSERT//////");
		
		
		System.out.println(daoF.findById(1));
		System.out.println(daoM.findById(1));
		System.out.println(daoS.findById(1));
		System.out.println(daoO.findById(1));
		
		
		f1.setLibelle("test");
		f1.setFin(LocalDate.now());
		daoF.update(f1);
		
		m1.setLibelle("test");
		m1.setQuest(1234);
		daoM.update(m1);
		
		s1.setPrenom("test");
		s1.setNom("test");
		s1.setEmail("test");
		daoS.update(s1);
		
		o1.setMarque("test");
		o1.setRam(50);
		daoO.update(o1);
		
		System.out.println("////UPDATE//////");
		
	
		
		
		
		daoM.delete(1);
		daoO.delete(1);
		daoS.delete(1);
		daoF.delete(1);
		
		*/
		
	}

}
