package esercizio4;

class InterruptMultipli extends Thread{
	@Override
	public void run() {
		System.out.println("Thread " + Thread.currentThread().getName() + " terminato");
	}
}