package Vue.Charger;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Controleur.Jeu;
import Modeles.InitPartie;
import Vue.Menu.Chargement;
import Vue.TexturePack.LoadTexture;

public class ChargerPanel extends JPanel{
	
	// PARAMETRE JEU
	private JFrame window;
	public Dimension tailleFenetre;
	public JScrollPane scrollPane;
	public Chargement chargement;
	public JList list;
	public InitPartie initPartie;
	
	//AFFICHAGE FIXE
	public int posXScrollpanel, posYScrollpanel;
	public int largeurScrollpanel, hauteurScrollpanel;
	public String nomSauvegarde[];
	
	public int posXBoutonLoad, posYBoutonLoad, largeurBoutonLoad, hauteurBoutonLoad;
	public boolean presseBoutonLoad = false;
	
	public int posXRetourMenu, posYRetourMenu, largeurRetourMenu, hauteurRetourMenu;
	public boolean presseRetourMenu = false;
			
	//COMPOSYANT IMPORTER
	public LoadTexture texture;
	
	public ChargerPanel(JFrame w, LoadTexture texture, Chargement chargement, InitPartie i) {
		this.setLayout(null);
		this.initPartie=i;
		this.chargement = chargement;
		window = w; 
		this.tailleFenetre = window.getSize();
		this.texture = texture;
		nomSauvegarde=Jeu.listerSauvegardes(System.getProperty("user.home")+ "/Desktop/Jeu_K3/Sauvegardes/");
		list = new JList(nomSauvegarde);
		this.scrollPane = new JScrollPane(list);
		this.add(scrollPane);
		tailleFenetre = window.getSize();
		changementTaillefenetre();
	}
	
	// FONCTION POUR REDIMENTIONNER LES ELEMENTS----------------------------------------------
	public void changementTaillefenetre() {
		tailleFenetre = window.getSize();
	
		//Position objet
		posXScrollpanel = 0;
		posYScrollpanel = 0;
		largeurScrollpanel = tailleFenetre.width/4;
		hauteurScrollpanel = tailleFenetre.height/2;
		scrollPane.setBounds(posXScrollpanel, posYScrollpanel, largeurScrollpanel, hauteurScrollpanel);
		
		posXBoutonLoad = largeurScrollpanel;
		posYBoutonLoad = 0;
		largeurBoutonLoad = tailleFenetre.width/10;
		hauteurBoutonLoad = tailleFenetre.height/10;
		
		posXRetourMenu = largeurScrollpanel;
		posYRetourMenu = posYBoutonLoad+hauteurBoutonLoad + 100;
		largeurRetourMenu = tailleFenetre.width/10;
		hauteurRetourMenu = tailleFenetre.height/10;
		
	}
	
	// AFFICHAGE***************************************************************
	
	// AFFICHAGE FOND D ECRAN -------------------------------------------------------------------
	public void affichageBackGround(Graphics g) {
	    g.drawImage(texture.background, 0, 0, tailleFenetre.width, tailleFenetre.height,null);
	}
	
	// AFFICHAGE FOND D ECRAN -------------------------------------------------------------------
	public void affichageBoutonLoad(Graphics g) {
		if(!presseBoutonLoad) {
			g.drawImage(texture.boutonLoad, posXBoutonLoad, posYBoutonLoad, largeurBoutonLoad, hauteurBoutonLoad, null);
		}else {
			g.drawImage(texture.boutonLoadPresse, posXBoutonLoad, posYBoutonLoad, largeurBoutonLoad, hauteurBoutonLoad, null);
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
		affichageBoutonLoad(g);
		affichageRetourMenu(g);
		scrollPane.paint(g);
	}
	
}
