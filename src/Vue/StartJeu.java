package Vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Graphics;

public class StartJeu extends JPanel {

	JFrame window;
	JButton nouvellePartie, options, quitter;
	String chemin;
	int width, height;
	final Image fondecran;
	
	public StartJeu() {
		chemin="./src/Ressources/";
		Dimension size=Toolkit.getDefaultToolkit().getScreenSize();
	    // width will store the width of the screen
	    width = (int)size.getWidth();
	     
	    // height will store the height of the screen
	    height = (int)size.getHeight();

		window = new JFrame("Jeu K3");
		window.setSize(width,height);
		int x = 0;
	    int y = 0;
	    window.setExtendedState(JFrame.MAXIMIZED_BOTH);
	    window.setLocation(x, y);
	    fondecran = Toolkit.getDefaultToolkit().createImage(chemin+"background.jpg");
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		window.setLayout(null);
		
		setBoutons();
		
		//window.pack();
		window.setVisible(true);
		repaint();
	}
	
	public void paint(Graphics g) {
		System.out.println("ejouferofj");
        super.paint(g);
        g.drawImage(this.fondecran, 0, 0, null);
	}
	
	public void setFullScreen(boolean b) {//plein ecran
		window.setUndecorated(b);;
	}
	
	public void setBoutons() {
		Icon icon1 = new ImageIcon(chemin+"nouvellePartie2.png");
		nouvellePartie=new JButton(icon1); 
		nouvellePartie.setBounds(width/2-814/2-56,(height/3)-149*2,814,149); 
		nouvellePartie.setOpaque(false);
		nouvellePartie.setContentAreaFilled(false);
		nouvellePartie.setBorderPainted(false);
		
		Icon icon2 = new ImageIcon(chemin+"options2.png");
		options=new JButton(icon2); 
		options.setBounds(width/2-814/2-56,(height/3),814,149); 
		options.setOpaque(false);
		options.setContentAreaFilled(false);
		options.setBorderPainted(false);
		
		Icon icon3 = new ImageIcon(chemin+"quitter2.png");
		quitter=new JButton(icon3); 
		quitter.setBounds(width/2-814/2-56,(height/3)+149*2,814,149); 
		quitter.setOpaque(false);
		quitter.setContentAreaFilled(false);
		quitter.setBorderPainted(false);
		
	    window.add(nouvellePartie);
	    window.add(options);
	    window.add(quitter);
		
	}

}
