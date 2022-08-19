package esercizio2;

import java.util.concurrent.ThreadLocalRandom;

class SommaParallela{
	public static void main(String[] args) {
		int somma = 0;
		int[] serieNumerica = new int[50];
		TaskSommatore[] threads = new TaskSommatore[5];
		int n = 5;
		
		for(int i=0; i<serieNumerica.length; i++) {
			serieNumerica[i] = ThreadLocalRandom.current().nextInt();
		}
		
		for(int i=0; i<n; i++) {
			threads[i] = new TaskSommatore(serieNumerica, i*10, i*10+9);
			threads[i].start();
		}
		
		for(int i=0; i<n; i++) {
			try{
				threads[i].join();
				somma += threads[i].getResult();
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("La somma finale è: " + somma);
		
	}
}