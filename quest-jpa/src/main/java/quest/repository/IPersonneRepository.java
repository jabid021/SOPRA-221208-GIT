package quest.repository;

import java.util.List;

import quest.model.Filiere;
import quest.model.Formateur;
import quest.model.Personne;
import quest.model.Stagiaire;

public interface IPersonneRepository extends IRepository<Personne, Integer>{
	List<Formateur> findAllFormateur();
	List<Stagiaire> findAllStagiaire();
	
	List<Object[]> findAllStagiaireAndOrdinateur();
	
	Object[] findStagiaireAndOrdinateurById(Integer id);
	
	Stagiaire findStagiaireById(Integer id);
	
	Stagiaire findStagiaireByIdWithOrdinateur(Integer id);
	
	List<Stagiaire> findAllStagiaireByFiliere(Filiere filiere);
}

