package factory;

import hebras.Carrera;
import hebras.CarreraCarretera;
import objects.BiciCarretera;
import objects.CuadroCarretera;
import objects.ManillarCarretera;
import objects.RuedasCarretera;

public abstract class FactoriaCarreraCarretera {
	
	private static CarreraCarretera carrera;
	
	private static BiciCarretera makeBicicletaCarretera(int numeroCorredor){
		
		BiciCarretera newBici = new BiciCarretera(Integer.toString(numeroCorredor));
		newBici.montarCuadro(new CuadroCarretera());
		newBici.montarManillar(new ManillarCarretera());
		newBici.montarRuedas(new RuedasCarretera());
		
		return newBici;
	}
	
	public static Carrera startCarrera(int nParticipantes, int duracion){
		
		carrera = new CarreraCarretera(duracion);
		
		for (int i=0; i<nParticipantes; i++){
			carrera.apuntarParticipante(makeBicicletaCarretera(i));
		}
		
		carrera.start();
		return carrera;
		
		
	}
	
}
