package quest.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import quest.config.AppConfig;
import quest.model.Client;
import quest.model.TypeClient;
import quest.service.ClientService;

class ClientServiceTest {
	
	static AnnotationConfigApplicationContext ctx;
	ClientService clientSrv;
	
	@BeforeEach
	void beforeEach() {
		System.out.println("avant chaque test");
		clientSrv=ctx.getBean(ClientService.class);
	}
	
	@AfterEach
	void afterEach() {
		System.out.println("apres chaque test");
	}
	
	@BeforeAll
	static void beforeAll() {
		System.out.println("avant le 1er test");
		 ctx = new AnnotationConfigApplicationContext(AppConfig.class);

	}
	
	@AfterAll
	static void afterAll() {
		System.out.println("apres le dernier test");
		ctx.close();
	}

	@Test
	void test() {
		assertNotNull(clientSrv);
		
	}

	@Test
	void creationClientTest() {
		Client client=new Client("test", TypeClient.SARL, "lllll");
		clientSrv.create(client);
		assertNotNull(clientSrv.findById(client.getId()));
	}

}
