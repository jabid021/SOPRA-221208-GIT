package quest.test;

import static org.junit.jupiter.api.Assertions.*;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import quest.config.AppConfig;
import quest.exception.ClientException;
import quest.model.Client;
import quest.model.ClientId;
import quest.model.TypeClient;
import quest.service.ClientService;

@SpringJUnitConfig(AppConfig.class)
@Transactional
@Rollback
class ClientServiceSpringTest {

	@Autowired
	ClientService clientSrv;

	@Test
	void injectionClientServiceTest() {
		assertNotNull(clientSrv);
	}

	@Test
	void creationClientTest() {
		Client client = new Client("test", TypeClient.SARL, "lllll");
		clientSrv.create(client);
		assertNotNull(clientSrv.findById(client.getId()));
	}

	@Test
	void findByIdTest() {
		assertThrows(ClientException.class, () -> {
			clientSrv.findById(new ClientId("ppppp", TypeClient.SARL));
		});
	}

}
