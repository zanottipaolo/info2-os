package scaffali;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Prodotto {
	String name;
	int qta_residua, prezzo;
	
	private Lock key;
	private Condition attesaProdotto;
	
	public Prodotto(String name, int qta_residua, int prezzo) {
		this.name = name;
		this.qta_residua = qta_residua;
		this.prezzo = prezzo;
		
		key = new ReentrantLock();
		attesaProdotto = key.newCondition();
	}
	
	public void Preleva(int qta) {
		key.lock();
		
		try {
			// sezione critica

			if(qta_residua < qta)
				attesaProdotto.await();
			else {
				qta_residua -= qta;
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			key.unlock();
		}
	}
	
	public void Reso(int qta) {
		key.lock();
		
		try {
			// sezione critica
			qta_residua += qta;
			
			attesaProdotto.signalAll();
		}
		finally {
			key.unlock();
		}
	}
}
