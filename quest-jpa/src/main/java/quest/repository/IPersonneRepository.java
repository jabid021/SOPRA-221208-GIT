package quest.repository;

import java.util.List;

import quest.model.Formateur;
import quest.model.Personne;
import quest.model.Stagiaire;

public interface IPersonneRepository extends IRepository<Personne, Integer>{
	List<Formateur> findAllFormateur();
	List<Stagiaire> findAllStagiaire();
}
