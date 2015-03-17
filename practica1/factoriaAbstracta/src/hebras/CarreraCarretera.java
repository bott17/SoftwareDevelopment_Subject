package hebras;

import generic.Bicicleta;

public class CarreraCarretera extends Carrera{
	
	public CarreraCarretera(int duracion){
		
		super(duracion);
		
	}
	
	public void run (){
		System.out.println("Carrera de carretera iniciada");
		
		for(Bicicleta participante: participantes){
			participante.start();
		}
		
		try {
			Thread.sleep(duracion*1000);
			this.retirarBicicletas("Carretera");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
