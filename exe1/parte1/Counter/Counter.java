package Counter;

import java.util.concurrent.Semaphore;

class Counter{
	int start = 0;
	int max = 10;
	private Semaphore countSync;
	private Semaphore mutex; // per accedere alla sezione critica
	
	public Counter() {
		mutex = new Semaphore(1);
		countSync = new Semaphore(0);
	}
	
	public void incrementa() {
		try {
			mutex.acquire();
			
			// sezione critica
			if(countSync.availablePermits() < max) {
				countSync.release(1);
				
				System.out.println(Thread.currentThread().getName() + ": incremento il contatore");
				System.out.println("Contatore = " + countSync.availablePermits());
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			mutex.release(); // esco dalla sezione critica
		}
	}
	
	public void decrementa() {
		try {
			mutex.acquire();
			
			if(countSync.availablePermits() > start) {
				countSync.acquire(1);
				
				System.out.println(Thread.currentThread().getName() + ": decremento il contatore");
				System.out.println("Contatore = " + countSync.availablePermits());
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			mutex.release();
		}
	}
}