package Modeles;

import java.util.Random;

public class Aleatoire {

	public static int genInt(int borneInf, int borneSup) {// renvoie un nombre al�atoire entre borneInf et borneSup compris
		Random rand = new Random();
		int n = borneInf + rand.nextInt(borneSup - borneInf + 1);
		return n;
	}

}
