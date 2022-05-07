package modeles;

import java.util.Random;

public class Aleatoire {
	
	 public int genInt(int borneInf, int borneSup){//renvoie un nombre aléatoire entre borneInf et borneSup compris
	    	Random rand = new Random();
	    	int n=borneInf+rand.nextInt(borneSup-borneInf+1);
	    	return n;
	    }
	 
}
