class BankAccount {
    private String accountNumber;
    private String accountHolderName;
    private double balance;
    private static int totalAccounts = 0;
    private static int accountCounter = 0;

    public BankAccount(String accountHolderName, double initialDeposit) {
        this.accountHolderName = accountHolderName;
        this.balance = initialDeposit;
        this.accountNumber = generateAccountNumber();
        totalAccounts++;
    }

    private static String generateAccountNumber() {
        accountCounter++;
        return String.format("ACC%03d", accountCounter);
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println(amount + " deposited successfully into " + accountNumber);
        } else {
            System.out.println("Deposit amount must be positive!");
        }
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive!");
        } else if (amount > balance) {
            System.out.println("Insufficient funds in " + accountNumber);
        } else {
            balance -= amount;
            System.out.println(amount + " withdrawn successfully from " + accountNumber);
        }
    }

    public void checkBalance() {
        System.out.println("Account " + accountNumber + " Balance: " + balance);
    }

    public static int getTotalAccounts() {
        return totalAccounts;
    }

    public void displayAccountInfo() {
        System.out.println("=====================================");
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Account Holder : " + accountHolderName);
        System.out.println("Balance        : " + balance);
        System.out.println("=====================================");
    }
}

public class BankSystem {
    public static void main(String[] args) {
        BankAccount[] accounts = new BankAccount[3];

        accounts[0] = new BankAccount("Alice", 5000);
        accounts[1] = new BankAccount("Bob", 3000);
        accounts[2] = new BankAccount("Charlie", 7000);

        System.out.println("Initial Accounts:");
        for (BankAccount acc : accounts) {
            acc.displayAccountInfo();
        }

        accounts[0].deposit(2000);
        accounts[1].withdraw(1000);
        accounts[2].withdraw(8000); 

        System.out.println("\nAfter Transactions:");
        for (BankAccount acc : accounts) {
            acc.checkBalance();
        }

        System.out.println("\nTotal Accounts Created: " + BankAccount.getTotalAccounts());
    }
}
