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

import quest.model.Formateur;
import quest.model.Views;
import quest.repository.FormateurRepository;

@RestController
@RequestMapping("/formateur")
@CrossOrigin("*")
public class FormateurRestController {
	@Autowired
	private FormateurRepository formateurRepository;

	@GetMapping("")
	@JsonView(Views.ViewFormateur.class)
	public List<Formateur> findAll() {
		List<Formateur> formateurs = formateurRepository.findAll();

		return formateurs;
	}

	@GetMapping("/{id}")
	@JsonView(Views.ViewFormateur.class)
	public Formateur findById(@PathVariable Integer id) {
		Optional<Formateur> optFormateur = formateurRepository.findById(id);

		if (optFormateur.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		return optFormateur.get();
	}

	@PostMapping("")
	@JsonView(Views.ViewFormateur.class)
	public Formateur create(@RequestBody Formateur formateur) {
		formateur = formateurRepository.save(formateur);

		return formateur;
	}

	@PutMapping("/{id}")
	@JsonView(Views.ViewFormateur.class)
	public Formateur update(@RequestBody Formateur formateur, @PathVariable Integer id) {
		if (id != formateur.getId()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

		if (!formateurRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		formateur = formateurRepository.save(formateur);

		return formateur;
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		formateurRepository.deleteById(id);
	}

}
