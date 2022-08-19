package Counter;

import java.util.concurrent.ThreadLocalRandom;

class TaskI extends Thread{
	Counter c;
	
	public TaskI(Counter c, int id) {
		this.c = c;
		this.setName("TaskI" + id);
	}
	
	public void run() {
		while(true) {
			try {
				Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 5000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			c.incrementa();
		}
	}
}