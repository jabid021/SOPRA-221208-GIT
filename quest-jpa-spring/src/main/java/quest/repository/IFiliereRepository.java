package quest.repository;

import java.util.List;

import quest.model.Filiere;

public interface IFiliereRepository extends IRepository<Filiere, Integer>{
	List<Filiere> findAllByLibelle(String libelle);
	List<Filiere> findAllByNomClient(String client);
	
	List<Filiere> findAllBySalleVille(String ville);
	
	int countByReferentNom(String nom);
	
	List<Object[]> findAllFiliereAndMatiere();
	
	List<Filiere> findAllWithMatieres();
	
	Filiere findByIdWithReferentAndMatieres(Integer id);
	
	Filiere findByIdWithMatieres(Integer id);
	
	Filiere findByIdWithStagiaires(Integer id);
}
