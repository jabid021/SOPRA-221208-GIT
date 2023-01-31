package quest.repository.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import quest.context.Application;
import quest.model.Client;
import quest.model.ClientId;
import quest.repository.IClientRepository;

@Repository
public class ClientRepositoryJpa implements IClientRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Client> findAll() {
		List<Client> clients = new ArrayList<>();
		TypedQuery<Client> query = em.createQuery("select c from Client c", Client.class);
		clients = query.getResultList();
		return clients;
	}

	@Override
	public Client findById(ClientId id) {
		Client client = null;

//			TypedQuery<Client> query = em.createQuery("select c from Client c where c.id.nom = :nom and c.id.type = :type", Client.class);
//			query.setParameter("nom", id.getNom());
//			query.setParameter("type", id.getType());
//			
//			client = query.getSingleResult();

		client = em.find(Client.class, id);
		return client;
	}

	@Override
	@Transactional
	public Client save(Client client) {
		client = em.merge(client);
		return client;
	}

	@Override
	@Transactional
	public void deleteById(ClientId id) {

//			Client client = em.find(Client.class, id);
//			
//			em.remove(client);

		TypedQuery<Client> query = em.createQuery("delete from Client c where c.id.nom = :nom and c.id.type = :type",
				Client.class);
		query.setParameter("nom", id.getNom());
		query.setParameter("type", id.getType());
		query.executeUpdate();
	}

	@Override
	@Transactional
	public void delete(Client o) {
		em.remove(em.merge(o));
	}

	@Override
	public List<Client> findAllWithFilieres() {
		List<Client> clients = new ArrayList<>();
		TypedQuery<Client> query = em.createQuery("select distinct c from Client c join fetch c.filieres",
				Client.class);
		clients = query.getResultList();
		return clients;
	}

}
