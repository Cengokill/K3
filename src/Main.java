import java.io.File;
import java.util.LinkedList;

import controleur.*;
import modeles.*;

public class Main {
	public static void main(String args[]) {
		Jeu j=new Jeu();
		
		LinkedList<PiecePyramide> coups=j.getBaseMontagne().piecesPosables();
		//j.afficherCoups(coups);
		PyramideJoueur camp1=j.joueur1().getCamp();
		PyramideJoueur camp2=j.joueur2().getCamp();
		Position pos1=new Position(0,5);
		Piece p2=camp2.getPiece(pos1);
		j.volerPiece(j.joueur1(), j.joueur2(), new PiecePyramide(p2, pos1));
		System.out.println(camp2.toString());
		
		pos1=new Position(1,4);
		p2=camp2.getPiece(pos1);
		j.volerPiece(j.joueur1(), j.joueur2(), new PiecePyramide(p2, pos1));
		System.out.println(camp2.toString());
		
		pos1=new Position(0,4);
		p2=camp2.getPiece(pos1);
		j.volerPiece(j.joueur1(), j.joueur2(), new PiecePyramide(p2, pos1));
		System.out.println(camp2.toString());
		

		String chemin="C:/Users/Killian/Desktop/AI/";
		File f = new File(chemin+"sauvegarde_001.txt");
		j.sauverPartie(chemin+"sauvegarde_001.txt");

	}
}
