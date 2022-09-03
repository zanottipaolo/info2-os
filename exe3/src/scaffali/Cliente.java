package scaffali;

public class Cliente extends Thread{
	String name;
	int conto, qta_prelevare, qta_restituire;
	Prodotto p;
	
	public Cliente(String name, int conto, int qta_prelevare, int qta_restituire, Prodotto p) {
		this.setName(name);
		this.conto = conto;
		this.qta_prelevare = qta_prelevare;
		this.qta_restituire = qta_restituire;
		this.p = p;
	}
	
	public int getConto() {
		return this.conto;
	}
	
	@Override
	public void run() {
		if(qta_prelevare * p.prezzo < conto) {
			p.Preleva(qta_prelevare);
			conto -= qta_prelevare * p.prezzo;
		}
		else {
			System.out.println("Non bastano i soldi");
		}
		
		p.Reso(qta_restituire);
		
		conto += qta_restituire * p.prezzo;
	}
}
