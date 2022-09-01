package Bankaccount;

public class TransferThread extends Thread{
	public BankAccount a, b;
	public double amount;
	
	public TransferThread(BankAccount a, BankAccount b, double amount) {
	  this.a = a;
	  this.b = b;
	  this.amount = amount;
	}
	
	public void run() {
	  System.out.println(Thread.currentThread().getName()+": Before transfer a has $"+a.getBalance());
	  System.out.println(Thread.currentThread().getName()+": Before transfer b has $"+b.getBalance());
	  a.transfer(amount, b);
	  System.out.println(Thread.currentThread().getName()+": After transfer a has $"+a.getBalance());
	  System.out.println(Thread.currentThread().getName()+": After transfer b has $"+b.getBalance());
	}    
}
