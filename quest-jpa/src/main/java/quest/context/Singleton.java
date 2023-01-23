package quest.context;

import quest.dao.DAOFiliere;
import quest.dao.DAOMatiere;
import quest.dao.DAOOrdinateur;
import quest.dao.DAOStagiaire;

public class Singleton {

	private DAOMatiere daoMatiere = new DAOMatiere();
	private DAOOrdinateur daoOrdinateur = new DAOOrdinateur();
	private DAOStagiaire daoStagiaire = new DAOStagiaire();
	private DAOFiliere daoFiliere = new DAOFiliere();
	private static Singleton instance;
	
	
	
	public static Singleton getInstance() 
	{
		if(instance==null) 
		{
			instance=new Singleton();
		}
		return instance;
	}
	
	
	
	public DAOMatiere getDaoMatiere() {
		return daoMatiere;
	}
	public void setDaoMatiere(DAOMatiere daoMatiere) {
		this.daoMatiere = daoMatiere;
	}
	public DAOOrdinateur getDaoOrdinateur() {
		return daoOrdinateur;
	}
	public void setDaoOrdinateur(DAOOrdinateur daoOrdinateur) {
		this.daoOrdinateur = daoOrdinateur;
	}
	public DAOStagiaire getDaoStagiaire() {
		return daoStagiaire;
	}
	public void setDaoStagiaire(DAOStagiaire daoStagiaire) {
		this.daoStagiaire = daoStagiaire;
	}
	public DAOFiliere getDaoFiliere() {
		return daoFiliere;
	}
	public void setDaoFiliere(DAOFiliere daoFiliere) {
		this.daoFiliere = daoFiliere;
	}
	
	
}
