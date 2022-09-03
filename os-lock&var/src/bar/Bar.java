package bar;

import java.util.concurrent.locks.*;

public class Bar implements BarIF {
	private final int NMAX = 10; // capacità bar
	private int clientiO;	// numero di tifosi ospiti nel bar
	private int clientiL;	// numero di tifosi locali nel bar
	private boolean uscita; // flag per indiciare che il barista vuole chiudere
	private int sospesiO;	// numero di ospiti in attesa di entrare
	private int sospesiL;	// numero di locali in attesa di entrare
	
	private Lock key;
	private Condition codaClientiL, codaClientiO, codaBar; // codabar è per il barista
	
	public Bar() {
		// All'inizio il bar è vuoto e il barista non vuole chiudere
		clientiO = 0;
		clientiL = 0;
		sospesiO = 0;
		sospesiL = 0;
		
		uscita = false;
		
		key = new ReentrantLock();
		codaClientiL = key.newCondition();
		codaClientiO = key.newCondition();
		codaBar = key.newCondition();
	}

	@Override
	public void entraO() {
		key.lock();
		
		try {
			// sezione critica
			
			// i clienti ospiti aspettano ad entrare se c'è dentro almeno un tifoso avversario o se il bar è pieno
			// o se il barista vuole chiudere
			while(clientiL != 0 || (clientiO + clientiL == NMAX) || uscita) {
				try {
					codaClientiO.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				sospesiO++;
			}
			
			sospesiO--;
			clientiO++;
			
		}
		finally {
			key.unlock();
		}
	}

	@Override
	public void entraL() {
		// deve dare precedenza alla squadra ospite
		key.lock();
		
		try {
			// sezione critica
			
			// i clienti locali aspettano ad entrare se c'è dentro almeno un tifoso avversario o se il bar è pieno
			// o se il barista vuole chiudere oppure se c'è un tifoso della squadra ospite in attesa 
			// (questo perché devo dare precedenza alla squadra ospite)
			while(clientiO != 0 || (clientiO + clientiL == NMAX) || uscita || sospesiO > 0) {
				try {
					codaClientiL.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				sospesiL++;
			}
			
			sospesiL--;
			clientiL++;
			
		}
		finally {
			key.unlock();
		}
	}

	@Override
	public void esciO() {
		key.lock();
		
		try {
			clientiO--;
			
			if(uscita && clientiO == 0)	// se il barista vuole chiudere e se sono l'ultimo cliente allora tocca a me svegliare il barista
				codaBar.signal();
			else if(sospesiO > 0)	// se c'è qualche altro ospite in attesa lo risveglio (devo dare precedenza agli ospiti)
				codaClientiO.signal();
			else
				codaClientiL.signal();
		}
		finally {
			key.unlock();
		}
	}

	@Override
	public void esciL() {
		key.lock();
		
		try {
			clientiL--;
			
			if(uscita && clientiL == 0)	// se il barista vuole chiudere e se sono l'ultimo cliente allora tocca a me svegliare il barista
				codaBar.signal();
			else if(sospesiO > 0)	// se c'è qualche altro ospite in attesa lo risveglio (devo dare precedenza agli ospiti)
				codaClientiO.signal();
			else
				codaClientiL.signal();
		}
		finally {
			key.unlock();
		}
	}

	@Override
	public void inizio_chiusura() {
		key.lock();
		
		try {
			uscita = true;
			
			// finché ci sono clienti nel bar il barista aspetta
			while(clientiL + clientiO > 0) {
				try {
					codaBar.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		finally {
			key.unlock();
		}
	}

	@Override
	public void fine_chiusura() {
		key.lock();
		
		try {
			uscita = false;
			
			System.out.println("Bar di nuovo aperto");
			// risveglia prima gli ospiti in attesa quando riapre
			if(sospesiO > 0)
				codaClientiO.signalAll();
			else
				codaClientiL.signalAll(); 
		}
		finally {
			key.unlock();
		}
	}

}
