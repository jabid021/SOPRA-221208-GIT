package quest.repository.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import quest.context.Application;
import quest.model.Ordinateur;
import quest.repository.IOrdinateurRepository;

public class OrdinateurRepositoryJpa implements IOrdinateurRepository {

	@Override
	public List<Ordinateur> findAll() {
		List<Ordinateur> ordinateurs = new ArrayList<>();
		
		EntityManager em = null;
		EntityTransaction tx = null;
		
		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			
			TypedQuery<Ordinateur> query = em.createQuery("select o from Ordinateur o", Ordinateur.class);
			
			ordinateurs = query.getResultList();
			
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if(tx != null && tx.isActive()) {
				tx.rollback();
			}
		} finally {
			if (em != null) {
				em.close();
			}
		}
		
		return ordinateurs;
	}

	@Override
	public Ordinateur findById(Integer id) {
		Ordinateur ordinateur = null;
		
		EntityManager em = null;
		EntityTransaction tx = null;
		
		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			
//			TypedQuery<Ordinateur> query = em.createQuery("select o from Ordinateur o where o.id = :idOrdi", Ordinateur.class);
//			query.setParameter("idOrdi", id);
//			
//			ordinateur = query.getSingleResult();
			
			ordinateur = em.find(Ordinateur.class, id);
			
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if(tx != null && tx.isActive()) {
				tx.rollback();
			}
		} finally {
			if (em != null) {
				em.close();
			}
		}
		
		return ordinateur;
	}

	@Override
	public Ordinateur save(Ordinateur ordinateur) {
		EntityManager em = null;
		EntityTransaction tx = null;
		
		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			
			ordinateur = em.merge(ordinateur);
			
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if(tx != null && tx.isActive()) {
				tx.rollback();
			}
		} finally {
			if (em != null) {
				em.close();
			}
		}
		
		return ordinateur;
	}

	@Override
	public void deleteById(Integer id) {
		EntityManager em = null;
		EntityTransaction tx = null;
		
		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			
//			Ordinateur ordinateur = em.find(Ordinateur.class, id);
//			
//			em.remove(ordinateur);
			
			TypedQuery<Ordinateur> query = em.createQuery("delete from Ordinateur o where o.id = :id", Ordinateur.class);
			query.setParameter("id", id);
			
			query.executeUpdate();
			
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if(tx != null && tx.isActive()) {
				tx.rollback();
			}
		} finally {
			if (em != null) {
				em.close();
			}
		}

	}
	
	@Override
	public void delete(Ordinateur o) {
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
			if(tx != null && tx.isActive()) {
				tx.rollback();
			}
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	@Override
	public List<Ordinateur> findAllByMarque(String marque) {
		List<Ordinateur> ordinateurs = new ArrayList<>();
		
		EntityManager em = null;
		EntityTransaction tx = null;
		
		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			
			TypedQuery<Ordinateur> query = em.createQuery("select o from Ordinateur o where o.marque = ?1", Ordinateur.class);
			query.setParameter(1, marque);
			
			ordinateurs = query.getResultList();
			
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if(tx != null && tx.isActive()) {
				tx.rollback();
			}
		} finally {
			if (em != null) {
				em.close();
			}
		}
		
		return ordinateurs;
	}

	@Override
	public Ordinateur findByStagiaireId(Integer id) {
Ordinateur ordinateur = null;
		
		EntityManager em = null;
		EntityTransaction tx = null;
		
		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			
			TypedQuery<Ordinateur> query = em.createQuery("select o from Ordinateur o where o.stagiaire.id = ?1", Ordinateur.class);
			query.setParameter(1, id);
			
			ordinateur = query.getSingleResult();
			
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if(tx != null && tx.isActive()) {
				tx.rollback();
			}
		} finally {
			if (em != null) {
				em.close();
			}
		}
		
		return ordinateur;
	}

}
