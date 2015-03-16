package hebras;

import generic.Bicicleta;

import java.util.ArrayList;

public abstract class Carrera extends Thread {
	
	private ArrayList<Bicicleta> participantes;
	
	public Carrera(){
		participantes = new ArrayList<Bicicleta>();
	}
	
	public void apuntarParticipante(Bicicleta participante){
		participantes.add(participante);
	}
	
	public int getNumeroParticipantes(){
		return participantes.size();
	}
	
	public abstract void run ();
	
	protected void retirarBicicletas(String tipo){
		participantes.clear();
		System.out.println("bicicletas " + tipo + " retiradas");
	}

}
