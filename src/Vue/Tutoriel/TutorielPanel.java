package Vue.Tutoriel;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;

import Vue.Menu.Chargement;
import Vue.TexturePack.LoadTexture;

public class TutorielPanel extends JPanel{
	// PARAMETRE JEU
	private JFrame window;
	public Dimension tailleFenetre;
	public LoadTexture texture;
	public Chargement chargement;
	
	// PARAMETRE AFFICHAGE
	public int fenetreActuel = 0;
	public int fenetreMin = 0;
	public int fenetreMax = 2;
	
	public int posXSuivant, posYSuivant, largeurSuivant, hauteurSuivant;
	public boolean presseSuivant = false;
	
	public int posXPrecedent, posYPrecedent, largeurPrecedent, hauteurPrecedent;
	public boolean pressePrecedent = false;
	
	public int posXRetourMenu, posYRetourMenu, largeurRetourMenu, hauteurRetourMenu;
	public boolean presseRetourMenu = false;
	
	// CONSTRUCTEUR----------------------------------------------
	public TutorielPanel(JFrame w, LoadTexture texture, Chargement chargement){
		
		this.chargement = chargement;
		this.window = w;
		this.tailleFenetre = window.getSize();
		this.texture = texture;
		tailleFenetre = window.getSize();
		
		changementTaillefenetre();
		
	}
	public void changementTaillefenetre() {
		tailleFenetre = window.getSize();
	
		//Position objet
		posXSuivant =0;
		posYSuivant = 0;
		largeurSuivant = 100;
		hauteurSuivant = 50;
		
		posXPrecedent =0;
		posYPrecedent = posYSuivant+hauteurSuivant;
		largeurPrecedent = 100;
		hauteurPrecedent = 50;
		
		posXRetourMenu =0;
		posYRetourMenu = posYPrecedent+hauteurPrecedent;
		largeurRetourMenu = 100;
		hauteurRetourMenu = 50;
		
	}
	// AFFICHAGE FOND D ECRAN -------------------------------------------------------------------
	public void tutoSuivant() {
		if(fenetreActuel<fenetreMax) {
			fenetreActuel++;
		}    
	}
	public void tutoPrecedent() {
		if(fenetreActuel>fenetreMin) {
			fenetreActuel--;
		}    
	}
		
	// AFFICHAGE FOND D ECRAN -------------------------------------------------------------------
	public void affichageBackGround(Graphics g) {
		if(fenetreActuel ==0) {
			g.drawImage(texture.background, 0, 0, tailleFenetre.width, tailleFenetre.height,null);
		}else{
			g.drawImage(texture.background, 0, 0, tailleFenetre.width, tailleFenetre.height,null);
		}    
	}
	
	// AFFICHAGE FOND D ECRAN -------------------------------------------------------------------
	public void affichageBoutonSuivant(Graphics g) {
		if(fenetreActuel < fenetreMax) {
			if(!presseSuivant) {
				g.drawImage(texture.boutonLoad, posXSuivant, posYSuivant, largeurSuivant, hauteurSuivant, null);
			}else {
				g.drawImage(texture.boutonLoadPresse, posXSuivant, posYSuivant, largeurSuivant, hauteurSuivant, null);
			}
		}
	}
	// AFFICHAGE FOND D ECRAN -------------------------------------------------------------------
	public void affichageBoutonPrecedent(Graphics g) {
		if(fenetreActuel > fenetreMin) {
			if(!pressePrecedent) {
				g.drawImage(texture.boutonLoad, posXPrecedent, posYPrecedent, largeurPrecedent, hauteurPrecedent, null);
			}else {
				g.drawImage(texture.boutonLoadPresse, posXPrecedent, posYPrecedent, largeurPrecedent, hauteurPrecedent, null);
			}
		}
	}
	// AFFICHAGE FOND D ECRAN -------------------------------------------------------------------
	public void affichageRetourMenu(Graphics g) {
		if(!presseRetourMenu) {
			g.drawImage(texture.boutonLoad, posXRetourMenu, posYRetourMenu, largeurRetourMenu, hauteurRetourMenu, null);
		}else {
			g.drawImage(texture.boutonLoadPresse, posXRetourMenu, posYRetourMenu, largeurRetourMenu, hauteurRetourMenu, null);
		}
	}
	
	public void paint(Graphics g) {
		if(tailleFenetre != window.getSize()) {
			changementTaillefenetre();
		}
		affichageBackGround(g);
		affichageBoutonSuivant(g);
		affichageBoutonPrecedent(g);
		affichageRetourMenu(g);
	}
}
