package quest.test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.convert.JodaTimeConverters.LocalDateTimeToDateConverter;

import quest.exception.ClientException;
import quest.model.Civilite;
import quest.model.Client;
import quest.model.ClientId;
import quest.model.Filiere;
import quest.model.Matiere;
import quest.model.NiveauEtude;
import quest.model.Stagiaire;
import quest.model.TypeClient;
import quest.repository.ClientRepository;
import quest.repository.FiliereRepository;
import quest.repository.MatiereRepository;
import quest.repository.StagiaireRepository;

public class Principal {

	@Autowired
	private ClientRepository clientRepo;
	@Autowired
	private StagiaireRepository stagiaireRepo;
	@Autowired
	private MatiereRepository matiereRepo;
	@Autowired
	private FiliereRepository filiereRepo;

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

		Stagiaire stagiaire = new Stagiaire(Civilite.MME, "delphine", "delphine", "delphine@delphine.com",
				LocalDate.of(2000, 1, 1), NiveauEtude.BAC_5);
		stagiaireRepo.save(stagiaire);
		Matiere matiere = new Matiere("spring", 123);

		Filiere filiere = new Filiere("sopra", LocalDate.now(), LocalDate.of(2023, 2, 5));
		Set<Stagiaire> stagiaires = new HashSet<Stagiaire>();
		stagiaires.add(stagiaire);
		filiere.setStagiaires(stagiaires);
		filiereRepo.save(filiere);
		matiere.setFiliere(filiere);
		matiereRepo.save(matiere);

		filiere = filiereRepo.findByIdWithMatieresAndStagiaire(filiere.getId()).get();
		System.out.println(filiere.getStagiaires());
		System.out.println(filiere.getMatieres());

		
		Filiere filiereDuFindAll= filiereRepo.findAll().get(0);
		System.out.println(filiere==filiereDuFindAll);
		System.out.println(filiere.equals(filiereDuFindAll));
	}

}
