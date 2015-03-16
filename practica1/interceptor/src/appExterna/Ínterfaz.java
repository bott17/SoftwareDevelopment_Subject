package appExterna;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Ínterfaz extends JFrame {
	
	public Ínterfaz(JPanel jpanel){
		
		Dimension dimensionFrame = new Dimension(500, 350);
		
		this.setSize(dimensionFrame);
		this.add(jpanel);
		this.setVisible(true);
	}
	
}
