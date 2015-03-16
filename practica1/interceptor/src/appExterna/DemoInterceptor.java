package appExterna;

import services.Cliente;
import services.GestorFiltros;


public class DemoInterceptor {

	private static Interfaz frame;
	private static Cliente cliente;
	private static GestorFiltros gestor;
	
	public static void main(String[] args){
		
		initNewServices();
		makeFrame();
	}

	private static void makeFrame() {
		frame = new Interfaz();
	}
	
	private static void initNewServices(){
		gestor = new GestorFiltros(frame);
		cliente = new Cliente();
		
		cliente.setGestorFiltros(gestor);
	}
	
	protected static void sendPetition(double peticion){
		
		cliente.enviarPeticion(peticion);
		
	}
}
