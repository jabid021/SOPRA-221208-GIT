package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Admin;
import model.Adresse;
import model.Client;
import model.Compte;
import model.Employe;
import model.Metier;
import model.Parcelle;

public class DAOCompte implements IDAO<Compte,Integer>{

	@Override
	public Compte findById(Integer id) {
		DAOParcelle daoP = new DAOParcelle();
		Compte c = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn = DriverManager.getConnection(urlBdd, loginBdd,passwordBdd);

			PreparedStatement ps = conn.prepareStatement("SELECT * FROM compte where id=? ");
			ps.setInt(1, id);

			ResultSet rs =   ps.executeQuery();
			while(rs.next()) {

				if(rs.getString("type_compte").equals("Admin")) {
					c= new Admin(rs.getString("login"),rs.getString("password"),rs.getString("nom"),rs.getString("prenom"),null);
				}
				else if(rs.getString("type_compte").equals("Employe")) 
				{

					Adresse a = new Adresse(rs.getString("numero"),rs.getString("voie"),rs.getString("ville"),rs.getString("cp"));
					Parcelle p = daoP.findById(rs.getInt("parcelle"));
					c = new Employe(rs.getInt("id"),rs.getString("login"),rs.getString("password"),rs.getString("nom"),rs.getString("prenom"),a,Metier.valueOf(rs.getString("metier")),p);
				}
				else 
				{
					Adresse a = new Adresse(rs.getString("numero"),rs.getString("voie"),rs.getString("ville"),rs.getString("cp"));
					c= new Client(rs.getInt("id"),rs.getString("login"),rs.getString("password"),rs.getString("nom"),rs.getString("prenom"),a);
				}

			}

			rs.close();
			ps.close();
			conn.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return c;
	}

	@Override
	public List<Compte> findAll() {
		DAOParcelle daoP = new DAOParcelle();
		List<Compte> comptes = new ArrayList();
		Compte c = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn = DriverManager.getConnection(urlBdd, loginBdd,passwordBdd);

			PreparedStatement ps = conn.prepareStatement("SELECT * FROM compte ");


			ResultSet rs =   ps.executeQuery();
			while(rs.next()) {

				if(rs.getString("type_compte").equals("Admin")) {
					c= new Admin(rs.getInt("id"),rs.getString("login"),rs.getString("password"),rs.getString("nom"),rs.getString("prenom"),null);
				}
				else if(rs.getString("type_compte").equals("Employe")) 
				{

					Adresse a = new Adresse(rs.getString("numero"),rs.getString("voie"),rs.getString("ville"),rs.getString("cp"));
					Parcelle p = daoP.findById(rs.getInt("parcelle"));
					c = new Employe(rs.getInt("id"),rs.getString("login"),rs.getString("password"),rs.getString("nom"),rs.getString("prenom"),a,Metier.valueOf(rs.getString("metier")),p);
				}
				else 
				{
					Adresse a = new Adresse(rs.getString("numero"),rs.getString("voie"),rs.getString("ville"),rs.getString("cp"));
					c= new Client(rs.getInt("id"),rs.getString("login"),rs.getString("password"),rs.getString("nom"),rs.getString("prenom"),a);
				}
				comptes.add(c);
			}

			rs.close();
			ps.close();
			conn.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return comptes;
	}

	@Override
	public void insert(Compte c) {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn = DriverManager.getConnection(urlBdd, loginBdd,passwordBdd);

			PreparedStatement ps = conn.prepareStatement("INSERT INTO compte (login,password,nom,prenom,numero,voie,ville,cp,metier,parcelle,type_compte) VALUES(?,?,?,?,?,?,?,?,?,?,?)");

			ps.setString(1,c.getLogin());
			ps.setString(2,c.getPassword());
			ps.setString(3,c.getNom());
			ps.setString(4,c.getPrenom());
			if(c instanceof Admin) 
			{
				ps.setString(5, null);
				ps.setString(6, null);
				ps.setString(7, null);
				ps.setString(8, null);
				ps.setString(9, null);
				ps.setObject(10, null);
			}
			else 
			{
				ps.setString(5, c.getAdresse().getNumero());
				ps.setString(6, c.getAdresse().getVoie());
				ps.setString(7, c.getAdresse().getVille());
				ps.setString(8, c.getAdresse().getCp());
				if(c instanceof Employe) 
				{
					ps.setString(9, ((Employe) c).getMetier().toString());
					ps.setInt(10, ((Employe) c).getParcelle().getId());
				}
				else 
				{
					ps.setString(9, null);
					ps.setObject(10, null);
				}
			}

			ps.setString(11, c.getClass().getSimpleName());

			ps.executeUpdate();


			ps.close();
			conn.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	@Override
	public void update(Compte o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn = DriverManager.getConnection(urlBdd, loginBdd,passwordBdd);

			PreparedStatement ps = conn.prepareStatement("DELETE from compte where id=?");

			ps.setInt(1,id);
			ps.executeUpdate();


			ps.close();
			conn.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}

	
	
	public Compte findByLoginAndPassword(String login, String password) {
		DAOParcelle daoP = new DAOParcelle();
		Compte c = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn = DriverManager.getConnection(urlBdd, loginBdd,passwordBdd);

			PreparedStatement ps = conn.prepareStatement("SELECT * FROM compte where login=? and password=?");
			ps.setString(1,login);
			ps.setString(2, password);

			ResultSet rs =   ps.executeQuery();
			while(rs.next()) {

				if(rs.getString("type_compte").equals("Admin")) {
					c= new Admin(rs.getInt("id"),rs.getString("login"),rs.getString("password"),rs.getString("nom"),rs.getString("prenom"),null);
				}
				else if(rs.getString("type_compte").equals("Employe")) 
				{

					Adresse a = new Adresse(rs.getString("numero"),rs.getString("voie"),rs.getString("ville"),rs.getString("cp"));
					Parcelle p = daoP.findById(rs.getInt("parcelle"));
					c = new Employe(rs.getInt("id"),rs.getString("login"),rs.getString("password"),rs.getString("nom"),rs.getString("prenom"),a,Metier.valueOf(rs.getString("metier")),p);
				}
				else 
				{
					Adresse a = new Adresse(rs.getString("numero"),rs.getString("voie"),rs.getString("ville"),rs.getString("cp"));
					c= new Client(rs.getInt("id"),rs.getString("login"),rs.getString("password"),rs.getString("nom"),rs.getString("prenom"),a);
				}
			}

			rs.close();
			ps.close();
			conn.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return c;
	}

	
}
