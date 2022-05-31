
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

public class Main {
	
	public static void main(String args[]) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		String CHEMIN = getChemin();
		OptionsJeu options = new OptionsJeu(CHEMIN);
		Chargement chargement = new Chargement();
		
		JFrame window = new JFrame("Jeu K3");

		InitPartie partie = new InitPartie();
		new Jeu(window, partie, options, chargement);
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
