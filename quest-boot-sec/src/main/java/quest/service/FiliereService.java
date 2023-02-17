package quest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import quest.exception.FiliereException;
import quest.exception.FormateurException;
import quest.exception.IdException;
import quest.model.Filiere;
import quest.repository.FiliereRepository;

@Service
public class FiliereService {

	@Autowired
	private FiliereRepository filiereRepo;

	public Filiere create(Filiere filiere) {
		checkNotNull(filiere);
		if (filiere.getId() != null) {
			throw new IdException();
		}
		checkConstraint(filiere);
		return filiereRepo.save(filiere);
	}

	private void checkConstraint(Filiere filiere) {
		if (filiere.getLibelle() == null || filiere.getLibelle().isEmpty()) {
			throw new FiliereException("libellé obligatoire");
		}
	}

	private void checkNotNull(Filiere filiere) {
		if (filiere == null) {
			throw new FiliereException("filiere obligatoire");
		}
	}

	private void checkId(Integer id) {
		if (id == null) {
			throw new IdException();
		}
	}

	private void checkExist(Filiere filiere) {
		checkId(filiere.getId());
		findById(filiere.getId());
	}

	public Filiere findById(Integer id) {
		checkId(id);
		return filiereRepo.findByIdWithReferentAndMatieres(id).orElseThrow(FormateurException::new);
	}

	public Filiere update(Filiere filiere) {
		checkNotNull(filiere);
		checkExist(filiere);
		checkConstraint(filiere);

		Filiere filiereEnBase = findById(filiere.getId());
		filiereEnBase.setLibelle(filiere.getLibelle());
		filiereEnBase.setDebut(filiere.getDebut() != null ? filiere.getDebut() : null);
		filiereEnBase.setFin(filiere.getFin() != null ? filiere.getFin() : null);
		filiereEnBase.setReferent(filiere.getReferent());

		return filiereRepo.save(filiereEnBase);
	}

	public List<Filiere> findAll() {
		return filiereRepo.findAllWithReferent();
	}

	public void delete(Filiere filiere) {
		checkExist(filiere);
		filiereRepo.delete(filiere);
	}

	public void delete(Integer id) {
		delete(findById(id));
	}
}

