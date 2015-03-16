package factory;

import hebras.Carrera;
import hebras.CarreraCarretera;
import objects.BiciCarretera;
import objects.CuadroCarretera;
import objects.ManillarCarretera;
import objects.RuedasCarretera;

public abstract class FactoriaCarreraCarretera {
	
	private static CarreraCarretera carrera;
	
	private static BiciCarretera makeBicicletaCarretera(){
		
		BiciCarretera newBici = new BiciCarretera(null);
		newBici.montarCuadro(new CuadroCarretera());
		newBici.montarManillar(new ManillarCarretera());
		newBici.montarRuedas(new RuedasCarretera());
		
		return newBici;
	}
	
	public static Carrera startCarrera(int nParticipantes){
		
		carrera = new CarreraCarretera();
		
		for (int i=0; i<nParticipantes; i++){
			carrera.apuntarParticipante(makeBicicletaCarretera());
		}
		
		carrera.start();
		return carrera;
		
		
	}
	
}
