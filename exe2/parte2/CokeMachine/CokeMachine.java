package CokeMachine;

public class CokeMachine {
	int numeroLattine;
	
	public CokeMachine() {
		numeroLattine = 10;
	}
	
	public synchronized void Preleva() {
		if(numeroLattine > 0) {
			numeroLattine--;
			
			System.out.println(Thread.currentThread().getName() + ": Prendo una lattina - " + "Lattine rimaste: " + numeroLattine);
			
			if(numeroLattine == 0) {
				// risveglio tutti, compreso il fornitore
				// avrei anche usato solo notify() ma non si può indicare di preciso quale Thread risvegliare,
				// quindi li risveglio tutti
				notifyAll();
				
				System.out.println(Thread.currentThread().getName() + ": Lattine finite, chiamo il fornitore - " + "Lattine rimaste: " + numeroLattine);
			}
		}
		else {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public synchronized void Rifornisci() {
		if(numeroLattine != 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		else {
			numeroLattine = 10;
			notifyAll();
			
			System.out.println("FORNITORE: macchinetta rifornita! - " + "Lattine rimaste: " + numeroLattine);
		}
	}
}
