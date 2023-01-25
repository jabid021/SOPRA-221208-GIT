package quest.context;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import quest.repository.IClientRepository;
import quest.repository.IOrdinateurRepository;
import quest.repository.ISalleRepository;
import quest.repository.jpa.ClientRepositoryJpa;
import quest.repository.jpa.OrdinateurRepositoryJpa;
import quest.repository.jpa.SalleRepositoryJpa;

public class Application {
	private static Application instance = null;

	private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("quest");

	private final IClientRepository clientRepo = new ClientRepositoryJpa();
	private final IOrdinateurRepository ordinateurRepo = new OrdinateurRepositoryJpa();
	private final ISalleRepository salleRepo = new SalleRepositoryJpa();

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

	public IOrdinateurRepository getOrdinateurRepo() {
		return ordinateurRepo;
	}

	public ISalleRepository getSalleRepo() {
		return salleRepo;
	}

}
