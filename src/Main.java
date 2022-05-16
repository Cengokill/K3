
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import Controleur.*;

public class Main {
	public static void main(String args[]) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		System.out.println("K3");
		Jeu j=new Jeu("Gaston","Mademoiselle Jeanne",1);
	}
}
