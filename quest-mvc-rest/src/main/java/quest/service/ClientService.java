package quest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import quest.exception.ClientException;
import quest.exception.IdException;
import quest.model.Client;
import quest.model.ClientId;
import quest.model.TypeClient;
import quest.repository.ClientRepository;
import quest.repository.FiliereRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepo;
	@Autowired
	private FiliereRepository filiereRepo;

	// creation client
	public Client create(String nom, TypeClient typeClient, String siret) {
		Client client = new Client(nom, typeClient);
		client.setSiret(siret);
		return create(client);
	}

	public Client create(Client client) {
		if (client.getId() == null || clientRepo.findById(client.getId()).isPresent()) {
			throw new ClientException("probleme id");
		}
		if (client.getSiret() == null || client.getSiret().isEmpty()) {
			throw new ClientException("siret absent");
		}
		if (client.getId().getNom() == null || client.getId().getNom().isEmpty()) {
			throw new ClientException("nom absent");
		}
		return clientRepo.save(client);
	}

	// suppression
	public void delete(Client client) {
		delete(client.getId());
	}

	public void delete(ClientId id) {
		Client clientEnBase=findByIdWithFiliere(id);
		clientEnBase.getFilieres().forEach(f->{
			f.setClient(null);
			filiereRepo.save(f);
		});
		clientRepo.deleteById(id);
	}

	// remontees =>select
	public List<Client> findAll() {
		return clientRepo.findAll();
	}

	public Client findById(ClientId id) {
		return clientRepo.findById(id).orElseThrow(() -> {
			throw new ClientException("id inconnu");
		});
	}

	public Client findByIdWithFiliere(ClientId id) {
		// si l'exception a un constructeur sans parametre
		return clientRepo.findbyIdWithFiliere(id).orElseThrow(IdException::new);
	}

	public Page<Client> findAll(Pageable pageable) {
		return clientRepo.findAll(pageable);
	}

	public Page<Client> findAll(int taille) {
		return findAll(0, taille);
	}

	public Page<Client> findAll(int page, int taille) {
		return findAll(PageRequest.of(page, taille));
	}

}
