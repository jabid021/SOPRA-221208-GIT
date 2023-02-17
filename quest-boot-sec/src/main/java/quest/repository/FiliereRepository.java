package quest.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import quest.model.Client;
import quest.model.Filiere;
import quest.model.Formateur;

public interface FiliereRepository extends JpaRepository<Filiere, Integer> {
	List<Filiere> findByLibelle(String libelle);

	List<Filiere> findBySalleAdrVille(String ville);

	List<Filiere> findByClient(Client client);

	@RestResource(path = "count-referent-by-nom")
	// @Query("select count(f) from Filiere f where f.referent.nom=:nom")
	int countByReferentNom(String nom);

	
	@Query("select distinct f from Filiere f left join fetch f.referent")
	List<Filiere> findAllWithReferent();
	
	@Query("select distinct f from Filiere f left join fetch f.matieres")
	List<Filiere> findAllWithMatieres();

	@Query("select distinct f from Filiere f left join fetch f.referent left join fetch f.matieres where f.id=:id")
	Optional<Filiere> findByIdWithReferentAndMatieres(@Param("id") Integer id);

	@Query("select distinct f from Filiere f left join fetch f.matieres where f.id=:id")
	Optional<Filiere> findByIdWithMatieres(@Param("id") Integer id);

	@Query("select distinct f from Filiere f left join fetch f.stagiaires where f.id=:id")
	Optional<Filiere> findByIdWithStagiaires(@Param("id") Integer id);

	@Query("select distinct f from Filiere f left join fetch f.matieres left join fetch f.stagiaires where f.id=:id")
	Optional<Filiere> findByIdWithMatieresAndStagiaire(@Param("id") Integer id);

//	@Query("update Filiere f set f.client=null where f.client.id.nom=:nom and f.client.id.type=:type")
//	void setClientToNullByClient(@Param("nom") String nom,@Param("type") TypeClient type);

//	@Query("update Filiere f set f.client=null where f.client.id=:id")
//	void setClientToNullByClient(@Param("id") ClientId id);

//	@Query("update Filiere f set f.client=null where f.client=:client")
//	void setClientToNullByClient(@Param("client") Client client);

	@Transactional
	@Modifying
	@Query("update Filiere f set f.referent=null where f.referent=:referent")
	void setReferentToNullByReferent(@Param("referent") Formateur referent);

}
