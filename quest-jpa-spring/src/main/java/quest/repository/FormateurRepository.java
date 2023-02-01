package quest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import quest.model.Filiere;
import quest.model.Formateur;

public interface FormateurRepository extends JpaRepository<Formateur, Integer>{

}
