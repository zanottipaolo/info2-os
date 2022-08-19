package esercizio2;

class esercizio2_primoMetodo{
	public static void main(String args[]) {
		System.out.println("Ciao, sono il thread: " + Thread.currentThread().getName());
		
		Thread A = new Nomi1("A");
		Thread B = new Nomi1("B");
		
		A.start();
		B.start();
	}
}