package esercizio4;

class TestInterruptMultipli{
	public static void main(String[] args) {
		Thread thread1 = new Thread();
		Thread thread2 = new Thread();
		
		thread1.start();
		thread2.start();
		
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		thread1.interrupt();
		thread2.interrupt();
	}
}