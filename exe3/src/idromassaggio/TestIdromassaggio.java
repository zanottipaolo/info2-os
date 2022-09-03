package idromassaggio;

public class TestIdromassaggio {
	public static void main(String[] args) {
		Idromassaggio idro = new Idromassaggio();
		
		Donna[] donne = new Donna[5];
		Uomo[] uomini = new Uomo[5];
		
		for(int i=0; i<5; i++) {
			donne[i] = new Donna(idro, i+1);
			uomini[i] = new Uomo(idro, i+1);
			
			donne[i].start();
			uomini[i].start();
		}
	}
}
