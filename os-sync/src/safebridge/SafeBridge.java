package safebridge;

public class SafeBridge implements Bridge {
	private int nRed, nBlue; // numero di auto rosse e blu in transito
	
	public SafeBridge() {
		// all'inizio il ponte è vuoto
		nRed = 0;
		nBlue = 0;
	}

	@Override
	public synchronized void redEnter() {
		// se il numero è maggiore di 0 vuol dire che il ponte è occupato
		while(nRed + nBlue > 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// accedo alla sezione critica
		nRed++;
		
		System.out.println("Sta passando " + Thread.currentThread().getName() + ". NRED: " + nRed + ", NBLUE: " + nBlue);
	}

	@Override
	public synchronized void redExit() {
		nRed--;
		
		System.out.println("Sta uscendo " + Thread.currentThread().getName() + ". NRED: " + nRed + ", NBLUE: " + nBlue);
		
		notify(); // la macchina è passata e quindi risveglio un altro thread (auto blu o rossa)
	}

	@Override
	public synchronized void blueEnter() {
		// se il numero è maggiore di 0 vuol dire che il ponte è occupato
		while(nRed + nBlue > 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// accedo alla sezione critica
		nBlue++;
			
		System.out.println("Sta passando " + Thread.currentThread().getName() + ". NRED: " + nRed + ", NBLUE: " + nBlue);
	}

	@Override
	public synchronized void blueExit() {
		nBlue--;
		
		System.out.println("Sta uscendo " + Thread.currentThread().getName() + ". NRED: " + nRed + ", NBLUE: " + nBlue);
		
		notify(); // la macchina è passata e quindi risveglio un altro thread (auto blu o rossa)
	}
}
