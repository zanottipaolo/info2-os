package esercizio1;

import java.util.concurrent.ThreadLocalRandom;

class TaskCounter extends Thread{
	Counter c;
	int n;
	
	public TaskCounter(Counter c, int n) {
		this.c = c;
		this.n = n;
	}
	
	@Override
	public void run() {
		int random = ThreadLocalRandom.current().nextInt(n/2, n+1);
		System.out.println("Numero estratto: " + random);
		
		for(int i=0; i<random; i++)
			c.next();
	}
}