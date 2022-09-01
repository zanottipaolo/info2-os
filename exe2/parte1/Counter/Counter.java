package Counter;

public class Counter{
	private int count, max, min;
	
	public Counter() {
		count = 0;
		max = 10;
		min = 0;
	}
	
	public synchronized int getCount() {
		return count;
	}
	
	public synchronized void increment() {
		if(count < max) {
			count++;
			
			System.out.println(Thread.currentThread().getName() + " - Incremento count: " + count);
		}
		else
			System.out.println(Thread.currentThread().getName() + " - Impossibile incrementare: count già a " + max);
	}
	
	public synchronized void decrement() {
		if(count > min) {
			count--;
			
			System.out.println(Thread.currentThread().getName() + " - Decremento count: " + count);
		}
		else
			System.out.println(Thread.currentThread().getName() + " - Impossibile decrementare: count già a " + min);
	}
}