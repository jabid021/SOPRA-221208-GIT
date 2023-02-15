package quest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import quest.model.Stagiaire;

public interface StagiaireRepository extends JpaRepository<Stagiaire, Integer> {
	@Query("select distinct s from Stagiaire s left join fetch s.filieres where s.id = ?1")
	Optional<Stagiaire> findByIdWithFilieres(Integer id);
}
