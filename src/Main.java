import java.util.LinkedList;

import Controleur.*;
import Modeles.*;

public class Main {
	public static void main(String args[]) {
		Jeu j=new Jeu();
		LinkedList<PiecePyramide> coups=j.getBaseMontagne().piecesPosables();
		j.afficherCoups(coups);
	}
}
