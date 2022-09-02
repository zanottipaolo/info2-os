package CokeMachine;

public class Rifornitore extends Thread{
	CokeMachine c;
	
	public Rifornitore(CokeMachine c) {
		this.c = c;
		this.setName("Rifornitore");
	}
	 @Override
	public void run() {
		 while(true)
			 c.Rifornisci();
	}
}
