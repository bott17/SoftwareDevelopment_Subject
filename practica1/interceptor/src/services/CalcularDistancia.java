package services;

public class CalcularDistancia implements Filtro {
	
	private double revolAnt;
	private double RADIO;

	@Override
	public double ejecutar(Object objeto) {
		
		double revoluciones = (double) objeto;
		double distancia = (revoluciones - revolAnt)*2*RADIO*3.1416;

		return distancia;
	}

}
