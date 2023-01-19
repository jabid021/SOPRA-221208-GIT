package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Filiere;
import model.Stagiaire;

public class DAOStagiaire implements IDAO<Stagiaire,Integer> {

	@Override
	public Stagiaire findById(Integer id) {
		Stagiaire stagiaire=null;
		DAOFiliere daoFiliere = new DAOFiliere();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url+port+bdd,login,password);

			PreparedStatement ps = conn.prepareStatement("SELECT * from stagiaire where id=?");
			ps.setInt(1, id);

			ResultSet rs =  ps.executeQuery();

			while(rs.next()) 
			{
				Filiere f = daoFiliere.findById(rs.getInt("filiere"));
				stagiaire = new Stagiaire(rs.getInt("id"),rs.getString("nom"),rs.getString("prenom"),rs.getString("email"),f);
			}

			ps.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return stagiaire;
	}

	@Override
	public List<Stagiaire> findAll() {
		Stagiaire stagiaire=null;
		List<Stagiaire> stagiaires=new ArrayList();
		
		DAOFiliere daoFiliere = new DAOFiliere();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url+port+bdd,login,password);

			PreparedStatement ps = conn.prepareStatement("SELECT * from stagiaire");

			ResultSet rs =  ps.executeQuery();

			while(rs.next()) 
			{
				Filiere f = daoFiliere.findById(rs.getInt("filiere"));
				stagiaire = new Stagiaire(rs.getInt("id"),rs.getString("nom"),rs.getString("prenom"),rs.getString("email"),f);
				stagiaires.add(stagiaire);
			}

			ps.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return stagiaires;
	}

	@Override
	public void insert(Stagiaire s) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url+port+bdd,login,password);

			PreparedStatement ps = conn.prepareStatement("INSERT INTO stagiaire (nom,prenom,email,filiere) VALUES (?,?,?,?)");
			ps.setString(1, s.getNom());
			ps.setString(2, s.getPrenom());
			ps.setString(3, s.getEmail());
			ps.setInt(4, s.getFiliere().getId());

			ps.executeUpdate();
			ps.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Stagiaire s) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url+port+bdd,login,password);

			PreparedStatement ps = conn.prepareStatement("UPDATE stagiaire set nom=?,prenom=?,email=?,filiere=? where id=?");
			ps.setString(1, s.getNom());
			ps.setString(2, s.getPrenom());
			ps.setString(3, s.getEmail());
			ps.setInt(4, s.getFiliere().getId());
			ps.setInt(5, s.getId());

			ps.executeUpdate();
			ps.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Integer id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url+port+bdd,login,password);

			PreparedStatement ps = conn.prepareStatement("DELETE from stagiaire where id=?");
			ps.setInt(1, id);

			ps.executeUpdate();
			ps.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
