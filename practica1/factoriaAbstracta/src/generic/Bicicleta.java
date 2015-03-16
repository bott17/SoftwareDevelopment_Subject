package generic;

public abstract class Bicicleta {
	
	TC tipo;
	Cuadro cuadro;
	Manillar manillar;
	Ruedas ruedas;
	 
	public Bicicleta(TC tipo){
		
		this.tipo=tipo;
		 
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
	
	@Override
	public String toString(){
		
		return ("bicicleta - " + tipo.toString());
	}
	
}
