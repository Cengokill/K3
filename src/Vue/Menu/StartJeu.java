package Vue.Menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

import Modeles.Aleatoire;
import Modeles.OptionsJeu;
import Modeles.SoundPlayer;
import Vue.PanelGeneral;
import Vue.TexturePack.LoadTexture;

public class StartJeu extends PanelGeneral implements ActionListener{

	public JpanelOptions jpanel;
    public int posX_bouton, posY_nouvellePartie, hauteur_bouton, largeur_bouton;
    public int posY_options, posY_quitter, posY_charger, posY_tuto;
    public int posX_Ile, posY_Ile, posMaxX_Ile, posMaxY_Ile, posMinX_Ile, posMinY_Ile;
    public int animX, animY;
    public boolean enfonce_nouvellePartie=false;
    public boolean enfonce_options=false;
    public boolean enfonce_tuto=false;
    public boolean enfonce_quitter=false;
    public boolean enfonce_charger=false;
    public boolean animation1=false;
    public boolean animation1_bordureX=false;
    public boolean animation1_bordureY=false;
    public Chargement chargement;
    private SoundPlayer simpleSoundPlayerSon;
    private OptionsJeu options;
    Timer animationTimer;
    Aleatoire rand = new Aleatoire();
	
	public StartJeu(JFrame w, Chargement ch, LoadTexture t, OptionsJeu o) {
		super(w, t, o);
		this.chargement=ch;
		this.options=o;
		this.setLayout(null);
		this.jpanel = new JpanelOptions();
		this.add(jpanel);
		window.setTitle("Partie en cours");
        addMouseListener(new StartJeuClics(this));
	    window.setExtendedState(JFrame.MAXIMIZED_BOTH);
	    window.setBackground(Color.BLACK);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);

		this.animationTimer = new Timer(100, this);
		this.animationTimer.start();
		animX=1;
		animY=1;
	}
	
	public void changementTaillefenetre() {
		setChangementTaillefenetre();
        double rapport=0.1924821775761504;//rapport de 297/1543
        this.largeur_bouton=Math.min(largeur_background/5, frameWidth/5);
        this.hauteur_bouton=(int)(largeur_bouton*rapport);
        int espacement = (int)(hauteur_bouton/4);
        
        this.posX_bouton=frameWidth/2-largeur_bouton/2;
        this.posY_nouvellePartie=posY_background+offset_vertical;
        this.posY_charger=posY_nouvellePartie+hauteur_bouton+espacement;
        this.posY_options=posY_charger+hauteur_bouton+espacement;
        this.posY_tuto=posY_options+hauteur_bouton+espacement;
        this.posY_quitter=posY_tuto+hauteur_bouton+espacement;
        
        if(!animation1) {
	        posX_Ile=posX_bouton-largeur_bouton;
			posY_Ile=posY_charger;
			posMaxX_Ile=posX_Ile+1;
			posMinX_Ile=posX_Ile-1;
			posMaxY_Ile=posY_Ile+4;
			posMinY_Ile=posY_Ile-4;
        }
	}
	
	public void animerIle1() {
		animation1=true;
		if(posX_Ile<posMaxX_Ile && !animation1_bordureX) {
			posX_Ile+=rand.genInt(0,animX);
		}
		else if(posX_Ile==posMaxX_Ile) {
			animation1_bordureX=true;
		}
		if(posX_Ile>posMinX_Ile && animation1_bordureX) {
			posX_Ile-=rand.genInt(0,animX);
		}
		else if(posX_Ile==posMinX_Ile) {
			animation1_bordureX=false;
		}
		if(posY_Ile<posMaxY_Ile && !animation1_bordureY) {
			posY_Ile+=animY;
		}
		else if(posY_Ile==posMaxY_Ile) {
			animation1_bordureY=true;
		}
		if(posY_Ile>posMinY_Ile && animation1_bordureY) {
			posY_Ile-=animY;
		}
		else if(posY_Ile==posMinY_Ile) {
			animation1_bordureY=false;
		}
		repaint();
	}
	
	public void setFullScreen(boolean b) {//plein ecran
		window.setUndecorated(b);;
	}
	
	public void afficheBoutonNouvellePartie(Graphics g) {
		if(!enfonce_nouvellePartie) {
			g.drawImage(texture.menuBouton1, posX_bouton, posY_nouvellePartie, largeur_bouton, hauteur_bouton, null);
		}else {
			g.drawImage(texture.menuBouton1_presse, posX_bouton, posY_nouvellePartie, largeur_bouton, hauteur_bouton, null);
		}
	}
	
	public void afficheBoutonCharger(Graphics g) {
		if(!enfonce_charger) {
			g.drawImage(texture.menuBoutonCharger, posX_bouton, posY_charger, largeur_bouton, hauteur_bouton, null);
		}else {
			g.drawImage(texture.menuBoutonCharger_presse, posX_bouton, posY_charger, largeur_bouton, hauteur_bouton, null);
		}
	}
	
	public void afficheBoutonOptions(Graphics g) {
		if(!enfonce_options) {
			g.drawImage(texture.menuBouton2, posX_bouton, posY_options, largeur_bouton, hauteur_bouton, null);
		}else {
			g.drawImage(texture.menuBouton2_presse, posX_bouton, posY_options, largeur_bouton, hauteur_bouton, null);
		}
	}
	
	public void afficheBoutonTuto(Graphics g) {
		if(!enfonce_tuto) {
			g.drawImage(texture.menuBouton3, posX_bouton, posY_tuto, largeur_bouton, hauteur_bouton, null);
		}else {
			g.drawImage(texture.menuBouton3_presse, posX_bouton, posY_tuto, largeur_bouton, hauteur_bouton, null);
		}
	}
	
	public void afficheBoutonQuitter(Graphics g) {
		if(!enfonce_quitter) {
			g.drawImage(texture.menuBouton4, posX_bouton, posY_quitter, largeur_bouton, hauteur_bouton, null);
		}else {
			g.drawImage(texture.menuBouton4_presse, posX_bouton, posY_quitter, largeur_bouton, hauteur_bouton, null);
		}
	}
	
	public void afficheIle1(Graphics g) {
		g.drawImage(texture.ile1, posX_Ile, posY_Ile, 660/3, 968/3, null);
	}
	
	public void paint(Graphics g) {
		if(tailleFenetre != window.getSize()) {
			//on detecte un changement de fenetre -> on met a jour L IHM
			changementTaillefenetre();
		}
		affichageBackGround(g,0);
		afficheIle1(g);
		afficheBoutonNouvellePartie(g);
		afficheBoutonCharger(g);
		afficheBoutonOptions(g);
		afficheBoutonTuto(g);
		afficheBoutonQuitter(g);
		jpanel.paint(g);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		animerIle1();
	}

}
