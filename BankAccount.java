package bankingApplication;

//Brian Siebert
//CIS-18B
//Lesson 3 Assignment

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//============================
//BankAccount (Subject in Observer Pattern)
//============================
class BankAccount {
 protected String accountNumber;
 protected double balance;
 protected boolean isActive;
 private List<Observer> observers = new ArrayList<>();

 public BankAccount(String accNum, double initialBalance) {
     this.accountNumber = accNum;
     this.balance = initialBalance;
     this.isActive = true;
 }

 // Attach observer
 public void addObserver(Observer observer) {
     observers.add(observer);
 }

 // Notify observers (TODO: Implement in methods)
 private void notifyObservers(String message) {
     // TODO: Notify all observers when a transaction occurs
     for (Observer observer : observers) {
         observer.update(message);
     }
 }

 public void deposit(double amount) throws Exception {
     // TODO: Implement exception handling for negative deposits
     if (amount < 0) {
         throw new NegativeDepositException("Negative deposit amount"); // Prevent negative deposits
     }
   balance += amount;
   notifyObservers("Deposited $" + amount);
 }

 public void withdraw(double amount) throws Exception {
     // TODO: Implement exception handling for overdrawing and closed accounts
   if (!isActive) {
     throw new InvalidAccountOperationException("Account is closed"); // Prevent further transactions
   }
   if (amount > balance) {
     throw new OverdrawException("Insufficient funds"); // Prevent overdrawing
   }
   balance -= amount;
   notifyObservers("Withdrew $" + amount);
 }

 public double getBalance() {
     return balance;
 }

 public void closeAccount() {
     // TODO: Prevent further transactions when the account is closed
     isActive = false;
     notifyObservers("Account closed");
 }
}
