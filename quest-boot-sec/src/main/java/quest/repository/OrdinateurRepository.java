package quest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import quest.model.Ordinateur;

public interface OrdinateurRepository extends JpaRepository<Ordinateur, Integer>{
	List<Ordinateur> findByMarque(String marque);
	
	Optional<Ordinateur> findByStagiaireId(Integer id);
}
