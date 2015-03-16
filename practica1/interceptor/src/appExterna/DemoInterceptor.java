package appExterna;


public class DemoInterceptor {

	private static PanelBotones panel;
	private static Ínterfaz frame;
	
	public static void main(String[] args){
		
		
		makeFrame(panel);
		
	}

	private static void makeFrame(PanelBotones jpanel) {
		

		panel = new PanelBotones();
		frame = new Ínterfaz(panel);
				
	}
}
