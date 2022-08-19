package esercizio3;

class esercizio3{
	public static void main(String[] args) {
		int numeroControlli = 0;
		boolean alive = false;
		
		Thread newThread = new Worker3();
		newThread.start();
		
		while(true) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			alive = newThread.isAlive();
			numeroControlli++;
			
			System.out.println("Numero di controlli effettuati: " + numeroControlli);
			
			if (!alive) {
				Thread.currentThread().interrupt();
				
				if(Thread.currentThread().isInterrupted())
					break;
			}
		}
	}
}