package dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import context.Singleton;
import dao.IDAOVisite;
import model.Medecin;
import model.Patient;
import model.Visite;

public class DAOVisiteJDBC implements IDAOVisite{

	@Override
	public Visite findById(Integer id) {
		Visite v = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn = DriverManager.getConnection(urlBdd, loginBdd,passwordBdd);

			PreparedStatement ps = conn.prepareStatement("SELECT * FROM visite where numero=?");
			ps.setInt(1,id);

			ResultSet rs =   ps.executeQuery();
			while(rs.next()) {

				Patient p = Singleton.getInstance().getDaoPatient().findById(rs.getInt("id_patient"));
				Medecin m = (Medecin) Singleton.getInstance().getDaoCompte().findById(rs.getInt("id_medecin"));

				v = new Visite(rs.getInt("numero"),p,m,rs.getDouble("prix"),rs.getInt("salle"),LocalDate.parse(rs.getString("date_visite")));
			}

			rs.close();
			ps.close();
			conn.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return v;
	}

	@Override
	public List<Visite> findAll() {
		List<Visite> visites = new ArrayList();
		Visite v = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn = DriverManager.getConnection(urlBdd, loginBdd,passwordBdd);

			PreparedStatement ps = conn.prepareStatement("SELECT * FROM visite");

			ResultSet rs =   ps.executeQuery();
			while(rs.next()) {

				Patient p = Singleton.getInstance().getDaoPatient().findById(rs.getInt("id_patient"));
				Medecin m = (Medecin) Singleton.getInstance().getDaoCompte().findById(rs.getInt("id_medecin"));

				v = new Visite(rs.getInt("numero"),p,m,rs.getDouble("prix"),rs.getInt("salle"),LocalDate.parse(rs.getString("date_visite")));
				visites.add(v);
			}

			rs.close();
			ps.close();
			conn.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return visites;
	}

	@Override
	public void insert(Visite v) {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn = DriverManager.getConnection(urlBdd, loginBdd,passwordBdd);

			PreparedStatement ps = conn.prepareStatement("insert into visite (id_patient,id_medecin,prix,salle,date_visite) VALUES (?,?,?,?,?)");
			ps.setInt(1, v.getPatient().getId());
			ps.setInt(2, v.getMedecin().getId());
			ps.setDouble(3, v.getPrix());
			ps.setInt(4, v.getSalle());
			ps.setString(5, v.getDateVisite().toString());
		
			ps.executeUpdate();
		
			ps.close();
			conn.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void update(Visite v) {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn = DriverManager.getConnection(urlBdd, loginBdd,passwordBdd);

			PreparedStatement ps = conn.prepareStatement("update visite set id_patient=?,id_medecin=?,prix=?,salle=?,date_visite=? where numero=?");
			ps.setInt(1, v.getPatient().getId());
			ps.setInt(2, v.getMedecin().getId());
			ps.setDouble(3, v.getPrix());
			ps.setInt(4, v.getSalle());
			ps.setString(5, v.getDateVisite().toString());
			ps.setInt(6, v.getId());
			
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

			PreparedStatement ps = conn.prepareStatement("DELETE from visite where numero=?");
			ps.setInt(1, id);
			
			ps.executeUpdate();
		
			ps.close();
			conn.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public List<Visite> findAllByPatient(Integer idPatient)
	{
		List<Visite> visites = new ArrayList();
		Visite v = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn = DriverManager.getConnection(urlBdd, loginBdd,passwordBdd);

			PreparedStatement ps = conn.prepareStatement("SELECT * FROM visite where id_patient=?");
			ps.setInt(1,idPatient);

			ResultSet rs =   ps.executeQuery();
			while(rs.next()) {

				Patient p = Singleton.getInstance().getDaoPatient().findById(rs.getInt("id_patient"));
				Medecin m = (Medecin) Singleton.getInstance().getDaoCompte().findById(rs.getInt("id_medecin"));

				v = new Visite(rs.getInt("numero"),p,m,rs.getDouble("prix"),rs.getInt("salle"),LocalDate.parse(rs.getString("date_visite")));
				visites.add(v);
			}

			rs.close();
			ps.close();
			conn.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return visites;
	}
}

