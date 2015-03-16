package hebras;

public abstract class ManejadorHebras {
	
	public Thread createThread (String name){
		return new Thread(name);
	}

}
