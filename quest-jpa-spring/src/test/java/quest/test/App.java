package quest.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import quest.config.AppConfig;

public class App {

	public static void main(String[] args) {
		//ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("application-context.xml");
		AnnotationConfigApplicationContext ctx=new AnnotationConfigApplicationContext(AppConfig.class);
		ctx.getBeanFactory().createBean(Principal.class).run();
		ctx.close();

	}

}
