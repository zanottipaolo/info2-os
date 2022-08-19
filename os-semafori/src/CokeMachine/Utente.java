package CokeMachine;

public class Utente extends Thread {
	CokeMachineIF c;
	
	public Utente(CokeMachineIF c, int id){
		this.c=c;
		this.setName("Utente"+id);
	}
	
	public void run()	{
		while(true){
			try	{
				c.remove();
				sleep((int)(Math.random()*5000)); //beve la lattina prima di prenderne un'altra
			}
			catch(InterruptedException e)			{
				e.printStackTrace();
			}
		}
	}
}
