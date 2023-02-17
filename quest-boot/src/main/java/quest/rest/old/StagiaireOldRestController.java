package quest.rest.old;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import quest.model.Stagiaire;
import quest.repository.StagiaireRepository;

@Controller
@RequestMapping("/old/stagiaire")
public class StagiaireOldRestController {
	@Autowired
	private StagiaireRepository stagiaireRepository;

	@GetMapping("")
	public ResponseEntity<List<Stagiaire>> findAll() {
		List<Stagiaire> stagiaires = stagiaireRepository.findAll();

		return new ResponseEntity<>(stagiaires, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Stagiaire> findById(@PathVariable Integer id) {
		Optional<Stagiaire> optStagiaire = stagiaireRepository.findById(id);

		if (optStagiaire.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(optStagiaire.get(), HttpStatus.OK);
	}

	@PostMapping("")
	public ResponseEntity<Stagiaire> create(@RequestBody Stagiaire stagiaire) {
		stagiaire = stagiaireRepository.save(stagiaire);
		
		return new ResponseEntity<>(stagiaire, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Stagiaire>  update(@RequestBody Stagiaire stagiaire, @PathVariable Integer id) {
		if(id != stagiaire.getId()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		if(!stagiaireRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		stagiaire = stagiaireRepository.save(stagiaire);
		
		return new ResponseEntity<>(stagiaire, HttpStatus.OK);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<Stagiaire> partialUpdate(@PathVariable Integer id, @RequestBody Map<String, Object> fields) {
		Optional<Stagiaire> optStagiaire = stagiaireRepository.findById(id);

		if (optStagiaire.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

		final Stagiaire stagiaire = optStagiaire.get();

//		if(fields.containsKey("nom")) {
//			stagiaire.setNom((String) fields.get("nom"));
//		}
//		
//		if(fields.containsKey("prenom")) {
//			stagiaire.setPrenom((String) fields.get("prenom"));
//		}
//
//		if(fields.containsKey("email")) {
//			stagiaire.setEmail((String) fields.get("email"));
//		}

		fields.forEach((key, value) -> {
			Field field = ReflectionUtils.findField(Stagiaire.class, key);
			if (field == null) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Propriété : " + key + "non trouvée");
			}
			ReflectionUtils.makeAccessible(field);
			ReflectionUtils.setField(field, stagiaire, value);
		});

		
		return new ResponseEntity<>(stagiaireRepository.save(stagiaire), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		stagiaireRepository.deleteById(id);
	}
}
