package dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.IDAOPatient;
import model.Patient;


public class DAOPatientJDBC implements IDAOPatient {

	
	@Override
	public Patient findById(Integer id) {
		Patient p = null;
		try {
			
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn = DriverManager.getConnection(urlBdd, loginBdd,passwordBdd);

			PreparedStatement ps = conn.prepareStatement("SELECT * FROM patient where id=?");

			ps.setInt(1, id);
			ResultSet rs =   ps.executeQuery();
			while(rs.next()) {

				

				p = new Patient(rs.getInt("id"),rs.getString("nom"),rs.getString("prenom"));
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
	public List<Patient> findAll() {
		List<Patient> patients = new ArrayList();
		Patient p = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn = DriverManager.getConnection(urlBdd, loginBdd,passwordBdd);

			PreparedStatement ps = conn.prepareStatement("SELECT * FROM patient");

			ResultSet rs =   ps.executeQuery();
			while(rs.next()) {

				

				p = new Patient(rs.getInt("id"),rs.getString("nom"),rs.getString("prenom"));
				patients.add(p);
			}

			rs.close();
			ps.close();
			conn.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return patients;
	}

	@Override
	public void insert(Patient p) {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn = DriverManager.getConnection(urlBdd, loginBdd,passwordBdd);

			PreparedStatement ps = conn.prepareStatement("INSERT INTO patient VALUES(?,?,?)");
			
			ps.setInt(1,p.getId());
			ps.setString(2, p.getNom());
			ps.setString(3, p.getPrenom());
			
			ps.executeUpdate();


			ps.close();
			conn.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void update(Patient p) {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn = DriverManager.getConnection(urlBdd, loginBdd,passwordBdd);

			PreparedStatement ps = conn.prepareStatement("Update patient set nom=?,prenom=? where id=?");

			ps.setString(1, p.getNom());
			ps.setString(2, p.getPrenom());
			ps.setInt(3,p.getId());
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

			PreparedStatement ps = conn.prepareStatement("DELETE from patient where id=?");

			ps.setInt(1,id);
			ps.executeUpdate();


			ps.close();
			conn.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}

}
