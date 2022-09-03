package bar;

public class ClienteOspite extends Thread{ 
	BarIF m;
	
	public ClienteOspite(BarIF M, int id){
		this.m = M;
		this.setName("Ospite" + id);
	}
	
	public void run(){
		try{
			m.entraO();
			System.out.print( this.getName() + ": sto consumando...\n");
			Thread.sleep(3000);
			System.out.println( this.getName() + ": sto uscendo...");
			m.esciO();
		}catch(InterruptedException e){}
  }
}

