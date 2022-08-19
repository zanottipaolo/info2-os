package CokeMachine;

import java.util.concurrent.Semaphore;

//variante 2: Le lattine sono rappresentate da una variabile contatore e 
//il fornitore viene svegliato da qualsiasi cliente che trova la macchinetta vuota

public class CokeMachine2 implements CokeMachineIF {
	static final int N = 5;
	int count = 0; // numero effettivo di lattine presenti in un dato istante
	
	// 2 semafori binari
	private Semaphore mutex; // mutua esclusione
	private Semaphore empty; // x la macchinetta vuota
	
	boolean fornitoreAvvisato = false;
	
	public CokeMachine2() {
		count = N; // macchinetta piena
		mutex = new Semaphore(1); // non c'è nessun utente in attesa all'inizio
		empty = new Semaphore(0); // il fornitore resta in attesa che la macchinetta diventi vuota
	}
	
	@Override
	public void remove() {
		try {
			mutex.acquire();
			
			if(count == 0 && !fornitoreAvvisato) {
				System.out.println("Macchinetta vuota, avviso il fornitore - " + Thread.currentThread().getName());
				fornitoreAvvisato = true;
				empty.release();
			}
			else {
				count--; // prelevo la lattina
				System.out.println("Prelevo la lattina: " + Thread.currentThread().getName());
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
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
			
			fornitoreAvvisato = false;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			mutex.release();
		}
	}

}
