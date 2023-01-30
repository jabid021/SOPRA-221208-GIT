package tp.spring.test;

import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import tp.spring.config.ApplicationConfig;
import tp.spring.orchestre.IMusicien;

public class Principal {

	public static void main(String[] args) {
//		ClassPathXmlApplicationContext spring = new ClassPathXmlApplicationContext("application-context.xml");
		
		AnnotationConfigApplicationContext spring = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		

		System.out.println("Quel musicien souhaitez-vous entendre ?");
		System.out.println("1 - Guitariste");
		System.out.println("2 - Pianiste");

		Scanner clavier = new Scanner(System.in);

		int choix = clavier.nextInt();

//		IMusicien monMusicien = null;
//
//		if (choix == 1) {
//			monMusicien = spring.getBean("guitariste", IMusicien.class);
//		} else {
//			monMusicien = spring.getBean("pianiste", IMusicien.class);
//		}

		IMusicien monMusicien = spring.getBean(choix == 1 ? "guitariste" : "pianiste", IMusicien.class);

		monMusicien.jouer();

		clavier.close();
		spring.close();
	}

}
