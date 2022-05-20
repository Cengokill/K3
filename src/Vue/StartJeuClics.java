package Vue;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Vue.Phase1.Phase1Panel;
import Vue.Phase1.ecouteurClick.DragListener;

public class StartJeuClics implements MouseListener{
	
	private StartJeu startJeu;
	public String typeCurseur;
	public Cursor cursor1, cursor2;
	public String chemin="./src/Ressources/";
	
	public StartJeuClics(StartJeu startJeu) {
		super();
		this.startJeu = startJeu;
		this.startJeu.addMouseMotionListener();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
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

}
