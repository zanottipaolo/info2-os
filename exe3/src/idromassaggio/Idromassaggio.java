package idromassaggio;

import java.util.concurrent.locks.*;

public class Idromassaggio {
	private final int MAX = 4;	// numero massimo di persone dentro l'idro
	private int nDonne, nUomini; // numero di donne e di uomini nell'idromassaggio
	private int wDonne, wUomini; // numero di donne e di uomini in attesa di entrare
	
	private Lock key;			// accesso alla sezione critica
	private Condition codaDonne, codaUomini; // Thread (donne e uomini) in attesa
	
	public Idromassaggio() {
		// all'inizio l'idro è vuoto
		nDonne = 0;
		nUomini = 0;
		wDonne = 0;
		wUomini = 0;
		
		key = new ReentrantLock();
		codaDonne = key.newCondition();
		codaUomini = key.newCondition();
	}
	
	public void DonnaEntra() {
		key.lock();
		
		try {
			// sezione critica
			
			wDonne++;
			
			// finché nell'idro ci sono dei maschi o se è pieno allora aspetta
			while(nUomini > 0 || nDonne + nUomini == MAX) {
				codaDonne.await();
			}
			
			System.out.println(Thread.currentThread().getName() + ": sto entrando. " + "Donne dentro: " + nDonne
					+ ", Uomini Dentro: " + nUomini + ". Donne in attesa: " + wDonne + ", Uomini in attesa: " + wUomini);
			
			// quando può finalmente entrare
			wDonne--;
			nDonne++;

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			key.unlock();
		}
	}
	
	public void UomoEntra() {
		key.lock();
		
		try {
			// sezione critica
			
			wUomini++;
			
			// si aspetta finché ci sono dentro delle donne o se è pieno o se ci sono delle donne in attesa
			// perché devo dare precedenza
			while(nDonne > 0 || nDonne + nUomini == MAX || wDonne > 0) {
				codaUomini.await();
			}
			
			System.out.println(Thread.currentThread().getName() + ": sto entrando. " + "Donne dentro: " + nDonne
					+ ", Uomini Dentro: " + nUomini + ". Donne in attesa: " + wDonne + ", Uomini in attesa: " + wUomini);
			
			// quando può finalmente entrare
			wUomini--;
			nUomini++;
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			key.unlock();
		}
	}
	
	public void DonnaEsce() {
		key.lock();
		
		try {
			// sezione critica
			
			nDonne--;
			
			System.out.println(Thread.currentThread().getName() + ": sto uscendo. " + "Donne dentro: " + nDonne
					+ ", Uomini Dentro: " + nUomini + ". Donne in attesa: " + wDonne + ", Uomini in attesa: " + wUomini);
			
			// se ci sono donne in attesa ne si risveglia una (per dare precedenza)
			if(wDonne > 0)
				codaDonne.signal();
			else if(nDonne == 0 && wUomini > 0) // se ci sono uomini in attesa e se nell'idro non ci sono più donne allora sveglia tutti gli uomini
				codaUomini.signalAll();
			
		}
		finally {
			key.unlock();
		}
	}
	
	public void UomoEsce() {
		key.lock();
		
		try {
			// sezione critica
			
			nUomini--;
			
			System.out.println(Thread.currentThread().getName() + ": sto uscendo. " + "Donne dentro: " + nDonne
					+ ", Uomini Dentro: " + nUomini + ". Donne in attesa: " + wDonne + ", Uomini in attesa: " + wUomini);
			
			// se ci sono delle donne in attesa e nell'idro non ci sono più uomini allora si risvegliano le donne
			if(nUomini == 0 && wDonne > 0)
				codaDonne.signalAll();
			else if(nDonne == 0 && wUomini > 0)
				codaUomini.signal();
		}
		finally {
			key.unlock();
		}
	}
}
