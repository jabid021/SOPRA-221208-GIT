package dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.IDAOCompte;
import model.Compte;
import model.Medecin;
import model.Secretaire;

public class DAOCompteJDBC implements IDAOCompte {

	@Override
	public Compte findById(Integer id) {
		Compte c = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn = DriverManager.getConnection(urlBdd, loginBdd,passwordBdd);

			PreparedStatement ps = conn.prepareStatement("SELECT * FROM compte where id=?");
			ps.setInt(1,id);

			ResultSet rs =   ps.executeQuery();
			while(rs.next()) {

				if(rs.getString("type_compte").equals("Secretaire")) {
					c= new Secretaire(rs.getInt("id"),rs.getString("login"),rs.getString("password"));
				}
				
				else 
				{
					c= new Medecin(rs.getInt("id"),rs.getString("login"),rs.getString("password"));
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
		List<Compte> comptes=new ArrayList();
		Compte c = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn = DriverManager.getConnection(urlBdd, loginBdd,passwordBdd);

			PreparedStatement ps = conn.prepareStatement("SELECT * FROM compte");
		
			ResultSet rs =   ps.executeQuery();
			while(rs.next()) {

				if(rs.getString("type_compte").equals("Secretaire")) {
					c= new Secretaire(rs.getInt("id"),rs.getString("login"),rs.getString("password"));
				}
				
				else 
				{
					c= new Medecin(rs.getInt("id"),rs.getString("login"),rs.getString("password"));
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

			PreparedStatement ps = conn.prepareStatement("insert into compte (login,password,type_compte) VALUES (?,?,?)");
			ps.setString(1, c.getLogin());
			ps.setString(2, c.getPassword());
			ps.setString(3, c.getClass().getSimpleName());
		
			ps.executeUpdate();
		
			ps.close();
			conn.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void update(Compte c) {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn = DriverManager.getConnection(urlBdd, loginBdd,passwordBdd);

			PreparedStatement ps = conn.prepareStatement("update compte set login=?,password=?,type_compte=? where id=?");
			ps.setString(1, c.getLogin());
			ps.setString(2, c.getPassword());
			ps.setString(3, c.getClass().getSimpleName());
			ps.setInt(4, c.getId());
		
			ps.executeUpdate();
		
			ps.close();
			conn.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void delete(Integer id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn = DriverManager.getConnection(urlBdd, loginBdd,passwordBdd);

			PreparedStatement ps = conn.prepareStatement("delete from compte where id=?");
			ps.setInt(1, id);
		
			ps.executeUpdate();
		
			ps.close();
			conn.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public Compte findByLoginAndPassword(String login,String password) 
	{
		Compte c = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn = DriverManager.getConnection(urlBdd, loginBdd,passwordBdd);

			PreparedStatement ps = conn.prepareStatement("SELECT * FROM compte where login=? and password=?");
			ps.setString(1,login);
			ps.setString(2, password);

			ResultSet rs =   ps.executeQuery();
			while(rs.next()) {

				if(rs.getString("type_compte").equals("Secretaire")) {
					c= new Secretaire(rs.getInt("id"),rs.getString("login"),rs.getString("password"));
				}
				
				else 
				{
					c= new Medecin(rs.getInt("id"),rs.getString("login"),rs.getString("password"));
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
