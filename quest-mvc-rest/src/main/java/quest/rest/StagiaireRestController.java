package quest.rest;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import quest.model.Stagiaire;
import quest.repository.StagiaireRepository;

@RestController
@RequestMapping("/stagiaire")
public class StagiaireRestController {
	@Autowired
	private StagiaireRepository stagiaireRepository;

	@GetMapping("")
	public List<Stagiaire> findAll() {
		List<Stagiaire> stagiaires = stagiaireRepository.findAll();

		return stagiaires;
	}

	@GetMapping("/{id}")
	public Stagiaire findById(@PathVariable Integer id) {
		Optional<Stagiaire> optStagiaire = stagiaireRepository.findById(id);

		if(optStagiaire.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		return optStagiaire.get();
	}
	
	@PostMapping("")
	public Stagiaire create(@RequestBody Stagiaire stagiaire) {
		stagiaire = stagiaireRepository.save(stagiaire);
		
		return stagiaire;
	}
	
	@PutMapping("/{id}")
	public Stagiaire update(@RequestBody Stagiaire stagiaire, @PathVariable Integer id) {
		if(id != stagiaire.getId()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		if(!stagiaireRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		stagiaire = stagiaireRepository.save(stagiaire);
		
		return stagiaire;
	}
	
	@PatchMapping("/{id}")
	public Stagiaire partialUpdate(@PathVariable Integer id, @RequestBody Map<String, Object> fields) {
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

		return stagiaireRepository.save(stagiaire);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		stagiaireRepository.deleteById(id);
	}
	
}
