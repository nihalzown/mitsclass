// Aim: Program to perform Multithreading operations (Bank BankAccountTask).
import java.util.Scanner;

class BankAccountTask {
    private int accountNo;
    private double balance;

    public BankAccountTask(int accountNo, double initialBalance) {
        this.accountNo = accountNo;
        this.balance = initialBalance;
    }

    public synchronized void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: " + amount + ", New Balance: " + balance);
    }

    public synchronized void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount + ", New Balance: " + balance);
        } else {
            System.out.println("Insufficient balance for withdrawal of " + amount);
        }
    }

    public void displayBalance() {
        System.out.println("BankAccountTask No: " + accountNo + ", Balance: " + balance);
    }
}

class DepositThread extends Thread {
    private BankAccountTask account;
    private double amount;

    public DepositThread(BankAccountTask account, double amount) {
        this.account = account;
        this.amount = amount;
    }

    public void run() {
        account.deposit(amount);
    }
}

class WithdrawThread extends Thread {
    private BankAccountTask account;
    private double amount;

    public WithdrawThread(BankAccountTask account, double amount) {
        this.account = account;
        this.amount = amount;
    }

    public void run() {
        account.withdraw(amount);
    }
}

public class BankAccount {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter BankAccountTask Number: ");
        int accountNo = scanner.nextInt();

        System.out.print("Enter Initial Balance: ");
        double initialBalance = scanner.nextDouble();

        BankAccountTask account = new BankAccountTask(accountNo, initialBalance);

        System.out.print("Enter amount to deposit: ");
        double depositAmount = scanner.nextDouble();

        System.out.print("Enter amount to withdraw: ");
        double withdrawAmount = scanner.nextDouble();

        DepositThread depositThread = new DepositThread(account, depositAmount);
        WithdrawThread withdrawThread = new WithdrawThread(account, withdrawAmount);

        depositThread.start();
        withdrawThread.start();
        scanner.close();
    }
}