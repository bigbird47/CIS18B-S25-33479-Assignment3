package bankingApplication;

//Brian Siebert
//CIS-18B
//Lesson 3 Assignment

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//============================
//Main Program
//============================

public class BankAccountTest {
 public static void main(String[] args) {
     Scanner scanner = new Scanner(System.in);

     try {
         // TODO: Ask the user to enter an initial balance and create a BankAccount object
         // Example: System.out.print("Enter initial balance: ");
         //          double initialBalance = scanner.nextDouble();
         //          BankAccount account = new BankAccount("123456", initialBalance);
         System.out.print("Enter initial balance: "); // Prompt user to enter initial balance
         double initialBalance = scanner.nextDouble();

         BankAccount account = new BankAccount("123456", initialBalance);
         System.out.println("Bank Account Created: #123456");

         // TODO: Create a TransactionLogger and attach it to the account
         TransactionLogger logger = new TransactionLogger();
         account.addObserver(logger);

         // TODO: Wrap account in SecureBankAccount decorator
         SecureBankAccount secureAccount = new SecureBankAccount(account);

         // TODO: Allow the user to enter deposit and withdrawal amounts
         // Example: secureAccount.deposit(amount);
         // Example: secureAccount.withdraw(amount);
         System.out.print("Enter deposit amount: "); // Prompt user to enter a deposit
         double depositAmount = scanner.nextDouble();
         secureAccount.deposit(depositAmount);

         System.out.print("Enter withdrawal amount: "); // Prompt user to withdraw amount
         double withdrawalAmount = scanner.nextDouble();
         secureAccount.withdraw(withdrawalAmount);

         // TODO: Display the final balance
         System.out.println("Final Balance: $" + secureAccount.getBalance());
     } catch (Exception e) {
         // TODO: Catch and handle exceptions properly
         System.out.println("Error: " + e.getMessage());
     } finally {
         scanner.close();
     }
 }
}