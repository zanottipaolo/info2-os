package bar;

public class Barista extends Thread{ 
	BarIF m;
	
	public Barista(BarIF M){
		this.m =M;
	}
	
	public void run(){
		try{ 
			while(true){
			     Thread.sleep(10000);
				 System.out.print( "Barista: il bar � sporco e voglio chiudere.\n");	
			     m.inizio_chiusura();
			     System.out.print( "Barista: sto pulendo il bar...\n");
			     Thread.sleep(5000);
			     System.out.print( "Barista: ho finito di pulire il bar.\n");
			     m.fine_chiusura();
    
			}
		}
		catch(InterruptedException e){}
	}
}
