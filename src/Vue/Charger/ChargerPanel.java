package Vue.Charger;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Controleur.Jeu;
import Modeles.InitPartie;
import Modeles.OptionsJeu;
import Vue.PanelGeneral;
import Vue.Menu.Chargement;
import Vue.TexturePack.LoadTexture;

public class ChargerPanel extends PanelGeneral{
	
	// PARAMETRE JEU
	private JFrame window;
	public JScrollPane scrollPane;
	public Chargement chargement;
	public JList list;
	public InitPartie initPartie;
	
	//AFFICHAGE FIXE
	public int posXScrollpanel, posYScrollpanel;
	public int largeurScrollpanel, hauteurScrollpanel, taille_police;
	public String nomSauvegarde[];
	
	public int posXBoutonLoad, posYBoutonLoad, largeurBoutonLoad, hauteurBoutonLoad;
	public boolean presseBoutonLoad = false;
	public boolean oldPresseBoutonLoad = false;
	
	public int posXRetourMenu, posYRetourMenu, largeurRetourMenu, hauteurRetourMenu;
	public boolean presseRetourMenu = false;
	public boolean oldPresseRetourMenu = false;
	
	private Color couleur;
			
	//COMPOSYANT IMPORTER
	public LoadTexture texture;
	
	public ChargerPanel(JFrame w, LoadTexture texture, Chargement chargement, OptionsJeu o, InitPartie i) {
		super(w, texture, o);
		this.setLayout(null);
		this.initPartie=i;
		this.chargement = chargement;
		this.window = w; 
		this.texture = texture;
		nomSauvegarde=Jeu.listerSauvegardes(System.getProperty("user.home")+ "/Desktop/Jeu_K3/Sauvegardes/");
		this.couleur = new Color(230,230,230);
		this.list = new JList(nomSauvegarde);
		this.scrollPane = new JScrollPane(list);
		this.add(scrollPane);
		changementTaillefenetre();
	}
	
	// FONCTION POUR REDIMENTIONNER LES ELEMENTS----------------------------------------------
	public void changementTaillefenetre() {
		setChangementTaillefenetre();
		//Position objet
		largeurScrollpanel = largeur_background/5;
		hauteurScrollpanel = (int)(largeurScrollpanel*0.6);
		posXScrollpanel = posX_background+largeur_background/2-largeurScrollpanel/2;
		posYScrollpanel = posY_background+(int)(hauteur_background*0.2);
		taille_police = hauteurScrollpanel/11;
		Font text1= new Font("Dialog", Font.BOLD, taille_police);
		scrollPane.setBounds(posXScrollpanel, posYScrollpanel, largeurScrollpanel, hauteurScrollpanel);
		list.setFont(text1);
		list.setBackground(couleur);
		
		double rapportBoutonLoad=0.193304535637149;//rapport de 179/926
		double rapportBackMenu = 1.185567010309278;
	       
		largeurBoutonLoad=Math.min(largeur_background/6, frameWidth/6);
		hauteurBoutonLoad=(int)(largeurBoutonLoad*rapportBoutonLoad);
		posXBoutonLoad = posX_background+largeur_background/2-largeurBoutonLoad/2;
		posYBoutonLoad = posYScrollpanel+(int)(hauteurScrollpanel*1.2);
		
		largeurRetourMenu = Math.min(largeur_background/15, frameWidth/15);
		hauteurRetourMenu = (int)(largeurRetourMenu/rapportBackMenu);
		posXRetourMenu = posX_background+largeur_background/2-largeurRetourMenu/2;
		posYRetourMenu = (int)(posYScrollpanel+hauteurScrollpanel*1.9);
		
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
		g.drawImage(texture.TutoMenu, posXRetourMenu, posYRetourMenu, largeurRetourMenu, hauteurRetourMenu, null);	
	}
	
	public void paint(Graphics g) {
		changementTaillefenetre();
		affichageBackGround(g,1);
		affichageBoutonLoad(g);
		affichageRetourMenu(g);
	}
	
}
