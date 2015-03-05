package services;

public class Calcular implements Filtro {
	
	private double revolAnt;
	private double INTERVALO;
	
	@Override
	public double ejecutar(Object objeto) {
		
		double distancia = (double) objeto;
		double velocidad = distancia*360/INTERVALO;
		
		revolAnt = revoluciones;
		return velocidad;
	}
	
	// TODO
	public double calcularDistancia(int revoluciones){
		
		return 0;
	}

}
