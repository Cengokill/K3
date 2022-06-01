package Vue.Phase2;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

import Modeles.Couleurs;
import Modeles.Coup;
import Modeles.Piece;
import Modeles.Position;
import Vue.Menu.Chargement.TypeFenetre;

public class Phase2Click implements MouseListener {

	private PanelPhase2 panel;
	private Position retour2;
	private Position retour;
	private boolean attentepyramide = false;

	// CONSTRUTEUR--------------------------------
	public Phase2Click(PanelPhase2 panel) {
		super();
		this.panel = panel;
		this.panel.addMouseMotionListener(new DragListener());
	}

	// SAUVEGARDE--------------------------------
	public boolean clicSave(MouseEvent e) {
		int startx = panel.posX_sauvegarder;
		int starty = panel.posY_sauvegarder;
		int largeurBouton = panel.largeur_sauvegarder;
		int hauteurBouton = panel.hauteur_sauvegarder;
		if (e.getX() >= startx && e.getX() <= startx + largeurBouton && e.getY() >= starty
				&& e.getY() <= starty + hauteurBouton) {
			return true;
		} else
			return false;
	}

	public boolean clicValider(MouseEvent e) {
		int startx = panel.posX_valider;
		int starty = panel.posY_valider;
		int largeurBouton = panel.largeur_valider;
		int hauteurBouton = panel.hauteur_valider;
		if (e.getX() >= startx && e.getX() <= startx + largeurBouton && e.getY() >= starty
				&& e.getY() <= starty + hauteurBouton) {
			return true;
		} else
			return false;
	}

	public boolean clicFermer(MouseEvent e) {
		int startx = panel.posX_fermer;
		int starty = panel.posY_fermer;
		int largeurBouton = panel.largeur_fermer;
		int hauteurBouton = panel.largeur_fermer;
		if (e.getX() >= startx && e.getX() <= startx + largeurBouton && e.getY() >= starty
				&& e.getY() <= starty + hauteurBouton) {
			return true;
		} else
			return false;
	}

	public boolean clicRetour(MouseEvent e) {
		int startx = panel.posX_back;
		int starty = panel.posY_back;
		int hauteurBouton = panel.hauteur_back;
		int largeurBouton = panel.largeur_back;
		if (e.getX() >= startx && e.getX() <= startx + largeurBouton && e.getY() >= starty
				&& e.getY() <= starty + hauteurBouton) {
			return true;
		} else
			return false;
	}

	public Position clicJoueur1(MouseEvent e) {
		System.out.println("CLIQUE J1");
		Position p = new Position(-1, -1);
		int decalage = 0;
		int posX_depart = panel.posX_ileJ1 + (int) (panel.largeur_ileJ * 0.54) - ((panel.largeur_piece * 6) / 2);
		int posX = posX_depart;
		int posY = panel.posY_depart;
		for (int i = 0; i < panel.jeu.partieEnCours.joueur1().getCamp().getHauteur(); i++) { // etage
			for (int j = 0; j < (panel.jeu.partieEnCours.joueur1().getCamp().getLargeur() - i); j++) { // rang
				if (e.getX() >= posX && e.getX() < posX + panel.largeur_piece && e.getY() >= posY
						&& e.getY() < posY + panel.hauteur_piece) {
					System.out.println("Clique sur les pions du joueur 1 en : " + i + " " + j);
					p.etage = i;
					p.rang = j;
					return p;
				}
				posX += panel.largeur_piece;
			}
			decalage += panel.largeur_piece / 2;
			posY -= panel.hauteur_piece * 0.9;
			posX = posX_depart + decalage;// +decalage;
		}
		return null;

	}

	public Position clicJoueur2(MouseEvent e) {
		Position p = new Position(-1, -1);
		int decalage = 0;
		int posX_depart = panel.posX_ileJ2 + (int) (panel.largeur_ileJ * 0.49) - ((panel.largeur_piece * 6) / 2);
		int posX = posX_depart;
		int posY = panel.posY_depart;
		for (int i = 0; i < panel.jeu.partieEnCours.joueur1().getCamp().getHauteur(); i++) { // etage
			for (int j = 0; j < (panel.jeu.partieEnCours.joueur1().getCamp().getLargeur() - i); j++) { // rang
				if (e.getX() >= posX && e.getX() <= posX + panel.largeur_piece && e.getY() >= posY
						&& e.getY() <= posY + panel.hauteur_piece) {
					System.out.println("Clique sur les pions du joueur 2 en : " + i + " " + j);
					p.etage = i;
					p.rang = j;
					return p;
				}
				posX += panel.largeur_piece;

			}
			decalage += panel.largeur_piece / 2;
			posY -= panel.hauteur_piece * 0.9;
			posX = posX_depart + decalage;// +decalage;
		}
		return null;

	}

	public Position clicPyramide(MouseEvent e) {
		Position p = new Position(-1, -1);
		Position actualpos = new Position(0, 0);
		int posX_depart = panel.posX_ileM + panel.largeur_ileM / 2 - ((panel.largeur_piece * 9) / 2);
		int posX = posX_depart;
		int posY = panel.posY_depart;
		int decalage = 0;
		for (int i = 0; i < panel.jeu.partieEnCours.getBaseMontagne().getHauteur(); i++) { // etage
			for (int j = 0; j < (panel.jeu.partieEnCours.getBaseMontagne().getLargeur() - i); j++) { // rang
				if (e.getX() >= posX && e.getX() <= posX + panel.largeur_piece && e.getY() >= posY
						&& e.getY() <= posY + panel.hauteur_piece) {
					System.out.println("Clique sur les pions du Milieu en : " + i + " " + j);
					p.etage = i;
					p.rang = j;
					return p;
				}
				posX += panel.largeur_piece;

			}
			decalage += panel.largeur_piece / 2;
			posY -= panel.hauteur_piece * 0.9;
			posX = posX_depart + decalage;// +decalage;

		}
		return null;

	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (panel.jeu.partieEnCours.joueurCourant == 0 && attentepyramide == false) {
			retour = clicJoueur1(e);
			attentepyramide = true;

		}

		if (panel.jeu.partieEnCours.joueurCourant == 1 && attentepyramide == false) {
			retour2 = clicJoueur2(e);
			attentepyramide = true;

		}

		Position retourpyramide = clicPyramide(e);
		if (retourpyramide != null && attentepyramide == true) {
			attentepyramide = false;
			if (panel.jeu.partieEnCours.joueurCourant == 1 && retour2 != null) {
				System.out.println(retour2);
				Piece piecetomove = panel.jeu.partieEnCours.joueur2().getCamp().getPiece(retour2);
				if (piecetomove.getColor() == Couleurs.BLANC) {
					panel.jeu.partieEnCours.joueur2().getCamp().retirer(retour2);
					panel.jeu.partieEnCours.joueur2().validerCoup = true;
					return;

				}
				panel.jeu.partieEnCours.joueur2().setCoupDemande(new Coup(piecetomove, retour2, retourpyramide));
				panel.jeu.partieEnCours.joueur2().validerCoup = true;
				return;
			}

			if (panel.jeu.partieEnCours.joueurCourant == 0 && retour != null) {
				System.out.println(retour);
				Piece piecetomove = panel.jeu.partieEnCours.joueur1().getCamp().getPiece(retour);
				if (piecetomove.getColor() == Couleurs.BLANC) {
					System.out.println("On retire le blanc");
					panel.jeu.partieEnCours.joueur1().getCamp().retirer(retour);
					panel.jeu.partieEnCours.joueur1().validerCoup = true;
					// panel.repaint();

					return;

				}

				panel.jeu.partieEnCours.joueur1().setCoupDemande(new Coup(piecetomove, retour, retourpyramide));
				panel.jeu.partieEnCours.joueur1().validerCoup = true;

				return;
			}
		}

		if (clicSave(e)) {
			panel.popup_save = true;
		}
		if (clicRetour(e)) {
			/*
			 * panel.chargement.nouvellePartie=false;
			 * panel.chargement.lancement = true;
			 * panel.chargement.setProchaineFenetre(TypeFenetre.MENU);
			 */
		}
		if (clicFermer(e)) {
			panel.popup_save = false;
		}
		if (clicValider(e)) {
			if (!panel.nomSave.getText().isEmpty()) {
				String chemin = panel.jeu.cheminSauvegardes;
				panel.jeu.partieEnCours.sauvegarderPartie(chemin + panel.nomSave.getText() + ".saveK3");
				panel.popup_save = false;
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	public class DragListener extends MouseMotionAdapter {
		public void mouseMoved(MouseEvent e) {
			if (panel.popup_save) {
				if (clicValider(e) || clicFermer(e)) {
					panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
				} else {
					panel.setCursor(new Cursor(0));
				}
			} else if (clicSave(e)) {
				panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
			} else {
				panel.setCursor(new Cursor(0));
			}
		}
	}
}