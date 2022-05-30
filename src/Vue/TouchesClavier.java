package Vue;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class TouchesClavier extends KeyAdapter {
	PanelGeneral panel;
	TouchesClavier(PanelGeneral p) {
		this.panel=p;
    }


    @Override
    public void keyPressed(KeyEvent event) {
    	System.out.println("key pressed");
        switch (event.getKeyCode()) {
            case KeyEvent.VK_F1:
            	System.out.println("f1");
            	//panel.window.setUndecorated(true);
            	break;
            case KeyEvent.VK_ESCAPE:
            	System.out.println("echap");
            	//panel.window.setUndecorated(false);
                break;
        }
    }
}
