package Scaffali;

class TestSupermercato{
	public static void main(String[] args) {
		Prodotti p1, p2;
		Utente u1, u2, u3;
		
		p1 = new Prodotti("Farina", 30, 50);
		p2 = new Prodotti("Biscotti", 20, 38);
		
		u1 = new Utente(p1, "Matteo", 500, 3, 2);
		u2 = new Utente(p1, "Simone", 200, 2, 1);
		u3 = new Utente(p2, "Cristian", 1000, 10, 4);
		
		u1.start();
		u2.start();
		u3.start();
		
		try {
			u1.join();
			u2.join();
			u3.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Conto residuo di " + u1.getName() + ": " + u1.getContoResiduo());
		System.out.println("Conto residuo di " + u2.getName() + ": " + u2.getContoResiduo());
		System.out.println("Conto residuo di " + u3.getName() + ": " + u3.getContoResiduo());
	}
}