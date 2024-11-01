import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

public class Scoreboard extends JFrame {
    public Scoreboard() {
        setTitle("Scoreboard");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Table model to hold the data
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Name");
        tableModel.addColumn("Domain");
        tableModel.addColumn("Score");

        JTable scoreTable = new JTable(tableModel);
        scoreTable.setEnabled(false);  // Make the table non-editable
        scoreTable.setRowHeight(25);

        // Fetch data from the database and populate the table model
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/QuizGameDB", "root", "aayush1291");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM scores");

            while (rs.next()) {
                String name = rs.getString("name");
                String domain = rs.getString("domain");
                int score = rs.getInt("marks");
                tableModel.addRow(new Object[]{name, domain, score});
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(scoreTable);
        add(scrollPane, BorderLayout.CENTER);
        
        // Add a back button to return to the main menu
        JButton backButton = new JButton("Back to Main Menu");
        backButton.addActionListener(e -> {
            new MainMenu();
            dispose();
        });
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
