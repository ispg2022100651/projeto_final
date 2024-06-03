package final_project;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
// Interface Gráfica
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main2
{
    private static List<User> users = new ArrayList<>();
    private static List<Account> accounts = new ArrayList<>();

    public static void main(String[] args)
    {
        FileManager.loadUsersFromFile(users);

        // Criação da janela principal
        JFrame frame = new JFrame("Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        frame.setLocationRelativeTo(null);

        // Criação do painel
        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel, frame);

        // Tornar a janela visível
        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel, JFrame frame)
    {
        panel.setLayout(null);

        JLabel menuLabel = new JLabel("Menu:");
        menuLabel.setBounds(10, 20, 80, 25);
        panel.add(menuLabel);

        JButton createUserButton = new JButton("1. Create User");
        createUserButton.setBounds(10, 50, 150, 25);
        panel.add(createUserButton);

        JButton loadUserButton = new JButton("2. Load User");
        loadUserButton.setBounds(10, 80, 150, 25);
        panel.add(loadUserButton);

        JButton exitButton = new JButton("3. Exit");
        exitButton.setBounds(10, 110, 150, 25);
        panel.add(exitButton);

        createUserButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean success = createUser();
                if (!success) {
                    JOptionPane.showMessageDialog(panel, "Error creating user. Please try again.");
                }
            }
        });

        loadUserButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                loadUser(frame);
            }
        });
        
        exitButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                JOptionPane.showMessageDialog(panel, "Exiting...");
                System.exit(0);
            }
        });
    }

    private static boolean createUser() {
        // Obter os dados do usuário através de diálogos
        String name = JOptionPane.showInputDialog("Enter name:");
        String email = JOptionPane.showInputDialog("Enter email:");
        String password = JOptionPane.showInputDialog("Enter password:");
        String cc = JOptionPane.showInputDialog("Enter CC:");

        if (name != null && email != null && password != null && cc != null &&
            !name.trim().isEmpty() && !email.trim().isEmpty() &&
            !password.trim().isEmpty() && !cc.trim().isEmpty()) {
            
            // Criar novo usuário e adicioná-lo à lista
            User newUser = new User(name, email, password, cc);
            users.add(newUser);
            
            // Salvar usuário no arquivo usando FileManager
            FileManager.saveUser(newUser);

            return true;
        } else {
            JOptionPane.showMessageDialog(null, "All fields are required.");
            return false;
        }
    }

    private static void loadUser(JFrame frame) {
        // Cria um diálogo para o usuário inserir o email e a senha
        String email = JOptionPane.showInputDialog("Enter the user's email:");
        String password = JOptionPane.showInputDialog("Enter the user's password:");

        if (email != null && !email.trim().isEmpty() && password != null && !password.trim().isEmpty()) {
            // Implementar a lógica de carregamento do usuário
            User user = FileManager.loadUser(email, password);
            if (user != null) {
                accountMenu(frame, user);
            } else {
                JOptionPane.showMessageDialog(null, "User not found or invalid email/password.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Email and password cannot be empty.");
        }
    }

    private static void accountMenu(JFrame frame, User user) {
        // Criar nova janela para mostrar as opções do usuário
        JFrame userFrame = new JFrame("User Options");
        userFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        userFrame.setSize(400, 300);
        userFrame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        userFrame.add(panel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        // User Ubfi
        JLabel userInfoLabel = new JLabel("User Info:");
        panel.add(userInfoLabel, gbc);
        // Name
        gbc.gridy++;
        JLabel userNameLabel = new JLabel("Name: " + user.getName());
        panel.add(userNameLabel, gbc);
        // Email
        gbc.gridy++;
        JLabel userEmailLabel = new JLabel("<html>Email: " + user.getEmail() + "</html>");
        panel.add(userEmailLabel, gbc);
        // CC
        gbc.gridy++;
        JLabel userCCLabel = new JLabel("CC: " + user.getIdCard());
        panel.add(userCCLabel, gbc);

        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        // Create Account
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton createAccountButton = new JButton("Create Account");
        buttonPanel.add(createAccountButton);
        // Load Account
        JButton loadAccountButton = new JButton("Load Account");
        buttonPanel.add(loadAccountButton);
        // Logout
        JButton logoutButton = new JButton("Logout");
        buttonPanel.add(logoutButton);

        panel.add(buttonPanel, gbc);

        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implementar lógica para criar conta
                createAccount();
            }
        });

        loadAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implementar lógica para carregar conta
                JOptionPane.showMessageDialog(panel, "Load Account clicked.");
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userFrame.dispose();
                frame.setVisible(true);
            }
        });

        frame.setVisible(false);
        userFrame.setVisible(true);
    }

    // public static void accountMenu_1(Scanner scanner, List<Account> accounts) {
    //     boolean running = true;

    //     while ( running )
    //     {
    //         System.out.println(" ----- ----- ----- ----- ");
    //         System.out.println("Account Menu:");
    //         System.out.println("1. Create Account");
    //         System.out.println("2. Load Account");
    //         System.out.println("3. Create Transaction");
    //         System.out.println("4. Back to Main Menu");
    //         System.out.println(" ----- ----- ----- ----- ");
    //         System.out.print("Choose an option: ");
    //         int choice = scanner.nextInt();
    //         scanner.nextLine(); // Consume newline

    //         switch (choice) {
    //             case 1:
    //                 createAccount(scanner, accounts);
    //                 break;
    //             case 2:
    //                 loadAccount(scanner, accounts);
    //                 break;
    //             case 3:
    //                 createTransaction(scanner, accounts);
    //                 break;
    //             case 4:
    //                 running = false;
    //                 break;
    //             default:
    //                 System.out.println("Invalid choice. Please try again.");
    //                 break;
    //         }
    //     }
    // }

    // public static void createAccount(Scanner scanner, List<Account> accounts) {
    //     // Your code to create an account goes here
    //     System.out.println("Account created successfully.");
    // }

    public static void loadAccount(Scanner scanner, List<Account> accounts) {
        // Your code to load an account goes here
        System.out.println("Account loaded successfully.");
    }

    public static void createTransaction(Scanner scanner, List<Account> accounts) {
        // Your code to create a transaction goes here
        System.out.println("Transaction created successfully.");
    }

    // public static void main_1(String[] args) {
    //     Scanner scanner = new Scanner(System.in);
    //     List<Account> accounts = new ArrayList<>();
    //     List<User> users = new ArrayList<>();
    //     boolean running = true;

    //     while (running) {
    //         System.out.println("Menu:");
    //         System.out.println("1. Create Account");
    //         System.out.println("2. Create Transaction");
    //         System.out.println("3. Create User");
    //         System.out.println("4. Load Account");
    //         System.out.println("5. Load User");
    //         System.out.println("6. Exit");
    //         System.out.print("Choose an option: ");
    //         int choice = scanner.nextInt();
    //         scanner.nextLine(); // Consume newline

    //         switch (choice) {
    //             case 1: createAccount(scanner); break;
    //             case 2:
    //                 System.out.print("Enter account number for transaction (int): ");
    //                 int transAccountNumber = scanner.nextInt();
    //                 Account transAccount = findAccount(accounts, transAccountNumber);
    //                 if (transAccount != null) {
    //                     System.out.print("Enter amount for transaction (double): ");
    //                     double transactionAmount = scanner.nextDouble();
    //                     scanner.nextLine();  // Consume newline
    //                     System.out.print("Enter description for transaction: ");
    //                     String transactionDescription = scanner.nextLine();
    //                     Transaction tr = new Expense(transactionAmount, transactionDescription, new Date(), "work");

    //                     System.out.print("Enter category name: ");
    //                     String categoryName = scanner.nextLine();
    //                     Category category = new Category(categoryName);

    //                     tr.setCategory(category);
    //                     transAccount.addTransaction(tr);
    //                     FileManager.saveAccounts(accounts);
    //                     System.out.println("Transaction added and saved.");
    //                 } else {
    //                     System.out.println("Account not found.");
    //                 }
    //                 break;

    //             case 3:
    //                 System.out.print("Enter user name: ");
    //                 String userName = scanner.nextLine();
    //                 System.out.print("Enter user email: ");
    //                 String userEmail = scanner.nextLine();
    //                 System.out.print("Enter user password: ");
    //                 String userPassword = scanner.nextLine();
    //                 System.out.print("Enter user tax ID (int): ");
    //                 int userTaxId = scanner.nextInt();
    //                 scanner.nextLine();  // Consume newline

    //                 User user = new User(userName, userEmail, userPassword, userTaxId);
    //                 System.out.print("Enter account number for user's account (int): ");
    //                 int userAccountNumber = scanner.nextInt();
    //                 System.out.print("Enter balance for user's account (double): ");
    //                 double userAccountBalance = scanner.nextDouble();

    //                 Account userAccount = new Account(userAccountNumber, userAccountBalance);
    //                 user.addAccount(userAccount);
    //                 users.add(user);
    //                 FileManager.saveUser(user);
    //                 System.out.println("User created and saved.");
    //                 break;

    //             case 4:
    //                 System.out.print("Enter account number to load (int): ");
    //                 int loadAccountNumber = scanner.nextInt();
    //                 Account loadedAccount = FileManager.loadAccount(loadAccountNumber);
    //                 if (loadedAccount != null)
    //                 {
    //                     loadedAccount.print();
    //                 }
    //                 break;

    //             case 5:
    //                 System.out.print("Enter user email to load: ");
    //                 String loadUserEmail = scanner.nextLine();
    //                 System.out.print("Enter user password to load: ");
    //                 String loadUserPassword = scanner.nextLine();
    //                 User loadedUser = FileManager.loadUser(loadUserEmail, loadUserPassword);
    //                 if (loadedUser != null) {
    //                     loadedUser.print();
    //                 } else {
    //                     System.out.println("Failed to load user.");
    //                 }
    //                 break;

    //             case 6:
    //                 running = false;
    //                 System.out.println("Exiting...");
    //                 break;

    //             default:
    //                 System.out.println("Invalid choice. Please try again.");
    //                 break;
    //         }
    //     }

    //     scanner.close();
    // }

    public static void createAccount()
    {
        JFrame createAccountFrame = new JFrame("Create Account");
        createAccountFrame.setSize(400, 300);
        createAccountFrame.setLocationRelativeTo(null);
        createAccountFrame.setLayout(new GridLayout(4, 2));

        JLabel accountNumberLabel = new JLabel("Account Number:");
        JTextField accountNumberField = new JTextField();
        JLabel balanceLabel = new JLabel("Balance:");
        JTextField balanceField = new JTextField();
        
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String accountNumberStr = accountNumberField.getText();
                    if (accountNumberStr.length() != 9 || !accountNumberStr.matches("\\d+")) {
                        throw new IllegalArgumentException("Account number must be 9 digits.");
                    }

                    int accountNumber = Integer.parseInt(accountNumberStr);
                    
                    // Verifica se a conta já existe
                    Account existingAccount = FileManager.loadAccount(accountNumber);
                    if (existingAccount != null) {
                        throw new IllegalArgumentException("Account already exists.");
                    }

                    double balance = Double.parseDouble(balanceField.getText());

                    Account account = new Account(accountNumber, balance);
                    FileManager.saveAccount(account);

                    JOptionPane.showMessageDialog(createAccountFrame, "Account created successfully!");
                    createAccountFrame.dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(createAccountFrame, "Invalid input. Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(createAccountFrame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(createAccountFrame, "Error creating account: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        createAccountFrame.add(accountNumberLabel);
        createAccountFrame.add(accountNumberField);
        createAccountFrame.add(balanceLabel);
        createAccountFrame.add(balanceField);
        createAccountFrame.add(new JLabel()); // Placeholder
        createAccountFrame.add(saveButton);

        createAccountFrame.setVisible(true);
    }

    // public static void createAccount(Scanner scanner)
    // {
    //     System.out.print("Enter account number (int): ");
    //     int accountNumber = scanner.nextInt();

    //     System.out.print("Enter balance (double): ");
    //     double balance = scanner.nextDouble();

    //     Account account = new Account(accountNumber, balance);
    //     FileManager.saveAccount(account);
    // }

    private static Account findAccount(List<Account> accounts, int accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        return null;
    }
}
