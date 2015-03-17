package main;

import java.util.Scanner;

import hebras.Carrera;
import factory.FactoriaCarreraCarretera;
import factory.FactoriaCarreraMontana;

public class TheRun {
	

	public static void main(String[] args){
		
		Carrera carreraCarretera, carreraMontana;
		int corredoresMontana, corredoresCarre = 0;
		int tiempoCarreraMontana, tiempoCarreraCarre= 0;
		
		// sc se usará para leer los datos
	    Scanner sc = new Scanner(System.in);
	    
		System.out.println("¿Cuantos participantes de MONTANA corren?");
		corredoresMontana = sc.nextInt();
		System.out.println("¿Cuanto dura carrera de MONTANA?");
		tiempoCarreraMontana = sc.nextInt();
		
		
		System.out.println("¿Cuantos participantes de CARRETERA corren?");
		corredoresCarre = sc.nextInt();
		System.out.println("¿Cuanto dura carrera de CARRETERA?");
		tiempoCarreraCarre = sc.nextInt();
		
		
		carreraCarretera = FactoriaCarreraCarretera.startCarrera(corredoresMontana, tiempoCarreraMontana);
		carreraMontana = FactoriaCarreraMontana.startCarrera(corredoresCarre, tiempoCarreraCarre);
		
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
