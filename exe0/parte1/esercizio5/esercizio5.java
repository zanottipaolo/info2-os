package esercizio5;

import java.util.Scanner;

class esercizio5{
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Quanti corridori ci sono?");
		
		int numerCorridori = sc.nextInt();
		
		sc.close();
		
		Thread[] atleti = new Corridori[numerCorridori];
		
		for(int i=0; i<atleti.length; i++) {
			atleti[i] = new Corridori("atleta" + (i+1));
			atleti[i].start();
		}
	}
}