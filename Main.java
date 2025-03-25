package bankingApplication;

//Brian Siebert
//CIS-18B
//Lesson 3 Assignment

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//============================
//TODO: Define Custom Exception Classes
//============================
class NegativeDepositException extends Exception { // NegativeDepositException Class
public NegativeDepositException(String message) {
 super(message);
}
}

class OverdrawException extends Exception { // OverdrawException Class
public OverdrawException(String message) {
 super(message);
}
}

class InvalidAccountOperationException extends Exception { //InvalidAccountOperationException Class
public InvalidAccountOperationException(String message) {
 super(message);
}
}

//============================
//Observer Pattern - Define Observer Interface
//============================

interface Observer {
 void update(String message);
}

//TODO: Implement TransactionLogger class (Concrete Observer)
class TransactionLogger implements Observer { // Implement Observer interface and TransactionLogger class
@Override
public void update(String message) {
 System.out.println("Transaction log: " + message);
}
}

//============================
//Decorator Pattern - Define SecureBankAccount Class
//============================

abstract class BankAccountDecorator extends BankAccount { // Create BankAccountDecorator
 protected BankAccount decoratedAccount;

 public BankAccountDecorator(BankAccount account) {
     super(account.accountNumber, 0);
     this.decoratedAccount = account;
 }
 @Override
 public void deposit(double amount) throws Exception {
	 decoratedAccount.deposit(amount);;
 }
 
 @Override
 public void withdraw(double amount) throws Exception {
	 decoratedAccount.withdraw(amount);
 }
 
 @Override
 public double getBalance() {
	 return decoratedAccount.getBalance();
 }
}

//TODO: Implement SecureBankAccount (Concrete Decorator)
class SecureBankAccount extends BankAccountDecorator { //Create SecureBankAccount
public SecureBankAccount(BankAccount account) {
 super(account);
}
@Override
public void withdraw(double amount) {
 try {
   if (amount > 500) {
     throw new InvalidAccountOperationException("Withdrawal amount exceeds limit"); // Prevent user from withdrawing more than $500 per transaction
   }
   decoratedAccount.withdraw(amount);
 } catch (Exception e) {
   System.out.println("Error: " + e.getMessage());
 }
}
}