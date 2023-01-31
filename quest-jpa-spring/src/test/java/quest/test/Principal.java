package quest.test;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import quest.exception.ClientException;
import quest.model.Client;
import quest.model.ClientId;
import quest.model.TypeClient;
import quest.repository.ClientRepository;

public class Principal {

	@Autowired
	private ClientRepository clientRepo;

	public void run() {

		System.out.println(clientRepo.count());
//		Client c=new Client("eric", TypeClient.SA);
//		clientRepo.save(c);

//		List<Client> clients= clientRepo.findAll();
//		for(Client c:clients) {
//			System.out.println(c.getId().getNom()+" "+c.getId().getType());
//		}

		clientRepo.findAll().forEach(c -> {
			System.out.println(c.getId().getNom() + " " + c.getId().getType());
		});

		Optional<Client> opt = clientRepo.findById(new ClientId("olivier", TypeClient.EURL));

		System.out.println(opt);
		if (opt.isPresent()) {
			System.out.println(opt.get());
		} else {
			System.out.println("pas de resultat");
		}

		// clientRepo.findById(new ClientId("olivier", TypeClient.EURL)).orElse(null)
//		Client recherche= clientRepo.findById(new ClientId("olivrrrier", TypeClient.EURL)).orElseThrow(()->{
//			throw new ClientException();
//		});
//		System.out.println(recherche.getId().getNom());
		
		System.out.println(clientRepo.findBySiret("aa"));
		
		System.out.println(clientRepo.findByIdNomContaining("eri"));
	}

}
