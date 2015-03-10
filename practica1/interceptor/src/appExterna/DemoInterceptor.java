package appExterna;

import gui.PanelBotones;
import gui.PrincipalFrame;

public class DemoInterceptor {

	private static PanelBotones panel;
	private static PrincipalFrame frame;
	
	public static void main(String[] args){
		
		
		makeFrame(panel);
		
	}

	private static void makeFrame(PanelBotones jpanel) {
		

		panel = new PanelBotones();
		frame = new PrincipalFrame(panel);
				
	}
}
