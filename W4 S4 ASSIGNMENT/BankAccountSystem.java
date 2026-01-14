import java.util.Random;

// 💳 Bank Account System
class BankAccount {
    private String accountHolder;
    private int accountNumber;
    private double balance;

    // 1. Default constructor → balance = 0
    public BankAccount() {
        this("Unknown", 0, 0.0);
    }

    // 2. Constructor with name → assigns random account number
    public BankAccount(String accountHolder) {
        this(accountHolder, generateAccountNumber(), 0.0);
    }

    // 3. Constructor with name and initial balance
    public BankAccount(String accountHolder, double balance) {
        this(accountHolder, generateAccountNumber(), balance);
    }

    // Main constructor
    public BankAccount(String accountHolder, int accountNumber, double balance) {
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    // Static method to generate random account numbers
    private static int generateAccountNumber() {
        Random rand = new Random();
        return 100000 + rand.nextInt(900000); // 6-digit account number
    }

    // Deposit method
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("✅ Deposited ₹" + amount + " into account " + accountNumber);
        } else {
            System.out.println("❌ Invalid deposit amount!");
        }
    }

    // Withdraw method
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("✅ Withdrawn ₹" + amount + " from account " + accountNumber);
        } else {
            System.out.println("❌ Insufficient balance or invalid amount!");
        }
    }

    // Display account details
    public void displayAccount() {
        System.out.println("\n--- Account Details ---");
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance: ₹" + balance);
    }
}

// === MAIN CLASS ===
public class BankAccountSystem {
    public static void main(String[] args) {
        System.out.println("=== BANK ACCOUNT SYSTEM ===");

        // Using different constructors
        BankAccount acc1 = new BankAccount(); // default
        BankAccount acc2 = new BankAccount("Adarsh"); // name only
        BankAccount acc3 = new BankAccount("Garvita", 5000.0); // name + balance

        // Perform transactions
        acc2.deposit(2000);
        acc2.withdraw(500);

        acc3.deposit(1500);
        acc3.withdraw(7000); // should fail (insufficient balance)

        // Display account details
        acc1.displayAccount();
        acc2.displayAccount();
        acc3.displayAccount();
    }
}
