package proiezione;

import java.util.concurrent.ThreadLocalRandom;

public class Clienti extends Thread{
	String name;
	int seats;
	Proiezione film;
	
	public Clienti(String name, int seats, Proiezione film) {
		this.setName(name);
		this.seats = seats;
		this.film = film;
	}
	
	public int getSeats() {
		return seats;
	}
	
	public void run() {
		int needSeats = ThreadLocalRandom.current().nextInt(1, 20);
		
		while(true) {
			film.prenota(this.getName(), needSeats);	
			
			if (Thread.currentThread().isInterrupted()) {
				break;
			}
		}
		
		seats += needSeats;
	}
}
