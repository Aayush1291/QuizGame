import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DomainSelection extends JFrame {
    public DomainSelection() {
        setTitle("Select Domain");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set background color and layout
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setLayout(new GridLayout(4, 1, 10, 10));

        JTextField nameField = new JTextField(10);
        JButton cButton = new JButton("C");
        JButton cppButton = new JButton("C++");
        JButton javaButton = new JButton("Java");

        // Style buttons
        styleButton(cButton);
        styleButton(cppButton);
        styleButton(javaButton);

        panel.add(new JLabel("Enter your name:"));
        panel.add(nameField);
        panel.add(cButton);
        panel.add(cppButton);
        panel.add(javaButton);
        
        cButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new QuizPage(nameField.getText(), "C");
                dispose();
            }
        });
        
        cppButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new QuizPage(nameField.getText(), "C++");
                dispose();
            }
        });
        
        javaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new QuizPage(nameField.getText(), "Java");
                dispose();
            }
        });

        add(panel);
        setVisible(true);
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(Color.BLUE);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    }
}
