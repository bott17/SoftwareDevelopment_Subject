package factory;

import objects.BiciMontana;
import objects.CuadroMontana;
import objects.ManillarMontana;
import objects.RuedasMontana;

public abstract class FactoriaCarreraMontana {
	
public static BiciMontana makeBicicletaMontana(){
		
		BiciMontana newBici = new BiciMontana(null);
		newBici.montarCuadro(new CuadroMontana());
		newBici.montarManillar(new ManillarMontana());
		newBici.montarRuedas(new RuedasMontana());
		
		return newBici;
	}

}
