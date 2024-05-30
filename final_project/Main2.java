package final_project;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Menu principal
        System.out.println("Bem-vindo ao sistema de contas!");
        System.out.println("Escolha uma opção:");
        System.out.println("1. Criar nova conta");
        System.out.println("2. Carregar conta existente");
        System.out.print("Opção: ");
        int option = scanner.nextInt();
        
        switch (option) {
            case 1:
                createNewAccount(scanner);
                break;
            case 2:
                loadExistingAccount(scanner);
                break;
            default:
                System.out.println("Opção inválida.");
        }
        
        scanner.close();
    }   
    
    private static void createNewAccount(Scanner scanner) {
        // Get account details from user
        System.out.println("Enter account number:");
        int accountNumber = scanner.nextInt();

        System.out.println("Enter initial balance:");
        double initialBalance = scanner.nextDouble();

        // Create account
        Account account = new Account(accountNumber, initialBalance);

        // Get transaction details from user
        System.out.println("Enter expense amount:");
        double expenseAmount = scanner.nextDouble();
        System.out.println("Enter expense description:");
        String expenseDescription = scanner.next();
        // You can add more inputs for other fields of FixedExpense

        // Create transaction
        FixedExpense fixedExpense = new FixedExpense(expenseAmount, expenseDescription, new Date(), "Provider X", "Monthly", 30);

        // Add transaction to account
        account.addTransaction(fixedExpense);

        // Save account to file in the "database" folder
        List<Account> accounts = new ArrayList<>();
        accounts.add(account);
        FileManager.saveAccounts(accounts);
    }
    
    private static void loadExistingAccount(Scanner scanner) {
        System.out.println("Enter account number to load:");
        int accountNumber = scanner.nextInt();
        
        // Load account by account number
        Account loadedAccount = FileManager.loadAccount(accountNumber);
        
        // Display loaded account
        if (loadedAccount != null) {
            loadedAccount.print();
        } else {
            System.out.println("Failed to load account.");
        }
    }
}
