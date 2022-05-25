package Vue.Menu;

public class Chargement {
	public boolean lancement=false;
	private int prochainePrecedent=0;
	private int prochaineFenetre=0;
	
	/*
	 * menu : 0
	 * nouvelle partie : 1
	 * charger partie : 2
	 * option : 3
	 * tutoriel : 4
	 * FENETRE JEU : 5
	*/
	
	public int getProchaineFenetre() {
		return prochaineFenetre;
	}
	public void setProchaineFenetre(int i) {
		this.prochainePrecedent = prochaineFenetre;
		this.prochaineFenetre = i;
	}
	public int getProchainePrecedent() {
		return prochainePrecedent;
	}
	
	
}
