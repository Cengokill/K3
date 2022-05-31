package Vue.Menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Modeles.SoundPlayer;

public class OptionsPanel extends JPanel {
	public final String CHEMIN = System.getProperty("user.dir")+"./src/Ressources/";
	JFrame window;
	public Dimension tailleEcran, tailleFenetre;
	public int screenHeight, screenWidth, frameHeight, frameWidth;
	private SoundPlayer simpleSoundPlayerSon;
	private Color color = new Color(51,102,153);
	
	public OptionsPanel(JFrame w, Graphics g) {
		this.window = w;
		window.setTitle("Partie en cours");
		tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
        frameWidth=window.getWidth();
        frameHeight=window.getHeight();
        screenWidth=tailleEcran.width;
        screenHeight=tailleEcran.height;
		window.setLocation(screenWidth/2-frameWidth/2, screenHeight/2-frameHeight/2);
		window.setSize(screenWidth,screenHeight);
	    window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		window.setVisible(true);
		changementTaillefenetre();
		simpleSoundPlayerSon = new SoundPlayer(8);
	}
	
	 protected void paintComponent(Graphics g) {
		    super.paintComponent(g);  
		    g.drawRect(230,80,10,10);
			g.setColor(color);
			g.fillRect(230,80,10,10); 
		  }
	 
	public void paint(Graphics g) {
		if(this.tailleEcran != this.window.getSize()) {
			//on detecte un changement de fenetre -> on met a jour L IHM
			changementTaillefenetre();
		}
	}
	
	public void changementTaillefenetre() {
		this.tailleFenetre=window.getSize();
		this.frameWidth=window.getWidth();
        this.frameHeight=window.getHeight();
        this.window.setBounds(0, 0, frameWidth, frameHeight);
	    repaint();
	}
}
