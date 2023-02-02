package quest.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import quest.model.Client;
import quest.model.ClientId;
import quest.model.Filiere;
import quest.model.Formateur;
import quest.model.Salle;
import quest.model.TypeClient;

public interface FiliereRepository extends JpaRepository<Filiere, Integer> {
	List<Filiere> findByLibelle(String libelle);

	List<Filiere> findBySalleAdrVille(String ville);

	List<Filiere> findByClient(Client client);

	// @Query("select count(f) from Filiere f where f.referent.nom=:nom")
	int countByReferentNom(String nom);

	@Query("select f from Filiere f left join fetch f.matieres")
	List<Filiere> findAllWithMatieres();

	@Query("select f from Filiere f left join fetch f.referent left join fetch f.matieres where f.id=:id")
	Optional<Filiere> findByIdWithReferentAndMatieres(@Param("id") Integer id);

	@Query("select f from Filiere f left join fetch f.matieres where f.id=:id")
	Optional<Filiere> findByIdWithMatieres(Integer id);

	@Query("select f from Filiere f left join fetch f.stagiaires where f.id=:id")
	Optional<Filiere> findByIdWithStagiaires(Integer id);

	@Query("select f from Filiere f left join fetch f.matieres left join fetch f.stagiaires where f.id=:id")
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
