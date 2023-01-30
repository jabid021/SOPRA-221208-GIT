package tp.spring.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringApplication {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext spring = new ClassPathXmlApplicationContext("application-context.xml");

//		AnnotationConfigApplicationContext spring = new AnnotationConfigApplicationContext(ApplicationConfig.class);

		Principal principal = spring.getBeanFactory().createBean(Principal.class);

		principal.run(args);

		spring.close();
	}
}
