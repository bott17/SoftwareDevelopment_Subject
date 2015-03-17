package hebras;

import generic.Bicicleta;

public class CarreraMontana extends Carrera {
	

	public CarreraMontana(int duracion) {
		super(duracion);
		
	}
	
	@Override
	public void run() {
		System.out.println("Carrera de montaña iniciada");
		
		for(Bicicleta participante: participantes){
			participante.start();
		}
		
		try {
			Thread.sleep(duracion);
			this.retirarBicicletas("Montana");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
