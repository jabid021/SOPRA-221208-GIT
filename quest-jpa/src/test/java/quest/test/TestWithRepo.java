package quest.test;

import java.time.LocalDate;
import java.util.List;

import quest.context.Application;
import quest.model.Adresse;
import quest.model.Civilite;
import quest.model.Client;
import quest.model.Filiere;
import quest.model.Formateur;
import quest.model.Matiere;
import quest.model.NiveauEtude;
import quest.model.Ordinateur;
import quest.model.Salle;
import quest.model.Stagiaire;
import quest.model.TypeClient;
import quest.repository.IClientRepository;
import quest.repository.IFiliereRepository;
import quest.repository.IMatiereRepository;
import quest.repository.IOrdinateurRepository;
import quest.repository.IPersonneRepository;
import quest.repository.ISalleRepository;

public class TestWithRepo {

	public static void main(String[] args) {
		IClientRepository clientRepo = Application.getInstance().getClientRepo();
		IFiliereRepository filiereRepo = Application.getInstance().getFiliereRepo();
//		IFormateurRepository formateurRepo = Application.getInstance().getFormateurRepo();
		IMatiereRepository matiereRepo = Application.getInstance().getMatiereRepo();
		IOrdinateurRepository ordinateurRepo = Application.getInstance().getOrdinateurRepo();
		IPersonneRepository personneRepo = Application.getInstance().getPersonneRepo();
		ISalleRepository salleRepo = Application.getInstance().getSalleRepo();
//		IStagiaireRepository stagiaireRepo = Application.getInstance().getStagiaireRepo();

		Client sopraSteria = new Client("SOPRA STERIA", TypeClient.SA);
		sopraSteria.setSiret("326 820 065 00083");
		sopraSteria = clientRepo.save(sopraSteria);

		Salle saphirAJC = new Salle("SAPHIR", 4, 25, true);
		saphirAJC.setAdr(new Adresse("6 rue Rougement", "1er escalier gauche", "75009", "Paris"));
		saphirAJC = salleRepo.save(saphirAJC);

		Formateur jordan = new Formateur(Civilite.M, "ABID", "Jordan", "j.abid@ajc-ingenerie.fr", false, 10);
		jordan.setAdresse(new Adresse("1 rue de la Paix", "2ème étage", "75008", "Paris"));
		jordan = (Formateur) personneRepo.save(jordan);

		Formateur eric = new Formateur(Civilite.M, "SULTAN", "Eric", "e.sultan@ajc-ingenerie.fr", false, 23);
		eric.setAdresse(new Adresse("5 rue de Paris", "RDC", "33000", "Bordeaux"));
		eric = (Formateur) personneRepo.save(eric);

		Filiere java221208 = new Filiere("JAVA / SPRING / ANGULAR", LocalDate.parse("2022-12-08"),
				LocalDate.parse("2023-03-03"));
		java221208.setClient(sopraSteria);
		java221208.setSalle(saphirAJC);
		java221208.setReferent(jordan);
		java221208 = filiereRepo.save(java221208);

		Matiere algo = new Matiere("ALGO EN JAVA", 1717);
		algo.setFiliere(java221208);
		algo.setFormateur(jordan);
		algo = matiereRepo.save(algo);

		Matiere javaObjet = new Matiere("JAVA OBJET", 9932);
		javaObjet.setFiliere(java221208);
		javaObjet.setFormateur(jordan);
		javaObjet = matiereRepo.save(javaObjet);

		Matiere jpa = new Matiere("JPA 2 AVEC HIBERNATE", 7466);
		jpa.setFiliere(java221208);
		jpa.setFormateur(eric);
		jpa = matiereRepo.save(jpa);

		Ordinateur ordiAsus = new Ordinateur("ASUS", 16);
		ordiAsus = ordinateurRepo.save(ordiAsus);

		Ordinateur ordiHP = new Ordinateur("HP", 32);
		ordiHP = ordinateurRepo.save(ordiHP);

		Stagiaire audrey = new Stagiaire(Civilite.MME, "PENAZZI", "Audrey", "audrey@gmail.com",
				LocalDate.parse("1998-01-27"), NiveauEtude.BAC_5);
		audrey.setAdresse(new Adresse("1 rue de Bordeaux", "", "31200", "Toulouse"));
		audrey.setOrdinateur(ordiAsus);
		audrey = (Stagiaire) personneRepo.save(audrey);

		Stagiaire aurelien = new Stagiaire(Civilite.M, "BULME", "Aurélien", "aurelien@gmail.com",
				LocalDate.parse("1996-07-17"), NiveauEtude.BAC_5);
		aurelien.setAdresse(new Adresse("1 rue de Toulouse", "", "33000", "Bordeaux"));
		aurelien.setOrdinateur(ordiHP);
		aurelien = (Stagiaire) personneRepo.save(aurelien);

		java221208.getStagiaires().add(audrey); // detached => pas de synchro
		java221208.getStagiaires().add(aurelien);

		java221208 = filiereRepo.save(java221208); // appeler le merge explicitement pour synchroniser

		List<Stagiaire> stagiaires = personneRepo.findAllStagiaire();

		for (Stagiaire stagiaire : stagiaires) {
			System.out.println(stagiaire.getPrenom());
		}

		List<Formateur> formateurs = personneRepo.findAllFormateur();

		for (Formateur formateur : formateurs) {
			System.out.println(formateur.getPrenom());
		}
		
		List<Filiere> filieres = filiereRepo.findAllByLibelle("JAVA / SPRING / ANGULAR");
		
		System.out.println(filieres.size());
		
		filieres = filiereRepo.findAllByNomClient("SOPRA STERIA");
		
		System.out.println(filieres.size());
		
		int size =  filiereRepo.countByReferentNom("ABID");
		
		System.out.println("count=" +size);
	}

}
