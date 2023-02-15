package quest.test;

import static org.junit.jupiter.api.Assertions.*;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import quest.config.AppConfig;
import quest.exception.FormateurException;
import quest.model.Adresse;
import quest.model.Civilite;
import quest.model.Formateur;
import quest.service.FormateurService;

@Transactional
@Rollback
@SpringJUnitConfig(AppConfig.class)
class FormateurServiceTest {
	@Autowired
	FormateurService formateurSrv;

	@Test
	void testCreate() {
		Formateur formateur = new Formateur(Civilite.M, "gozlan", "olivier", "olivier", false, 1);
		formateurSrv.create(formateur);
		assertNotNull(formateurSrv.findById(formateur.getId()));
		assertThrows(FormateurException.class, () -> {
			formateurSrv.create(new Formateur());
		});
	}

	@Test
	void testUpdate() {
		Formateur formateur = new Formateur(Civilite.M, "gozlan", "olivier", "olivier", false, 1);
		formateurSrv.create(formateur);
		formateur.setExperience(10);
		formateur.setAdresse(new Adresse("rue", "complement", "11111", "paris"));
		formateurSrv.update(formateur);
		formateur = formateurSrv.findById(formateur.getId());
		assertEquals("rue", formateur.getAdresse().getRue());
		assertEquals("complement", formateur.getAdresse().getComplement());
		// ...mettre les autres assertEquals pour l'adresse
		assertEquals(10, formateur.getExperience());

	}

	@Test
	void testFindAll() {
		assertTrue(formateurSrv.findAll().isEmpty());
		Formateur formateur = new Formateur(Civilite.M, "gozlan", "olivier", "olivier", false, 1);
		formateurSrv.create(formateur);

		assertEquals(1, formateurSrv.findAll().size());

	}

	@Test
	void testDeleteFormateur() {
		Formateur formateur = new Formateur(Civilite.M, "gozlan", "olivier", "olivier", false, 1);
		formateurSrv.create(formateur);
		formateurSrv.delete(formateur);
		assertThrows(FormateurException.class, () -> {
			formateurSrv.findById(formateur.getId());
		});
	}

	@Test
	void testDeleteInteger() {
		Formateur formateur = new Formateur(Civilite.M, "gozlan", "olivier", "olivier", false, 1);
		formateurSrv.create(formateur);
		formateurSrv.delete(formateur.getId());
		assertThrows(FormateurException.class, () -> {
			formateurSrv.findById(formateur.getId());
		});
	}

}
