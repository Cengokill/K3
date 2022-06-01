package Vue.Phase1;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

import Modeles.Acteur;
import Modeles.Joueur;
import Modeles.Piece;
import Modeles.Position;
import Vue.Menu.Chargement.TypeFenetre;

public class ecouteurClick implements MouseListener {
	
	private Phase1Panel panel;
	public String typeCurseur;
	public Cursor cursorMainFermee, cursorMainDepose;
	
	//CONSTRUTEUR--------------------------------
	public ecouteurClick(Phase1Panel panel) {
		super();
		this.panel = panel;
		setCustomCurseur();
		DragListener dragListener = new DragListener();
		this.panel.addMouseMotionListener(dragListener);
	}
	
	//CURSEUR--------------------------------
	public void setCustomCurseur() {
		Toolkit tkit=Toolkit.getDefaultToolkit();

		Image mainFermee = panel.textures.mainFermee;
		Image curseurPlus = panel.textures.mainDepose;

		Point point = new Point(0,0);
		this.cursorMainFermee = tkit.createCustomCursor(mainFermee, point, "mainFermee");
		this.cursorMainDepose = tkit.createCustomCursor(curseurPlus, point, "curseurPlus");
	}
	
	public void changeCustomCurseur() {
		if(panel.getCursor().getName() != this.typeCurseur) {
			switch(this.typeCurseur) {
			case "Default Cursor":
				panel.setCursor(new Cursor(0));
				break;
			case "mainFermee":
				//this.cursor1
				panel.setCursor(cursorMainFermee);
				break;
			case "curseurPlus":
				//this.cursor2
				panel.setCursor(cursorMainDepose);
				break;
			case "main":
				panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
				break;
			default:
				panel.setCursor(new Cursor(0));
			}
		}
	}
	
	//PYRAMIDE JOUEUR--------------------------------
	public Position clickpyramide(MouseEvent e){
		Acteur a = panel.initAffichageJoueurs();
		int startx = panel.POSX_BASE_JOUEUR;
		int starty = panel.POSY_BASE_JOUEUR;
		int realx = e.getX() - startx;
		int realy = e.getY() - starty;
		
		int y = (a.getCamp().getHauteur()-1) - realy / (panel.TAILLE_CUBES_HAUTEUR+1);
		if(y<0 || y > a.getCamp().getHauteur()) {
			return null;
		}
		
		realx-=((panel.TAILLE_CUBES_LARGEUR+1)/2)*y;
		int x = realx / (panel.TAILLE_CUBES_LARGEUR+1);
		if(realx<0 || x>=a.getCamp().getLargeur()-y) {
			return null;
		}
		return new Position(y,x);
	}
	//PIOCHE DU JOUEUR--------------------------------
	public int clickPioche(MouseEvent e){
		int startx = panel.POSX_PIOCHE;
		int starty = panel.POSY_PIOCHE;
		int realx = e.getX() - startx;
		int realy = e.getY() - starty;
		
		int x = realx / (panel.TAILLE_CUBES_LARGEUR+1);
		int y = realy / (panel.TAILLE_CUBES_HAUTEUR+1);
		if(x<0 || (x >= panel.coupure+1 && realy > (panel.TAILLE_CUBES_HAUTEUR+1)) || (x >= panel.coupure && realy < (panel.TAILLE_CUBES_HAUTEUR+1))) {
			return -1;
		}
		if(realy < 0 || realy > (panel.TAILLE_CUBES_HAUTEUR+1)*2) {
			return -1;
		}
		return x+y*panel.coupure;
	}
	//CLIC BOUTONS
	public boolean clicSettings(MouseEvent e){
		int startx = panel.posX_settings;
		int starty = panel.posY_settings;
		int hauteurBouton=panel.largeur_settings;
		int largeurBouton=panel.largeur_settings;
		if(e.getX() >= startx && e.getX() <= startx+largeurBouton && e.getY() >= starty && e.getY() <= starty+hauteurBouton) {
			return true;
		}else return false;
	}
	
	public boolean clicMelange(MouseEvent e){
		int startx = panel.posX_bouton_melange;
		int starty = panel.posY_bouton_melange;
		int hauteurBouton=panel.hauteur_bouton;
		int largeurBouton=panel.largeur_bouton;
		if(e.getX() >= startx && e.getX() <= startx+largeurBouton && e.getY() >= starty && e.getY() <= starty+hauteurBouton) {
			return true;
		}else return false;
	}
	
	public boolean clicValider(MouseEvent e){
		int startx = panel.posX_bouton_valider;
		int starty = panel.posY_bouton_valider;
		int hauteurBouton=panel.hauteur_bouton;
		int largeurBouton=panel.largeur_bouton;
		if(e.getX() >= startx && e.getX() <= startx+largeurBouton && e.getY() >= starty && e.getY() <= starty+hauteurBouton) {
			return true;
		}else return false;
	}
	
	public boolean clicRetour(MouseEvent e){
		int startx = panel.posXRetourMenu;
		int starty = panel.posYRetourMenu;
		int hauteurBouton=panel.hauteurRetourMenu;
		int largeurBouton=panel.largeurRetourMenu;
		if(e.getX() >= startx && e.getX() <= startx+largeurBouton && e.getY() >= starty && e.getY() <= starty+hauteurBouton) {
			return true;
		}else return false;
	}
	
	public boolean clicOui(MouseEvent e){
		int startx = panel.posX_oui;
		int starty = panel.posY_oui;
		int hauteurBouton=panel.hauteur_oui;
		int largeurBouton=panel.largeur_oui;
		if(e.getX() >= startx && e.getX() <= startx+largeurBouton && e.getY() >= starty && e.getY() <= starty+hauteurBouton) {
			return true;
		}else return false;
	}
	
	public boolean clicNon(MouseEvent e){
		int startx = panel.posX_non;
		int starty = panel.posY_non;
		int hauteurBouton=panel.hauteur_oui;
		int largeurBouton=panel.largeur_oui;
		if(e.getX() >= startx && e.getX() <= startx+largeurBouton && e.getY() >= starty && e.getY() <= starty+hauteurBouton) {
			return true;
		}else return false;
	}
	
	//GESTION SOURIS----------------------------------------------------------------
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(panel.partieEnCoursSet == true) {
			if(panel.popup) {
				if(clicOui(e)) {
					panel.jouerSonClic();
					panel.popup=false;
					panel.chargement.lancement = true;
					panel.chargement.setProchaineFenetre(TypeFenetre.MENU);
				}else if(clicNon(e)) {
					panel.jouerSonClic();
					panel.popup=false;
				}
			}else {
				if(clicRetour(e)) {
					panel.jouerSonClic();
					panel.popup=true;
				}
				else if(clicSettings(e)) {
					panel.jouerSonClic();
					panel.chargement.nouvellePartie=false;
					panel.chargement.lancement = true;
					panel.chargement.setProchaineFenetre(TypeFenetre.OPTION);
				}
				if(panel.initAffichageJoueurs().getClass() == Joueur.class) {
					if(e.getButton() == MouseEvent.BUTTON1) { 
						if(panel.getPieceSelectionnee() == null) { // selectionner si aucune piece lock
							int index = clickPioche(e);
							if(index>=0) {
								Acteur a = panel.initAffichageJoueurs();
								panel.setPieceSelectionnee(a.getPiecesPiochees().get(index));
							}
						}else {
							Position p = clickpyramide(e);
							if(p != null) {
								panel.empiler(p);//on empile la piece a l'endroit clique
							}
						}
					}else { // deselectionner avec clic droit
						if(panel.getPieceSelectionnee() == null) {
							Position p = clickpyramide(e);
							if(p != null) {
								Acteur a = panel.initAffichageJoueurs();
								Piece pie = a.getCamp().retirePhase1(p);
								if(pie!=null) {
									a.addPiecePiochee(pie);
								}
							}
						}else {
							panel.setPieceSelectionnee(null);
						}
					}
				}
				if(panel.initAffichageJoueurs().getClass() == Joueur.class) {
					Acteur a = panel.initAffichageJoueurs();
					if(clicMelange(e)) {
						panel.emettreSonClic();
						if(!panel.enfonce_melange) {
							panel.enfonce_melange=true;
						}
						a.melangeAleatCamp();
						panel.repaint();
					}
					if(clicValider(e) && a.getCamp().estPleine()) {
						panel.emettreSonClic();
						if(!panel.enfonce_valider) {
							panel.enfonce_valider=true;
						}
						a.valideCamp=true;
						panel.repaint();
					}
				}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		panel.enfonce_melange=false;
		panel.enfonce_valider=false;
		panel.repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	
	public class DragListener extends MouseMotionAdapter{
		public void mouseMoved(MouseEvent e) {
			if(panel.partieEnCoursSet == true) {
				if(panel.popup) {
					if(clicOui(e) || clicNon(e)) {
						typeCurseur="main";
					}else {
						typeCurseur = "Default Cursor";
					}
				}else {
					if(clicRetour(e) || clicSettings(e)) {
						typeCurseur="main";
					}else {
						typeCurseur = "Default Cursor";
					}
					if(panel.initAffichageJoueurs().getClass() == Joueur.class) {
						panel.OldX = panel.currentX;
						panel.OldY = panel.currentY;
						panel.currentX = e.getX();
						panel.currentY = e.getY();
						
						if(clickpyramide(e)!=null) {
							if(panel.getPieceSelectionnee()!=null) {
								typeCurseur="curseurPlus";
							}else {
								typeCurseur="Default Cursor";
							}
						}
						else if(clickPioche(e)!=-1 || panel.getPieceSelectionnee()!=null) {
							typeCurseur = "mainFermee";//ok
						}else if(clicMelange(e)) {
							typeCurseur="main";
						}else if(clicValider(e)) {
							typeCurseur="main";
						}
					}
				}
			}
			changeCustomCurseur();
			panel.repaint();
		}
	}

}
