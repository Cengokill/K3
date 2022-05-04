package Modeles;

public class Joueur {
	private String nom;
	private Pyramide p;
	
	public Joueur(String nom) {
		this.nom=nom;
	}
	
	public void creerPyramide() {
		p=new Pyramide();
	}
	
	public void coupsJouables(Jeu j, pyramide p) {//renvoie les coups jouables du joueur
		
	}
	
	public String nomJ() {//renvoie le nom du joueur
		return this.nom;
	}
	
	public void volerPiece(Joueur j) {//vole une piece au joueur j
		
	}
	
	public void placerPiece(Piece p) {
		
	}
}