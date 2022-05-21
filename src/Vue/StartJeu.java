package Vue;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StartJeu extends JPanel{

	private JFrame window;
	public Dimension tailleEcran;	
	private JButton nouvellePartie, options, quitter;
	public final String CHEMIN = System.getProperty("user.dir")+"./src/Ressources/";
	public final String NOMBACKGROUND = "background.jpg";
	public Image background = Toolkit.getDefaultToolkit().createImage(CHEMIN+NOMBACKGROUND);
	int x = 0;
    int y = 0;
    public int screenHeight, screenWidth, frameHeight, frameWidth;
	
	public StartJeu() {
		this.tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
        this.frameWidth=this.window.getWidth();
        this.frameHeight=this.window.getHeight();
        this.screenWidth=tailleEcran.width;
        this.screenHeight=tailleEcran.height;
		window = new JFrame("Jeu K3");
		this.window.setLocation(screenWidth/2-frameWidth/2, screenHeight/2-frameHeight/2);
		window.setSize(screenWidth,screenHeight);
	    window.setExtendedState(JFrame.MAXIMIZED_BOTH);
	    window.setLocation(x, y);
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		window.setLayout(null);
		window.setVisible(true);
		window.setContentPane(this);
		
	}

	public void paint(Graphics g) {
		setBoutons(g);
		affichageBackGround(g);
	}
	
	public void affichageBackGround(Graphics g) {
	    g.drawImage(background, 0, 0, tailleFenetre.width, tailleFenetre.height, null);
	}
	
	public void setFullScreen(boolean b) {//plein ecran
		window.setUndecorated(b);;
	}
	
	public void setBoutons(Graphics g) {
		Icon icon1 = new ImageIcon(CHEMIN+"cadre_non_presse2.png");
		nouvellePartie=new JButton(icon1); 
		nouvellePartie.setBounds(tailleFenetre.width/2-814/2-56,(tailleFenetre.height/3)-155*2,814,155); 
		nouvellePartie.setOpaque(false);
		nouvellePartie.setContentAreaFilled(false);
		nouvellePartie.setBorderPainted(false);
		
		Icon icon2 = new ImageIcon(CHEMIN+"options2.png");
		options=new JButton(icon2); 
		options.setBounds(tailleFenetre.width/2-814/2-56,(tailleFenetre.height/3),814,149); 
		options.setOpaque(false);
		options.setContentAreaFilled(false);
		options.setBorderPainted(false);
		
		Icon icon3 = new ImageIcon(CHEMIN+"quitter2.png");
		quitter=new JButton(icon3); 
		quitter.setBounds(tailleFenetre.width/2-814/2-56,(tailleFenetre.height/3)+149*2,814,149); 
		quitter.setOpaque(false);
		quitter.setContentAreaFilled(false);
		quitter.setBorderPainted(false);
		
	    window.add(nouvellePartie);
	    window.add(options);
	    window.add(quitter);
	    this.repaint();
	}


}
