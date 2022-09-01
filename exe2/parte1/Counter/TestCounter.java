package Counter;

public class TestCounter {
	public static void main(String[] args) {
		Counter c = new Counter();
		Thread inc[] = new TaskI[5];
		Thread dec[] = new TaskD[5];
		
		for(int i=0; i<5; i++) {
			inc[i] = new TaskI(c, i+1);
			inc[i].start();
			
			dec[i] = new TaskD(c, i+1);
			dec[i].start();
		}
		
		for(int i=0; i<5; i++) {
			try {
				inc[i].join();
				dec[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
