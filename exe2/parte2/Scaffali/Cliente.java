package Scaffali;

public class Cliente extends Thread{
	String name;
	int conto_residuo, qta_prelevare, qta_restituire;
	Prodotto p;
	
	public Cliente(Prodotto p, String name, int conto_residuo, int qta_prelevare, int qta_restituire) {
		this.name = name;
		this.conto_residuo = conto_residuo;
		this.qta_prelevare = qta_prelevare;
		this.qta_restituire = qta_restituire;
		this.p = p;
	}
	
	public int getBalance() {
		return conto_residuo;
	}
	
	@Override
	public void run() {
		if(conto_residuo < p.prezzo * qta_prelevare) {
			throw new RuntimeException(Thread.currentThread().getName() + " non ha abbastanza soldi.");
		}
		
		p.Preleva(qta_prelevare);
		conto_residuo -= qta_prelevare * p.prezzo;
				
		p.Restituisci(qta_restituire);
		conto_residuo += qta_restituire * p.prezzo;
	}
}
