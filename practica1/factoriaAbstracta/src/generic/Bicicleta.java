package generic;

public abstract class Bicicleta extends Thread {
	
	TC tipo;
	Cuadro cuadro;
	Manillar manillar;
	Ruedas ruedas;
	int distancia;
	boolean correr;
	String numeroCorredor;
	 
	public Bicicleta(TC tipo, String _numero_corredor){
		
		this.tipo=tipo;
		distancia = 0;
		correr = true;
		numeroCorredor = _numero_corredor;
		 
	}
	
	public void montarCuadro(Cuadro nuevoCuadro){
		cuadro = nuevoCuadro;
	}
	
	public void montarManillar (Manillar nuevoManillar){
		manillar = nuevoManillar;
	}
	
	public void montarRuedas (Ruedas nuevasRuedas){
		ruedas = nuevasRuedas;
	}
	
	public TC getTipo(){
		return tipo;
	}
	
	private void correr(){
		distancia++;
	}
	
	public void run(){
		
		while(correr){
			
			try {
				Thread.sleep((long) Math.random() * 5);
				this.correr();
				if(distancia % 7 == 0)
					System.out.println(numeroCorredor + " distancia: " + distancia);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void pararBici(){
		correr = false;
	}
	
	@Override
	public String toString(){
		
		return ("bicicleta - " + tipo.toString());
	}
	
	
}
