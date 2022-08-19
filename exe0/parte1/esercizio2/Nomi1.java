package esercizio2;

class Nomi1 extends Thread{
	String name;
	
	public Nomi1(String name) {
		this.name = name;
	}
	
	public void run() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Ciao, sono il thread: " + this.name);
	}
}