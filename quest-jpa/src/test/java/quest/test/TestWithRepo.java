package quest.test;

import quest.context.Application;
import quest.model.Ordinateur;
import quest.repository.IClientRepository;
import quest.repository.IOrdinateurRepository;
import quest.repository.ISalleRepository;

public class TestWithRepo {

	public static void main(String[] args) {
		IClientRepository clientRepo = Application.getInstance().getClientRepo();
		IOrdinateurRepository ordinateurRepo = Application.getInstance().getOrdinateurRepo();
		ISalleRepository salleRepo = Application.getInstance().getSalleRepo();

		Ordinateur ordiAsus = new Ordinateur("ASUS", 16);
		ordiAsus = ordinateurRepo.save(ordiAsus);

		Ordinateur ordiHP = new Ordinateur("HP", 32);
		ordiHP = ordinateurRepo.save(ordiHP);

	}

}
