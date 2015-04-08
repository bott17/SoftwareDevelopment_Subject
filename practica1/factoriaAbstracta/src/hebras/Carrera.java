package hebras;

import generic.Bicicleta;

import java.util.ArrayList;

public abstract class Carrera extends Thread {
	
	protected ArrayList<Bicicleta> participantes;
	protected int duracion;
	
	public Carrera(int _duracion_){
		participantes = new ArrayList<Bicicleta>();
		duracion = _duracion_;
	}
	
	public void apuntarParticipante(Bicicleta participante){
		participantes.add(participante);
	}
	
	public int getNumeroParticipantes(){
		return participantes.size();
	}
	
	public abstract void run ();
	
	protected void retirarBicicletas(String tipo){
		
		int maximo = 0;
		String ganador = "";
		
		for(Bicicleta participante: participantes){
			
			try {
				participante.pararBici();
				participante.join();
				
				if(participante.getDistancia() > maximo){
					maximo=participante.getDistancia();
					ganador = participante.getNumero();
				}
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		participantes.clear();
		System.out.println("bicicletas " + tipo + " retiradas\n El Ganador de " + tipo + " es: " + ganador + " ha recorrido: " + maximo + " km");
	}

}
