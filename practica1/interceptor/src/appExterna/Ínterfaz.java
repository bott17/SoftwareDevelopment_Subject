package appExterna;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class �nterfaz extends JFrame {
	
	public �nterfaz(JPanel jpanel){
		
		Dimension dimensionFrame = new Dimension(500, 350);
		
		this.setSize(dimensionFrame);
		this.add(jpanel);
		this.setVisible(true);
	}
	
}
