package safebridge;

public class FairSafeBridgeSem implements Bridge {
	private int nRed, nBlue; // numero di auto rosse e blu in transito
	private int wRed, wBlue; // numero di auto rosse e bli un attesa
	private boolean turn; // se TRUE è il turno delle auto blu, se è FALSE è il turno delle auto rosse
	
	public FairSafeBridgeSem() {
		// all'inizio il ponte è vuoto
		nRed = 0;
		nBlue = 0;
		wRed = 0;
		wBlue = 0;
		turn = true;
	}

	@Override
	public synchronized void redEnter() {
		wRed++; // si inizia con un'auto rossa che vuole entrare
		
		// l'auto resta in attesa se il ponte è occupato o se c'è un'auto blu in attesa di passare e il turno è delle auto blu
		while((nRed + nBlue > 0) || (wBlue > 0 && turn)) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// accedo alla sezione critica
		nRed++;
		wRed--; // l'auto rossa sta passando quindi non è più in attesa
		
		System.out.println("Sta passando " + Thread.currentThread().getName() + ". NRED: " + nRed + ", NBLUE: " + nBlue);
	}

	@Override
	public synchronized void redExit() {
		nRed--;
		
		System.out.println("Sta uscendo " + Thread.currentThread().getName() + ". NRED: " + nRed + ", NBLUE: " + nBlue);
		
		// se è l'ultima auto rossa allora poi sarà il turno delle auto blu
		if(nRed == 0) {
			turn = true;
			
			notifyAll(); // l'utlima macchina è passata e quindi risveglio tutti i thread (auto blu o rossa)
		}
	}

	@Override
	public synchronized void blueEnter() {
		wBlue++; // si inizia con un'auto blu che vuole entrare
		
		// l'auto resta in attesa se il ponte è occupato o se c'è un'auto rossa in attesa di passare e il turno è delle auto rosse
		while((nRed + nBlue > 0) || (wRed > 0 && !turn)) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// accedo alla sezione critica
		nBlue++;
		wBlue--; // l'auto blu sta passando quindi non è più in attesa
		
		System.out.println("Sta passando " + Thread.currentThread().getName() + ". NRED: " + nRed + ", NBLUE: " + nBlue);
	}

	@Override
	public synchronized void blueExit() {
		nBlue--;
		
		System.out.println("Sta uscendo " + Thread.currentThread().getName() + ". NRED: " + nRed + ", NBLUE: " + nBlue);
		
		// se ultima macchina poi si cambia il turno di passaggio
		if(nBlue == 0) {
			turn = false;
			
			notifyAll(); // l'ultima macchina è passata e quindi risveglio tutti i thread (auto blu o rossa)
		}
	}
}
