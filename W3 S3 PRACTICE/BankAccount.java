public class BankAccount {
    static String bankName;
    static int totalAccounts = 0;
    static double interestRate;

    String accountNumber;
    String accountHolder;
    double balance;

    public BankAccount(String accountNumber, String accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
        totalAccounts++;
    }

    public static void setBankName(String name) {
        bankName = name;
    }

    public static void setInterestRate(double rate) {
        interestRate = rate;
    }

    public static int getTotalAccounts() {
        return totalAccounts;
    }

    public static void displayBankInfo() {
        System.out.println("Bank: " + bankName + ", Total Accounts: " + totalAccounts + ", Interest Rate: " + interestRate + "%");
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println(accountHolder + " deposited " + amount + ". New Balance: " + balance);
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println(accountHolder + " withdrew " + amount + ". New Balance: " + balance);
        } else {
            System.out.println("Insufficient balance for " + accountHolder);
        }
    }

    public void calculateInterest() {
        double interest = balance * (interestRate / 100);
        System.out.println(accountHolder + " will earn interest: " + interest);
    }

    public void displayAccountInfo() {
        System.out.println("Account Number: " + accountNumber + ", Holder: " + accountHolder + ", Balance: " + balance);
    }

    public static void main(String[] args) {
        BankAccount.setBankName("State Bank of India");
        BankAccount.setInterestRate(5.0);

        BankAccount acc1 = new BankAccount("A101", "Adarsh", 5000);
        BankAccount acc2 = new BankAccount("A102", "Garvita", 10000);

        BankAccount.displayBankInfo();

        acc1.displayAccountInfo();
        acc1.deposit(2000);
        acc1.calculateInterest();

        acc2.displayAccountInfo();
        acc2.withdraw(3000);
        acc2.calculateInterest();

        System.out.println("Total Accounts Created: " + BankAccount.getTotalAccounts());

        acc1.displayBankInfo(); // Static method called with object
    }
}
