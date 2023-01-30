package tp.spring.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import tp.spring.orchestre.IMusicien;

public class Principal {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext spring = new ClassPathXmlApplicationContext("application-context.xml");

		IMusicien monMusicien = spring.getBean(IMusicien.class);

		monMusicien.jouer();

		spring.close();
	}

}
