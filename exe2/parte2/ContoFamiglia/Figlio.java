package ContoFamiglia;

public class Figlio extends Thread{
	Conto c;
	int eta;
	
	public Figlio(int id, Conto c, int eta) {
		this.setName("Figlio" + id);
		this.c = c;
		this.eta = eta;
	}
	
	@Override
	public void run() {
		while(true) {
			c.Prelievo(this.eta * 10.5);
		}
	}
}
