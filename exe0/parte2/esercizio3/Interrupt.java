package esercizio3;

class Interrupt extends Thread{
	long time = 0;
	long start = 0;
	long end = 0;
	
	@Override
	public void run() {	
		try {
			start = System.currentTimeMillis();
			Thread.sleep(10000);
			
		} catch (InterruptedException e) {
			end = System.currentTimeMillis();
			time = end - start;
			
			System.out.println("Thread figlio interrotto dopo " + time + " ms");
		}
	}
}