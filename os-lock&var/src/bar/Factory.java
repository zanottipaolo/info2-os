package bar;

import java.util.concurrent.*;

public class Factory {

	public static void main(String[] args) {
		int i;
		Bar M=new Bar();
		ClienteOspite []CO= new ClienteOspite[10];
		ClienteLocale []CL= new ClienteLocale[10];
		Barista B=new Barista(M);
		
		System.out.println("BAR APERTO!!!!");
		
		for(i=0;i<10;i++){
			CO[i]=new ClienteOspite(M, i+1);
			CL[i]=new ClienteLocale(M, i+1);
		}
		
		for(i=0;i<10;i++){
			CO[i].start();
			CL[i].start();
		}
		
		B.start();
	}

}
