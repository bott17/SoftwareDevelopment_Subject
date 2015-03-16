package appExterna;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class Interfaz extends JFrame {
	
	private static PanelBotones jpanel;
	
	public Interfaz(){
		
		jpanel = new PanelBotones();
		
		Dimension dimensionFrame = new Dimension(500, 350);
		
		this.setSize(dimensionFrame);
		this.add(jpanel);
		this.setVisible(true);
		
		this.addWindowListener(new WindowAdapter (){
			
			public void windowsClosing(WindowEvent e){
				System.exit(0);
			}
		});
	}
}
