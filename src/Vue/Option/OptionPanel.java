package Vue.Option;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Modeles.GestionSons;
import Modeles.OptionsJeu;
import Vue.PanelGeneral;
import Vue.Menu.Chargement;
import Vue.TexturePack.LoadTexture;

public class OptionPanel extends PanelGeneral {
	
	// PARAMETRE JEU
	public Chargement chargement;
	public OptionsJeu options;
	public GestionSons gestionSons;
	
	// PARAMETRE affichage
	
	public int posXRetourMenu, posYRetourMenu, largeurRetourMenu, hauteurRetourMenu;
	public boolean oldPresseRetourMenu = false;
	public boolean presseRetourMenu = false;
	public Color couleur = new Color(96,132,175);
	
	//SOUND
	public int taille_police;
	public int POSX_sound_Label,POSY_sound_Label,LARGEUR_sound_Label,HAUTEUR_sound_Label;
	
	public int soundMin = 0;
	public int soundMax = 10;
	public int soundInit;
	public int POSX_sound,POSY_sound,LARGEUR_sound,HAUTEUR_sound;
	public JSlider slideSound;
	
	//MUSIC
	public int LARGEUR_music_Label,HAUTEUR_music_Label,POSX_music_Label,POSY_music_Label;
	
	public int musicMin = 0;
	public int musicMax = 10;
	public int musicInit;
	public int POSX_music,POSY_music,LARGEUR_music,HAUTEUR_music;
	public JSlider slideMusic;
	public boolean enfonce_active;
	//MODE PLEIN ECRAN
	public int posXPleinEcran, posYPleinEcran, posXActive, posYActive, largeurActive, hauteurActive;
	//LABELS
	public int largeur_label, hauteur_label;
	
	// CONSTRUCTEUR----------------------------------------------
	public OptionPanel(JFrame w, LoadTexture t, Chargement chargement, OptionsJeu o){
		super(w, t, o);
		this.chargement = chargement;
		this.options=o;
		this.gestionSons=options.gestionSons;
		this.setLayout(null);
		recupParametres();
		slideSound = new JSlider(JSlider.HORIZONTAL,soundMin, soundMax, soundInit);
		slideSound.setMajorTickSpacing(5);
		slideSound.setPaintTicks(true);
		slideSound.setPaintLabels(true);
		slideSound.setBackground(couleur);
		slideSound.setForeground(Color.WHITE);
		slideMusic = new JSlider(JSlider.HORIZONTAL,soundMin, soundMax, musicInit);
		slideMusic.setMajorTickSpacing(5);
		slideMusic.setPaintTicks(true);
		slideMusic.setPaintLabels(true);
		slideMusic.setBackground(couleur);
		slideMusic.setForeground(Color.WHITE);
		window.setVisible(true);
		this.addMouseListener(new OptionPanelClick(this));
		slideSound.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				options.setVolumeSons(slideSound.getValue());
			}
		}); 
		slideMusic.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				options.setVolumeMusique(slideMusic.getValue());
			}
		});		
		this.add(slideSound);
		this.add(slideMusic);
		
		if(o.modePleinEcran==0) {
			enfonce_active=false;
		}else {
			enfonce_active=true;
		}
	}
	
	public void recupParametres() {
		musicInit = options.volumeMusique;
		soundInit = options.volumeEffetsSonores;
	}
	
	public void ecrireOptions() {
		options.ecrireOptions();
	}
	
	public void changementTaillefenetre() {
		setChangementTaillefenetre();	
		double rapportBackMenu = 1.185567010309278;
		double rapportLabel=0.2360335195530726;
		double rapport3=0.4183168316831683;//rapport de 169/404
		int espacement = hauteur_background/8;
		//SLIDER
		LARGEUR_sound = Math.min(largeur_background/6, frameWidth/6);
		HAUTEUR_sound = (int)(LARGEUR_sound*0.2);
		POSX_sound = posX_background+largeur_background/2;
		POSY_sound = posY_background+hauteur_background/3;
		taille_police = LARGEUR_sound/17;
		Font text1= new Font("Dialog", Font.BOLD, taille_police);
		slideSound.setFont(text1);
		slideSound.setBounds(POSX_sound, POSY_sound, LARGEUR_sound, HAUTEUR_sound);
		
		POSX_music = POSX_sound;
		POSY_music = POSY_sound+espacement;
		LARGEUR_music = LARGEUR_sound;
		HAUTEUR_music = HAUTEUR_sound;
		slideMusic.setFont(text1);
		slideMusic.setBounds(POSX_music, POSY_music, LARGEUR_music, HAUTEUR_music);
		
		//labels
		largeur_label = Math.min(largeur_background/6, frameWidth/6);
		hauteur_label = (int)(largeur_label*rapportLabel);
		
		POSX_sound_Label = POSX_sound-(int)(largeur_label*1.1);
		POSY_sound_Label = POSY_sound;
		
		POSX_music_Label = POSX_sound_Label;
		POSY_music_Label = POSY_music;
		
		posXPleinEcran = POSX_music_Label;
		posYPleinEcran = POSY_music+espacement;
		
		posXActive = POSX_sound;
		posYActive = posYPleinEcran;
		
		hauteurActive=hauteur_label;
		largeurActive=(int)(hauteur_label/rapport3);
		
		//Position bouton retour
		largeurRetourMenu = Math.min(largeur_background/14, frameWidth/14);
		hauteurRetourMenu = (int)(largeurRetourMenu/rapportBackMenu);
		posXRetourMenu = frameWidth/2-largeurRetourMenu/2;
		posYRetourMenu = posYPleinEcran+(hauteurRetourMenu*2);
		
	}

	// AFFICHAGE FOND D ECRAN -------------------------------------------------------------------
	public void affichageRetourMenu(Graphics g) {
		if(!presseRetourMenu) {
			g.drawImage(texture.TutoMenu, posXRetourMenu, posYRetourMenu, largeurRetourMenu, hauteurRetourMenu, null);
		}else {
			g.drawImage(texture.TutoMenu, posXRetourMenu, posYRetourMenu, largeurRetourMenu, hauteurRetourMenu, null);
		}			
	}
	//AFFICHAGE LABELS
	public void affichageLabelSound(Graphics g) {
		g.drawImage(texture.volumeSons, POSX_sound_Label, POSY_sound_Label, largeur_label, hauteur_label, null);	
	}
	
	public void affichageLabelMusic(Graphics g) {
		g.drawImage(texture.volumeMusique, POSX_music_Label, POSY_music_Label, largeur_label, hauteur_label, null);	
	}
	
	public void affichageLabelPleinEcran(Graphics g) {
		g.drawImage(texture.modePleinEcran, posXPleinEcran, posYPleinEcran, largeur_label, hauteur_label, null);	
	}
	
	public void affichageBoutonActive(Graphics g) {
		if(!enfonce_active) {
			g.drawImage(texture.desactive, posXActive, posYActive, largeurActive, hauteurActive, null);
		}else {
			g.drawImage(texture.active, posXActive, posYActive, largeurActive, hauteurActive, null);
		}
	}
	
	// FONCTION POUR AFFICHER TOUT LES ELEMENTS VISUELS----------------------------------------
	public void paint(Graphics g) {
		changementTaillefenetre();
		affichageBackGround(g,1);
		affichageRetourMenu(g);
		affichageLabelSound(g);
		affichageLabelMusic(g);
		affichageLabelPleinEcran(g);
		affichageBoutonActive(g);
	}
	
	
}