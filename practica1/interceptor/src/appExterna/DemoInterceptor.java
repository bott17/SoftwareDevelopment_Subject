package appExterna;


public class DemoInterceptor {

	private static PanelBotones panel;
	private static �nterfaz frame;
	
	public static void main(String[] args){
		
		
		makeFrame(panel);
		
	}

	private static void makeFrame(PanelBotones jpanel) {
		

		panel = new PanelBotones();
		frame = new �nterfaz(panel);
				
	}
}
