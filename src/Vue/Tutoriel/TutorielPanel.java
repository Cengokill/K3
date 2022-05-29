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
	public int fenetreActuel = 1;
	public int fenetreMin = 1;
	public int fenetreMax = 4;
	
	public int posXSuivant, posYSuivant, largeurSuivant, hauteurSuivant;
	public boolean presseSuivant = false;
	
	public int posXPrecedent, posYPrecedent, largeurPrecedent, hauteurPrecedent;
	public boolean pressePrecedent = false;
	
	public int posXRetourMenu, posYRetourMenu, largeurRetourMenu, hauteurRetourMenu;
	public boolean presseRetourMenu = false;

	public int posXtuto, posYtuto, largeurTuto, hauteurTuto;
	
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
		hauteurSuivant = 80;
		
		posXPrecedent =0;
		posYPrecedent = posYSuivant+hauteurSuivant;
		largeurPrecedent = 100;
		hauteurPrecedent = 80;
		
		posXRetourMenu =0;
		posYRetourMenu = posYPrecedent+hauteurPrecedent;
		largeurRetourMenu = 100;
		hauteurRetourMenu = 80;

		posXtuto = 200;
		posYtuto = 100;
		largeurTuto = 1000;
		hauteurTuto = 600;
		
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
			g.drawImage(texture.background, 0, 0, tailleFenetre.width, tailleFenetre.height,null);   
	}
	
	// AFFICHAGE FOND D ECRAN -------------------------------------------------------------------
	public void affichageBoutonSuivant(Graphics g) {
		if(fenetreActuel < fenetreMax) {
			if(!presseSuivant) {
				g.drawImage(texture.TutoSuivant, posXSuivant, posYSuivant, largeurSuivant, hauteurSuivant, null);
			}else {
				g.drawImage(texture.TutoSuivant, posXSuivant, posYSuivant, largeurSuivant, hauteurSuivant, null);
			}
		}
	}
	// AFFICHAGE FOND D ECRAN -------------------------------------------------------------------
	public void affichageBoutonPrecedent(Graphics g) {
		if(fenetreActuel > fenetreMin) {
			if(!pressePrecedent) {
				g.drawImage(texture.TutoPrecedent, posXPrecedent, posYPrecedent, largeurPrecedent, hauteurPrecedent, null);
			}else {
				g.drawImage(texture.TutoPrecedent, posXPrecedent, posYPrecedent, largeurPrecedent, hauteurPrecedent, null);
			}
		}
	}
	// AFFICHAGE FOND D ECRAN -------------------------------------------------------------------
	public void affichageRetourMenu(Graphics g) {
		if(!presseRetourMenu) {
			g.drawImage(texture.TutoMenu, posXRetourMenu, posYRetourMenu, largeurRetourMenu, hauteurRetourMenu, null);
		}else {
			g.drawImage(texture.TutoMenu, posXRetourMenu, posYRetourMenu, largeurRetourMenu, hauteurRetourMenu, null);
		}
	}

	public void affichageTuto(Graphics g) {
		if(fenetreActuel == 1) {
			g.drawImage(texture.Tuto1, posXtuto, posYtuto, largeurTuto, hauteurTuto, null);
		}else if (fenetreActuel == 2){
			g.drawImage(texture.Tuto2, posXtuto, posYtuto, largeurTuto, hauteurTuto, null);
		}else if (fenetreActuel == 3){
			g.drawImage(texture.Tuto3, posXtuto, posYtuto, largeurTuto, hauteurTuto, null);
		}else{
			g.drawImage(texture.Tuto4, posXtuto, posYtuto, largeurTuto, hauteurTuto, null);
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
		affichageTuto(g);
	}
}
