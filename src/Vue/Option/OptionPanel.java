package Vue.Option;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Vue.Menu.Chargement;
import Vue.TexturePack.LoadTexture;

public class OptionPanel extends JPanel {
	
	// PARAMETRE JEU
	private JFrame window;
	public Dimension tailleFenetre;
	public LoadTexture texture;
	public Chargement chargement;
	
	// PARAMETRE affichage
	
	public int posXRetourMenu, posYRetourMenu, largeurRetourMenu, hauteurRetourMenu;
	public boolean oldPresseRetourMenu = false;
	public boolean presseRetourMenu = false;
	
	//SOUND
	public int POSX_sound_Label;
	public int POSY_sound_Label;
	public int LARGEUR_sound_Label;
	public int HAUTEUR_sound_Label;
	
	public int soundMin = 0;
	public int soundMax = 10;
	public int soundInit;
	public int POSX_sound;
	public int POSY_sound;
	public int LARGEUR_sound;
	public int HAUTEUR_sound;
	public JSlider slideSound;
	
	//MUSIC
	public int LARGEUR_music_Label;
	public int HAUTEUR_music_Label;
	public int POSX_music_Label;
	public int POSY_music_Label;
	
	public int musicMin = 0;
	public int musicMax = 10;
	public int musicInit;
	public int POSX_music;
	public int POSY_music;
	public int LARGEUR_music;
	public int HAUTEUR_music;
	public JSlider slideMusic;
	
	
	// CONSTRUCTEUR----------------------------------------------
	public OptionPanel(JFrame w, LoadTexture texture, Chargement chargement){
		this.setLayout(null);
		RecupParametre();
		
		this.chargement = chargement;
		this.window = w;
		this.tailleFenetre = window.getSize();
		this.texture = texture;
		
		slideSound = new JSlider(JSlider.HORIZONTAL,soundMin, soundMax, soundInit);
		slideSound.setMajorTickSpacing(5);
		slideSound.setPaintTicks(true);
		slideSound.setPaintLabels(true);
		slideMusic = new JSlider(JSlider.HORIZONTAL,soundMin, soundMax, soundInit);
		slideMusic.setMajorTickSpacing(5);
		slideMusic.setPaintTicks(true);
		slideMusic.setPaintLabels(true);
		
		slideSound.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				System.out.println("La valeur du Slider est : " + slideSound.getValue()); 
				
			}
		}); 
		slideMusic.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				System.out.println("La valeur du Slider est : " + slideMusic.getValue());
				
			}
		});
		tailleFenetre = window.getSize();
		
		
		
		changementTaillefenetre();
		
		
		this.add(slideSound);
		this.add(slideMusic);
		
		
		
	}
	
	public void RecupParametre() {
		musicInit = 5;
		soundInit = 5;
	}
	
	public void changementTaillefenetre() {
		tailleFenetre = window.getSize();
	
		//Position objet
		posXRetourMenu = 300;
		posYRetourMenu = 300;
		largeurRetourMenu = tailleFenetre.width/10;
		hauteurRetourMenu = tailleFenetre.height/10;
		
		//SLIDER
		POSX_sound =100;
		POSY_sound = 0;
		LARGEUR_sound = 100;
		HAUTEUR_sound = 50;
		slideSound.setFont(new Font("Dialog", Font.BOLD, LARGEUR_sound/10));
		slideSound.setBounds(POSX_sound, POSY_sound, LARGEUR_sound, HAUTEUR_sound);
		
		LARGEUR_sound_Label = 100;
		HAUTEUR_sound_Label = 50;
		POSX_sound_Label =POSX_sound-LARGEUR_sound_Label-10;
		POSY_sound_Label =POSY_sound;
		
		POSX_music = 100;
		POSY_music = 100;
		LARGEUR_music = 100;
		HAUTEUR_music = 50;
		slideMusic.setFont(new Font("Dialog", Font.BOLD, LARGEUR_music/10));
		slideMusic.setBounds(POSX_music, POSY_music, LARGEUR_music, HAUTEUR_music);
		
		LARGEUR_music_Label = 100;
		HAUTEUR_music_Label = 50;
		POSX_music_Label =POSX_music-LARGEUR_music-10;
		POSY_music_Label =POSY_music;
		
	}
	
	// AFFICHAGE FOND D ECRAN -------------------------------------------------------------------
	public void affichageBackGround(Graphics g) {
	    g.drawImage(texture.background, 0, 0, tailleFenetre.width, tailleFenetre.height,null);
	}
	
	// AFFICHAGE SLIDER -------------------------------------------------------------------
	public void affichageSliderSound(Graphics g) {
		g.drawRect(POSX_sound_Label, POSY_sound_Label, LARGEUR_sound_Label, HAUTEUR_sound_Label);
		slideSound.repaint();
	}
	public void affichageSliderMusic(Graphics g) {
		g.drawRect(POSX_music_Label, POSY_music_Label, LARGEUR_music_Label, HAUTEUR_music_Label);
		slideMusic.repaint();
	}
	// AFFICHAGE FOND D ECRAN -------------------------------------------------------------------
	public void affichageRetourMenu(Graphics g) {
		if(!presseRetourMenu) {
			g.drawImage(texture.menuRetour, posXRetourMenu, posYRetourMenu, largeurRetourMenu, hauteurRetourMenu, null);
		}else {
			g.drawImage(texture.menuRetour, posXRetourMenu, posYRetourMenu, largeurRetourMenu, hauteurRetourMenu, null);
		}			
	}
	
	// FONCTION POUR AFFICHER TOUT LES ELEMENTS VISUELS----------------------------------------
	public void paint(Graphics g) {
		if(tailleFenetre != window.getSize()) {
			changementTaillefenetre();
		}
		affichageBackGround(g);
		affichageSliderSound(g);
		affichageSliderMusic(g);
		affichageRetourMenu(g);
	}
	
	
}