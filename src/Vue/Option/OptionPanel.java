package Vue.Option;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;

import Vue.Menu.Chargement;
import Vue.TexturePack.LoadTexture;

public class OptionPanel extends JPanel {
	
	// PARAMETRE JEU
	private JFrame window;
	public Dimension tailleFenetre;
	public LoadTexture texture;
	public Chargement chargement;
	
	// PARAMETRE affichage
	public int soundMin = 0;
	public int soundMax = 10;
	public int soundInit = 2;
	public int POSX_sound =0;
	public int POSY_sound = 0;
	public int LARGEUR_sound = 100;
	public int HAUTEUR_sound = 50;
	public JSlider slideSound;
	
	public int musicMin = 0;
	public int musicMax = 10;
	public int musicInit = 2;
	public int POSX_music = 0;
	public int POSY_music = 100;
	public int LARGEUR_music = 100;
	public int HAUTEUR_music = 50;
	public JSlider slideMusic;
	
	
	// CONSTRUCTEUR----------------------------------------------
	public OptionPanel(JFrame w, LoadTexture texture, Chargement chargement){
		this.setLayout(null);
		
		
		this.chargement = chargement;
		this.window = w;
		this.tailleFenetre = window.getSize();
		this.texture = texture;
		
		slideSound = new JSlider(JSlider.HORIZONTAL,soundMin, soundMax, soundInit);
		slideMusic = new JSlider(JSlider.HORIZONTAL,soundMin, soundMax, soundInit);
		tailleFenetre = window.getSize();
		
		this.add(slideSound);
		this.add(slideMusic);
		
		changementTaillefenetre();
		
	}
	
	public void changementTaillefenetre() {
		tailleFenetre = window.getSize();
	
		//Position objet
		POSX_sound =0;
		POSY_sound = 0;
		LARGEUR_sound = 100;
		HAUTEUR_sound = 50;
		slideSound.setBounds(POSX_sound, POSY_sound, LARGEUR_sound, HAUTEUR_sound);
		
		POSX_music = 0;
		POSY_music = 100;
		LARGEUR_music = 100;
		HAUTEUR_music = 50;
		slideMusic.setBounds(POSX_music, POSY_music, LARGEUR_music, HAUTEUR_music);
		
	}
	
	// AFFICHAGE FOND D ECRAN -------------------------------------------------------------------
	public void affichageBackGround(Graphics g) {
	    g.drawImage(texture.background, 0, 0, tailleFenetre.width, tailleFenetre.height,null);
	}
	// FONCTION POUR AFFICHER TOUT LES ELEMENTS VISUELS----------------------------------------
	public void paint(Graphics g) {
		if(tailleFenetre != window.getSize()) {
			changementTaillefenetre();
		}
		affichageBackGround(g);
		slideSound.paint(g);
		slideMusic.paint(g);
	}
	
}