import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {
    public MainMenu() {
        setTitle("Quiz Game");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set background color and layout
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setLayout(new GridLayout(3, 1, 10, 10));

        JButton playButton = new JButton("Play");
        JButton scoreboardButton = new JButton("Scoreboard");

        // Style buttons
        styleButton(playButton);
        styleButton(scoreboardButton);

        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new DomainSelection();
                dispose();
            }
        });

        scoreboardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Scoreboard();
                dispose();
            }
        });

        panel.add(playButton);
        panel.add(scoreboardButton);
        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(Color.BLUE);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    }
}
