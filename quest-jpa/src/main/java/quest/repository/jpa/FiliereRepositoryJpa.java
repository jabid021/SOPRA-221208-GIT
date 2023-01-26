package quest.repository.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import quest.context.Application;
import quest.model.Filiere;
import quest.repository.IFiliereRepository;

public class FiliereRepositoryJpa implements IFiliereRepository {

	@Override
	public List<Filiere> findAll() {
		List<Filiere> filieres = new ArrayList<>();

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			TypedQuery<Filiere> query = em.createQuery("select f from Filiere f", Filiere.class);

			filieres = query.getResultList();

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

		return filieres;
	}

	@Override
	public Filiere findById(Integer id) {
		Filiere filiere = null;

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			filiere = em.find(Filiere.class, id);

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

		return filiere;
	}

	@Override
	public Filiere save(Filiere filiere) {
		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			filiere = em.merge(filiere);

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

		return filiere;
	}

	@Override
	public void deleteById(Integer id) {
		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			TypedQuery<Filiere> query = em.createQuery("delete from Filiere f where f.id = ?1", Filiere.class);
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
	public void delete(Filiere filiere) {
		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			em.remove(em.merge(filiere));

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
	public List<Filiere> findAllByLibelle(String libelle) {
		List<Filiere> filieres = new ArrayList<>();

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			TypedQuery<Filiere> query = em.createQuery("select f from Filiere f where f.libelle = :libelle", Filiere.class);

			query.setParameter("libelle", libelle);
			
			filieres = query.getResultList();

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

		return filieres;
	}

	@Override
	public List<Filiere> findAllByNomClient(String client) {
		List<Filiere> filieres = new ArrayList<>();

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			TypedQuery<Filiere> query = em.createQuery("select f from Filiere f where f.client.id.nom = ?1", Filiere.class);
//			TypedQuery<Filiere> query = em.createQuery("select f from Filiere f join f.client c where c.id.nom = ?1", Filiere.class);
//			TypedQuery<Filiere> query = em.createQuery("select f from Client c join c.filieres f where c.id.nom = ?1", Filiere.class);

			query.setParameter(1, client);
			
			filieres = query.getResultList();

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

		return filieres;
	}

	@Override
	public List<Filiere> findAllBySalleVille(String ville) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countByReferentNom(String nom) {
		// TODO Auto-generated method stub
		return 0;
	}

}
