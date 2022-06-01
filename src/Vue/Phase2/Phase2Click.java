package Vue.Phase2;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

import Modeles.*;

public class Phase2Click implements MouseListener {

	public PanelPhase2 panel;

	// CONSTRUTEUR--------------------------------
	public Phase2Click(PanelPhase2 panel) {
		super();
		this.panel = panel;
		DragListener dragListener = new DragListener();
		this.panel.addMouseMotionListener(dragListener);
	}

	// CLICK JOUEUR1--------------------------------
	public Position clickpyramideJ1(MouseEvent e) {
		if (panel.jeu.partieEnCours.joueurCourant == 0) {
			Acteur a = panel.jeu.partieEnCours.joueur1();
			int posX_depart = panel.posX_ileJ1 + (int) (panel.largeur_ileJ * 0.49) - ((panel.largeur_piece * 6) / 2);
			int posX = posX_depart;
			int posY = panel.posY_depart;
			int startx = posX;
			int starty = posY;
			System.out.println("startx: " + startx + " starty: " + starty);
			int realx = e.getX() - startx;
			int realy = e.getY() - starty;

			int y = (a.getCamp().getHauteur() - 1) - realy / (panel.hauteur_piece + 1);
			if (y < 0 || y > a.getCamp().getHauteur()) {
				return null;
			}

			realx -= ((panel.largeur_piece + 1) / 2) * y;
			int x = realx / (panel.largeur_piece + 1);
			if (realx < 0 || x >= a.getCamp().getLargeur() - y) {
				return null;
			}
			for (PiecePyramide pp : panel.jeu.partieEnCours.joueur1().getCamp().piecesJouables()) {
				if (pp.getPos().etage == y && pp.getPos().rang == x) {
					return new Position(y, x);
				}
			}
		}
		return null;
	}

	// VOL DU JOUEUR--------------------------------
	public int clickVolJ1(MouseEvent e) {
		int startx = panel.posX_volJ1;
		int starty = panel.posY_volJ1;
		int realx = e.getX() - startx;
		int realy = e.getY() - starty;

		int x = realx / (panel.largeur_piece + 1);
		int y = realy / (panel.hauteur_piece + 1);
		if (x < 0 || (x >= panel.coupure + 1 && realy > (panel.hauteur_piece + 1))
				|| (x >= panel.coupure && realy < (panel.hauteur_piece + 1))) {
			return -1;
		}
		if (realy < 0 || realy > (panel.hauteur_piece + 1) * 2) {
			return -1;
		}
		int v = x + y * panel.coupure;
		if (v < panel.jeu.partieEnCours.joueur1().getPiecesVolees().size()) {
			return v;
		}
		return -1;
	}

	// CLICK JOUEUR2--------------------------------
	public Position clickpyramideJ2(MouseEvent e) {
		if (panel.jeu.partieEnCours.joueurCourant == 1) {
			Acteur a = panel.jeu.partieEnCours.joueur2();
			int posX_depart = panel.posX_ileJ2 + (int) (panel.largeur_ileJ * 0.49) - ((panel.largeur_piece * 6) / 2);
			int posX = posX_depart;
			int posY = panel.posY_depart;
			int startx = posX;
			int starty = posY;

			System.out.println("startx: " + startx + " starty: " + starty);
			int realx = e.getX() - startx;
			int realy = e.getY() - starty;

			int y = (a.getCamp().getHauteur() - 1) - realy / (panel.hauteur_piece + 1);
			if (y < 0 || y > a.getCamp().getHauteur()) {
				return null;
			}

			realx -= ((panel.largeur_piece + 1) / 2) * y;
			int x = realx / (panel.largeur_piece + 1);
			if (realx < 0 || x >= a.getCamp().getLargeur() - y) {
				return null;
			}

			for (PiecePyramide pp : panel.jeu.partieEnCours.joueur2().getCamp().piecesJouables()) {
				if (pp.getPos().etage == y && pp.getPos().rang == x) {
					return new Position(y, x);
				}
			}

		}
		return null;
	}

	// VOL DU JOUEUR2--------------------------------
	public int clickVolJ2(MouseEvent e) {
		int startx = panel.posX_volJ2;
		int starty = panel.posY_volJ2;
		int realx = e.getX() - startx;
		int realy = e.getY() - starty;

		int x = realx / (panel.largeur_piece + 1);
		int y = realy / (panel.hauteur_piece + 1);
		if (x < 0 || (x >= panel.coupure + 1 && realy > (panel.hauteur_piece + 1))
				|| (x >= panel.coupure && realy < (panel.hauteur_piece + 1))) {
			return -1;
		}
		if (realy < 0 || realy > (panel.hauteur_piece + 1) * 2) {
			return -1;
		}
		int v = x + y * panel.coupure;
		if (v < panel.jeu.partieEnCours.joueur2().getPiecesVolees().size()) {
			return v;
		}
		return -1;
	}

	// CLICK MONTAGNE--------------------------------
	public Position clickMontagne(MouseEvent e) {
		if (panel.getPieceSelectionnee() != null) {
			int startx = panel.posX_ileM;
			int starty = panel.posX_ileM;
			int realx = e.getX() - startx;
			int realy = e.getY() - starty;

			int y = (panel.jeu.partieEnCours.getBaseMontagne().getHauteur() - 1) - realy / (panel.hauteur_piece + 1);
			if (y < 0 || y > panel.jeu.partieEnCours.getBaseMontagne().getHauteur()) {
				return null;
			}

			realx -= ((panel.largeur_piece + 1) / 2) * y;
			int x = realx / (panel.largeur_piece + 1);
			if (realx < 0 || x >= panel.jeu.partieEnCours.getBaseMontagne().getLargeur() - y) {
				return null;
			}
			for (PiecePyramide pp : panel.jeu.partieEnCours.getBaseMontagne().piecesPosables()) {

				if (pp.getPiece().getColor() == panel.getPieceSelectionnee().getPiece().getColor()
						&& x == pp.getPos().rang && y == pp.getPos().etage) {
					return new Position(y, x);
				}
			}
		}
		return null;

	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (panel.partieEnCoursSet == true) {
			if (e.getButton() == e.BUTTON3) {
				panel.setPieceSelectionnee(null);
			} else {
				// J1
				// ----------------------------------------------------------------------------
				if (panel.jeu.partieEnCours.joueurCourant == 0) {
					// LEJOUEUR 1 DOIT SE FAIRE VOLER UN CUBE
					if (panel.jeu.partieEnCours.joueur2().doitVol) {
						Position p = clickpyramideJ1(e);
						if (p != null) {
							Piece piece = panel.jeu.partieEnCours.joueur1().getCamp().getPiece(p);
							if (piece != null) {
								panel.jeu.partieEnCours.joueur2().setChoixVol(new PiecePyramide(piece, p));
								panel.jeu.partieEnCours.joueur2().validerVol = true;
								System.out.println("J1 :" + panel.jeu.partieEnCours.joueur2().validerVol);
							}
						}

					} else {// ON JOUE NORMALEMENT
						if (panel.getPieceSelectionnee() == null) {
							// PYRAMIDE
							Position p = clickpyramideJ1(e);
							if (p != null) {
								Piece piece = panel.jeu.partieEnCours.joueur1().getCamp().getPiece(p);
								if (piece != null) {
									panel.setPieceSelectionnee(new PiecePyramide(piece, p));
								}
							}
							// VOL
							int v = clickVolJ1(e);
							if (v != -1) {
								Piece piece = panel.jeu.partieEnCours.joueur1().getPiecesVolees().get(v);
								if (piece != null) {
									panel.setPieceSelectionnee(new PiecePyramide(piece, null));
								}
							}
						} else {
							// JOUER COUP
							Position p = clickMontagne(e);
							if (p != null) {
								panel.setCoup(p, panel.jeu.partieEnCours.joueur1());
							}
							// passer sont tour
							if (panel.getPieceSelectionnee().getPiece().getColor() == Couleurs.BLANC) {
								panel.setPasser(panel.jeu.partieEnCours.joueur1());
							}
						}
					}
					// J2--------------------------------------------------------------------------------
				} else if (panel.jeu.partieEnCours.joueurCourant == 1) {
					// LEJOUEUR 2 DOIT SE FAIRE VOLER UN CUBE
					if (panel.jeu.partieEnCours.joueur1().doitVol) {
						Position p = clickpyramideJ2(e);
						if (p != null) {
							Piece piece = panel.jeu.partieEnCours.joueur2().getCamp().getPiece(p);
							if (piece != null) {
								panel.jeu.partieEnCours.joueur1().setChoixVol(new PiecePyramide(piece, p));
								panel.jeu.partieEnCours.joueur1().validerVol = true;
								System.out.println("J2 :" + panel.jeu.partieEnCours.joueur1().validerVol);
							}
						}

					} else { // ON JOUE NORMALEMENT
						if (panel.getPieceSelectionnee() == null) {
							// PYRAMIDE
							Position p = clickpyramideJ2(e);
							if (p != null) {
								Piece piece = panel.jeu.partieEnCours.joueur2().getCamp().getPiece(p);
								if (piece != null) {
									panel.setPieceSelectionnee(new PiecePyramide(piece, p));
								}
							}
							// VOL
							int v = clickVolJ2(e);
							if (v != -1) {
								Piece piece = panel.jeu.partieEnCours.joueur2().getPiecesVolees().get(v);
								if (piece != null) {
									panel.setPieceSelectionnee(new PiecePyramide(piece, null));
								}
							}
						} else {
							// JOUER COUP
							Position p = clickMontagne(e);
							if (p != null) {
								panel.setCoup(p, panel.jeu.partieEnCours.joueur2());
							}
							// passer sont tour
							if (panel.getPieceSelectionnee().getPiece().getColor() == Couleurs.BLANC) {

								panel.setPasser(panel.jeu.partieEnCours.joueur2());
							}
						}
					}

				}
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
			if (panel.partieEnCoursSet == true) {
				if (panel.initAffichageJoueurs().getClass() == Joueur.class) {
					panel.OldX = panel.currentX;
					panel.OldY = panel.currentY;
					panel.currentX = e.getX();
					panel.currentY = e.getY();
					panel.repaint();
					if (panel.getPieceSelectionnee() == null) {
						if (panel.jeu.partieEnCours.joueurCourant == 0
								&& (clickpyramideJ1(e) != null || (clickVolJ1(e) != -1))) {
							panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
						} else if (panel.jeu.partieEnCours.joueurCourant == 1
								&& (clickpyramideJ2(e) != null || (clickVolJ2(e) != -1))) {
							panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
						} else {
							panel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
						}
					} else {
						if (clickMontagne(e) != null) {
							panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
						} else {
							panel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
						}
					}
				}
			}
		}
	}

}