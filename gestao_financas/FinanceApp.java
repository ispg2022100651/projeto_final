import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.*;

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
        setSize(640, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

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
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); // Espaçamento entre os botões
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        JButton registerButton = new JButton("Registar Utilizador");
        registerButton.addActionListener(e -> app.showRegisterPanel());
        add(registerButton, gbc);

        gbc.gridy++;
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> app.showLoginPanel());
        add(loginButton, gbc);

        gbc.gridy++;
        JButton exitButton = new JButton("Sair");
        exitButton.addActionListener(e -> System.exit(0));
        add(exitButton, gbc);
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
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;

        add(new JLabel("Nome:"), gbc);
        gbc.gridx = 1;
        nameField = new JTextField(20);
        add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        emailField = new JTextField(20);
        add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        passwordField = new JPasswordField(20);
        add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("CC:"), gbc);
        gbc.gridx = 1;
        ccField = new JTextField(20);
        add(ccField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        JButton registerButton = new JButton("Registar");
        registerButton.addActionListener(e -> {
            String name = nameField.getText();
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            String cc = ccField.getText();

            User user = new User(name, email, password, cc);
            FinanceApp.getUsers().add(user);

            try {
                FileManager.saveUsers(FinanceApp.getUsers());
            } catch (IOException ex) {
                System.out.println("Erro ao salvar utilizadores.");
            }

            JOptionPane.showMessageDialog(this, "Utilizador registado com sucesso!");
            app.showMainMenu();
        });
        add(registerButton, gbc);

        gbc.gridy++;
        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(e -> app.showMainMenu());
        add(backButton, gbc);
    }
}

class LoginPanel extends JPanel
{
    private JTextField emailField;
    private JPasswordField passwordField;

    public LoginPanel(FinanceApp app)
    {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;

        add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        emailField = new JTextField(20);
        add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        passwordField = new JPasswordField(20);
        add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            for (User user : FinanceApp.getUsers()) {
                if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                    FinanceApp.setCurrentUser(user);
                    JOptionPane.showMessageDialog(this, "Login bem-sucedido!");
                    app.showUserMenu();
                    return;
                }
            }

            JOptionPane.showMessageDialog(this, "Email ou password incorretos.");
        });
        add(loginButton, gbc);

        gbc.gridy++;
        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(e -> app.showMainMenu());
        add(backButton, gbc);
    }
}

class UserMenuPanel extends JPanel
{
    public UserMenuPanel(FinanceApp app)
    {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        User currentUser = FinanceApp.getCurrentUser();

        // User Details
        JLabel userDetailsLabel = new JLabel("<html>" + currentUser.toString().replace("\n", "<br>") + "</html>");
        add(userDetailsLabel, gbc);

        // Buttons
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        JPanel buttonsPanel = new JPanel(new FlowLayout());
        
        // Create Account Button
        JButton createAccountButton = new JButton("Criar Conta");
        createAccountButton.addActionListener(e -> app.showCreateAccountPanel());
        buttonsPanel.add(createAccountButton);

        // Load Account Button
        JButton selectAccountButton = new JButton("Entrar em Conta Existente");
        selectAccountButton.addActionListener(e -> app.showSelectAccountPanel());
        buttonsPanel.add(selectAccountButton);

        gbc.gridy++;
        add(buttonsPanel, gbc);

        // Logout Button
        gbc.gridy++;
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
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.insets = new Insets(10, 10, 10, 10); // Espaçamento entre os componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        add(new JLabel("Número da Conta:"), gbc);

        gbc.gridx = 1;
        accountNumberField = new JTextField(20);
        add(accountNumberField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        JButton createButton = new JButton("Criar Conta");
        createButton.addActionListener(e -> {
            String accountNumber = accountNumberField.getText();

            if (accountNumber.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, insira o número da conta.");
                return;
            }

            Account account = new Account(accountNumber, 0.0, null);
            FinanceApp.getCurrentUser().addAccount(account);

            try {
                FileManager.saveUsers(FinanceApp.getUsers());
            } catch (IOException ex) {
                System.out.println("Erro ao salvar conta.");
            }

            JOptionPane.showMessageDialog(this, "Conta criada com sucesso!");
            app.showUserMenu();
        });
        add(createButton, gbc);

        gbc.gridy++;
        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(e -> app.showUserMenu());
        add(backButton, gbc);
    }
}

class SelectAccountPanel extends JPanel
{
    public SelectAccountPanel(FinanceApp app)
    {
        setLayout(new BorderLayout());

        // Painel para a lista de contas
        JPanel accountListPanel = new JPanel(new GridBagLayout());
        JScrollPane scrollPane = new JScrollPane(accountListPanel);
        add(scrollPane, BorderLayout.CENTER);

        // Botão Voltar
        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(e -> app.showUserMenu());
        add(backButton, BorderLayout.SOUTH);

        if (FinanceApp.getCurrentUser() != null) {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.gridx = 0;
            gbc.gridy = 0;

            for (Account account : FinanceApp.getCurrentUser().getAccounts()) {
                JButton accountButton = new JButton(account.getAccountNumber());
                accountButton.addActionListener(e -> {
                    FinanceApp.setCurrentAccount(account);
                    app.showAccountMenu();
                });
                accountListPanel.add(accountButton, gbc);
                gbc.gridy++;
            }
        }
    }
}

class AccountMenuPanel extends JPanel
{
    public AccountMenuPanel(FinanceApp app)
    {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        Account currentAccount = FinanceApp.getCurrentAccount();

        // Account Details
        JLabel accountDetailsLabel = new JLabel("<html>" + currentAccount.toString().replace("\n", "<br>") + "</html>");
        add(accountDetailsLabel, gbc);

        // Create Transaction Button
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton createTransactionButton = new JButton("Criar Transação");
        createTransactionButton.addActionListener(e -> app.showCreateTransactionPanel());
        add(createTransactionButton, gbc);

        // View Transaction History Button
        gbc.gridy++;
        JButton viewTransactionHistoryButton = new JButton("Ver Histórico de Transações");
        viewTransactionHistoryButton.addActionListener(e -> app.showTransactionHistoryPanel());
        add(viewTransactionHistoryButton, gbc);

        // Back Button
        gbc.gridy++;
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
    private JComboBox<String> typesComboBox;
    private JTextField frequencyField;

    public CreateTransactionPanel(FinanceApp app)
    {
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        Account currentAccount = FinanceApp.getCurrentAccount();

        // Account Details
        JLabel accountDetailsLabel = new JLabel("<html>" + currentAccount.toString().replace("\n", "<br>") + "</html>");
        add(accountDetailsLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        
        add(new JLabel("Valor:"), gbc);
        gbc.gridx = 1;
        amountField = new JTextField();
        add(amountField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Descrição:"), gbc);
        gbc.gridx = 1;
        descriptionField = new JTextField();
        add(descriptionField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Categoria:"), gbc);
        gbc.gridx = 1;
        Category[] categories = {new Category("Transferência"), new Category("Levantamento"), new Category("Depósito")};
        categoryComboBox = new JComboBox<>(categories);
        add(categoryComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Fixed:"), gbc);
        gbc.gridx = 1;
        fixedCheckBox = new JCheckBox();
        add(fixedCheckBox, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Tipo:"), gbc);
        gbc.gridx = 1;
        String[] types = {"Semanalmente", "Mensalmente", "Anualmente"};
        typesComboBox = new JComboBox<>(types);
        add(typesComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Frequência:"), gbc);
        gbc.gridx = 1;
        frequencyField = new JTextField();
        add(frequencyField, gbc);

        // Initially disable type and frequency fields
        typesComboBox.setEnabled(true);
        frequencyField.setEnabled(true);

        // Add item listener to fixedCheckBox
        fixedCheckBox.setSelected(true);
        fixedCheckBox.addItemListener(e ->
        {
            boolean selected = e.getStateChange() == ItemEvent.SELECTED;
            typesComboBox.setEnabled(selected);
            frequencyField.setEnabled(selected);
        });

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;

        JButton expenseButton = new JButton("Despesa");
        expenseButton.addActionListener(e -> {
            double amount = Double.parseDouble(amountField.getText());
            String description = descriptionField.getText();
            Date date = new Date();
            boolean isFixed = fixedCheckBox.isSelected();

            Category category = (Category) categoryComboBox.getSelectedItem();

            String destination = JOptionPane.showInputDialog(this, "Destino:");

            if (isFixed)
            {
                String type = (String) typesComboBox.getSelectedItem();
                int frequency = Integer.parseInt(frequencyField.getText());

                Transaction expense = new FixedExpense(amount, description, date, category, destination, type, frequency);
                FinanceApp.getCurrentAccount().addTransaction(expense);
            }
            else
            {
                Transaction expense = new Expense(amount, description, date, category, destination);
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

        gbc.gridx = 1;
        JButton incomeButton = new JButton("Receita");
        incomeButton.addActionListener(e -> {
            double amount = Double.parseDouble(amountField.getText());
            String description = descriptionField.getText();
            Date date = new Date();
            boolean isFixed = fixedCheckBox.isSelected();

            Category category = (Category) categoryComboBox.getSelectedItem();

            String source = JOptionPane.showInputDialog(this, "Origem:");

            if (isFixed)
            {
                String type = (String) typesComboBox.getSelectedItem();
                int frequency = Integer.parseInt(frequencyField.getText());

                Transaction income = new FixedIncome(amount, description, date, category, source, type, frequency);
                FinanceApp.getCurrentAccount().addTransaction(income);
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

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
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

        // Detalhes da conta
        Account currentAccount = FinanceApp.getCurrentAccount();
        JLabel accountDetailsLabel = new JLabel("<html>" + currentAccount.toString().replace("\n", "<br>") + "</html>");
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        detailsPanel.add(accountDetailsLabel);
        add(detailsPanel, BorderLayout.NORTH);

        // Área de texto para o histórico de transações
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        // Painel de botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(e -> app.showAccountMenu());
        buttonPanel.add(backButton);
        
        add(buttonPanel, BorderLayout.SOUTH);
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