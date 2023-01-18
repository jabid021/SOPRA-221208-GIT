package dao;

import model.Compte;

public interface IDAOCompte extends IDAO<Compte,Integer> {

	public Compte findByLoginAndPassword(String login,String password);
}
