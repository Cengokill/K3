package Modeles;

public interface Acteur {
	
	//phase construction
	public void piocher(Piece p);
	public void construire();
	
	// phase 2
	public PiecePyramide jouer(PyramideMontagne pyramide); //pyramide au milieu
	
}
