import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

// Interface Gráfica
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class Main {
    private static ArrayList<User> users = new ArrayList<>();
    private static User currentUser = null;
    private static Account currentAccount = null;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            users = FileManager.loadUsers();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Nenhum utilizador encontrado.");
        }

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                FileManager.saveUsers(users);
            } catch (IOException e) {
                System.out.println("Erro ao salvar utilizadores.");
            }
        }));

        while (true) {
            if (currentUser == null) {
                showMainMenu();
            } else {
                showUserMenu();
            }
        }
    }

    private static void showMainMenu() {
        System.out.println("1. Registar Utilizador");
        System.out.println("2. Login");
        System.out.println("0. Sair");

        int choice = scanner.nextInt();
        scanner.nextLine();  // consume newline

        switch (choice) {
            case 1:
                registerUser();
                break;
            case 2:
                loginUser();
                break;
            case 0:
                System.exit(0);
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }

    private static void registerUser() {
        System.out.print("Nome: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("CC: ");
        String cc = scanner.nextLine();

        User user = new User(name, email, password, cc);
        users.add(user);

        try {
            FileManager.saveUsers(users);
        } catch (IOException e) {
            System.out.println("Erro ao salvar utilizadores.");
        }

        System.out.println("Utilizador registado com sucesso!");
    }

    private static void loginUser() {
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                currentUser = user;
                System.out.println("Login bem-sucedido!");
                return;
            }
        }

        System.out.println("Email ou password incorretos.");
    }

    private static void showUserMenu() {
        System.out.println("1. Criar Conta");
        System.out.println("2. Entrar em Conta Existente");
        System.out.println("3. Logout");
        System.out.println("0. Sair");

        int choice = scanner.nextInt();
        scanner.nextLine();  // consume newline

        switch (choice) {
            case 1:
                createAccount();
                break;
            case 2:
                selectAccount();
                break;
            case 3:
                currentUser = null;
                currentAccount = null;
                break;
            case 0:
                System.exit(0);
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }

    private static void createAccount() {
        System.out.print("Número da Conta: ");
        String accountNumber = scanner.nextLine();

        Account account = new Account(accountNumber);
        currentUser.addAccount(account);

        try {
            FileManager.saveUsers(users);
        } catch (IOException e) {
            System.out.println("Erro ao salvar conta.");
        }

        System.out.println("Conta criada com sucesso!");
    }

    private static void selectAccount() {
        System.out.println("Contas:");
        for (int i = 0; i < currentUser.getAccounts().size(); i++) {
            System.out.println((i + 1) + ". " + currentUser.getAccounts().get(i).getAccountNumber());
        }

        System.out.print("Escolha uma conta: ");
        int choice = scanner.nextInt();
        scanner.nextLine();  // consume newline

        if (choice > 0 && choice <= currentUser.getAccounts().size()) {
            currentAccount = currentUser.getAccounts().get(choice - 1);
            showAccountMenu();
        } else {
            System.out.println("Opção inválida!");
        }
    }

    private static void showAccountMenu() {
        while (currentAccount != null) {
            System.out.println("1. Criar Transação");
            System.out.println("2. Ver Histórico de Transações");
            System.out.println("3. Logout");
            System.out.println("0. Sair");

            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (choice) {
                case 1:
                    createTransaction();
                    break;
                case 2:
                    viewTransactionHistory();
                    break;
                case 3:
                    currentAccount = null;
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static void createTransaction() {
        System.out.print("Valor: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();  // consume newline
        System.out.print("Descrição: ");
        String description = scanner.nextLine();
        Date date = new Date();

        System.out.println("1. Despesa");
        System.out.println("2. Receita");
        int choice = scanner.nextInt();
        scanner.nextLine();  // consume newline

        Transaction transaction = null;
        switch (choice) {
            case 1:
                System.out.print("Destino: ");
                String destination = scanner.nextLine();
                transaction = new Expense(amount, description, date, destination);
                break;
            case 2:
                System.out.print("Origem: ");
                String source = scanner.nextLine();
                transaction = new Income(amount, description, date, source);
                break;
            default:
                System.out.println("Opção inválida!");
                return;
        }

        currentAccount.addTransaction(transaction);

        try {
            FileManager.saveUsers(users);
        } catch (IOException e) {
            System.out.println("Erro ao salvar transação.");
        }

        System.out.println("Transação criada com sucesso!");
    }

    private static void viewTransactionHistory() {
        for (Transaction transaction : currentAccount.getTransactions()) {
            System.out.println(transaction.getDate() + " - " + transaction.getDescription() + " - " + transaction.getAmount());
        }
    }
}
