package Vue.Tutoriel;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;

import Modeles.OptionsJeu;
import Vue.PanelGeneral;
import Vue.Menu.Chargement;
import Vue.TexturePack.LoadTexture;

public class TutorielPanel extends PanelGeneral{
	// PARAMETRE JEU
	private JFrame window;
	public Dimension tailleFenetre;
	public LoadTexture texture;
	public Chargement chargement;
	private OptionsJeu options;
	
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
	public TutorielPanel(JFrame w, LoadTexture texture, Chargement chargement, OptionsJeu o){
		super(w, texture, o);
		this.options=o;
		this.chargement = chargement;
		this.window = w;
		this.tailleFenetre = window.getSize();
		this.texture = texture;
		tailleFenetre = window.getSize();
		
	}
	public void changementTaillefenetre() {
		setChangementTaillefenetre();
		double rapportRetour = 1.171597633136095;
		double rapportBackMenu = 1.185567010309278;
		
		double rapport=0.500262467191601;//rapport de 953/1905
        this.largeurTuto=Math.min((int)(largeur_background/1.3), (int)(frameWidth/1.3));
        this.hauteurTuto=(int)(largeurTuto*rapport);
		//Position tutoriel
		posXtuto = frameWidth/2-largeurTuto/2;
		posYtuto = (int)(posY_background+hauteur_background/2)-hauteurTuto/2;
		
		//Dimensions bouton retour et accueil
		largeurSuivant = Math.min(largeur_background/15, frameWidth/15);
		hauteurSuivant = (int)(largeurSuivant/rapportRetour);
		largeurPrecedent=largeurSuivant;
		hauteurPrecedent=hauteurSuivant;
		largeurRetourMenu=(int)(largeurSuivant*1.1);
		hauteurRetourMenu=(int)(largeurRetourMenu/rapportBackMenu);
		//Position bouton retour
		posXSuivant = (int)(posXtuto*1.5);
		posYSuivant = posYtuto;
		
		posXPrecedent = (int)(posXtuto+largeurTuto-largeurPrecedent);
		posYPrecedent = (int)(posYSuivant+hauteurSuivant*1.5);
		//Position bouton accueil
		posXRetourMenu=frameWidth/2-largeurRetourMenu/2;
		posYRetourMenu=(int)(posYtuto+hauteurTuto*1.02);
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
	
	public void affichageBoutonSuivant(Graphics g) {
		if(fenetreActuel < fenetreMax) {
			if(!presseSuivant) {
				g.drawImage(texture.TutoSuivant, posXSuivant, posYSuivant, largeurSuivant, hauteurSuivant, null);
			}else {
				g.drawImage(texture.TutoSuivant, posXSuivant, posYSuivant, largeurSuivant, hauteurSuivant, null);
			}
		}
	}
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
		affichageBackGround(g,1);
		affichageBoutonSuivant(g);
		affichageBoutonPrecedent(g);
		affichageRetourMenu(g);
		affichageTuto(g);
	}
}
