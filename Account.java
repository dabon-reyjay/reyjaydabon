// Represents a Bank Account
class Account {
    private int id;
    private String name;
    private double balance;

    public Account(int id, String name, double initialDeposit) {
        this.id = id;
        this.name = name;
        this.balance = initialDeposit;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        } else {
            System.out.println("Invalid withdrawal amount.");
        }
    }

    public void displayAccountInfo() {
        System.out.println("Account ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Balance: " + balance);
    }
}