package quest.repository;

import java.util.List;

import quest.model.Client;
import quest.model.ClientId;


public interface IClientRepository extends IRepository<Client, ClientId>{
	List<Client> findAllWithFilieres();
}
