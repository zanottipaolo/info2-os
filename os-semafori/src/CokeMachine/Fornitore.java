package CokeMachine;

public class Fornitore extends Thread {
	CokeMachineIF c;
	
	public Fornitore(CokeMachineIF c) {
		this.c=c;
		this.setName("Fornitore");
	}
	
	public void run(){
		while(true)
			c.deposit();
	}
}
