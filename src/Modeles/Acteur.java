package Modeles;

import java.util.ArrayList;

public interface Acteur {

	// phase construction
	public Piece piocherPiece(ArrayList<Piece> sac);

	// phase 2
	public PiecePyramide jouer(PyramideMontagne p); // pyramide au milieu

}
