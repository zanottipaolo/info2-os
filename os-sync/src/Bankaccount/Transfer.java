package Bankaccount;


public class Transfer  {
  public static void main(String[] args) {
    
	BankAccount a = new BankAccount(100.0);
    BankAccount b = new BankAccount(100.0);
	  
    Thread t1 = new TransferThread(a, b, 50.0);  // thread che effettua il trasferimento
    Thread t2 = new TransferThread(b, a, 10.0);
    t1.start();
    t2.start();      
    //Thread.yield();  // Force a context switch
    try {
		t1.join();
		t2.join();	
		
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	
    
	}
    System.out.println("Account a has $"+a.getBalance());
    System.out.println("Account b has $"+b.getBalance());
  }

}

