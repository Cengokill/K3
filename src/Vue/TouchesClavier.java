package Vue;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class TouchesClavier extends KeyAdapter {
	PanelGeneral panel;
	public boolean estPleinEcran;
	TouchesClavier(PanelGeneral p) {
		this.panel=p;
		estPleinEcran=false;
    }


    @Override
    public void keyPressed(KeyEvent event) {
        switch (event.getKeyCode()) {
            case KeyEvent.VK_F1:
            	if(!estPleinEcran) {
	            	panel.window.dispose();
	            	panel.window.setUndecorated(true);
	            	panel.window.setVisible(true);
	            	estPleinEcran=true;
            	}
            	break;
            case KeyEvent.VK_ESCAPE:
            	if(estPleinEcran) {
	            	panel.window.dispose();
	            	panel.window.setUndecorated(false);
	            	panel.window.setVisible(true);
	            	estPleinEcran=false;
            	}
                break;
        }
    }
}
