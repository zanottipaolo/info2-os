package esercizio1;

import java.util.Scanner;

class TestCounter{
	public static void main(String[] args) {
		System.out.println("Inserisci il numero n: ");
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sc.close();
		
		Counter c = new Counter();
		
		Thread newThread = new TaskCounter(c, n);
		
		newThread.start();
		
		try {
			newThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Contatore: " + c.getCount());
	}
}