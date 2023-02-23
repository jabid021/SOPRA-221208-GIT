package quest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import quest.model.Roles;
import quest.model.Utilisateur;
import quest.repository.UtilisateurRepository;

@SpringBootTest
class QuestBootApplicationTests {

	@Autowired
	private UtilisateurRepository utilisateurRepo;
	
	@Test
	void contextLoads() {
		Utilisateur delphine = new Utilisateur("delphine", "123456", "LANSELLE", "Delphine", false);
		delphine.getRoles().add(Roles.STAGIAIRE);
		
		delphine = utilisateurRepo.save(delphine);
		
		Utilisateur max = new Utilisateur("max", "123456", "BLANC", "Maximilien", false);
		max.getRoles().add(Roles.STAGIAIRE);
		
		max = utilisateurRepo.save(max);
		
		Utilisateur jordan = new Utilisateur("jordan", "123456", "ABID", "Jordan", false);
		jordan.getRoles().add(Roles.FORMATEUR);
		
		jordan = utilisateurRepo.save(jordan);
		
		Utilisateur eric = new Utilisateur("eric", "123456", "SULTAN", "Eric", false);
		eric.getRoles().add(Roles.ADMIN);
		eric.getRoles().add(Roles.FORMATEUR);
		
		eric = utilisateurRepo.save(eric);
		
		Utilisateur aurelien = new Utilisateur("aurelien", "123456", "BULME", "Aurélien", false);
		aurelien.getRoles().add(Roles.ADMIN);
		
		aurelien = utilisateurRepo.save(aurelien);
		
		Utilisateur sylvain = new Utilisateur("sylvain", "123456", "BEDOURET", "Sylvain", true);
		sylvain.getRoles().add(Roles.STAGIAIRE);
		
		sylvain = utilisateurRepo.save(sylvain);
	}

}
