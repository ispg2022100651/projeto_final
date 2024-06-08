import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class FinanceApp extends JFrame
{
    private static ArrayList<User> users = new ArrayList<>();
    private static ArrayList<Account> accounts = new ArrayList<>();
    private static User currentUser = null;
    private static Account currentAccount = null;

    private CardLayout cardLayout = new CardLayout();
    private JPanel mainPanel = new JPanel(cardLayout);

    private TransactionHistoryPanel transactionHistoryPanel;

    public FinanceApp()
    {
        try
        {
            users = FileManager.loadUsers();
        }
        catch (IOException | ClassNotFoundException e)
        {
            System.out.println("Nenhum utilizador encontrado.");
        }

        setTitle("Aplicação de Finanças Pessoais");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //mainPanel.add(new MainMenuPanel(this), "MainMenu");
        //mainPanel.add(new RegisterPanel(this), "Register");
        //mainPanel.add(new LoginPanel(this), "Login");
        //mainPanel.add(new UserMenuPanel(this), "UserMenu");
        //mainPanel.add(new AccountMenuPanel(this), "AccountMenu");
        //mainPanel.add(new CreateTransactionPanel(this), "CreateTransaction");

        //transactionHistoryPanel = new TransactionHistoryPanel(this);
        //mainPanel.add(transactionHistoryPanel, "TransactionHistory");

        //mainPanel.add(new CreateAccountPanel(this), "CreateAccount");
        //mainPanel.add(new SelectAccountPanel(this), "SelectAccount");

        add(mainPanel);
        showMainMenu();
    }

    public void showMainMenu()
    {
        mainPanel.add(new MainMenuPanel(this), "MainMenu");
        cardLayout.show(mainPanel, "MainMenu");
    }

    public void showRegisterPanel()
    {
        mainPanel.add(new RegisterPanel(this), "Register");
        cardLayout.show(mainPanel, "Register");
    }

    public void showLoginPanel()
    {
        mainPanel.add(new LoginPanel(this), "Login");
        cardLayout.show(mainPanel, "Login");
    }

    public void showUserMenu()
    {
        mainPanel.add(new UserMenuPanel(this), "UserMenu");
        cardLayout.show(mainPanel, "UserMenu");
    }

    public void showAccountMenu()
    {
        mainPanel.add(new AccountMenuPanel(this), "AccountMenu");
        cardLayout.show(mainPanel, "AccountMenu");
    }

    public void showCreateTransactionPanel()
    {
        mainPanel.add(new CreateTransactionPanel(this), "CreateTransaction");
        cardLayout.show(mainPanel, "CreateTransaction");
    }

    public void showTransactionHistoryPanel()
    {
        transactionHistoryPanel = new TransactionHistoryPanel(this);
        mainPanel.add(transactionHistoryPanel, "TransactionHistory");
        transactionHistoryPanel.updateTransactionHistory();
        cardLayout.show(mainPanel, "TransactionHistory");
    }

    public void showCreateAccountPanel()
    {
        mainPanel.add(new CreateAccountPanel(this), "CreateAccount");
        cardLayout.show(mainPanel, "CreateAccount");
    }

    public void showSelectAccountPanel()
    {
        mainPanel.add(new SelectAccountPanel(this), "SelectAccount");
        cardLayout.show(mainPanel, "SelectAccount");
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() -> {
            FinanceApp app = new FinanceApp();
            app.setVisible(true);
        });

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try
            {
                FileManager.saveUsers(users);
            }
            catch (IOException e)
            {
                System.out.println("Erro ao salvar utilizadores.");
            }
        }));
    }

    public static ArrayList<User> getUsers()
    {
        return users;
    }

    public static User getCurrentUser()
    {
        return currentUser;
    }

    public static void setCurrentUser(User user)
    {
        currentUser = user;
    }

    public static Account getCurrentAccount()
    {
        return currentAccount;
    }

    public static void setCurrentAccount(Account account)
    {
        currentAccount = account;
    }
}

class MainMenuPanel extends JPanel
{
    public MainMenuPanel(FinanceApp app)
    {
        setLayout(new GridLayout(3, 1));

        JButton registerButton = new JButton("Registar Utilizador");
        registerButton.addActionListener(e -> app.showRegisterPanel());
        add(registerButton);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> app.showLoginPanel());
        add(loginButton);

        JButton exitButton = new JButton("Sair");
        exitButton.addActionListener(e -> System.exit(0));
        add(exitButton);
    }
}

class RegisterPanel extends JPanel
{
    private JTextField nameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JTextField ccField;

    public RegisterPanel(FinanceApp app)
    {
        setLayout(new GridLayout(5, 2));

        add(new JLabel("Nome:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Email:"));
        emailField = new JTextField();
        add(emailField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        add(new JLabel("CC:"));
        ccField = new JTextField();
        add(ccField);

        JButton registerButton = new JButton("Registar");
        registerButton.addActionListener(e -> {
            String name = nameField.getText();
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            String cc = ccField.getText();

            User user = new User(name, email, password, cc);
            FinanceApp.getUsers().add(user);

            try
            {
                FileManager.saveUsers(FinanceApp.getUsers());
            }
            catch (IOException ex)
            {
                System.out.println("Erro ao salvar utilizadores.");
            }

            JOptionPane.showMessageDialog(this, "Utilizador registado com sucesso!");
            app.showMainMenu();
        });
        add(registerButton);

        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(e -> app.showMainMenu());
        add(backButton);
    }
}

class LoginPanel extends JPanel
{
    private JTextField emailField;
    private JPasswordField passwordField;

    public LoginPanel(FinanceApp app)
    {
        setLayout(new GridLayout(3, 2));

        add(new JLabel("Email:"));
        emailField = new JTextField();
        add(emailField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            for ( User user : FinanceApp.getUsers() )
            {
                if ( user.getEmail().equals(email) && user.getPassword().equals(password) )
                {
                    FinanceApp.setCurrentUser(user);
                    JOptionPane.showMessageDialog(this, "Login bem-sucedido!");
                    app.showUserMenu();
                    return;
                }
            }

            JOptionPane.showMessageDialog(this, "Email ou password incorretos.");
        });
        add(loginButton);

        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(e -> app.showMainMenu());
        add(backButton);
    }
}

class UserMenuPanel extends JPanel
{
    public UserMenuPanel(FinanceApp app)
    {
        setLayout(new GridLayout(3, 1));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        User currentUser = FinanceApp.getCurrentUser();

        // User Details
        gbc.gridy++;
        JLabel userDetailsLabel = new JLabel("<html>" + currentUser.toString().replace("\n", "<br>") + "</html>");
        add(userDetailsLabel, gbc);
        // Buttons
        gbc.gridy++;
        gbc.gridwidth = 2;

        JPanel buttonsPanel = new JPanel(new FlowLayout());
        // Create Account Button
        JButton createAccountButton = new JButton("Criar Conta");
        createAccountButton.addActionListener(e -> app.showCreateAccountPanel());
        buttonsPanel.add(createAccountButton, gbc);
        // Load Account Button
        JButton selectAccountButton = new JButton("Entrar em Conta Existente");
        selectAccountButton.addActionListener(e -> app.showSelectAccountPanel());
        buttonsPanel.add(selectAccountButton, gbc);

        add(buttonsPanel, gbc);
        // Logout Button
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> {
            FinanceApp.setCurrentUser(null);
            FinanceApp.setCurrentAccount(null);
            app.showMainMenu();
        });
        add(logoutButton, gbc);
    }
}

class CreateAccountPanel extends JPanel
{
    private JTextField accountNumberField;

    public CreateAccountPanel(FinanceApp app)
    {
        setLayout(new GridLayout(3, 2));

        add(new JLabel("Número da Conta:"));
        accountNumberField = new JTextField();
        add(accountNumberField);

        JButton createButton = new JButton("Criar Conta");
        createButton.addActionListener(e -> {
            String accountNumber = accountNumberField.getText();

            if ( accountNumber.isEmpty() )
            {
                JOptionPane.showMessageDialog(this, "Por favor, insira o número da conta.");
                return;
            }

            Account account = new Account(accountNumber, 0.0, null);
            FinanceApp.getCurrentUser().addAccount(account);

            try
            {
                FileManager.saveUsers(FinanceApp.getUsers());
            }
            catch (IOException ex)
            {
                System.out.println("Erro ao salvar conta.");
            }

            JOptionPane.showMessageDialog(this, "Conta criada com sucesso!");
            app.showUserMenu();
        });
        add(createButton);

        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(e -> app.showUserMenu());
        add(backButton);
    }
}

class SelectAccountPanel extends JPanel
{
    public SelectAccountPanel(FinanceApp app)
    {
        setLayout(new BorderLayout());

        JPanel accountListPanel = new JPanel(new GridLayout(0, 1));
        JScrollPane scrollPane = new JScrollPane(accountListPanel);
        add(scrollPane, BorderLayout.CENTER);

        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(e -> app.showUserMenu());
        add(backButton, BorderLayout.SOUTH);

        if ( FinanceApp.getCurrentUser() != null )
        {
            for ( Account account : FinanceApp.getCurrentUser().getAccounts() )
            {
                JButton accountButton = new JButton(account.getAccountNumber());
                accountButton.addActionListener(e -> {
                    FinanceApp.setCurrentAccount(account);
                    app.showAccountMenu();
                });
                accountListPanel.add(accountButton);
            }
        }
    }
}

class AccountMenuPanel extends JPanel
{
    public AccountMenuPanel(FinanceApp app)
    {
        setLayout(new GridLayout(4, 1));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        Account currentAccount = FinanceApp.getCurrentAccount();

        // Account Details
        gbc.gridy++;
        JLabel accountDetailsLabel = new JLabel("<html>" + currentAccount.toString().replace("\n", "<br>") + "</html>");
        add(accountDetailsLabel, gbc);

        JButton createTransactionButton = new JButton("Criar Transação");
        createTransactionButton.addActionListener(e -> app.showCreateTransactionPanel());
        add(createTransactionButton, gbc);

        JButton viewTransactionHistoryButton = new JButton("Ver Histórico de Transações");
        viewTransactionHistoryButton.addActionListener(e -> app.showTransactionHistoryPanel());
        add(viewTransactionHistoryButton, gbc);

        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(e -> app.showUserMenu());
        add(backButton, gbc);
    }
}

class CreateTransactionPanel extends JPanel
{
    private JTextField amountField;
    private JTextField descriptionField;
    private JComboBox<Category> categoryComboBox;
    private JCheckBox fixedCheckBox;

    public CreateTransactionPanel(FinanceApp app)
    {
        setLayout(new GridLayout(6, 2));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;

        Account currentAccount = FinanceApp.getCurrentAccount();

        // Account Details
        gbc.gridy++;
        JLabel accountDetailsLabel = new JLabel("<html>" + currentAccount.toString().replace("\n", "<br>") + "</html>");
        add(accountDetailsLabel, gbc);

        add(new JLabel("Valor:"));
        amountField = new JTextField();
        add(amountField, gbc);

        add(new JLabel("Descrição:"));
        descriptionField = new JTextField();
        add(descriptionField, gbc);

        add(new JLabel("Categoria:"));
        Category[] categories = {new Category("Transferência"), new Category("Levantamento"), new Category("Depósito")};
        categoryComboBox = new JComboBox<>(categories);
        add(categoryComboBox, gbc);
        System.out.println(FinanceApp.getCurrentAccount());

        add(new JLabel("Fixed:"));
        fixedCheckBox = new JCheckBox();
        add(fixedCheckBox, gbc);

        JButton expenseButton = new JButton("Despesa");
        expenseButton.addActionListener(e -> {
            double amount = Double.parseDouble(amountField.getText());
            String description = descriptionField.getText();
            Date date = new Date();
            boolean isFixed = fixedCheckBox.isSelected();

            String destination = JOptionPane.showInputDialog(this, "Destino:");
            Category category = (Category) categoryComboBox.getSelectedItem();
            System.out.println(destination);
            if ( isFixed )
            {
                FixedExpense expense = new FixedExpense(amount, description, date, category, destination, "Semana", 5);
                FinanceApp.getCurrentAccount().addFixedExpenseTransaction(expense);
            }
            else
            {
                System.out.println(FinanceApp.getCurrentAccount() + "teste");
                Transaction expense = new Expense(amount, description, date, category, destination); // TRANSACTION NÃO TEM DESTINATION
                FinanceApp.getCurrentAccount().addTransaction(expense);
            }

            try
            {
                FileManager.saveUsers(FinanceApp.getUsers());
            }
            catch (IOException ex)
            {
                System.out.println("Erro ao salvar transação.");
            }

            JOptionPane.showMessageDialog(this, "Despesa criada com sucesso!");
            app.showAccountMenu();
        });
        add(expenseButton, gbc);

        JButton incomeButton = new JButton("Receita");
        incomeButton.addActionListener(e -> {
            double amount = Double.parseDouble(amountField.getText());
            String description = descriptionField.getText();
            Date date = new Date();
            boolean isFixed = fixedCheckBox.isSelected();

            String source = JOptionPane.showInputDialog(this, "Origem:");
            Category category = (Category) categoryComboBox.getSelectedItem();

            if ( isFixed )
            {
                FixedIncome income = new FixedIncome(amount, description, date, category, source, "Semana", 5);
                FinanceApp.getCurrentAccount().addFixedIncomeTransaction(income);
            }
            else
            {
                Transaction income = new Income(amount, description, date, category, source);
                FinanceApp.getCurrentAccount().addTransaction(income);
            }

            try
            {
                FileManager.saveUsers(FinanceApp.getUsers());
            }
            catch (IOException ex)
            {
                System.out.println("Erro ao salvar transação.");
            }

            JOptionPane.showMessageDialog(this, "Receita criada com sucesso!");
            app.showAccountMenu();
        });
        add(incomeButton, gbc);

        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(e -> app.showAccountMenu());
        add(backButton, gbc);
    }
}

class TransactionHistoryPanel extends JPanel
{
    private JTextArea textArea;

    public TransactionHistoryPanel(FinanceApp app)
    {
        setLayout(new BorderLayout());
        
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(e -> app.showAccountMenu());
        add(backButton, BorderLayout.SOUTH);
    }
    
    public void updateTransactionHistory()
    {
        textArea.setText("");
        if ( FinanceApp.getCurrentAccount() != null &&  FinanceApp.getCurrentAccount().getTransactions() != null)
        {
            for ( Transaction transaction : FinanceApp.getCurrentAccount().getTransactions() )
            {
                textArea.append(transaction.getDate() + " - " + transaction.getDescription() + " - " + transaction.getAmount() + "\n");
            }
        }
    }
}