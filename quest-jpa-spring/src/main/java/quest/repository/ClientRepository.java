package quest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import quest.model.Client;
import quest.model.ClientId;

public interface ClientRepository extends JpaRepository<Client, ClientId> {

	// Optional<Client> | List<Client> | (Client pas une bonne pratique)
	// findByAttributDeLEntite
	Optional<Client> findBySiret(String siret);

	List<Client> findByIdNom(String nom);
	List<Client> findByIdNomContaining(String nom);
	
	@Query("select distinct c from Client c join fetch c.filieres")
	List<Client> findAllWithFilieres();
	
	@Query("select distinct c from Client c join fetch c.filieres where c.id=:id")
	Optional<Client> findbyIdWithFiliere(@Param("id") ClientId id);
	
	
}
