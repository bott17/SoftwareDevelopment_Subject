package hebras;

public class CarreraMontana extends Carrera {

	public CarreraMontana() {
		
		super();
	}
	
	@Override
	public void run() {
		System.out.println("Carrera de montaña iniciada");
		
		try {
			this.sleep(60000);
			this.retirarBicicletas("Montana");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
