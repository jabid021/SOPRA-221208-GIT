package quest.repository.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import quest.context.Application;
import quest.model.Filiere;
import quest.model.Formateur;
import quest.model.Personne;
import quest.model.Stagiaire;
import quest.repository.IPersonneRepository;

public class PersonneRepositoryJpa implements IPersonneRepository {

	@Override
	public List<Personne> findAll() {
		List<Personne> personnes = new ArrayList<>();

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			TypedQuery<Personne> query = em.createQuery("select p from Personne p", Personne.class);

			personnes = query.getResultList();

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

		return personnes;
	}

	@Override
	public Personne findById(Integer id) {
		Personne personne = null;

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			personne = em.find(Personne.class, id);

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

		return personne;
	}

	@Override
	public Personne save(Personne personne) {
		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			personne = em.merge(personne);

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

		return personne;
	}

	@Override
	public void deleteById(Integer id) {
		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			TypedQuery<Personne> query = em.createQuery("delete from Personne p where p.id = :id", Personne.class);
			query.setParameter("id", id);

			query.executeUpdate();

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

	}

	@Override
	public void delete(Personne personne) {
		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			em.remove(em.merge(personne));

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
	}

	@Override
	public List<Formateur> findAllFormateur() {
		List<Formateur> formateurs = new ArrayList<>();

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			TypedQuery<Formateur> query = em.createQuery("select f from Formateur f", Formateur.class);

			formateurs = query.getResultList();

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

		return formateurs;
	}

	@Override
	public List<Stagiaire> findAllStagiaire() {
		List<Stagiaire> stagiaires = new ArrayList<>();

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			TypedQuery<Stagiaire> query = em.createQuery("select s from Stagiaire s", Stagiaire.class);

			stagiaires = query.getResultList();

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

		return stagiaires;
	}

	@Override
	public Object[] findStagiaireAndOrdinateurById(Integer id) {
		Object[] result = null;

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			TypedQuery<Object[]> query = em.createQuery("select s, o from Stagiaire s left join s.ordinateur o where s.id = ?1", Object[].class);

			query.setParameter(1, id);
			
			result = query.getSingleResult();

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

		return result;
	}

	@Override
	public Stagiaire findStagiaireById(Integer id) {
		Stagiaire stagiaire = null;

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			TypedQuery<Stagiaire> query = em.createQuery("select s from Stagiaire s where s.id = :id", Stagiaire.class);

			query.setParameter("id", id);
			
			stagiaire = query.getSingleResult();

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

		return stagiaire;
	}

	@Override
	public Stagiaire findStagiaireByIdWithOrdinateur(Integer id) {
		Stagiaire stagiaire = null;

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			TypedQuery<Stagiaire> query = em.createQuery("select distinct s from Stagiaire s join fetch s.ordinateur where s.id = :id", Stagiaire.class);

			query.setParameter("id", id);
			
			stagiaire = query.getSingleResult();

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

		return stagiaire;
	}

	@Override
	public List<Stagiaire> findAllStagiaireByFiliere(Filiere filiere) {
		List<Stagiaire> stagiaires = new ArrayList<>();

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			TypedQuery<Stagiaire> query = em.createQuery("select s from Stagiaire s join s.filieres f where f = :maFiliere", Stagiaire.class);

			query.setParameter("maFiliere", filiere);
			
			stagiaires = query.getResultList();

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

		return stagiaires;
	}

	@Override
	public List<Object[]> findAllStagiaireAndOrdinateur() {
		List<Object[]> result = new ArrayList<>();

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			TypedQuery<Object[]> query = em.createQuery("select s, o from Stagiaire s left join s.ordinateur o", Object[].class);

			result = query.getResultList();

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

		return result;
	}

}
