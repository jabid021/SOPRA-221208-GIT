package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import model.Animation;
import model.Client;
import model.Reservation;

public class DAOReservation implements IDAO<Reservation,Integer>{

	@Override
	public Reservation findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reservation> findAll() {
		DAOAnimation daoA = new DAOAnimation();
		DAOCompte daoC= new DAOCompte();
		List<Reservation> reservations = new ArrayList();
		Reservation r = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn = DriverManager.getConnection(urlBdd, loginBdd,passwordBdd);

			PreparedStatement ps = conn.prepareStatement("SELECT * FROM reservation ");


			ResultSet rs =   ps.executeQuery();
			while(rs.next()) {

				Client client = (Client) daoC.findById(rs.getInt("client"));
				Animation animation = daoA.findById(rs.getInt("animation"));
				r = new Reservation(rs.getInt("id"), rs.getInt("qte"),rs.getDouble("prix_total"),LocalDate.parse(rs.getString("jour")), LocalTime.parse(rs.getString("heure")),client,animation);

				reservations.add(r);
			}

			rs.close();
			ps.close();
			conn.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return reservations;
	}

	@Override
	public void insert(Reservation reservation) {
		try {
			Class.forName("com.mysql.jdbc.Driver");



			Connection conn = DriverManager.getConnection(urlBdd, loginBdd,passwordBdd);

			PreparedStatement ps = conn.prepareStatement("INSERT INTO reservation (qte,prix_total,jour,heure,client,animation) VALUES(?,?,?,?,?,?)");

			ps.setInt(1,reservation.getQte());
			ps.setDouble(2,reservation.getPrixTotal());
			ps.setString(3,reservation.getJour().toString());
			ps.setString(4,reservation.getHeure().toString());
			ps.setInt(5,reservation.getClient().getId());
			ps.setInt(6,reservation.getAnimation().getId());



			ps.executeUpdate();

			ps.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(Reservation o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	

	public List<Reservation> findAllByClient(Integer idClient) {
		
		DAOAnimation daoA = new DAOAnimation();
		DAOCompte daoC= new DAOCompte();
		
		List<Reservation> reservations = new ArrayList();
		Reservation r = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn = DriverManager.getConnection(urlBdd, loginBdd,passwordBdd);

			PreparedStatement ps = conn.prepareStatement("SELECT * FROM reservation where client=?");
			ps.setInt(1, idClient);


			ResultSet rs =   ps.executeQuery();
			while(rs.next()) {

				Client client = (Client) daoC.findById(idClient);
				Animation animation = daoA.findById(rs.getInt("animation"));
				r = new Reservation(rs.getInt("id"), rs.getInt("qte"),rs.getDouble("prix_total"),LocalDate.parse(rs.getString("jour")), LocalTime.parse(rs.getString("heure")),client,animation);

				reservations.add(r);
			}

			rs.close();
			ps.close();
			conn.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return reservations;
	}
}
