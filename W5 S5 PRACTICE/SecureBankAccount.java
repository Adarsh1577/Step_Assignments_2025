public class SecureBankAccount {
    private String accountNumber;
    private double balance;
    private int pin;
    private boolean isLocked;
    private int failedAttempts;

    private static final int MAX_FAILED_ATTEMPTS = 3;
    private static final double MIN_BALANCE = 0.0;

    public SecureBankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance >= MIN_BALANCE ? initialBalance : MIN_BALANCE;
        this.pin = 0;
        this.isLocked = false;
        this.failedAttempts = 0;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        if (isLocked) {
            System.out.println("Account is locked. Cannot view balance.");
            return -1;
        }
        return balance;
    }

    public boolean isAccountLocked() {
        return isLocked;
    }

    public boolean setPin(int oldPin, int newPin) {
        if (pin == oldPin && !isLocked) {
            pin = newPin;
            System.out.println("PIN changed successfully.");
            return true;
        }
        System.out.println("PIN change failed.");
        return false;
    }

    public boolean validatePin(int enteredPin) {
        if (isLocked) {
            System.out.println("Account is locked.");
            return false;
        }
        if (enteredPin == pin) {
            resetFailedAttempts();
            return true;
        } else {
            incrementFailedAttempts();
            return false;
        }
    }

    public void unlockAccount(int correctPin) {
        if (pin == correctPin) {
            isLocked = false;
            resetFailedAttempts();
            System.out.println("Account unlocked.");
        } else {
            System.out.println("Incorrect PIN. Cannot unlock.");
        }
    }

    public void deposit(double amount, int enteredPin) {
        if (validatePin(enteredPin)) {
            if (amount > 0) {
                balance += amount;
                System.out.println("Deposited: " + amount);
            }
        }
    }

    public void withdraw(double amount, int enteredPin) {
        if (validatePin(enteredPin)) {
            if (amount > 0 && balance - amount >= MIN_BALANCE) {
                balance -= amount;
                System.out.println("Withdrew: " + amount);
            } else {
                System.out.println("Insufficient funds.");
            }
        }
    }

    public void transfer(SecureBankAccount target, double amount, int enteredPin) {
        if (validatePin(enteredPin)) {
            if (amount > 0 && balance - amount >= MIN_BALANCE) {
                balance -= amount;
                target.balance += amount;
                System.out.println("Transferred: " + amount + " to " + target.getAccountNumber());
            } else {
                System.out.println("Insufficient funds for transfer.");
            }
        }
    }

    private void lockAccount() {
        isLocked = true;
        System.out.println("Account locked due to too many failed attempts.");
    }

    private void resetFailedAttempts() {
        failedAttempts = 0;
    }

    private void incrementFailedAttempts() {
        failedAttempts++;
        if (failedAttempts >= MAX_FAILED_ATTEMPTS) {
            lockAccount();
        } else {
            System.out.println("Incorrect PIN. Failed attempts: " + failedAttempts);
        }
    }

    public static void main(String[] args) {
        SecureBankAccount acc1 = new SecureBankAccount("ACC1001", 500);
        SecureBankAccount acc2 = new SecureBankAccount("ACC2002", 1000);

        System.out.println("Account 1 Number: " + acc1.getAccountNumber());
        System.out.println("Account 2 Number: " + acc2.getAccountNumber());

        acc1.setPin(0, 1234);
        acc2.setPin(0, 5678);

        acc1.deposit(200, 1234);
        acc1.withdraw(100, 1234);
        System.out.println("Balance (acc1): " + acc1.getBalance());

        acc1.transfer(acc2, 300, 1234);
        System.out.println("Balance (acc1): " + acc1.getBalance());
        System.out.println("Balance (acc2): " + acc2.getBalance());

        acc1.withdraw(2000, 1234);

        acc1.withdraw(50, 9999);
        acc1.withdraw(50, 9999);
        acc1.withdraw(50, 9999);

        acc1.deposit(100, 1234);
        System.out.println("Balance (acc1): " + acc1.getBalance());

        acc1.unlockAccount(1234);
        acc1.deposit(100, 1234);
        System.out.println("Balance (acc1): " + acc1.getBalance());
    }
}
