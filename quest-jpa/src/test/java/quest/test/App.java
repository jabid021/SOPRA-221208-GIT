package quest.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import quest.context.Application;
import quest.model.Ordinateur;

public class App {

	public static void main(String[] args) {
		EntityManagerFactory emf = Application.getInstance().getEmf();

		EntityManager em = emf.createEntityManager(); // PersistenceContext Implicite
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		Ordinateur ordiAsus = new Ordinateur("ASUS", 16); // new ou transient

		em.persist(ordiAsus); // managed

		ordiAsus.setRam(32); // dirty cheking => synchro automatique -- toujours managed
		
		tx.commit(); // em.flush();
		em.close();
		
		ordiAsus.setRam(64); // detached 

		em = emf.createEntityManager();
		tx = em.getTransaction();
		tx.begin();
		
		Ordinateur ordiAsusCopy = em.merge(ordiAsus); // ordiAsus : detached - ordiAsusCopy : managed

		ordiAsusCopy.setMarque("HP");

		tx.commit(); // em.flush();
		em.close();
		
		em = emf.createEntityManager();
		tx = em.getTransaction();
		tx.begin();
		
		Ordinateur ordiAsusCopyCopy = em.merge(ordiAsusCopy);
		
		em.remove(ordiAsusCopyCopy); // removed

		tx.commit(); // em.flush();
		em.close();
		
		ordiAsusCopyCopy.setMarque("Asus"); // new

				
		
		emf.close();
	}

}
