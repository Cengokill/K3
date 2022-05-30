package Vue;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class TouchesClavier extends KeyAdapter {
	PanelGeneral panel;
	TouchesClavier(PanelGeneral p) {
		this.panel=p;
    }


    @Override
    public void keyPressed(KeyEvent event) {
        switch (event.getKeyCode()) {
            case KeyEvent.VK_F1:
            	if(!panel.estPleinEcran) {
            		panel.fullScreenOn();
            	}
            	break;
            case KeyEvent.VK_ESCAPE:
            	if(panel.estPleinEcran) {
            		panel.window.setLocation(0,0);
            		panel.window.setSize(panel.tailleEcran);
            		panel.fullScreenOff();
            	}
                break;
        }
    }
}
