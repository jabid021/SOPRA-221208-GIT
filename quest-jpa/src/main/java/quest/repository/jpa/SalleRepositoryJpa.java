package quest.repository.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import quest.context.Application;
import quest.model.Salle;
import quest.model.SalleId;
import quest.repository.ISalleRepository;

public class SalleRepositoryJpa implements ISalleRepository {

	@Override
	public List<Salle> findAll() {
		List<Salle> salles = new ArrayList<>();

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			TypedQuery<Salle> query = em.createQuery("select s from Salle s", Salle.class);

			salles = query.getResultList();

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

		return salles;
	}

	@Override
	public Salle findById(SalleId id) {
		Salle salle = null;

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

//			TypedQuery<Salle> query = em.createQuery("select s from Salle s where s.nom = :nom and s.etage = :etage", Salle.class);
//			query.setParameter("nom", id.getNom());
//			query.setParameter("etage", id.getEtage());
//			
//			salle = query.getSingleResult();

			salle = em.find(Salle.class, id);

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

		return salle;
	}

	@Override
	public Salle save(Salle salle) {
		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			salle = em.merge(salle);

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

		return salle;
	}

	@Override
	public void deleteById(SalleId id) {
		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

//			Salle salle = em.find(Salle.class, id);
//			
//			em.remove(salle);

			TypedQuery<Salle> query = em
					.createQuery("delete from Salle s where s.nom = :nom and s.etage = :etage", Salle.class);
			query.setParameter("nom", id.getNom());
			query.setParameter("etage", id.getEtage());

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
	public void delete(Salle o) {
		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			em.remove(em.merge(o));

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

}
