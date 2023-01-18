package context;

import java.util.LinkedList;

import dao.IDAOCompte;
import dao.IDAOPatient;
import dao.IDAOVisite;
import dao.jdbc.DAOCompteJDBC;
import dao.jdbc.DAOPatientJDBC;
import dao.jdbc.DAOVisiteJDBC;
import model.Patient;

public class Singleton {

	private IDAOCompte daoCompte = new DAOCompteJDBC();
	private IDAOVisite daoVisite = new DAOVisiteJDBC();
	private IDAOPatient daoPatient = new DAOPatientJDBC();
	private LinkedList<Patient> fileAttente = new LinkedList();
	
	
	// On définit un champ privé et statique qui contiendra notre instance
	private static Singleton instance;
	
	// On déclare le constructeur privé pour empêcher la création d'une instance en dehors de la classe
	private Singleton (){}
	
	
	
	 // On définit une méthode publique et statique qui nous retournera l'instance unique
	public static Singleton getInstance() 
	{
		if(instance==null) 
		{
			instance=new Singleton();
		}
		return instance;
	}
	
	public LinkedList<Patient> getFileAttente() {
		return fileAttente;
	}


	


	public IDAOCompte getDaoCompte() {
		return daoCompte;
	}



	public IDAOVisite getDaoVisite() {
		return daoVisite;
	}



	public IDAOPatient getDaoPatient() {
		return daoPatient;
	}



	public void setDaoCompte(IDAOCompte daoCompte) {
		this.daoCompte = daoCompte;
	}



	public void setDaoVisite(IDAOVisite daoVisite) {
		this.daoVisite = daoVisite;
	}



	public void setDaoPatient(IDAOPatient daoPatient) {
		this.daoPatient = daoPatient;
	}



	public void setFileAttente(LinkedList<Patient> fileAttente) {
		this.fileAttente = fileAttente;
	}
	
	
	
	

}
