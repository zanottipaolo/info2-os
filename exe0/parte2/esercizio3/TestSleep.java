package esercizio3;

class TestSleep{
	public static void main(String[] args) {
		Interrupt newThread = new Interrupt();
		newThread.start();
		
		try{ Thread.sleep(5000); } catch(InterruptedException ie) {};
		
		newThread.interrupt();
	}
}