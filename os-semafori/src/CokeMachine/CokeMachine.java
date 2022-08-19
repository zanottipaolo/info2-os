package CokeMachine;

import java.util.concurrent.Semaphore;

// variante 1: Le lattine sono rappresentate da una variabile contatore e 
// il fornitore viene svegliato dal cliente (una sola notifica) che consuma l'ultima lattina

public class CokeMachine implements CokeMachineIF {
	static final int N = 5;
	int count = 0; // numero effettivo di lattine presenti in un dato istante
	
	// 2 semafori binari
	private Semaphore mutex; // mutua esclusione
	private Semaphore empty; // x la macchinetta vuota
	
	public CokeMachine() {
		count = N; // macchinetta piena
		mutex = new Semaphore(1); // non c'è nessun utente in attesa all'inizio
		empty = new Semaphore(0); // il fornitore resta in attesa che la macchinetta diventi vuota
	}
	
	@Override
	public void remove() {
		try {
			mutex.acquire();
			
			if(count > 0) {
				count--; // prelevo la lattina
				System.out.println("Lattina rimossa da: " + Thread.currentThread().getName());
				
				// se ho rimosso l'ultima lattina avviso il fornitore
				if(count == 0) {
					System.out.println("Macchientta vuota, avviso il fornitore: " + empty.availablePermits());
					empty.release();
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			mutex.release();
		}
	}

	@Override
	public void deposit() {
		try {
			empty.acquire();
			mutex.acquire();
			
			//sezione critica
			count = N; //riempie la macchinetta
			System.out.println(Thread.currentThread().getName() + ": n. lattine = " + count);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			mutex.release();
		}
	}

}
