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

import quest.model.Client;
import quest.model.ClientId;
import quest.model.TypeClient;
import quest.model.Views;
import quest.repository.ClientRepository;

@RestController
@RequestMapping("/client")
public class ClientRestController {
	@Autowired
	private ClientRepository clientRepository;

	@GetMapping("")
	@JsonView(Views.ViewClient.class)
	public List<Client> findAll() {
		List<Client> clients = clientRepository.findAll();

		return clients;
	}

	@GetMapping("/{nom}:{type}")
	@JsonView(Views.ViewClient.class)
	public Client findById(@PathVariable String nom, @PathVariable TypeClient type) {
		ClientId id = new ClientId(nom, type);

		Optional<Client> optClient = clientRepository.findById(id);

		if (optClient.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		return optClient.get();
	}
	
	@GetMapping("/{nom}:{type}/detail")
	@JsonView(Views.ViewClientWithFilieres.class)
	public Client detailById(@PathVariable String nom, @PathVariable TypeClient type) {
		ClientId id = new ClientId(nom, type);

		Optional<Client> optClient = clientRepository.findbyIdWithFiliere(id);

		if (optClient.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		return optClient.get();
	}

	@PostMapping("")
	@JsonView(Views.ViewClient.class)
	public Client create(@RequestBody Client client) {
		client = clientRepository.save(client);

		return client;
	}

	@PutMapping("/{nom}:{type}")
	@JsonView(Views.ViewClient.class)
	public Client update(@RequestBody Client client, @PathVariable String nom, @PathVariable TypeClient type) {
		ClientId id = new ClientId(nom, type);

		if (!id.equals(client.getId())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

		if (!clientRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		client = clientRepository.save(client);

		return client;
	}

	@DeleteMapping("/{nom}:{type}")
	public void delete(@PathVariable String nom, @PathVariable TypeClient type) {
		ClientId id = new ClientId(nom, type);

		clientRepository.deleteById(id);
	}

}
