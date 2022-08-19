package esercizio4;

class esercizio4{
	public static void main(String[] args) {
		Thread newThread = new Worker4();
		newThread.start();
		
		try {
			newThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}