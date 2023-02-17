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

import quest.model.Salle;
import quest.model.SalleId;
import quest.model.Views;
import quest.repository.SalleRepository;

@RestController
@RequestMapping("/salle")
public class SalleRestController {
	@Autowired
	private SalleRepository salleRepository;

	@GetMapping("")
	@JsonView(Views.ViewSalle.class)
	public List<Salle> findAll() {
		List<Salle> salles = salleRepository.findAll();

		return salles;
	}

	@GetMapping("/{nom}:{etage}")
	@JsonView(Views.ViewSalle.class)
	public Salle findById(@PathVariable String nom, @PathVariable int etage) {
		SalleId id = new SalleId(nom, etage);

		Optional<Salle> optSalle = salleRepository.findById(id);

		if (optSalle.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		return optSalle.get();
	}

	@PostMapping("")
	@JsonView(Views.ViewSalle.class)
	public Salle create(@RequestBody Salle salle) {
		salle = salleRepository.save(salle);

		return salle;
	}

	@PutMapping("/{nom}:{etage}")
	@JsonView(Views.ViewSalle.class)
	public Salle update(@RequestBody Salle salle, @PathVariable String nom, @PathVariable int etage) {
		SalleId id = new SalleId(nom, etage);

		if (! (nom.equals(salle.getNom()) && etage == salle.getEtage()) ) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

		if (!salleRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		salle = salleRepository.save(salle);

		return salle;
	}

	@DeleteMapping("/{nom}:{etage}")
	public void delete(@PathVariable String nom, @PathVariable int etage) {
		SalleId id = new SalleId(nom, etage);

		salleRepository.deleteById(id);
	}

}
