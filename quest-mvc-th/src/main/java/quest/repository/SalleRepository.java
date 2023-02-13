package quest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import quest.model.Filiere;
import quest.model.Salle;
import quest.model.SalleId;

public interface SalleRepository extends JpaRepository<Salle, SalleId>{

}
