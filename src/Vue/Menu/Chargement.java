package Vue.Menu;

public class Chargement {
	public boolean lancement=false;
	private int prochainePrecedent=0;
	private int prochaineFenetre=0;
	
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
