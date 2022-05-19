package Vue;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Slider implements ChangeListener {
	JFrame frame;
	JPanel panel;
	JLabel label;
	JSlider slider1, slider2;
	
	public Slider() {
		frame = new JFrame("Slider de volume");
		panel = new JPanel();
		label = new JLabel();
		slider1 = new JSlider(0,10, 5);
		slider1.setPreferredSize(new Dimension(400, 200));
		slider1.setMinorTickSpacing(1);//affiche des traits a chaque intervalle voulu (ici 1)
		slider1.setPaintTicks(true);
		slider1.setPaintTrack(true);
		slider1.setMajorTickSpacing(5);//affiche des traits plus longs a chaque intervalle voulu (ici 5)
		slider1.setPaintLabels(true);
		slider1.setFont(new Font("Calibri", Font.BOLD, 22));
		slider1.addChangeListener(this);
		
		slider2 = new JSlider(0,10, 5);
		slider2.setPreferredSize(new Dimension(400, 200));
		slider2.setMinorTickSpacing(1);
		slider2.setPaintTicks(true);
		slider2.setPaintTrack(true);
		slider2.setMajorTickSpacing(5);//affiche des traits plus longs a chaque intervalle voulu (ici 5)
		slider2.setPaintLabels(true);
		slider2.setFont(new Font("Calibri", Font.BOLD, 22));
		slider1.addChangeListener(this);
		
		label.setText(""+slider1.getValue());
		//label.setText(""+slider2.getValue());
		label.setFont(new Font("Calibri", Font.BOLD, 22));
		
		panel.add(slider1);
		panel.add(slider2);

		
		panel.add(label);
		frame.add(panel);
		frame.setSize(460,460);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void stateChanged(ChangeEvent e) {
		label.setText(""+slider1.getValue());
		//label.setText(""+slider2.getValue());
	}
}
