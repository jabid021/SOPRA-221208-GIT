package quest.repository;

import java.util.List;

import quest.model.Ordinateur;

public interface IOrdinateurRepository extends IRepository<Ordinateur, Integer>{
	List<Ordinateur> findAllByMarque(String marque);
}
