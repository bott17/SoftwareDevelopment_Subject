package hebras;

public class CarreraCarretera extends Carrera{
	
	public CarreraCarretera(){
		
		super();
		
	}
	
	public void run (){
		System.out.println("Carrera de carretera iniciada");
		
		try {
			this.sleep(60000);
			this.retirarBicicletas("Carretera");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
