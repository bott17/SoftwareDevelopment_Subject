package main;

import hebras.Carrera;
import factory.FactoriaCarreraCarretera;
import factory.FactoriaCarreraMontana;

public class TheRun {
	

	public static void main(String[] args){
		
		Carrera carreraCarretera, carreraMontana;
		carreraCarretera = FactoriaCarreraCarretera.startCarrera(50);
		carreraMontana = FactoriaCarreraMontana.startCarrera(40);
		
		try {
			carreraCarretera.join();
			System.out.println("Carrera de carretera acabada");
			carreraMontana.join();
			System.out.println("Carrera de montana acabada");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
