package SafeBridge;

import java.util.concurrent.Semaphore;

public class FairSafeBridgeSem implements Bridge {
	private int nBlue; // numero di auto blu in transito sul ponte
	private int nRed; // numero di auto rosse in transito sul ponte
	private int wBlue; // numero di auto blu in attesa di passare sul ponte
	private int wRed; // numero di auto rosse in attesa di passare sul ponte
	
	private Semaphore mutex; // semaforo di mutua esclusione per accedere alla sezione critica
	private Semaphore turnRed, turnBlue; // semaforo per il turno delle auto rosse e uno per le auto blu
	
	public FairSafeBridgeSem() {
		// Si assume che il ponte sia sgombro all'inizio
		nBlue = 0;
		nRed = 0;
		wBlue = 0;
		wRed = 0;
		
		mutex = new Semaphore(1);
		turnRed = new Semaphore(0, true); // le macchine rosse non possono passare all'inizio, flag di fairness = true
		turnBlue = new Semaphore(0, true); // le macchine blue non possono passare all'inizio
		
		// la prima macchina che arriva sul ponte e che richiederà di passare farà scattare il semaforo
		
		// N.B.
		// flag di fairness è un paramento della classe Semaphore, mettendolo su true andrà a 
		// risvegliare il primo thread che si è messo nella coda d'attesa del semaforo
		// e non uno a caso.
	}

	@Override
	public void redEnter() {
		try {
			mutex.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Entro nella sezione critica
		
		// Se il ponte è libero, passo
		if(nRed == 0 && (nBlue + wBlue == 0)) {
			// diamo il permesso alle macchine rosse di passare
			turnRed.release();
			
			nRed++;
			
			System.out.println(Thread.currentThread().getName() + " sta passando. Nred = " + nRed + ", Nblue: " + 
			nBlue + ", WRed =  " + wRed + ", Wblue = " + wBlue);
		}
		else {
			// se il ponte non è libero allora la macchian deve attendere
			wRed++;
		}
		
		// lascio la Sezione Critica
		mutex.release();

		// Serve per bloccare le auto in attesa. Finché non viene rilasciata la risorsa (nell'IF) restano in attesa
		// cioè: finché quella macchina non entra allora si blocca il thread.
		
		// Poi quindi tolgo il permesso alle auto rosse di passare tramite l'acquire
		try {
			turnRed.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void redExit() {
		try {
			mutex.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// sezione critica
		
		// la macchina rossa esce
		nRed--;
		
		// controllo se ci sono auto blu e rosse in attesa di entrare sul ponte
		// così da dare precedenza a quelle blu
		if(wBlue == 0 && wRed > 0) {
			// sveglia un'auto rossa in attesa se non c'è nessuna auto blu in attesa.
			// Quindi può procedere con il turnRed.acquire() della riga 68
			turnRed.release();
			
			// quando l'auto in attesa finalmente ha il permesso di passare poiché dato 
			// dall'auto uscente allora aumenterà il numero di auto sul ponte e diminuirà il numero di 
			// auto in attesa
			nRed++;
			wRed--;
			
		}
		// se ci sono auto blu in attesa
		else if(wBlue > 0 && nRed == 0){
			// sveglia un'auto blu in attesa che quindi può procedere con il turnBlue.acquire() della riga 151
			turnBlue.release();
			
			nBlue++;
			wBlue--;
		}
		
		System.out.println(Thread.currentThread().getName() + " sta uscendo. Nred = " + nRed + ", Nblue: " + 
				nBlue + ", WRed =  " + wRed + ", Wblue = " + wBlue);
		
		// lascio la sezione critica
		mutex.release();

	}

	@Override
	public void blueEnter() {
		try {
			mutex.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// sezione critica
		
		// se il ponte è libero allora passo
		if(nBlue == 0 && (nRed + wRed == 0)) {
			turnBlue.release(); // Permesso alle auto blu di passare
			
			nBlue++;
			
			System.out.println(Thread.currentThread().getName() + " sta passando. Nred = " + nRed + ", Nblue: " + 
					nBlue + ", WRed =  " + wRed + ", Wblue = " + wBlue);
			
		}
		else {
			// se il ponte non è libero allora vado in attesa
			wBlue++;
		}
		
		// lascio la sezione critica
		mutex.release();
		
		// Serve per bloccare le auto in attesa. Finché non viene rilasciata la risorsa (nell'IF) restano in attesa
		// cioè: finché quella macchina non entra allora si blocca il thread.
		
		// Poi quindi tolgo il permesso alle auto blu di passare
		try {
			turnBlue.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void blueExit() {
		try {
			mutex.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// sezione critica
		
		// la macchina blu esce
		nBlue--;
		
		// controllo se ci sono auto blu in attesa di entrare sul ponte
		// così da dare loro precedenza
		if(wRed == 0 && wBlue > 0) {
			// sveglia un'auto blu in attesa che quindi può procedere con il turnBlue.acquire() della riga 151
			turnBlue.release();
			
			// quando l'auto in attesa finalmente ha il permesso di passare poiché dato 
			// dall'auto uscente allora aumenterà il numero di auto sul ponte e diminuirà il numero di 
			// auto in attesa
			nBlue++;
			wBlue--;
		}
		// se ci sono auto rosse in attesa
		else if(wRed > 0 && nBlue == 0){
			// sveglia un'auto rossa in attesa che quindi può procedere con il turnRed.acquire() della riga 68
			turnRed.release();
			
			nRed++;
			wRed--;
		}
		
		System.out.println(Thread.currentThread().getName() + " sta uscendo. Nred = " + nRed + ", Nblue: " + 
				nBlue + ", WRed =  " + wRed + ", Wblue = " + wBlue);
		
		// lascio la sezione critica
		mutex.release();
	}

}
