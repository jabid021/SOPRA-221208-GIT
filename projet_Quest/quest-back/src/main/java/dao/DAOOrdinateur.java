package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Ordinateur;
import model.Stagiaire;

public class DAOOrdinateur implements IDAO<Ordinateur,Integer> {

	@Override
	public Ordinateur findById(Integer id) {
		Ordinateur ordinateur=null;
		DAOStagiaire daoStagiaire=new DAOStagiaire();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url+port+bdd,login,password);

			PreparedStatement ps = conn.prepareStatement("SELECT * from ordinateur where id=?");
			ps.setInt(1, id);

			ResultSet rs =  ps.executeQuery();

			while(rs.next()) 
			{
				Stagiaire s = daoStagiaire.findById(rs.getInt("stagiaire"));
				ordinateur = new Ordinateur(rs.getInt("id"),rs.getString("marque"),rs.getInt("ram"),s);
			}

			ps.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ordinateur;
	}

	@Override
	public List<Ordinateur> findAll() {
		Ordinateur ordinateur=null;
		List<Ordinateur> ordinateurs=new ArrayList();
		
		DAOStagiaire daoStagiaire=new DAOStagiaire();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url+port+bdd,login,password);

			PreparedStatement ps = conn.prepareStatement("SELECT * from ordinateur");

			ResultSet rs =  ps.executeQuery();

			while(rs.next()) 
			{
				Stagiaire s = daoStagiaire.findById(rs.getInt("stagiaire"));
				ordinateur = new Ordinateur(rs.getInt("id"),rs.getString("marque"),rs.getInt("ram"),s);
				ordinateurs.add(ordinateur);
			}

			ps.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ordinateurs;
	}

	@Override
	public void insert(Ordinateur o) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url+port+bdd,login,password);

			PreparedStatement ps = conn.prepareStatement("INSERT INTO ordinateur (marque,ram,stagiaire) VALUES (?,?,?)");
			ps.setString(1, o.getMarque());
			ps.setInt(2, o.getRam());
			ps.setInt(3, o.getStagiaire().getId());

			ps.executeUpdate();
			ps.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Ordinateur o) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url+port+bdd,login,password);

			PreparedStatement ps = conn.prepareStatement("UPDATE ordinateur set marque=?,ram=?,stagiaire=? where id=?");
			ps.setString(1, o.getMarque());
			ps.setInt(2, o.getRam());
			ps.setInt(3, o.getStagiaire().getId());
			ps.setInt(4, o.getId());

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

			PreparedStatement ps = conn.prepareStatement("DELETE from ordinateur where id=?");
			ps.setInt(1, id);

			ps.executeUpdate();
			ps.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
