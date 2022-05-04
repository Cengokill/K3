package Modeles;

public interface Acteur {
	
	//phase construction
	public void piocher(Piece p);
	public void construire();
	
	// phase 2
	public Coup jouer(PyramideMontagne pyramide); //pyramide au milieu
	public Position volerPiece();
	
}
