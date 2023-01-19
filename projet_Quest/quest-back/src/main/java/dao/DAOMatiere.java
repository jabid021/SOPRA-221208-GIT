package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Matiere;

public class DAOMatiere implements IDAO<Matiere,Integer> {

	@Override
	public Matiere findById(Integer id) {
		Matiere matiere=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url+port+bdd,login,password);

			PreparedStatement ps = conn.prepareStatement("SELECT * from matiere where id=?");
			ps.setInt(1, id);

			ResultSet rs =  ps.executeQuery();

			while(rs.next()) 
			{
				matiere = new Matiere(rs.getInt("id"),rs.getString("libelle"),rs.getInt("quest"));
			}

			ps.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return matiere;
	}

	@Override
	public List<Matiere> findAll() {
		Matiere matiere=null;
		List<Matiere> matieres=new ArrayList();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url+port+bdd,login,password);

			PreparedStatement ps = conn.prepareStatement("SELECT * from matiere");

			ResultSet rs =  ps.executeQuery();

			while(rs.next()) 
			{
				matiere = new Matiere(rs.getInt("id"),rs.getString("libelle"),rs.getInt("quest"));
				matieres.add(matiere);
			}

			ps.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return matieres;
	}

	@Override
	public void insert(Matiere m) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url+port+bdd,login,password);

			PreparedStatement ps = conn.prepareStatement("INSERT INTO matiere (libelle,quest) VALUES (?,?)");
			ps.setString(1, m.getLibelle());
			ps.setInt(2, m.getQuest());

			ps.executeUpdate();
			ps.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Matiere m) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url+port+bdd,login,password);

			PreparedStatement ps = conn.prepareStatement("UPDATE matiere set libelle=?,quest=? where id=?");
			ps.setString(1, m.getLibelle());
			ps.setInt(2, m.getQuest());
			ps.setInt(3, m.getId());

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

			PreparedStatement ps = conn.prepareStatement("DELETE from matiere where id=?");
			ps.setInt(1, id);

			ps.executeUpdate();
			ps.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
