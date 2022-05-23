package Vue.Charger;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ChargerPanel extends JPanel{
	
	// PARAMETRE JEU
	private JFrame window;
	public Dimension tailleFenetre;
	public JScrollPane scrollPane;
	public JList list;
	
	//AFFICHAGE FIXE
	public int posXScrollpanel, posYScrollpanel;
	public int largeurScrollpanel, hauteurScrollpanel;
	public String nomSauvegarde[] = { "PARTI1", "PARTI2"};
	
	public int posXBoutonLoad, posYBoutonLoad, largeurBoutonLoad, hauteurBoutonLoad;
	public boolean presseBoutonLoad = false;
			
	//COMPOSYANT IMPORTER
	public final String CHEMIN = System.getProperty("user.dir")+"/src/Ressources/";
		
	public final String NOMBACKGROUND = "background.jpg";
	public Image background = Toolkit.getDefaultToolkit().createImage(CHEMIN+NOMBACKGROUND);
	
	public final String NOMBOUTONLOADENFONCER = "chargerPartie.png";
	public Image boutonLoad = Toolkit.getDefaultToolkit().createImage(CHEMIN+NOMBOUTONLOADENFONCER);
	public final String NOMBOUTONLOADRELACHER = "chargerPartiePresse.png.png";
	public Image boutonLoadPresse = Toolkit.getDefaultToolkit().createImage(CHEMIN+NOMBOUTONLOADRELACHER);
	
	
	
	public ChargerPanel(JFrame w) {
		window = w; 
		this.tailleFenetre = window.getSize();
		
		list = new JList(nomSauvegarde);

		this.scrollPane = new JScrollPane(list);
		scrollPane.setBounds(posXScrollpanel, posYScrollpanel, largeurScrollpanel, hauteurScrollpanel);
		this.setLayout(null);
		this.add(scrollPane);
		tailleFenetre = window.getSize();
	}
	
	// FONCTION POUR REDIMENTIONNER LES ELEMENTS----------------------------------------------
	public void changementTaillefenetre() {
		tailleFenetre = window.getSize();
	
		//Position objet
		posXScrollpanel = 0;
		posYScrollpanel = 0;
		largeurScrollpanel = tailleFenetre.width/4;
		hauteurScrollpanel = tailleFenetre.height/2;
		scrollPane.setBounds(posXScrollpanel, posYScrollpanel, largeurScrollpanel, hauteurScrollpanel);
		
		posXBoutonLoad = largeurScrollpanel;
		posYBoutonLoad = 0;
		largeurBoutonLoad = tailleFenetre.width/10;
		hauteurBoutonLoad = tailleFenetre.height/10;
		
	}
	
	// AFFICHAGE***************************************************************
	
	// AFFICHAGE FOND D ECRAN -------------------------------------------------------------------
	public void affichageBackGround(Graphics g) {
	    g.drawImage(background, 0, 0, tailleFenetre.width, tailleFenetre.height,null);
	}
	
	// AFFICHAGE FOND D ECRAN -------------------------------------------------------------------
	public void affichageBoutonLoad(Graphics g) {
		if(!presseBoutonLoad) {
			g.drawImage(boutonLoad, posXBoutonLoad, posYBoutonLoad, largeurBoutonLoad, hauteurBoutonLoad, null);
		}else {
			g.drawImage(boutonLoadPresse, posXBoutonLoad, posYBoutonLoad, largeurBoutonLoad, hauteurBoutonLoad, null);
		}
		
	}
	
	
	public void paint(Graphics g) {
		if(tailleFenetre != window.getSize()) {
			changementTaillefenetre();
		}
		affichageBackGround(g);
		affichageBoutonLoad(g);
		scrollPane.paint(g);
	}
	
}
