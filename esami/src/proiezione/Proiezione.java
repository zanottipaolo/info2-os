package proiezione;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class Proiezione {
	private String filmName;
	private int totalSeats;
	private Semaphore mutex, currentSeats;
	private boolean flag;
	private int hours;
	
	public Proiezione(String filmName, int totalSeats, int hours) {
		this.filmName = filmName;
		this.totalSeats = totalSeats;
		mutex = new Semaphore(1);
		currentSeats = new Semaphore(this.totalSeats);
		flag = false;
		this.hours = hours;
	}
	
	public Semaphore getSeats() {
		return this.currentSeats;
	}
	
	public int getHour() {
		return this.hours;
	}
	
	public void prenota(String nomeCliente, int numPosti) {
		// accedo alla sezione critica
		try {
			mutex.acquire();
			
			// se i posti che richiedo sono più di quelli disponibili: errore
			// altrimenti li prenoto
			if(numPosti > currentSeats.availablePermits()) {
				System.out.println("ERRORE: non ci sono abbastanza posti!");
				Thread.currentThread().sleep(5000);
				
				// se manca meno di mezz'ora dalla prenotazione all'inizio del film allora interrompo il thread
				if(this.getHour() - ThreadLocalRandom.current().nextInt(0, 23) < 30) {
					Thread.currentThread().interrupt();
				}
			}
			else {
				try {
					currentSeats.acquire(numPosti);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println("Il cliente " + Thread.currentThread().getName() + " ha prenotato " + numPosti + " posti.");
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		// esco dalla sezione critic
		mutex.release();
	}
	
	public void cancella(int totalSeats) {
		try {
			mutex.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		currentSeats.release();
		
		System.out.println("Il cliente " + Thread.currentThread().getName() + " ha cancellato tutti i suoi posti.");
		
		mutex.release();
	}
}
