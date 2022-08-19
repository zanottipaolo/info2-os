package esercizio2;

class esercizio2_secondoMetodo{
	public static void main(String[] args) {
		System.out.println("Ciao, sono il Thread: " + Thread.currentThread().getName());
		
		Thread A = new Thread(new Nomi2("A"));
		Thread B = new Thread(new Nomi2("B"));
		
		A.start();
		B.start();
	}
}