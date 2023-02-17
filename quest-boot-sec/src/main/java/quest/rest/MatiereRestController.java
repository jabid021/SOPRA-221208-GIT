package quest.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import quest.model.Matiere;
import quest.model.Views;
import quest.repository.MatiereRepository;

@RestController
@RequestMapping("/matiere")
@CrossOrigin("*")
public class MatiereRestController {
	@Autowired
	private MatiereRepository matiereRepository;

	@GetMapping("")
	@JsonView(Views.ViewMatiere.class)
	public List<Matiere> findAll() {
		List<Matiere> matieres = matiereRepository.findAll();

		return matieres;
	}

	@GetMapping("/{id}")
	@JsonView(Views.ViewMatiere.class)
	public Matiere findById(@PathVariable Integer id) {
		Optional<Matiere> optMatiere = matiereRepository.findById(id);

		if (optMatiere.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		return optMatiere.get();
	}
	
	@GetMapping("/by-filiere/{id}")
	@JsonView(Views.ViewMatiere.class)
	public List<Matiere> findAllByFiliere(@PathVariable Integer id) {
		List<Matiere> matieres = matiereRepository.findAllByFiliere(id);
		
		return matieres;
	}

	@PostMapping("")
	@JsonView(Views.ViewMatiere.class)
	public Matiere create(@RequestBody Matiere matiere) {
		matiere = matiereRepository.save(matiere);

		return matiere;
	}

	@PutMapping("/{id}")
	@JsonView(Views.ViewMatiere.class)
	public Matiere update(@RequestBody Matiere matiere, @PathVariable Integer id) {
		if (id != matiere.getId()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

		if (!matiereRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		matiere = matiereRepository.save(matiere);

		return matiere;
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		matiereRepository.deleteById(id);
	}

}
