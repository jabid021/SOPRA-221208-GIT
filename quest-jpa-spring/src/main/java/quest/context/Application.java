package quest.context;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import quest.repository.IClientRepository;
import quest.repository.IFiliereRepository;
import quest.repository.IMatiereRepository;
import quest.repository.IOrdinateurRepository;
import quest.repository.IPersonneRepository;
import quest.repository.ISalleRepository;
import quest.repository.jpa.ClientRepositoryJpa;
import quest.repository.jpa.FiliereRepositoryJpa;
import quest.repository.jpa.MatiereRepositoryJpa;
import quest.repository.jpa.OrdinateurRepositoryJpa;
import quest.repository.jpa.PersonneRepositoryJpa;
import quest.repository.jpa.SalleRepositoryJpa;

public class Application {
	private static Application instance = null;

	private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("quest");

	private final IClientRepository clientRepo = new ClientRepositoryJpa();
	private final IFiliereRepository filiereRepo = new FiliereRepositoryJpa();
//	private final IFormateurRepository formateurRepo = new FormateurRepositoryJpa();
	private final IMatiereRepository matiereRepo = new MatiereRepositoryJpa();
	private final IOrdinateurRepository ordinateurRepo = new OrdinateurRepositoryJpa();
	private final IPersonneRepository personneRepo = new PersonneRepositoryJpa();
	private final ISalleRepository salleRepo = new SalleRepositoryJpa();
//	private final IStagiaireRepository stagiaireRepo = new StagiaireRepositoryJpa();

	private Application() {
	}

	public static Application getInstance() {
		if (instance == null) {
			instance = new Application();
		}

		return instance;
	}

	public EntityManagerFactory getEmf() {
		return emf;
	}

	public IClientRepository getClientRepo() {
		return clientRepo;
	}

	public IFiliereRepository getFiliereRepo() {
		return filiereRepo;
	}

//	public IFormateurRepository getFormateurRepo() {
//		return formateurRepo;
//	}

	public IMatiereRepository getMatiereRepo() {
		return matiereRepo;
	}

	public IOrdinateurRepository getOrdinateurRepo() {
		return ordinateurRepo;
	}

	public IPersonneRepository getPersonneRepo() {
		return personneRepo;
	}

	public ISalleRepository getSalleRepo() {
		return salleRepo;
	}

//	public IStagiaireRepository getStagiaireRepo() {
//		return stagiaireRepo;
//	}

}
