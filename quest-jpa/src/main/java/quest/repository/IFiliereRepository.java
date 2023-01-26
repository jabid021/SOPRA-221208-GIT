package quest.repository;

import java.util.List;

import quest.model.Filiere;

public interface IFiliereRepository extends IRepository<Filiere, Integer>{
	List<Filiere> findAllByLibelle(String libelle);
	List<Filiere> findAllByNomClient(String client);
	
	List<Filiere> findAllBySalleVille(String ville);
	
	int countByReferentNom(String nom);
}
