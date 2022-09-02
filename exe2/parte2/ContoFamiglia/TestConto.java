package ContoFamiglia;

public class TestConto {
	public static void main(String[] args) {
		Conto c = new Conto();
		int numeroFigli = 5;
		Figlio[] figliolo = new Figlio[numeroFigli];
		
		Thread.currentThread().setName("Padre");
		
		for(int i=0; i<numeroFigli; i++) {
			figliolo[i] = new Figlio(i+1, c, i+15);
			figliolo[i].start();
		}
		
		while(true) {
			c.Versamento();
		}
	}
}
