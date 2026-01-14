public class PersonalAccount {
    // Instance variables (unique for each account)
    private String accountHolderName;
    private String accountNumber;
    private double currentBalance;
    private double totalIncome;
    private double totalExpenses;

    // Static variables (shared across all accounts)
    private static int totalAccounts = 0;
    private static String bankName = "Default Bank";
    private static int accountCounter = 1;  // used to generate unique account numbers

    // Constructor
    public PersonalAccount(String accountHolderName, double initialDeposit) {
        this.accountHolderName = accountHolderName;
        this.accountNumber = generateAccountNumber();
        this.currentBalance = initialDeposit;
        this.totalIncome = initialDeposit;
        this.totalExpenses = 0;
        totalAccounts++;
    }

    // Instance Methods
    public void addIncome(double amount, String description) {
        if (amount > 0) {
            currentBalance += amount;
            totalIncome += amount;
            System.out.println(accountHolderName + " received income: " + amount + " (" + description + ")");
        } else {
            System.out.println("Invalid income amount!");
        }
    }

    public void addExpense(double amount, String description) {
        if (amount > 0 && amount <= currentBalance) {
            currentBalance -= amount;
            totalExpenses += amount;
            System.out.println(accountHolderName + " spent: " + amount + " (" + description + ")");
        } else {
            System.out.println("Invalid or insufficient balance for expense!");
        }
    }

    public double calculateSavings() {
        return totalIncome - totalExpenses;
    }

    public void displayAccountSummary() {
        System.out.println("\n--- Account Summary ---");
        System.out.println("Bank: " + bankName);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Current Balance: " + currentBalance);
        System.out.println("Total Income: " + totalIncome);
        System.out.println("Total Expenses: " + totalExpenses);
        System.out.println("Savings: " + calculateSavings());
    }

    // Static Methods
    public static void setBankName(String name) {
        bankName = name;
    }

    public static int getTotalAccounts() {
        return totalAccounts;
    }

    private static String generateAccountNumber() {
        return "ACC" + String.format("%03d", accountCounter++);
    }

    // Main method (Test Cases)
    public static void main(String[] args) {
        // Set the bank name (shared for all accounts)
        PersonalAccount.setBankName("Smart Finance Bank");

        // Create multiple accounts
        PersonalAccount acc1 = new PersonalAccount("Adarsh", 5000);
        PersonalAccount acc2 = new PersonalAccount("Garvita", 8000);
        PersonalAccount acc3 = new PersonalAccount("Aradhya", 3000);

        // Perform transactions
        acc1.addIncome(2000, "Freelancing");
        acc1.addExpense(1500, "Groceries");
        
        acc2.addIncome(5000, "Scholarship");
        acc2.addExpense(2500, "Books & Supplies");

        acc3.addExpense(1000, "Stationery");
        acc3.addIncome(2000, "Part-time Job");

        // Display account summaries
        acc1.displayAccountSummary();
        acc2.displayAccountSummary();
        acc3.displayAccountSummary();

        // Demonstrate static vs instance variables
        System.out.println("\n=== Bank Statistics ===");
        System.out.println("Bank Name (Shared): " + bankName);
        System.out.println("Total Accounts Created: " + PersonalAccount.getTotalAccounts());
    }
}
