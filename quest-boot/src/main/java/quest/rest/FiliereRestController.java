package quest.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;

import quest.model.Filiere;
import quest.model.Matiere;
import quest.model.Views;
import quest.repository.FiliereRepository;
import quest.repository.MatiereRepository;

@RestController
@RequestMapping("/filiere")
public class FiliereRestController {
	@Autowired
	private FiliereRepository filiereRepository;
	@Autowired
	private MatiereRepository matiereRepository;

	@GetMapping("")
	@JsonView(Views.ViewFiliere.class)
	public List<Filiere> findAll() {
		List<Filiere> filieres = filiereRepository.findAll();

		return filieres;
	}

	@GetMapping("/{id}")
	@JsonView(Views.ViewFiliere.class)
	public Filiere findById(@PathVariable Integer id) {
		Optional<Filiere> optFiliere = filiereRepository.findById(id);

		if (optFiliere.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		return optFiliere.get();
	}

	@GetMapping("/{id}/detail")
	@JsonView(Views.ViewFiliereWithStagiaires.class)
	public Filiere detailById(@PathVariable Integer id) {
		Optional<Filiere> optFiliere = filiereRepository.findByIdWithStagiaires(id);

		if (optFiliere.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		return optFiliere.get();
	}
	
	@GetMapping("/{id}/matieres")
	@JsonView(Views.ViewMatiere.class)
	public List<Matiere> findAll(@PathVariable Integer id) {
		List<Matiere> matieres = matiereRepository.findAllByFiliere(id);

		return matieres;
	}

	@PostMapping("")
	@JsonView(Views.ViewFiliere.class)
	public Filiere create(@RequestBody Filiere filiere) {
		filiere = filiereRepository.save(filiere);

		return filiere;
	}

	@PutMapping("/{id}")
	@JsonView(Views.ViewFiliere.class)
	public Filiere update(@RequestBody Filiere filiere, @PathVariable Integer id) {
		if (id != filiere.getId()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

		if (!filiereRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		filiere = filiereRepository.save(filiere);

		return filiere;
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		filiereRepository.deleteById(id);
	}

}
