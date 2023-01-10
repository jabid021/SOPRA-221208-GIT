package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Animation;
import model.Attraction;
import model.Dinosaure;
import model.Espece;
import model.Parcelle;

public class DAOAnimation implements IDAO<Animation,Integer> {

	@Override
	public Animation findById(Integer id) {
		Animation a = null;
		DAOParcelle daoP = new DAOParcelle();
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn = DriverManager.getConnection(urlBdd, loginBdd,passwordBdd);

			PreparedStatement ps = conn.prepareStatement("SELECT * FROM animation where id=? ");
			ps.setInt(1, id);


			ResultSet rs =   ps.executeQuery();
			while(rs.next()) {

				Parcelle parcelle = daoP.findById(rs.getInt("parcelle"));
				if(rs.getString("type_animation").equals("attraction")) {
					a = new Attraction(rs.getInt("id"), rs.getInt("duree"), rs.getInt("places"), rs.getDouble("prix"),parcelle, rs.getString("nom"), rs.getBoolean("active"));
				}

				else 
				{
					a = new Dinosaure(rs.getInt("id"), rs.getInt("duree"), rs.getInt("places"), rs.getDouble("prix"),parcelle, rs.getInt("stress"), Espece.valueOf(rs.getString("espece")));

				}
			}
			rs.close();
			ps.close();
			conn.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return a;
	}

	@Override
	public List<Animation> findAll() {
		DAOParcelle daoP = new DAOParcelle();
		List<Animation> animations = new ArrayList();
		Animation a = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn = DriverManager.getConnection(urlBdd, loginBdd,passwordBdd);

			PreparedStatement ps = conn.prepareStatement("SELECT * FROM animation ");


			ResultSet rs =   ps.executeQuery();
			while(rs.next()) {

				Parcelle parcelle = daoP.findById(rs.getInt("parcelle"));
				if(rs.getString("type_animation").equals("attraction")) {
					a = new Attraction(rs.getInt("id"), rs.getInt("duree"), rs.getInt("places"), rs.getDouble("prix"),parcelle, rs.getString("nom"), rs.getBoolean("active"));
				}

				else 
				{
					a = new Dinosaure(rs.getInt("id"), rs.getInt("duree"), rs.getInt("places"), rs.getDouble("prix"),parcelle, rs.getInt("stress"), Espece.valueOf(rs.getString("espece")));

				}
				animations.add(a);
			}

			rs.close();
			ps.close();
			conn.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return animations;
	}

	@Override
	public void insert(Animation animation) {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn = DriverManager.getConnection(urlBdd, loginBdd,passwordBdd);


			if(animation instanceof Attraction) 
			{
				PreparedStatement ps = conn.prepareStatement("INSERT INTO animation (duree,places,prix,nom,active,parcelle,type_animation) VALUES(?,?,?,?,?,?,?)");

				Attraction attraction= (Attraction) animation;
				ps.setInt(1, attraction.getDuree());
				ps.setInt(2, attraction.getPlaces());
				ps.setDouble(3, attraction.getPrix());
				ps.setString(4, attraction.getNom());
				ps.setBoolean(5, attraction.isActive());
				ps.setInt(6, attraction.getParcelle().getId());
				ps.setString(7, "attraction");

				ps.executeUpdate();
				ps.close();
			}
			else 
			{
				PreparedStatement ps = conn.prepareStatement("INSERT INTO animation (duree,places,prix,stress,espece,parcelle,type_animation) VALUES(?,?,?,?,?,?,?)");

				Dinosaure dino= (Dinosaure) animation;
				ps.setInt(1, dino.getDuree());
				ps.setInt(2, dino.getPlaces());
				ps.setDouble(3, dino.getPrix());
				ps.setInt(4, dino.getStress());
				ps.setString(5, dino.getEspece().toString());
				ps.setInt(6, dino.getParcelle().getId());
				ps.setString(7, "dinosaure");

				ps.executeUpdate();
				ps.close();

			}


			conn.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void update(Animation o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

}
