package quest.repository.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import quest.context.Application;
import quest.model.Matiere;
import quest.repository.IMatiereRepository;

public class MatiereRepositoryJpa implements IMatiereRepository {

	@Override
	public List<Matiere> findAll() {
		List<Matiere> matieres = new ArrayList<>();
		
		EntityManager em = null;
		EntityTransaction tx = null;
		
		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			
			TypedQuery<Matiere> query = em.createQuery("select m from Matiere m", Matiere.class);
			
			matieres = query.getResultList();
			
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
		
		return matieres;
	}

	@Override
	public Matiere findById(Integer id) {
		Matiere matiere = null;
		
		EntityManager em = null;
		EntityTransaction tx = null;
		
		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			
			matiere = em.find(Matiere.class, id);
			
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
		
		return matiere;
	}

	@Override
	public Matiere save(Matiere matiere) {
		EntityManager em = null;
		EntityTransaction tx = null;
		
		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			
			matiere = em.merge(matiere);
			
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
		
		return matiere;
	}

	@Override
	public void deleteById(Integer id) {
		EntityManager em = null;
		EntityTransaction tx = null;
		
		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			
			TypedQuery<Matiere> query = em.createQuery("delete from Matiere m where m.id = :id", Matiere.class);
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
	public void delete(Matiere matiere) {
		EntityManager em = null;
		EntityTransaction tx = null;
		
		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();
						
			em.remove(em.merge(matiere));
			
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

}
