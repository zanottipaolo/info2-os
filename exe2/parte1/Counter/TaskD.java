package Counter;

import java.util.concurrent.ThreadLocalRandom;

public class TaskD extends Thread {
	Counter c;
	
	public TaskD(Counter c, int id) {
		this.c = c;
		this.setName("ThreadD" + id);
	}
	
	@Override
	public void run() {
		while(true) {
			// sleep
			try {
				this.sleep(ThreadLocalRandom.current().nextInt(0, 5000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// decremento
			c.decrement();
		}
	}
}
