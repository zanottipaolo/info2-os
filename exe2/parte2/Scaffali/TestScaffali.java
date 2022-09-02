package Scaffali;

public class TestScaffali {
	public static void main(String[] args) {
		Prodotto p1 = new Prodotto("Farina", 500, 15);
		Prodotto p2 = new Prodotto("Pizza", 22, 10);
		
		Cliente c1 = new Cliente(p1, "Matteo", 100, 3, 1);
		Cliente c2 = new Cliente(p1, "Federico", 350, 2, 2);
		Cliente c3 = new Cliente(p2, "Simone", 87, 6, 4);
		
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
		
		System.out.println("Il saldo finale di " + c1.getName() + " è di: " + c1.getBalance());
		System.out.println("Il saldo finale di " + c2.getName() + " è di: " + c2.getBalance());
		System.out.println("Il saldo finale di " + c3.getName() + " è di: " + c3.getBalance());
	}
}
