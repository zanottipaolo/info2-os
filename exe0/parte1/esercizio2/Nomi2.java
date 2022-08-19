package esercizio2;

class Nomi2 implements Runnable{
	String name;
	
	public Nomi2(String name) {
		this.name = name;
	}
	
	public void run() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Ciao, sono il Thread: " + this.name);
	}
}