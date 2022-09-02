package proiezione;

public class TestProiezione {
	public static void main(String[] args) {
		Proiezione film = new Proiezione("Harry Potter 7", 50, 19);
		
		Clienti cliente1 = new Clienti("Matteo", 0, film);
		Clienti cliente2 = new Clienti("Paolo", 0, film);
		Clienti cliente3 = new Clienti("Simone", 0, film);
		Clienti cliente4 = new Clienti("Cristian", 0, film);
		Clienti cliente5 = new Clienti("Federico", 0, film);
		
		cliente1.start();
		cliente2.start();
		cliente3.start();
		cliente4.start();
		cliente5.start();
		
		try {
			cliente1.join();
			cliente2.join();
			cliente3.join();
			cliente4.join();
			cliente5.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Matteo ha prenotato " + cliente1.getSeats() + " posti.");
		System.out.println("Paolo ha prenotato " + cliente2.getSeats() + " posti.");
		System.out.println("Simone ha prenotato " + cliente3.getSeats() + " posti.");
		System.out.println("Cristian ha prenotato " + cliente4.getSeats() + " posti.");
		System.out.println("Federico ha prenotato " + cliente5.getSeats() + " posti.");
	}
}
