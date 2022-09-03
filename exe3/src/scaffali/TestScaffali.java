package scaffali;

public class TestScaffali {
	public static void main(String[] args) {
		Prodotto p1 = new Prodotto("Farina", 100, 4);
		Prodotto p2 = new Prodotto("Pizza", 50, 7);
		
		Cliente c1 = new Cliente("Matteo", 50, 4, 3, p1);
		Cliente c2 = new Cliente("Cristian", 150, 10, 5, p1);
		Cliente c3 = new Cliente("Federico", 84, 6, 2, p2);
		
		c1.start();
		c2.start();
		c3.start();
		
		try {
			c1.join();
			c2.join();
			c3.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Conto finale di " + c1.getName() + ": " + c1.getConto());
		System.out.println("Conto finale di " + c2.getName() + ": " + c2.getConto());
		System.out.println("Conto finale di " + c3.getName() + ": " + c3.getConto());
	}
}
