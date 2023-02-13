package quest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import quest.exception.FormateurException;
import quest.exception.IdException;
import quest.model.Adresse;
import quest.model.Formateur;
import quest.repository.FiliereRepository;
import quest.repository.FormateurRepository;

@Service
public class FormateurService {

	@Autowired
	private FormateurRepository formateurRepo;
	@Autowired
	private FiliereRepository filiereRepo;

	public Formateur create(Formateur formateur) {
		checkNotNull(formateur);
		if (formateur.getId() != null) {
			throw new IdException();
		}
		checkConstraint(formateur);
		return formateurRepo.save(formateur);
	}

	private void checkConstraint(Formateur formateur) {
		if (formateur.getPrenom() == null || formateur.getPrenom().isEmpty()) {
			throw new FormateurException("prenom obligatoire");
		}
		if (formateur.getNom() == null || formateur.getNom().isEmpty()) {
			throw new FormateurException("nom obligatoire");
		}
	}

	private void checkNotNull(Formateur formateur) {
		if (formateur == null) {
			throw new FormateurException("formateur obligatoire");
		}
	}

	private void checkId(Integer id) {
		if (id == null) {
			throw new IdException();
		}
	}

	private void checkExist(Formateur formateur) {
		checkId(formateur.getId());
		findById(formateur.getId());
	}

	public Formateur findById(Integer id) {
		checkId(id);
		return formateurRepo.findById(id).orElseThrow(FormateurException::new);
	}

	public Formateur update(Formateur formateur) {
		checkNotNull(formateur);
		checkExist(formateur);
		checkConstraint(formateur);
		Formateur formateurEnBase = findById(formateur.getId());
		formateurEnBase
				.setCivilite(formateur.getCivilite() == null ? formateurEnBase.getCivilite() : formateur.getCivilite());
		formateurEnBase.setPrenom(formateur.getPrenom());
		formateurEnBase.setNom(formateur.getNom());
		formateurEnBase.setEmail(formateur.getEmail());
		formateurEnBase.setExperience(formateur.getExperience());
		if (formateur.getAdresse() != null) {
			formateurEnBase
					.setAdresse(new Adresse(formateur.getAdresse().getRue(), formateur.getAdresse().getComplement(),
							formateur.getAdresse().getCodePostal(), formateur.getAdresse().getVille()));
		}
		// si pas d'adresse dans le formateur recu on garde l'ancienne adresse

		formateurEnBase.setExterne(formateur.isExterne());
		return formateurRepo.save(formateurEnBase);
	}

	public List<Formateur> findAll() {
		return formateurRepo.findAll();
	}

	public void delete(Formateur formateur) {
		checkExist(formateur);
		filiereRepo.setReferentToNullByReferent(formateur);
		formateurRepo.delete(formateur);
	}

	public void delete(Integer id) {
		delete(findById(id));
	}
}
