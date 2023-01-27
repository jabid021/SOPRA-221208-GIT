package quest.test;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import quest.context.Application;
import quest.model.Adresse;
import quest.model.Civilite;
import quest.model.Client;
import quest.model.Filiere;
import quest.model.Formateur;
import quest.model.NiveauEtude;
import quest.model.Salle;
import quest.model.Stagiaire;
import quest.model.TypeClient;
import quest.repository.IClientRepository;
import quest.repository.IFiliereRepository;
import quest.repository.IPersonneRepository;
import quest.repository.ISalleRepository;

public class FiliereTest {

	private static IClientRepository clientRepo;
	private static IFiliereRepository filiereRepo;
	private static IPersonneRepository personneRepo;
	private static ISalleRepository salleRepo;

	@BeforeClass
	public static void initAllTest() {
		clientRepo = Application.getInstance().getClientRepo();
		filiereRepo = Application.getInstance().getFiliereRepo();
		personneRepo = Application.getInstance().getPersonneRepo();
		salleRepo = Application.getInstance().getSalleRepo();
	}

	@Test
	public void creation() {
		// Arrange
		Client sopraSteria = new Client("SOPRA STERIA", TypeClient.SA);
		sopraSteria = clientRepo.save(sopraSteria);

		Salle saphirAJC = new Salle("SAPHIR", 4, 25, true);
		saphirAJC = salleRepo.save(saphirAJC);

		Formateur jordan = new Formateur(Civilite.M, "ABID", "Jordan", "j.abid@ajc-ingenerie.fr", false, 10);
		jordan = (Formateur) personneRepo.save(jordan);

		Stagiaire audrey = new Stagiaire(Civilite.MME, "PENAZZI", "Audrey", "audrey@gmail.com",
				LocalDate.parse("1998-01-27"), NiveauEtude.BAC_5);
		audrey = (Stagiaire) personneRepo.save(audrey);

		// Act
		Filiere java221208 = new Filiere("JAVA / SPRING / ANGULAR", LocalDate.parse("2022-12-08"),
				LocalDate.parse("2023-03-03"));
		java221208.setClient(sopraSteria);
		java221208.setSalle(saphirAJC);
		java221208.setReferent(jordan);
		java221208.getStagiaires().add(audrey);
		java221208 = filiereRepo.save(java221208);

		// Assert
		Filiere java221208Find = filiereRepo.findByIdWithStagiaires(java221208.getId());
		Assert.assertEquals("JAVA / SPRING / ANGULAR", java221208Find.getLibelle());
		Assert.assertEquals(LocalDate.parse("2022-12-08"), java221208Find.getDebut());
		Assert.assertEquals(LocalDate.parse("2023-03-03"), java221208Find.getFin());
		Assert.assertEquals(sopraSteria.getId(), java221208Find.getClient().getId());
		Assert.assertEquals(saphirAJC.getNom(), java221208Find.getSalle().getNom());
		Assert.assertEquals(saphirAJC.getEtage(), java221208Find.getSalle().getEtage());
		Assert.assertEquals(1, java221208Find.getStagiaires().size());
	}

	@Test
	public void miseajour() {
		// Arrange
		Client sopraSteria = new Client("SOPRA-STERIA", TypeClient.SA);
		sopraSteria = clientRepo.save(sopraSteria);
		
		Client ajc = new Client("AJC", TypeClient.SARL);
		ajc = clientRepo.save(ajc);

		Salle saphirAJC = new Salle("SAPHIRA", 4, 25, true);
		saphirAJC = salleRepo.save(saphirAJC);
		
		Salle topazeAJC = new Salle("TOPAZA", 4, 18, true);
		topazeAJC = salleRepo.save(topazeAJC);

		Formateur jordan = new Formateur(Civilite.M, "ABIDOU", "Jordan", "j.abid@ajc-ingenerie.fr", false, 10);
		jordan = (Formateur) personneRepo.save(jordan);
		
		Formateur eric = new Formateur(Civilite.M, "SULTANINOU", "Eric", "e.sultan@ajc-ingenerie.fr", false, 23);
		eric = (Formateur) personneRepo.save(eric);

		Stagiaire audrey = new Stagiaire(Civilite.MME, "PENAZZIa", "Audrey", "audrey@gmail.com",
				LocalDate.parse("1998-01-27"), NiveauEtude.BAC_5);
		audrey = (Stagiaire) personneRepo.save(audrey);
		
		Stagiaire nicolas = new Stagiaire(Civilite.M, "JOSEPHa", "Nicolas", "nicolas@gmail.com",
				LocalDate.parse("1998-02-10"), NiveauEtude.BAC_5);
		nicolas = (Stagiaire) personneRepo.save(nicolas);

		Stagiaire aurelien = new Stagiaire(Civilite.M, "BULMEa", "Aurélien", "aurelien@gmail.com",
				LocalDate.parse("1996-07-17"), NiveauEtude.BAC_5);
		aurelien = (Stagiaire) personneRepo.save(aurelien);
		
		Filiere java221208 = new Filiere("JAVA / SPRING / ANGULAR", LocalDate.parse("2022-12-08"),
				LocalDate.parse("2023-03-03"));
		java221208.setClient(sopraSteria);
		java221208.setSalle(saphirAJC);
		java221208.setReferent(jordan);
		java221208.getStagiaires().add(audrey);
		
		java221208 = filiereRepo.save(java221208);

		// Act
		Filiere java221208Find = filiereRepo.findByIdWithStagiaires(java221208.getId());
		java221208Find.setLibelle("JAVA / SPRING / VUEJS");
		java221208Find.setDebut(LocalDate.parse("2022-12-10"));
		java221208Find.setFin(LocalDate.parse("2023-03-05"));
		java221208Find.setClient(ajc);
		java221208Find.setSalle(topazeAJC);
		java221208Find.setReferent(eric);
		java221208Find.getStagiaires().add(nicolas);
		java221208Find.getStagiaires().add(aurelien);
		
		java221208Find = filiereRepo.save(java221208Find);
		

		// Assert
		java221208Find = filiereRepo.findByIdWithStagiaires(java221208Find.getId());
		Assert.assertEquals("JAVA / SPRING / VUEJS", java221208Find.getLibelle());
		Assert.assertEquals(LocalDate.parse("2022-12-10"), java221208Find.getDebut());
		Assert.assertEquals(LocalDate.parse("2023-03-05"), java221208Find.getFin());
		Assert.assertEquals(ajc.getId(), java221208Find.getClient().getId());
		Assert.assertEquals(topazeAJC.getNom(), java221208Find.getSalle().getNom());
		Assert.assertEquals(topazeAJC.getEtage(), java221208Find.getSalle().getEtage());
		Assert.assertEquals(3, java221208Find.getStagiaires().size());
		
		// Clean
	}

	@Test
	public void bidon() { // Arrange - Act - Assert
		// Arrange
		String str = "Eric Sultan";

		// Act
		String strUpper = str.toUpperCase();

		// Assert
		Assert.assertEquals("ERIC SULTAN", strUpper);
	}
}
