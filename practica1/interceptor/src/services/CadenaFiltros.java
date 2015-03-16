package services;

import java.util.ArrayList;

import appExterna.Interfaz;

public class CadenaFiltros {
	
	private ArrayList<Filtro> filtros;
	private Interfaz objetivo;
	
	public  CadenaFiltros(){
		filtros = new ArrayList<Filtro>();
	} 
	
	public void addFiltro(Filtro filtro) throws Exception{
		
		if(filtro != null)
			filtros.add(filtro);
		else
			throw new Exception("Filtro null detected");
	}
	
	public void ejecutar(double peticion){
		
		for(Filtro filtro: filtros){
			System.out.println("Nueva velocidad (m/s) " + filtro.ejecutar(peticion));
		}
		
		objetivo.ejecutar(peticion);
	}

	public void setObjetivo(Interfaz objetivo){
		 this.objetivo = objetivo;
	}

	
	

}
