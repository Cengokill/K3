package Vue;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

import Vue.Menu.Chargement;
import Vue.Menu.Chargement.TypeFenetre;

public class NouvellePartieClics implements MouseListener {
	public String nomJ1, nomJ2, nomJ3;
	public int lvl_IA1, lvl_IA2, lvl_IA3;
	public NouvellePartie nouvellePartie;
	
	public NouvellePartieClics(NouvellePartie panel) {
		super();
		this.nouvellePartie = panel;
		DetectionSurvol survol = new DetectionSurvol();
		this.nouvellePartie.addMouseMotionListener(survol);		
	}

	public boolean estSurBouton(MouseEvent e, int startx, int starty, int largeur_bouton, int hauteur_bouton){
        return e.getX() >= startx && e.getX() <= startx+largeur_bouton && e.getY() >= starty && e.getY() <= starty+hauteur_bouton;
    }
	
	public boolean clicRetour(MouseEvent e){
		int startx = nouvellePartie.posX_back;
		int starty = nouvellePartie.posY_back;
		int hauteurBouton=nouvellePartie.hauteur_back;
		int largeurBouton=nouvellePartie.largeur_back;
		return estSurBouton( e, startx, starty, largeurBouton, hauteurBouton);
	}

	public boolean clicPVP(MouseEvent e){
		int startx = nouvellePartie.posX_PVP;
		int starty = nouvellePartie.posY_PVP;
		int hauteurBouton=nouvellePartie.hauteur_bouton1;
		int largeurBouton=nouvellePartie.largeur_bouton1;
		return estSurBouton( e, startx, starty, largeurBouton, hauteurBouton);
	}
	
	public boolean clicPVM(MouseEvent e){
		int startx = nouvellePartie.posX_PVM;
		int starty = nouvellePartie.posY_PVM;
		int hauteurBouton=nouvellePartie.hauteur_bouton1;
		int largeurBouton=nouvellePartie.largeur_bouton1;
		return estSurBouton( e, startx, starty, largeurBouton, hauteurBouton);
	}
	
	public boolean clicMVM(MouseEvent e){
		int startx = nouvellePartie.posX_MVM;
		int starty = nouvellePartie.posY_MVM;
		int hauteurBouton=nouvellePartie.hauteur_bouton1;
		int largeurBouton=nouvellePartie.largeur_bouton1;
		return estSurBouton( e, startx, starty, largeurBouton, hauteurBouton);
	}

	public boolean clicBpIA1_F(MouseEvent e){
        int startx = nouvellePartie.posX_IA1_FACILE;
        int starty = nouvellePartie.posY_IA1_FACILE;
        int hauteurBouton=nouvellePartie.hauteur_bouton3;
        int largeurBouton=nouvellePartie.largeur_bouton3;
        return estSurBouton(e, startx, starty, largeurBouton, hauteurBouton);
    }
        
    public boolean clicBpIA1_M(MouseEvent e){
        int startx = nouvellePartie.posX_IA1_MOYEN;
        int starty = nouvellePartie.posY_IA1_MOYEN;
        int hauteurBouton=nouvellePartie.hauteur_bouton3;
        int largeurBouton=nouvellePartie.largeur_bouton3;
        return estSurBouton(e, startx, starty, largeurBouton, hauteurBouton);
    }
        
    public boolean clicBpIA1_D(MouseEvent e){
        int startx = nouvellePartie.posX_IA1_DIFFICILE;
        int starty = nouvellePartie.posY_IA1_DIFFICILE;
        int hauteurBouton=nouvellePartie.hauteur_bouton3;
        int largeurBouton=nouvellePartie.largeur_bouton3;
        return estSurBouton(e, startx, starty, largeurBouton, hauteurBouton);
    }
        
    public boolean clicBpIA2_F(MouseEvent e){
        int startx = nouvellePartie.posX_IA2_FACILE;
        int starty = nouvellePartie.posY_IA2_FACILE;
        int hauteurBouton=nouvellePartie.hauteur_bouton3;
        int largeurBouton=nouvellePartie.largeur_bouton3;
        return estSurBouton(e, startx, starty, largeurBouton, hauteurBouton);
    }
        
    public boolean clicBpIA2_M(MouseEvent e){
        int startx = nouvellePartie.posX_IA2_MOYEN;
        int starty = nouvellePartie.posY_IA2_MOYEN;
        int hauteurBouton=nouvellePartie.hauteur_bouton3;
        int largeurBouton=nouvellePartie.largeur_bouton3;
        return estSurBouton(e, startx, starty, largeurBouton, hauteurBouton);
    }
        
    public boolean clicBpIA2_D(MouseEvent e){
        int startx = nouvellePartie.posX_IA2_DIFFICILE;
        int starty = nouvellePartie.posY_IA2_DIFFICILE;
        int hauteurBouton=nouvellePartie.hauteur_bouton3;
        int largeurBouton=nouvellePartie.largeur_bouton3;
        return estSurBouton(e, startx, starty, largeurBouton, hauteurBouton);
    }
        
    public boolean clicBpIA3_F(MouseEvent e){
        int startx = nouvellePartie.posX_IA3_FACILE;
        int starty = nouvellePartie.posY_IA3_FACILE;
        int hauteurBouton=nouvellePartie.hauteur_bouton3;
        int largeurBouton=nouvellePartie.largeur_bouton3;
        return estSurBouton(e, startx, starty, largeurBouton, hauteurBouton);
    }
        
    public boolean clicBpIA3_M(MouseEvent e){
        int startx = nouvellePartie.posX_IA3_MOYEN;
        int starty = nouvellePartie.posY_IA3_MOYEN;
        int hauteurBouton=nouvellePartie.hauteur_bouton3;
        int largeurBouton=nouvellePartie.largeur_bouton3;
        return estSurBouton(e, startx, starty, largeurBouton, hauteurBouton);
    }
        
    public boolean clicBpIA3_D(MouseEvent e){
        int startx = nouvellePartie.posX_IA3_DIFFICILE;
        int starty = nouvellePartie.posY_IA3_DIFFICILE;
        int hauteurBouton=nouvellePartie.hauteur_bouton3;
        int largeurBouton=nouvellePartie.largeur_bouton3;
        return estSurBouton(e, startx, starty, largeurBouton, hauteurBouton);
    }
      
    public boolean clicBp_COMMENCER(MouseEvent e){
        int startx = nouvellePartie.posX_COMMENCER;
        int starty = nouvellePartie.posY_COMMENCER;
        int hauteurBouton=nouvellePartie.hauteur_bouton1;
        int largeurBouton=nouvellePartie.largeur_bouton1;	
        return estSurBouton(e, startx, starty, largeurBouton, hauteurBouton);
    }

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(clicPVP(e)) {
			if(!nouvellePartie.enfonce_pb_PVP) {
				nouvellePartie.jouerSonClic();
				nouvellePartie.enfonce_pb_PVP=true;
				nouvellePartie.enfonce_pb_MVM=false;
				nouvellePartie.enfonce_pb_PVM=false;
				nouvellePartie.debut=false;
			}
		}
		else if(clicPVM(e)) {
			if(!nouvellePartie.enfonce_pb_PVM) {
				nouvellePartie.jouerSonClic();
				nouvellePartie.enfonce_pb_PVM=true;
				nouvellePartie.enfonce_pb_MVM=false;
				nouvellePartie.enfonce_pb_PVP=false;
				nouvellePartie.debut=false;
			}
		}
		else if(clicMVM(e)) {
			if(!nouvellePartie.enfonce_pb_MVM) {
				nouvellePartie.jouerSonClic();
				nouvellePartie.enfonce_pb_MVM=true;
				nouvellePartie.enfonce_pb_PVM=false;
				nouvellePartie.enfonce_pb_PVP=false;
				nouvellePartie.debut=false;
			}
		}
		else if (nouvellePartie.enfonce_pb_MVM){
			if (clicBpIA2_F(e)){
				nouvellePartie.jouerSonClic();
				nouvellePartie.enfonce_IA2_FACILE=true;
				nouvellePartie.enfonce_IA2_MOYEN=false;
				nouvellePartie.enfonce_IA2_DIFFICILE=false;
				nouvellePartie.enfonce_IA3_FACILE=false;
				nouvellePartie.enfonce_IA3_MOYEN=false;
				nouvellePartie.enfonce_IA3_DIFFICILE=false;
				nouvellePartie.debut=false;
				lvl_IA2 = 0;
			}
			else if (clicBpIA2_M(e)){
				nouvellePartie.jouerSonClic();
				nouvellePartie.enfonce_IA2_MOYEN=true;
				nouvellePartie.enfonce_IA2_DIFFICILE=false;
				nouvellePartie.enfonce_IA2_FACILE=false;
				nouvellePartie.debut=false;
				lvl_IA2 = 1;
			}
			else if (clicBpIA2_D(e)){
				nouvellePartie.jouerSonClic();
				nouvellePartie.enfonce_IA2_DIFFICILE=true;
				nouvellePartie.enfonce_IA2_MOYEN=false;
				nouvellePartie.enfonce_IA2_FACILE=false;
				nouvellePartie.debut=false;
				lvl_IA2 = 2;
			}
			if (clicBpIA3_F(e)){
				nouvellePartie.jouerSonClic();
				nouvellePartie.enfonce_IA3_FACILE=true;
				nouvellePartie.enfonce_IA3_MOYEN=false;
				nouvellePartie.enfonce_IA3_DIFFICILE=false;
				nouvellePartie.debut=false;
				lvl_IA3 = 0;
			}
			else if (clicBpIA3_M(e)){
				nouvellePartie.jouerSonClic();
				nouvellePartie.enfonce_IA3_MOYEN=true;
				nouvellePartie.enfonce_IA3_DIFFICILE=false;
				nouvellePartie.enfonce_IA3_FACILE=false;
				nouvellePartie.debut=false;
				lvl_IA3 = 1;
			}
			else if (clicBpIA3_D(e)){
				nouvellePartie.jouerSonClic();
				nouvellePartie.enfonce_IA3_DIFFICILE=true;
				nouvellePartie.enfonce_IA3_MOYEN=false;
				nouvellePartie.enfonce_IA3_FACILE=false;
				nouvellePartie.debut=false;
				lvl_IA3 = 2;
			}
		}
		else if (nouvellePartie.enfonce_pb_PVM){
			if (clicBpIA1_F(e)){
				nouvellePartie.jouerSonClic();
				nouvellePartie.enfonce_IA1_FACILE=true;
				nouvellePartie.enfonce_IA1_MOYEN=false;
				nouvellePartie.enfonce_IA1_DIFFICILE=false;
				nouvellePartie.debut=false;
				lvl_IA1 = 0;
			}
			else if (clicBpIA1_M(e)){
				nouvellePartie.jouerSonClic();
				nouvellePartie.enfonce_IA1_MOYEN=true;
				nouvellePartie.enfonce_IA1_DIFFICILE=false;
				nouvellePartie.enfonce_IA1_FACILE=false;
				nouvellePartie.debut=false;
				lvl_IA1 = 1;
			}
			else if (clicBpIA1_D(e)){
				nouvellePartie.jouerSonClic();
				nouvellePartie.enfonce_IA1_DIFFICILE=true;
				nouvellePartie.enfonce_IA1_MOYEN=false;
				nouvellePartie.enfonce_IA1_FACILE=false;
				nouvellePartie.debut=false;
				lvl_IA1 = 2;
			}
		}

		if (clicBp_COMMENCER(e)){
			/*
         	* modeDeJeu 0 : joueur contre joueur
         	* modeDeJeu 1 : IA contre joueur
         	* modeDeJeu 2 : IA contre IA
         	*/
			 if (nouvellePartie.enfonce_pb_MVM){
				 nouvellePartie.jouerSonLancement();
				 nouvellePartie.partie.modeDeJeu=2;
				 nouvellePartie.partie.nomJoueur1="Ordinateur 1";
				 nouvellePartie.partie.nomJoueur2="Ordinateur 2";
				 nouvellePartie.partie.difficulteIA1=lvl_IA2;
				 nouvellePartie.partie.difficulteIA2=lvl_IA3;
				 nouvellePartie.chargement.setProchaineFenetre(TypeFenetre.PHASE1);
				 nouvellePartie.chargement.lancement=true;
			 }
			 else if (nouvellePartie.enfonce_pb_PVM){
				 nouvellePartie.jouerSonLancement();
				 nouvellePartie.partie.modeDeJeu=1;
				 if(nouvellePartie.nomJ3.getText().isEmpty()) {
					 nouvellePartie.partie.nomJoueur1="Joueur";
				 }else {
					 nouvellePartie.partie.nomJoueur1=nouvellePartie.nomJ3.getText();
				 }
				 nouvellePartie.partie.nomJoueur2=" Ordinateur ";
				 nouvellePartie.partie.difficulteIA2=lvl_IA2;
				 nouvellePartie.chargement.setProchaineFenetre(TypeFenetre.PHASE1);
				 nouvellePartie.chargement.lancement=true;
			 }
			 else if (nouvellePartie.enfonce_pb_PVP){
				 nouvellePartie.jouerSonLancement();
				 nouvellePartie.partie.modeDeJeu = 0;
				 if(nouvellePartie.nomJ1.getText().isEmpty()) {
					 nouvellePartie.partie.nomJoueur1="Joueur 1";
				 }else {
					 nouvellePartie.partie.nomJoueur1=nouvellePartie.nomJ1.getText();
				 }
				 if(nouvellePartie.nomJ2.getText().isEmpty()) {
					 nouvellePartie.partie.nomJoueur2="Joueur 2";
				 }else {
					 nouvellePartie.partie.nomJoueur2=nouvellePartie.nomJ2.getText();
				 }
				 nouvellePartie.chargement.setProchaineFenetre(TypeFenetre.PHASE1);
				 nouvellePartie.chargement.lancement=true;
			 }
		}
		else if(clicRetour(e)) {
			nouvellePartie.jouerSonClic();
			nouvellePartie.chargement.lancement = true;
			nouvellePartie.chargement.setProchaineFenetre(nouvellePartie.chargement.getProchainePrecedent());
		}
		nouvellePartie.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		//nouvellePartie.repaint();
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public class DetectionSurvol extends MouseMotionAdapter{
		public void mouseMoved(MouseEvent e) {
			//Clic modes
			if(clicPVP(e)||clicPVM(e)||clicMVM(e)) {
				nouvellePartie.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			else if (clicBp_COMMENCER(e)){
				nouvellePartie.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			else if(clicRetour(e)) {
				nouvellePartie.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			//Choix des difficulte des IA
			//Clic difficulte IA2
			else if ((clicBpIA2_F(e)||clicBpIA2_M(e)||clicBpIA2_D(e)||clicBpIA3_F(e)||clicBpIA3_M(e)||clicBpIA3_D(e))&&nouvellePartie.enfonce_pb_MVM){
				nouvellePartie.setCursor(new Cursor(Cursor.HAND_CURSOR));	
			}
			//Clic difficulte IA1
			else if ((clicBpIA1_F(e)||clicBpIA1_M(e)||clicBpIA1_D(e))&&nouvellePartie.enfonce_pb_PVM){
					nouvellePartie.setCursor(new Cursor(Cursor.HAND_CURSOR));	
			}
			else {
				nouvellePartie.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			if(nouvellePartie.oldEnfonce_pb_PVP!=nouvellePartie.enfonce_pb_PVP) {
				nouvellePartie.repaint();
				nouvellePartie.oldEnfonce_pb_PVP=nouvellePartie.enfonce_pb_PVP;
			}
			if(nouvellePartie.oldEnfonce_pb_PVM!=nouvellePartie.enfonce_pb_PVM) {
				nouvellePartie.repaint();
				nouvellePartie.oldEnfonce_pb_PVM=nouvellePartie.enfonce_pb_PVM;
			}
		}
	}
}



















