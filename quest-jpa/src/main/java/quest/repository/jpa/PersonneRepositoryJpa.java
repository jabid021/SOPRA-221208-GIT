package quest.repository.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import quest.context.Application;
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
			if(tx != null && tx.isActive()) {
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
			if(tx != null && tx.isActive()) {
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
			if(tx != null && tx.isActive()) {
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
	public List<Formateur> findAllFormateur() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Stagiaire> findAllStagiaire() {
		// TODO Auto-generated method stub
		return null;
	}

}
