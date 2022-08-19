package esercizio5;

class Corridori extends Thread{
	String name;
	int i = 0;
	
	public Corridori(String name) {
		this.name = name;
	}
	
	@Override
	public void run() {
		while(i != 100) {
			i++;
			System.out.println(this.name + ": " + i);
		}
		
		// se esce dal while è perché i è arrivato a 100
		System.out.println(name + ": " + "Sono arrivato!");
	}
}