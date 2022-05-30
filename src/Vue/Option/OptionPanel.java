package Vue.Option;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
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
	
	//SOUND
	public int taille_police;
	public int POSX_sound_Label,POSY_sound_Label,LARGEUR_sound_Label,HAUTEUR_sound_Label,posXvolumeSons, posYvolumeSons;
	
	public int soundMin = 0;
	public int soundMax = 10;
	public int soundInit;
	public int POSX_sound,POSY_sound,LARGEUR_sound,HAUTEUR_sound;
	public JSlider slideSound;
	
	//MUSIC
	public int LARGEUR_music_Label,HAUTEUR_music_Label,POSX_music_Label,POSY_music_Label,posXvolumeMusique, posYvolumeMusique;
	
	public int musicMin = 0;
	public int musicMax = 10;
	public int musicInit;
	public int POSX_music,POSY_music,LARGEUR_music,HAUTEUR_music;
	public JSlider slideMusic;
	public boolean peutPaint=false;
	//MODE PLEIN ECRAN
	public int posXPleinEcran, posYPleinEcran;
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
		slideMusic = new JSlider(JSlider.HORIZONTAL,soundMin, soundMax, musicInit);
		slideMusic.setMajorTickSpacing(5);
		slideMusic.setPaintTicks(true);
		slideMusic.setPaintLabels(true);
		
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
		changementTaillefenetre();
		
		this.add(slideSound);
		this.add(slideMusic);
		peutPaint=true;
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
		//SLIDER
		LARGEUR_sound = 350;
		HAUTEUR_sound = 50;
		POSX_sound =frameWidth/2-LARGEUR_sound/2;
		POSY_sound = frameHeight/2;
		taille_police = LARGEUR_sound/17;
		slideSound.setFont(new Font("Dialog", Font.BOLD, taille_police));
		slideSound.setBounds(POSX_sound, POSY_sound, LARGEUR_sound, HAUTEUR_sound);
		
		POSX_music = POSX_sound;
		POSY_music = POSY_sound+HAUTEUR_sound*2;
		LARGEUR_music = LARGEUR_sound;
		HAUTEUR_music = HAUTEUR_sound;
		slideMusic.setFont(new Font("Dialog", Font.BOLD, taille_police));
		slideMusic.setBounds(POSX_music, POSY_music, LARGEUR_music, HAUTEUR_music);
		
		//labels
		largeur_label = 100;
		hauteur_label = (int)(LARGEUR_sound_Label*rapportLabel);
		
		POSX_sound_Label = POSX_sound-LARGEUR_sound_Label/2;
		POSY_sound_Label = POSY_sound;
		
		POSX_music_Label = POSX_sound_Label;
		POSY_music_Label = POSY_music;
		
		posXPleinEcran = POSX_music_Label;
		posYPleinEcran = POSY_music;
		
		//Position bouton retour
		largeurRetourMenu = Math.min(largeur_background/14, frameWidth/14);
		hauteurRetourMenu = (int)(largeurRetourMenu/rapportBackMenu);
		posXRetourMenu = frameWidth/2-largeurRetourMenu/2;
		posYRetourMenu = POSY_music_Label+(hauteurRetourMenu);
		
	}

	// AFFICHAGE SLIDER -------------------------------------------------------------------
	public void affichageSliderSound(Graphics g) {
		g.drawRect(POSX_sound_Label, POSY_sound_Label, LARGEUR_sound_Label, HAUTEUR_sound_Label);
	}
	public void affichageSliderMusic(Graphics g) {
		g.drawRect(POSX_music_Label, POSY_music_Label, LARGEUR_music_Label, HAUTEUR_music_Label);
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
		g.drawImage(texture.modePleinEcran, posXPleinEcran, posYPleinEcran, largeur_label, hauteur_label, null);	
	}
	
	public void affichageLabelMusic(Graphics g) {
		g.drawImage(texture.volumeMusique, posXvolumeMusique, posYvolumeMusique, largeur_label, hauteur_label, null);	
	}
	
	public void affichageLabelPleinEcran(Graphics g) {
		g.drawImage(texture.volumeSons, posXvolumeSons, posYvolumeSons, largeur_label, hauteur_label, null);	
	}
	
	// FONCTION POUR AFFICHER TOUT LES ELEMENTS VISUELS----------------------------------------
	public void paint(Graphics g) {
		if(peutPaint) {
			changementTaillefenetre();
			affichageBackGround(g,1);
			affichageSliderSound(g);
			affichageSliderMusic(g);
			affichageRetourMenu(g);
			affichageLabelSound(g);
			affichageLabelMusic(g);
			affichageLabelPleinEcran(g);
		}
	}
	
	
}