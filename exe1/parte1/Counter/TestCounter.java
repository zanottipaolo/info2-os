package Counter;

class TestCounter{
	public static void main(String[] args) {
		Counter c = new Counter();
		
		for(int i=0; i<5; i++) {
			TaskI inc = new TaskI(c, i+1);
			inc.start();
		}
		
		for(int i=0; i<5; i++) {
			TaskD dec = new TaskD(c, i+1);
			dec.start();
		}
	}
}