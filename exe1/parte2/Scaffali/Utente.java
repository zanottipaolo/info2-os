package Scaffali;

import java.util.concurrent.ThreadLocalRandom;

class Utente extends Thread{
	Prodotti p;
	private int conto_residuo;
	private int qta_prelievo;
	private int qta_restituzione;
	
	public Utente(Prodotti p, String nome, int conto_residuo, int qta_prelievo, int qta_restituzione) {
		this.p = p;
		this.setName(nome);
		this.conto_residuo = conto_residuo;
		this.qta_prelievo = qta_prelievo;
		this.qta_restituzione = qta_restituzione;
	}
	
	public int getContoResiduo() {
		return conto_residuo;
	}
	
	@Override
	public void run() {
		int spesa = p.prezzo * this.qta_prelievo;
		int rimborso = p.prezzo * this.qta_restituzione;
		
		System.out.println("Conto iniziale di " + Thread.currentThread().getName() + ": " + this.conto_residuo + "€");
		
		// se l'utente vuole prelevare
		if(this.qta_prelievo > 0) {
			// controllo se basta il credito (se il mio conto residuo è maggiore o uguale del costo del prodotto * le quantità che voglio prendere)
			if(this.conto_residuo >= spesa) {
				p.Preleva(this.qta_prelievo);
				this.conto_residuo -= spesa;
				
				System.out.println("L'utente " + Thread.currentThread().getName() + " ha prelevato un prodotto. Conto residuo: " + this.conto_residuo);
			}
			else
				System.out.println("L'utente " + Thread.currentThread().getName() + " non ha abbastanza fondi!");
		}
		
		// dorme un po' per simulare l'azione
		try {
			Thread.sleep(ThreadLocalRandom.current().nextInt(2000, 4000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// se vuole restituire dei prodotti
		if(this.qta_restituzione > 0) {
			p.Restituisci(this.qta_restituzione);
			this.conto_residuo += rimborso;
			
			System.out.println("L'utente " + Thread.currentThread().getName() + " ha restituito un prodotto. Conto residuo: " + this.conto_residuo);
		}
	}
}