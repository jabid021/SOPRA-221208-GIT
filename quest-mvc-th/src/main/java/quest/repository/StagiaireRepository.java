package quest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import quest.model.Stagiaire;

public interface StagiaireRepository extends JpaRepository<Stagiaire, Integer> {

}
