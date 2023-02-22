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

import quest.model.Ordinateur;
import quest.model.Views;
import quest.repository.OrdinateurRepository;

@RestController
@RequestMapping("/ordinateur")
@CrossOrigin("*")
public class OrdinateurRestController {
	@Autowired
	private OrdinateurRepository ordinateurRepository;

	@GetMapping("")
	@JsonView(Views.ViewOrdinateur.class)
	public List<Ordinateur> findAll() {
		List<Ordinateur> ordinateurs = ordinateurRepository.findAll();

		return ordinateurs;
	}

	@GetMapping("/{id}")
	@JsonView(Views.ViewOrdinateur.class)
	public Ordinateur findById(@PathVariable Integer id) {
		Optional<Ordinateur> optOrdinateur = ordinateurRepository.findById(id);

		if (optOrdinateur.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		return optOrdinateur.get();
	}

	@PostMapping("")
	@JsonView(Views.ViewOrdinateur.class)
	public Ordinateur create(@RequestBody Ordinateur ordinateur) {
		ordinateur = ordinateurRepository.save(ordinateur);

		return ordinateur;
	}

	@PutMapping("/{id}")
	@JsonView(Views.ViewOrdinateur.class)
	public Ordinateur update(@RequestBody Ordinateur ordinateur, @PathVariable Integer id) {
		if (id != ordinateur.getId()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

		if (!ordinateurRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		ordinateur = ordinateurRepository.save(ordinateur);

		return ordinateur;
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		ordinateurRepository.deleteById(id);
	}

	@GetMapping({"/orphans", "/orphans/{id}"})
	@JsonView(Views.ViewOrdinateur.class)
	public List<Ordinateur> findAllOrphans(@PathVariable(required = false) Integer id) {
		List<Ordinateur> ordinateurs = ordinateurRepository.findAllOrphans(id);

		return ordinateurs;
	}
}
