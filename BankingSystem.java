import java.util.ArrayList;    // Importing ArrayList for handling a list of accounts
import java.util.HashSet;      // Importing HashSet to store unique account IDs
import java.util.Random;       // Importing Random for generating random numbers
import java.util.Scanner;      // Importing Scanner for user input
import java.util.Set;          // Importing Set for the collection of unique account IDs

// Main class to handle banking operations
public class BankingSystem {
    private ArrayList<Account> accounts = new ArrayList<>(); // List to store all account objects
    private Set<Integer> accountIds = new HashSet<>();       // Set to store unique account IDs
    private Random random = new Random();                    // Random object for generating account IDs

    public static void main(String[] args) {
        BankingSystem bank = new BankingSystem(); // Create an instance of BankingSystem
        bank.run(); // Start the banking system
    }

    // Method to handle the main menu and user choices
    private void run() {
        Scanner scanner = new Scanner(System.in); // Scanner for user input
        int choice; // Variable to store user choice

        // Loop to display menu and process user choices
        do {
            System.out.println("=== Online Bank Menu ==="); // Display menu header
            System.out.println("1. Create New Account"); // Option to create a new account
            System.out.println("2. Deposit"); // Option to deposit money
            System.out.println("3. Withdraw"); // Option to withdraw money
            System.out.println("4. Check Balance"); // Option to check account balance
            System.out.println("5. Display All Accounts"); // Option to display all accounts
            System.out.println("0. Exit"); // Option to exit the system
            System.out.print("Enter your choice: "); // Prompt user for input
            choice = scanner.nextInt(); // Read user choice

            // Switch case to handle user choice
            switch (choice) {
                case 1:
                    createNewAccount(scanner); // Call method to create a new account
                    break;
                case 2:
                    deposit(scanner); // Call method to deposit money
                    break;
                case 3:
                    withdraw(scanner); // Call method to withdraw money
                    break;
                case 4:
                    checkBalance(scanner); // Call method to check balance
                    break;
                case 5:
                    displayAllAccounts(); // Call method to display all accounts
                    break;
                case 0:
                    System.out.println("Thank you for using the Online Bank."); // Exit message
                    break;
                default:
                    System.out.println("Invalid choice. Please try again."); // Invalid choice message
            }
        } while (choice != 0); // Continue until user chooses to exit

        scanner.close(); // Close the scanner to release resources
    }

    // Method to create a new account
    private void createNewAccount(Scanner scanner) {
        System.out.print("Enter name: "); // Prompt user for account holder's name
        String name = scanner.next(); // Read account holder's name
        System.out.print("Enter initial deposit: "); // Prompt user for initial deposit
        double initialDeposit = scanner.nextDouble(); // Read initial deposit amount
        int id = generateUnique8DigitId(); // Generate a unique 8-digit account ID
        Account newAccount = new Account(id, name, initialDeposit); // Create new account with given details
        accounts.add(newAccount); // Add the new account to the accounts list
        System.out.println("New account created with ID: " + newAccount.getId()); // Display new account ID
    }

    // Method to deposit money into an account
    private void deposit(Scanner scanner) {
        System.out.print("Enter account ID: "); // Prompt user for account ID
        int id = scanner.nextInt(); // Read account ID
        Account account = findAccountById(id); // Find the account by ID
        if (account != null) { // Check if account exists
            System.out.print("Enter amount to deposit: "); // Prompt user for deposit amount
            double amount = scanner.nextDouble(); // Read deposit amount
            account.deposit(amount); // Deposit the amount into the account
            System.out.println("Amount deposited. New balance: " + account.getBalance()); // Display new balance
        } else {
            System.out.println("Account not found."); // Display account not found message
        }
    }

    // Method to withdraw money from an account
    private void withdraw(Scanner scanner) {
        System.out.print("Enter account ID: "); // Prompt user for account ID
        int id = scanner.nextInt(); // Read account ID
        Account account = findAccountById(id); // Find the account by ID
        if (account != null) { // Check if account exists
            System.out.print("Enter amount to withdraw: "); // Prompt user for withdrawal amount
            double amount = scanner.nextDouble(); // Read withdrawal amount
            account.withdraw(amount); // Withdraw the amount from the account
            System.out.println("Amount withdrawn. New balance: " + account.getBalance()); // Display new balance
        } else {
            System.out.println("Account not found."); // Display account not found message
        }
    }

    // Method to check the balance of an account
    private void checkBalance(Scanner scanner) {
        System.out.print("Enter account ID: "); // Prompt user for account ID
        int id = scanner.nextInt(); // Read account ID
        Account account = findAccountById(id); // Find the account by ID
        if (account != null) { // Check if account exists
            System.out.println("Account balance: " + account.getBalance()); // Display account balance
        } else {
            System.out.println("Account not found."); // Display account not found message
        }
    }

    // Method to display information of all accounts
    private void displayAllAccounts() {
        for (Account account : accounts) { // Iterate through all accounts
            account.displayAccountInfo(); // Display information of each account
            System.out.println("-----"); // Separator for each account
        }
    }

    // Method to find an account by its ID
    private Account findAccountById(int id) {
        for (Account account : accounts) { // Iterate through all accounts
            if (account.getId() == id) { // Check if account ID matches
                return account; // Return the matching account
            }
        }
        return null; // Return null if account not found
    }

    // Method to generate a unique 8-digit account ID
    private int generateUnique8DigitId() {
        int id; // Variable to store the generated ID
        do {
            id = 10000000 + random.nextInt(90000000); // Generate an 8-digit number
        } while (accountIds.contains(id)); // Ensure the ID is unique
        accountIds.add(id); // Add the new ID to the set of account IDs
        return id; // Return the unique ID
    }
}
