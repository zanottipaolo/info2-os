package esercizio4;

class Worker4 extends Thread{
	int n = 2;
	int s = 5000;
	
	@Override
	public void run() {
		for(int i=0; i<n; i++) {
			try {
				Thread.sleep(s);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}