package CokeMachine;

import java.util.concurrent.ThreadLocalRandom;

public class Utente extends Thread{
	CokeMachine c;
	
	public Utente(CokeMachine c, int id) {
		this.c = c;
		this.setName("Utente" + id);
	}
	
	@Override
	public void run() {
		while(true) {
			c.Preleva();
			try {
				this.sleep(ThreadLocalRandom.current().nextInt(5000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
