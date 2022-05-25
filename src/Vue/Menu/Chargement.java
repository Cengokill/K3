package Vue.Menu;

public class Chargement {
	
	public enum typeFenetre { 
	    MENU, NEWPARTIE, CHARGERPARTIE, OPTION, TUTO, FENETREJEU  
	}
	
	public boolean lancement=false;
	private typeFenetre prochainePrecedent=typeFenetre.MENU;
	private typeFenetre prochaineFenetre=typeFenetre.MENU;
	
	public typeFenetre getProchaineFenetre() {
		return prochaineFenetre;
	}
	public void setProchaineFenetre(typeFenetre i) {
		this.prochainePrecedent = prochaineFenetre;
		this.prochaineFenetre = i;
	}
	public typeFenetre getProchainePrecedent() {
		return prochainePrecedent;
	}
	
	
}
