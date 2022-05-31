
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
		String CHEMIN = "src/ressources/";
		OptionsJeu options = new OptionsJeu(CHEMIN);
		Chargement chargement = new Chargement();

		JFrame window = new JFrame("Jeu K3");

		InitPartie partie = new InitPartie();
		new Jeu(window, partie, options, chargement);
	}
}
