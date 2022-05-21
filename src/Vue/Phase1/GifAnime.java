package Vue.Phase1;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GifAnime extends JFrame {
	
	public final String CHEMIN = System.getProperty("user.dir")+"/src/Ressources/";
	
	public GifAnime() {
		Icon icon = new ImageIcon(this.getClass().getResource(CHEMIN+"animation.gif"));
		JLabel label = new JLabel(icon);
		label.setSize(100,100);
		this.getContentPane().add(label);
		setSize(150,  150);
		setVisible(true);
	}
	
	public static void main(String... args) {
		new GifAnime();
	}

}
