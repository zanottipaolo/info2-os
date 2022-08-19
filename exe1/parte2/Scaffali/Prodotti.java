package Scaffali;

import java.util.concurrent.Semaphore;

class Prodotti{
	String nome;
	int qta_residua;
	int prezzo;
	
	private Semaphore mutex;
	private Semaphore pezzi;
	
	public Prodotti(String nome, int qta_residua, int prezzo) {
		this.nome = nome;
		this.qta_residua = qta_residua;
		this.prezzo = prezzo;
		
		mutex = new Semaphore(1);				// Mutex lo uso per accedere con Mutua Esclusione al prodotto
		pezzi = new Semaphore(qta_residua);		// Pezzi per contare i prodotti
	}
	
	public void Preleva(int qta) {
		try {
			mutex.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// sezione critica
		
		// se la quantità residua è minore della quantità richiesta: ERRORE
		if(this.qta_residua < qta) {
			System.out.println("Errore: qta prodotto non sufficiente!");
		}
		else{
			// prelevo la quantità desiderata di prodotto
			try {
				pezzi.acquire(qta);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			this.qta_residua -= qta;
			
			System.out.println(Thread.currentThread().getName() + " sta prelevando: " + this.nome + " x " + qta + " = " + this.prezzo * qta + "€");
			System.out.println("Quantità di " + this.nome + " rimasta: " + pezzi.availablePermits());
		}
		
		// esco dalla sezione critica
		mutex.release();
	}
	
	public void Restituisci(int qta) {
		try {
			mutex.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// sezione critica
		pezzi.release(qta);
		
		this.qta_residua += qta;
		
		System.out.println(Thread.currentThread().getName() + " sta restituendo: " + this.nome + " x " + qta + " = " + this.prezzo * qta + "€");
		System.out.println("Quantità di " + this.nome + "rimasta: " + pezzi.availablePermits());
		
		// esco dalla sezione critica
		mutex.release();
	}
}