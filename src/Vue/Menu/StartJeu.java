package Vue.Menu;

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
	public Dimension tailleEcran, tailleFenetre;	
	private JButton nouvellePartie, options, quitter;
	public final String CHEMIN = System.getProperty("user.dir")+"./src/Ressources/";
	public Image background = Toolkit.getDefaultToolkit().createImage(CHEMIN+"background.jpg");
	public Image bouton1 = Toolkit.getDefaultToolkit().createImage(CHEMIN+"cadre_non_presse2.png");
	public Image bouton1_presse = Toolkit.getDefaultToolkit().createImage(CHEMIN+"cadre_presse2.png");
    public int screenHeight, screenWidth, frameHeight, frameWidth;
    public int posX_nouvellePartie, posY_nouvellePartie, hauteur_nouvellePartie, largeur_nouvellePartie;
    public boolean enfonce_nouvellePartie=false;
    public Chargement chargement;
	
	public StartJeu(JFrame w, Chargement ch) {
    	this.chargement=ch;
		this.window = w;
		this.window.setTitle("Partie en cours");
		this.tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
        this.frameWidth=this.window.getWidth();
        this.frameHeight=this.window.getHeight();
        this.screenWidth=tailleEcran.width;
        this.screenHeight=tailleEcran.height;
		this.window.setLocation(screenWidth/2-frameWidth/2, screenHeight/2-frameHeight/2);
		window.setSize(screenWidth,screenHeight);
	    window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		window.setVisible(true);
		changementTaillefenetre();
	}

	public void paint(Graphics g) {
		if(this.tailleEcran != this.window.getSize()) {
			//on detecte un changement de fenetre -> on met a jour L IHM
			changementTaillefenetre();
		}
		affichageBackGround(g);
		afficheBoutonNouvellePartie(g);
	}
	
	public void affichageBackGround(Graphics g) {
	    g.drawImage(background, 0, 0, frameWidth, frameHeight, null);
	    repaint();
	}
	
	public void changementTaillefenetre() {
		this.tailleFenetre=window.getSize();
		this.frameWidth=window.getWidth();
        this.frameHeight=window.getHeight();
        this.largeur_nouvellePartie=(int)(814/1.5);
        this.hauteur_nouvellePartie=(int)(155/1.5);
        this.posX_nouvellePartie=frameWidth/2-largeur_nouvellePartie/2;
        this.posY_nouvellePartie=frameHeight/2-hauteur_nouvellePartie/2;
	}
	
	public void setFullScreen(boolean b) {//plein ecran
		window.setUndecorated(b);;
	}
	
	public void afficheBoutonNouvellePartie(Graphics g) {
		if(!enfonce_nouvellePartie) {
			g.drawImage(bouton1, posX_nouvellePartie, posY_nouvellePartie, largeur_nouvellePartie, hauteur_nouvellePartie, null);
		}else {
			g.drawImage(bouton1_presse, posX_nouvellePartie, posY_nouvellePartie, largeur_nouvellePartie, hauteur_nouvellePartie, null);
		}
	}
	
	public void setBoutons(Graphics g) {
		Icon icon1 = new ImageIcon(CHEMIN+"cadre_non_presse2.png");
		nouvellePartie=new JButton(icon1); 
		nouvellePartie.setBounds(frameWidth/2-814/2-56,(frameHeight/3)-155*2,814,155); 
		nouvellePartie.setOpaque(false);
		nouvellePartie.setContentAreaFilled(false);
		nouvellePartie.setBorderPainted(false);
		
		Icon icon2 = new ImageIcon(CHEMIN+"options2.png");
		options=new JButton(icon2); 
		options.setBounds(frameWidth/2-814/2-56,(frameHeight/3),814,149); 
		options.setOpaque(false);
		options.setContentAreaFilled(false);
		options.setBorderPainted(false);
		
		Icon icon3 = new ImageIcon(CHEMIN+"quitter2.png");
		quitter=new JButton(icon3); 
		quitter.setBounds(frameWidth/2-814/2-56,(frameHeight/3)+149*2,814,149); 
		quitter.setOpaque(false);
		quitter.setContentAreaFilled(false);
		quitter.setBorderPainted(false);
		
	    window.add(nouvellePartie);
	    window.add(options);
	    window.add(quitter);
	}


}
