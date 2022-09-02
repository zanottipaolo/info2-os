package CokeMachine;

public class TestMachine {
	public static void main(String[] args) {
		CokeMachine c = new CokeMachine();
		Utente[] utenti = new Utente[5];
		Rifornitore rifornitore = new Rifornitore(c);
		
		for(int i=0; i<5; i++) {
			utenti[i] = new Utente(c, i+1);
			utenti[i].start();
		}
		
		rifornitore.start();
	}
}
