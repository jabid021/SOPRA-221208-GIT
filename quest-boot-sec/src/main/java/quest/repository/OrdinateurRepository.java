package quest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import quest.model.Ordinateur;

public interface OrdinateurRepository extends JpaRepository<Ordinateur, Integer>{
	List<Ordinateur> findByMarque(String marque);
	
	Optional<Ordinateur> findByStagiaireId(Integer id);
	
	@Query("select o from Ordinateur o left join o.stagiaire s where s is null or o.id = :idOrdi")
	List<Ordinateur> findAllOrphans(@Param("idOrdi") Integer idOrdi);
}
