package tp.spring.test;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

import tp.spring.orchestre.IMusicien;

public class Principal {

	@Autowired
	private IMusicien guitariste;

	@Autowired
	private IMusicien pianiste;

	public void run(String[] args) {
		System.out.println("Quel musicien souhaitez-vous entendre ?");
		System.out.println("1 - Guitariste");
		System.out.println("2 - Pianiste");

		Scanner clavier = new Scanner(System.in);

		int choix = clavier.nextInt();

		IMusicien monMusicien = null;

		if (choix == 1) {
			monMusicien = guitariste;
		} else {
			monMusicien = pianiste;
		}

		monMusicien.jouer();

		clavier.close();
	}

}
