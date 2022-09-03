package idromassaggio;

import java.util.concurrent.ThreadLocalRandom;

public class Donna extends Thread {
	Idromassaggio i;
	
	public Donna(Idromassaggio i, int id) {
		this.setName("Donna" + id);
		this.i = i;
	}
	
	@Override
	public void run() {
		i.DonnaEntra();
		
		try {
			Thread.sleep(ThreadLocalRandom.current().nextInt(0, 5000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		i.DonnaEsce();
	}
}
