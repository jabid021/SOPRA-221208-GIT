package quest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import quest.model.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
	Optional<Utilisateur> findByIdentifiant(String identifiant);
	
	@Query("select u from Utilisateur u where u.identifiant = ?1 and u.motDePasse = ?2 and disabled = false")
	Optional<Utilisateur> findByLoginAndMotDePasse(String login, String password);
}
