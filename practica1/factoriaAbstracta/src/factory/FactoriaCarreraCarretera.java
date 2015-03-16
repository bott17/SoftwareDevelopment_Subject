package factory;

import objects.BiciCarretera;
import objects.CuadroCarretera;
import objects.ManillarCarretera;
import objects.RuedasCarretera;

public abstract class FactoriaCarreraCarretera {
	
	public static BiciCarretera makeBicicletaMontana(){
		
		BiciCarretera newBici = new BiciCarretera(null);
		newBici.montarCuadro(new CuadroCarretera());
		newBici.montarManillar(new ManillarCarretera());
		newBici.montarRuedas(new RuedasCarretera());
		
		return newBici;
	}

}
