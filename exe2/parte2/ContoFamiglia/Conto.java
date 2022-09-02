package ContoFamiglia;

public class Conto {
	int saldo;
	
	public Conto() {
		saldo = 10000;
	}
	
	public synchronized float getConto() {
		return saldo;
	}
	
	public synchronized void Versamento() {
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// ricarica il conto quando viene risvegliato
		saldo = 10000;
		notifyAll();
		
		System.out.println(Thread.currentThread().getName() + ": conto ricaricato!" + " Saldo: " + saldo);
	}
	
	public synchronized void Prelievo(double quantita) {
		if(saldo - quantita > 0) {
			saldo -= quantita;
			System.out.println(Thread.currentThread().getName() + ": prelevato " + quantita + ". Saldo: " + saldo);
		}
		else {
			try {
				// se non ci sono abbastanza soldi metto in pausa il figlio e risveglio il padre per ricaricare il conto
				System.out.println(Thread.currentThread().getName() + ": conto a 0 o in rosso, chiamo il Padre!");
				notify();
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
