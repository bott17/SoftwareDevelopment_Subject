package services;

import java.util.ArrayList;

import appExterna.Interfaz;

public class GestorFiltros {
	
	private ArrayList<Filtro> filtros = new ArrayList<Filtro>();
	private Interfaz objetivo;
	
	public void addFiltro(Filtro filtro) throws Exception{
		
		if(filtro != null)
			filtros.add(filtro);
		else
			throw new Exception("Filtro null detected");
	}
	
	// TODO
	public void setFiltro(Filtro filtro){}
	
	public void peticionFiltro(double peticion){}
	
	public void GestorFiltros(Interfaz interfaz){} 

}
