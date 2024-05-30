package final_project;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
// Interface Gráfica
import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;

public class Main2
{
    public static void main(String[] args) {
        // Create an account
        Account account = new Account(12345, 1000.0);

        // Create fixed transactions
        FixedExpense fixedExpense = new FixedExpense(100, "Internet", new Date(), "Provider X", "Monthly", 30);
        FixedIncome fixedIncome = new FixedIncome(2000, "Salary", new Date(), "Company Y", "Monthly", 30);

        // Add transactions to the account
        account.addTransaction(fixedExpense);
        account.addTransaction(fixedIncome);

        // Create the GUI
        createAndShowGUI(account);
    }

    private static void createAndShowGUI(Account account)
    {
        // Window
        JFrame frame = new JFrame("Account Information");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(3, 2));
        // Current Balance
        JLabel balanceLabel = new JLabel("Current Balance:");
        JTextField balanceField = new JTextField(account.getBalance() + " €");
        balanceField.setEditable(false);
        
        frame.add(balanceLabel);
        frame.add(balanceField);
        // Transactions
        JLabel transactionLabel = new JLabel("Transactions:");
        frame.add(transactionLabel);

        JTextArea transactionArea = new JTextArea();
        transactionArea.setEditable(false);
        transactionArea.setLineWrap(true); // permitindo quebras de linha
        transactionArea.setWrapStyleWord(true); // quebrando apenas entre palavras completas
        transactionArea.setPreferredSize(new Dimension(200, transactionArea.getFontMetrics(transactionArea.getFont()).getHeight())); // ajustando a altura para uma linha
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        for (Transaction transaction : account.getTransactions())
        {
            String transactionInfo = "Transaction: " + transaction.getDescription() + ", Amount: $" + transaction.getAmount() + ", Date: " + dateFormat.format(transaction.getDate()) + "\n";
            transactionArea.append(transactionInfo);
        }

        JScrollPane scrollPane = new JScrollPane(transactionArea);
        frame.add(scrollPane);

        frame.setVisible(true);
    }
}