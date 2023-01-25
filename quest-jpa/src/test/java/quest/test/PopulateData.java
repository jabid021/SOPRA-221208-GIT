package quest.test;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import quest.model.Adresse;
import quest.model.Civilite;
import quest.model.Client;
import quest.model.Filiere;
import quest.model.Formateur;
import quest.model.Matiere;
import quest.model.NiveauEtude;
import quest.model.Ordinateur;
import quest.model.Salle;
import quest.model.Stagiaire;
import quest.model.TypeClient;

public class PopulateData {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("quest");

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			Client sopraSteria = new Client("SOPRA STERIA", TypeClient.SA);
			sopraSteria.setSiret("326 820 065 00083");
			em.persist(sopraSteria);
			
			Salle saphirAJC = new Salle("SAPHIR", 4, 25, true);
			saphirAJC.setAdr(new Adresse("6 rue Rougement", "1er escalier gauche", "75009", "Paris"));
			em.persist(saphirAJC);
			
			Formateur jordan = new Formateur(Civilite.M, "ABID", "Jordan", "j.abid@ajc-ingenerie.fr", false, 10);
			jordan.setAdresse(new Adresse("1 rue de la Paix", "2ème étage", "75008", "Paris"));
			em.persist(jordan);

			Formateur eric = new Formateur(Civilite.M, "SULTAN", "Eric", "e.sultan@ajc-ingenerie.fr", false, 23);
			eric.setAdresse(new Adresse("5 rue de Paris", "RDC", "33000", "Bordeaux"));
			em.persist(eric);

			Filiere java221208 = new Filiere("JAVA / SPRING / ANGULAR", LocalDate.parse("2022-12-08"),
					LocalDate.parse("2023-03-03"));
			java221208.setClient(sopraSteria);
			java221208.setSalle(saphirAJC);
			java221208.setReferent(jordan);
			em.persist(java221208);

			Matiere algo = new Matiere("ALGO EN JAVA", 1717);
			algo.setFiliere(java221208);
			algo.setFormateur(jordan);
			em.persist(algo);

			Matiere javaObjet = new Matiere("JAVA OBJET", 9932);
			javaObjet.setFiliere(java221208);
			javaObjet.setFormateur(jordan);
			em.persist(javaObjet);

			Matiere jpa = new Matiere("JPA 2 AVEC HIBERNATE", 7466);
			jpa.setFiliere(java221208);
			jpa.setFormateur(eric);
			em.persist(jpa);

			Ordinateur ordiAsus = new Ordinateur("ASUS", 16);
			em.persist(ordiAsus);

			Ordinateur ordiHP = new Ordinateur("HP", 32);
			em.persist(ordiHP);	

			Stagiaire audrey = new Stagiaire(Civilite.MME, "PENAZZI", "Audrey", "audrey@gmail.com",
					LocalDate.parse("1998-01-27"), NiveauEtude.BAC_5);
			audrey.setAdresse(new Adresse("1 rue de Bordeaux", "", "31200", "Toulouse"));
			audrey.setOrdinateur(ordiAsus);
			em.persist(audrey);
			
			Stagiaire aurelien = new Stagiaire(Civilite.M, "BULME", "Aurélien", "aurelien@gmail.com",
					LocalDate.parse("1996-07-17"), NiveauEtude.BAC_5);
			aurelien.setAdresse(new Adresse("1 rue de Toulouse", "", "33000", "Bordeaux"));
			aurelien.setOrdinateur(ordiHP);
			em.persist(aurelien);
			
			java221208.getStagiaires().add(audrey);
			java221208.getStagiaires().add(aurelien);
			
						
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		} finally {
			if (em != null) {
				em.close();
			}
		}

		emf.close();

	}

}
