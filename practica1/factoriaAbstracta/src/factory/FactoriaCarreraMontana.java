package factory;

import hebras.Carrera;
import hebras.CarreraMontana;
import objects.BiciMontana;
import objects.CuadroMontana;
import objects.ManillarMontana;
import objects.RuedasMontana;

public abstract class FactoriaCarreraMontana {
	
	private static CarreraMontana carrera;
	
	private static BiciMontana makeBicicletaMontana(){
		
		BiciMontana newBici = new BiciMontana(null);
		newBici.montarCuadro(new CuadroMontana());
		newBici.montarManillar(new ManillarMontana());
		newBici.montarRuedas(new RuedasMontana());
		
		return newBici;
	}

	public static Carrera startCarrera(int nParticipantes){
		
		carrera = new CarreraMontana();
		
		for (int i=0; i<nParticipantes; i++){
			carrera.apuntarParticipante(makeBicicletaMontana());
		}
		
		carrera.start();
		return carrera;
	}
}
