package quest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import quest.model.Matiere;

public interface MatiereRepository extends JpaRepository<Matiere, Integer> {
	@Query("select m from Matiere m where m.filiere.id = ?1")
	List<Matiere> findAllByFiliere(Integer idFiliere);
}
