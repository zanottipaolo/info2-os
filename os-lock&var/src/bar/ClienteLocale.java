package bar;

public class ClienteLocale extends Thread{ 
   BarIF m;
   
   public ClienteLocale(BarIF M, int id){
	   this.m =M;
	   this.setName("Locale" + id);
   }
   		
   public void run(){
	try{
      m.entraL();
      System.out.println( this.getName() + ": sto consumando...");
      Thread.sleep(3000);
      System.out.println( this.getName() + ": sto uscendo...");
      m.esciL();
    }
    catch(InterruptedException e){}
 }
}
