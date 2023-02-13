package quest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import quest.exception.IdException;
import quest.exception.MatiereException;
import quest.model.Matiere;
import quest.repository.MatiereRepository;

@Service
public class MatiereService {

	@Autowired
	private MatiereRepository matiereRepo;

	public Matiere create(Matiere matiere) {
		checkNotNull(matiere);
		if (matiere.getId() != null) {
			throw new IdException();
		}
		checkConstraint(matiere);
		return matiereRepo.save(matiere);
	}

	private void checkConstraint(Matiere matiere) {
		if (matiere.getLibelle() == null || matiere.getLibelle().isEmpty()) {
			throw new MatiereException("libellé obligatoire");
		}
	}

	private void checkNotNull(Matiere matiere) {
		if (matiere == null) {
			throw new MatiereException("matiere obligatoire");
		}
	}

	private void checkId(Integer id) {
		if (id == null) {
			throw new IdException();
		}
	}

	private void checkExist(Matiere matiere) {
		checkId(matiere.getId());
		findById(matiere.getId());
	}

	public Matiere findById(Integer id) {
		checkId(id);
		return matiereRepo.findById(id).orElseThrow(MatiereException::new);
	}

	public Matiere update(Matiere matiere) {
		checkNotNull(matiere);
		checkExist(matiere);
		checkConstraint(matiere);

		Matiere matiereEnBase = findById(matiere.getId());
		matiereEnBase.setLibelle(matiere.getLibelle());
		matiereEnBase.setQuest(matiere.getQuest());
		matiereEnBase.setFormateur(matiere.getFormateur());

		return matiereRepo.save(matiereEnBase);
	}

	public List<Matiere> findAll() {
		return matiereRepo.findAll();
	}

	public void delete(Matiere matiere) {
		checkExist(matiere);
		matiereRepo.delete(matiere);
	}

	public void delete(Integer id) {
		delete(findById(id));
	}
}
