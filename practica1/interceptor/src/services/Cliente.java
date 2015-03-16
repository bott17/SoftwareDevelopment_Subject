package services;

public class Cliente {
	
	private GestorFiltros gestor;
	
	public Cliente(){
		
	}
	
	public void setGestorFiltros(GestorFiltros gestorF){
		gestor= gestorF;
	}
	
	public void enviarPeticion(double peticion){
		
		System.out.println(peticion+ " peticion recibida");
		
	}

}
