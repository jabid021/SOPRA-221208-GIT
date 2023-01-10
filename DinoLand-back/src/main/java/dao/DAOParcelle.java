package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Parcelle;
import model.Zone;

public class DAOParcelle implements IDAO<Parcelle,Integer> {

	@Override
	public Parcelle findById(Integer id) {
		Parcelle p = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn = DriverManager.getConnection(urlBdd, loginBdd,passwordBdd);

			PreparedStatement ps = conn.prepareStatement("SELECT * FROM parcelle where id=? ");
			ps.setInt(1, id);


			ResultSet rs =   ps.executeQuery();
			while(rs.next()) {

				p = new Parcelle(rs.getInt("id"), rs.getInt("superficie"), Zone.valueOf(rs.getString("zone")));

			}

			rs.close();
			ps.close();
			conn.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return p;
	}

	@Override
	public List<Parcelle> findAll() {
		List<Parcelle> parcelles = new ArrayList();
		Parcelle p = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn = DriverManager.getConnection(urlBdd, loginBdd,passwordBdd);

			PreparedStatement ps = conn.prepareStatement("SELECT * FROM parcelle ");


			ResultSet rs =   ps.executeQuery();
			while(rs.next()) {

				p = new Parcelle(rs.getInt("id"), rs.getInt("superficie"), Zone.valueOf(rs.getString("zone")));

				parcelles.add(p);
			}

			rs.close();
			ps.close();
			conn.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return parcelles;
	}

	@Override
	public void insert(Parcelle parcelle) {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn = DriverManager.getConnection(urlBdd, loginBdd,passwordBdd);

			PreparedStatement ps = conn.prepareStatement("INSERT INTO parcelle (superficie,zone) VALUES(?,?)");

			ps.setInt(1, parcelle.getSuperficie());
			ps.setString(2, parcelle.getZone().toString());

			ps.executeUpdate();
			ps.close();


			conn.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void update(Parcelle parcelle) {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn = DriverManager.getConnection(urlBdd, loginBdd,passwordBdd);

			PreparedStatement ps = conn.prepareStatement("Update parcelle set superficie=?,zone=? where id=?");

			ps.setInt(1, parcelle.getSuperficie());
			ps.setString(2, parcelle.getZone().toString());
			ps.setInt(3, parcelle.getId());
			ps.executeUpdate();
			ps.close();


			conn.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

}
