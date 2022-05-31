package Vue.Menu;

public class Chargement {
	
	public enum TypeFenetre { 
	    MENU, NEWPARTIE, CHARGERPARTIE, OPTION, TUTO, FIN, PHASE1, PHASE2
	}
	
	public boolean lancement=false;
	private TypeFenetre prochainePrecedent=TypeFenetre.MENU;
	private TypeFenetre prochaineFenetre=TypeFenetre.MENU;
	
	public TypeFenetre getProchaineFenetre() {
		return prochaineFenetre;
	}
	
	public void setProchaineFenetre(TypeFenetre i) {
		this.prochainePrecedent = prochaineFenetre;
		this.prochaineFenetre = i;
	}
	
	public TypeFenetre getProchainePrecedent() {
		return prochainePrecedent;
	}
	
	
}
