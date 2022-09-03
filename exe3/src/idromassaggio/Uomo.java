package idromassaggio;

import java.util.concurrent.ThreadLocalRandom;

public class Uomo extends Thread{
	Idromassaggio i;
	
	public Uomo(Idromassaggio i, int id) {
		this.setName("Uomo" + id);
		this.i = i;
	}
	
	@Override
	public void run() {
		i.UomoEntra();
		
		try {
			Thread.sleep(ThreadLocalRandom.current().nextInt(0, 5000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		i.UomoEsce();
	}
}
