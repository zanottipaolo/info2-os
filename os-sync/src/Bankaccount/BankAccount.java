package Bankaccount;


public class BankAccount {

  private double balance;

  public BankAccount(double balance) {
    this.balance = balance;
  }

  public synchronized double getBalance() {
    return balance;
  }

  public synchronized void deposit(double amount) {
    balance += amount;
  }

  public synchronized void withdraw(double amount) 
    throws RuntimeException {
    if (amount > balance) {
      throw new RuntimeException("Overdraft");
    }
    balance -= amount; 
  }

  public synchronized void transfer(double amount, BankAccount destination) {
    synchronized(destination) {
    	this.withdraw(amount);
    	
        System.out.println(Thread.currentThread().getName()+": Ho prelevato $"+amount);
        
        destination.deposit(amount);
        
        System.out.println(Thread.currentThread().getName()+": Ho depositato $"+amount);
    }
  }
}


