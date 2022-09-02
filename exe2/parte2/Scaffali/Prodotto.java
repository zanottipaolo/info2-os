package Scaffali;

public class Prodotto {
	String name;
	int qta_residua, prezzo;
	
	public Prodotto(String name, int qta_residua, int prezzo) {
		this.name = name;
		this.qta_residua = qta_residua;
		this.prezzo = prezzo;
	}
	
	public synchronized void Preleva(int qta) {
		if(qta > qta_residua)
			throw new RuntimeException("Prodotto non disponibile in quelle quantità");
		
			qta_residua -= qta;
	}
	
	public synchronized void Restituisci(int qta) {
		qta_residua += qta;
	}
}
