package Modeles;

import java.util.ArrayList;
import java.util.Random;

public class Aleatoire {
	
	 public int genInt(int borneInf, int borneSup){//renvoie un nombre aléatoire entre borneInf et borneSup compris
	    	Random rand = new Random();
	    	int n=borneInf+rand.nextInt(borneSup-borneInf+1);
	    	return n;
	    }
	 
	 public ArrayList<Piece> ArrayShuffle(ArrayList<Piece> arr){
	    	Random rand = new Random();
	    	int taille=arr.size();
	    	ArrayList<Piece> arrNew = new ArrayList<Piece>();
	    	for(int i=0; i<taille ; i++) {
	    		
	    	}
	    	return arr;
	    	
	    }
	 
}
