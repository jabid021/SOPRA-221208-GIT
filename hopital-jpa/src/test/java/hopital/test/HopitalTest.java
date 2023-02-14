package hopital.test;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.jupiter.api.Test;

import hopital.model.Medecin;
import hopital.model.Patient;
import hopital.model.Secretaire;
import hopital.model.Visite;

public class HopitalTest {

	@Test
	public void loadData() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hopital");

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			Medecin docBrown = new Medecin("docbrown@backtothefuture.com", "doloreane");

			docBrown = em.merge(docBrown);

			Secretaire laSecretaire = new Secretaire("secret@backtothefuture.com", "Password");

			laSecretaire = em.merge(laSecretaire);

			Patient marty = new Patient("MacFly", "Marty");

			marty = em.merge(marty);

			Visite visiteMarty = new Visite(marty, docBrown, 30, 1, LocalDate.now());
			
			visiteMarty = em.merge(visiteMarty);

			tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
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
