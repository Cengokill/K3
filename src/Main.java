
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import Controleur.*;
import Modeles.InitPartie;
import Modeles.OptionsJeu;
import Vue.Menu.Chargement;
import Vue.Menu.Chargement.TypeFenetre;
import Vue.Menu.StartJeu;
import Vue.Option.*;
import Vue.TexturePack.LoadTexture;
import Vue.IHM;
import Vue.NouvellePartie;
import Vue.Charger.*;
import Vue.Tutoriel.*;

public class Main {
	
	public static void main(String args[]) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		String CHEMIN = getChemin();
		LoadTexture texture = new LoadTexture(CHEMIN);
		//String chemin=System.getProperty("user.home")+ "/Desktop/Jeu_K3/";
		OptionsJeu options = new OptionsJeu(CHEMIN);
		Chargement chargement = new Chargement();
		
		JFrame window = new JFrame("Jeu K3");

		InitPartie partie = new InitPartie();
		Jeu jeu = new Jeu(window, partie, options, chargement);

		/*
		InitPartie partie = new InitPartie();
		options.gestionSons.changeMusique(43);
		options.gestionSons.playMusique();
		TypeFenetre prochaineFenetre = chargement.getProchaineFenetre();
		chargement.lancement = true;
		JFrame window = new JFrame("Jeu K3");
		window.setMinimumSize(new Dimension(960, 540));
		window.setSize(1024,768);
		window.setLocationRelativeTo(null);//centrage de la fenetre
		while(chargement.getProchaineFenetre()!=TypeFenetre.FENETREJEU) {
			if(chargement.lancement == true) {
				prochaineFenetre = chargement.getProchaineFenetre();
				chargement.lancement = false;
				if(prochaineFenetre==TypeFenetre.MENU) {
					lancementMenu(window, texture, chargement, options);
				}
				else if(prochaineFenetre==TypeFenetre.NEWPARTIE) {
					lancementNouvellePartie(window, texture, partie, chargement, options);
				}
				else if(prochaineFenetre==TypeFenetre.CHARGERPARTIE){
					lancementChargerPartie(window, texture, partie, options, chargement);
				}else if(prochaineFenetre==TypeFenetre.OPTION) {
					lancementOption(window, texture, chargement, options);
				}else if(prochaineFenetre==TypeFenetre.TUTO) {
					lancementTuto(window, texture, chargement, options);
				}
			}
		}
		window.setTitle("Partie en cours");
        window.setMinimumSize(new java.awt.Dimension(1200, 1080));
        window.setResizable(true);
        options.gestionSons.stopMusique();
		new Jeu(window, partie, texture, options, chargement);
		*/
	}
	
	public static void lancementMenu(JFrame window, LoadTexture texture, Chargement chargement, OptionsJeu options) {		
		StartJeu panel = new StartJeu(window, chargement, texture, options);
		window.setContentPane(panel);
		window.paintAll(window.getGraphics());
		while(!panel.chargement.lancement) {
			Jeu.timer(100);
		}
	}
	
	public static void lancementNouvellePartie(JFrame window, LoadTexture texture, InitPartie partie, Chargement chargement, OptionsJeu options) {
		partie.paramCharges = false;	

		NouvellePartie newPartie = new NouvellePartie(window, texture, partie, chargement, options);
		window.setContentPane(newPartie);
		window.paintAll(window.getGraphics());
		while(!chargement.lancement) {
			Jeu.timer(200);
		}
	}
	
	public static void lancementChargerPartie(JFrame window, LoadTexture texture, InitPartie partie, OptionsJeu options, Chargement chargement) {	
		partie.paramCharges = false;
		ChargerPanel panel = new ChargerPanel(window, texture, chargement, options, partie);
		window.setContentPane(panel);
		panel.addMouseListener(new ChargementClick(panel));
		window.paintAll(window.getGraphics());
		while(!chargement.lancement) {
			Jeu.timer(100);
		}
	}
	
	public static void lancementOption(JFrame window, LoadTexture texture, Chargement chargement, OptionsJeu options) {	
		OptionPanel panel = new OptionPanel(window,texture, chargement, options);
		window.setContentPane(panel);
		window.paintAll(window.getGraphics());
		while(!chargement.lancement) {
			Jeu.timer(100);
		}
	}
	
	public static void lancementTuto(JFrame window, LoadTexture texture, Chargement chargement, OptionsJeu options) {	
		TutorielPanel panel = new TutorielPanel(window,texture, chargement, options);
		window.setContentPane(panel);
		panel.addMouseListener(new TutorielClick(panel));
		window.paintAll(window.getGraphics());
		while(!chargement.lancement) {
			Jeu.timer(100);
		}
	}
	
	public static String getChemin() {
		String r="";
		String path = "src/Ressources/";
		File jarFile = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath());

		if(jarFile.isFile()) {  // si .jar execute
		    JarFile jar;
			try {
				jar = new JarFile(jarFile);
				Enumeration<JarEntry> entries = jar.entries(); //gives ALL entries in jar
			    while(entries.hasMoreElements()) {
			        String name = entries.nextElement().getName();
			        if (name.startsWith(path + "/")) { //filter according to the path
			            System.out.println(name);
			        }
			    }
			    try {
					jar.close();
				} catch (IOException e) {
					System.err.println("Erreur");
					e.printStackTrace();
				}
			} catch (IOException e1) {
				System.err.println("Erreur");
				e1.printStackTrace();
			}
		    
		} else { // Run avec IDE
		    URL url = Main.class.getClassLoader().getResource("Ressources/");
		    if (url != null) {
		        try {
		            final File apps = new File(url.toURI());
		            r = apps.toString()+"/";
		        } catch (URISyntaxException ex) {
		        	System.err.println("Erreur");
		        }
		        System.out.println("chemin de l'IDE");
		    }else {
		    	System.err.println("Erreur url est nul.");
		    }
		}
		System.out.println(r);
		return r;
	}
}
