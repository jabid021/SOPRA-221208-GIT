package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Filiere;

public class DAOFiliere implements IDAO<Filiere,Integer> {

	@Override
	public Filiere findById(Integer id) {
		Filiere filiere=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url+port+bdd,login,password);

			PreparedStatement ps = conn.prepareStatement("SELECT * from filiere where id=?");
			ps.setInt(1, id);

			ResultSet rs =  ps.executeQuery();

			while(rs.next()) 
			{
				filiere = new Filiere(rs.getInt("id"),rs.getString("libelle"),LocalDate.parse(rs.getString("debut")),LocalDate.parse(rs.getString("fin")));
			}

			ps.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return filiere;
	}

	@Override
	public List<Filiere> findAll() {
		Filiere filiere=null;
		List<Filiere> filieres=new ArrayList();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url+port+bdd,login,password);

			PreparedStatement ps = conn.prepareStatement("SELECT * from filiere");

			ResultSet rs =  ps.executeQuery();

			while(rs.next()) 
			{
				filiere = new Filiere(rs.getInt("id"),rs.getString("libelle"),LocalDate.parse(rs.getString("debut")),LocalDate.parse(rs.getString("fin")));
				filieres.add(filiere);
			}

			ps.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return filieres;
	}

	@Override
	public void insert(Filiere f) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url+port+bdd,login,password);

			PreparedStatement ps = conn.prepareStatement("INSERT INTO filiere (libelle,debut,fin) VALUES (?,?,?)");
			ps.setString(1, f.getLibelle());
			ps.setString(2, f.getDebut().toString());
			ps.setString(3, f.getFin().toString());

			ps.executeUpdate();
			ps.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Filiere f) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url+port+bdd,login,password);

			PreparedStatement ps = conn.prepareStatement("UPDATE filiere set libelle=?,debut=?,fin=? where id=?");
			ps.setString(1, f.getLibelle());
			ps.setString(2, f.getDebut().toString());
			ps.setString(3, f.getFin().toString());
			ps.setInt(4, f.getId());

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

			PreparedStatement ps = conn.prepareStatement("DELETE from filiere where id=?");
			ps.setInt(1, id);

			ps.executeUpdate();
			ps.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
