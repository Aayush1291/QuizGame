import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class QuizPage extends JFrame {
    private String name;
    private String domain;
    private int score = 0;
    private ArrayList<String> selectedQuestions = new ArrayList<>();
    private ArrayList<String> selectedAnswers = new ArrayList<>();

    private JLabel[] questionLabels = new JLabel[10];
    private JTextField[] answerFields = new JTextField[10];
    private JButton submitButton = new JButton("Submit All Answers");

    private static final HashMap<String, ArrayList<String>> questionBank = new HashMap<>();
    private static final HashMap<String, ArrayList<String>> answerBank = new HashMap<>();

    // Initialize question banks
static {
    // Questions and answers for C
    ArrayList<String> cQuestions = new ArrayList<>();
    ArrayList<String> cAnswers = new ArrayList<>();
    cQuestions.add("What is a pointer?");
    cAnswers.add("Address");
    cQuestions.add("What does `malloc` do?");
    cAnswers.add("Allocate");
    cQuestions.add("Purpose of `main` function?");
    cAnswers.add("Entry");
    cQuestions.add("Difference between `struct` and `union`?");
    cAnswers.add("Memory");
    cQuestions.add("Significance of `const` keyword?");
    cAnswers.add("Immutable");
    cQuestions.add("How do you declare an array?");
    cAnswers.add("Type");
    cQuestions.add("What is a segmentation fault?");
    cAnswers.add("Error");
    cQuestions.add("What is a header file?");
    cAnswers.add("Declarations");
    cQuestions.add("What is recursion?");
    cAnswers.add("Self-call");
    cQuestions.add("Use of `static` keyword?");
    cAnswers.add("Visibility");
    cQuestions.add("What are macros?");
    cAnswers.add("Snippets");
    cQuestions.add("Dynamically allocate memory?");
    cAnswers.add("malloc");
    cQuestions.add("Purpose of `return` statement?");
    cAnswers.add("Exit");
    cQuestions.add("Difference between `==` and `===`?");
    cAnswers.add("Equality");
    cQuestions.add("Role of pointers in arrays?");
    cAnswers.add("Traversal");
    cQuestions.add("What is a NULL pointer?");
    cAnswers.add("Empty");
    cQuestions.add("How to read input?");
    cAnswers.add("scanf");
    cQuestions.add("Different types of storage classes?");
    cAnswers.add("Classes");
    cQuestions.add("Difference between local and global variable?");
    cAnswers.add("Scope");
    cQuestions.add("What is an infinite loop?");
    cAnswers.add("Endless");
    cQuestions.add("How to handle errors in C?");
    cAnswers.add("Return");
    cQuestions.add("Role of preprocessor?");
    cAnswers.add("Directives");
    cQuestions.add("What is type casting?");
    cAnswers.add("Convert");
    cQuestions.add("Difference between pass by value and reference?");
    cAnswers.add("Address");
    cQuestions.add("What is a function pointer?");
    cAnswers.add("Address");
    cQuestions.add("What are command-line arguments?");
    cAnswers.add("Inputs");
    cQuestions.add("How to use `switch` statements?");
    cAnswers.add("Branching");
    cQuestions.add("Purpose of `break` and `continue`?");
    cAnswers.add("Control");

    // Add questions and answers to the question and answer banks
    questionBank.put("C", cQuestions);
    answerBank.put("C", cAnswers);

    // Questions and answers for C++
    ArrayList<String> cppQuestions = new ArrayList<>();
    ArrayList<String> cppAnswers = new ArrayList<>();
    cppQuestions.add("What are classes?");
    cppAnswers.add("Blueprints");
    cppQuestions.add("Concept of inheritance?");
    cppAnswers.add("Properties");
    cppQuestions.add("What is polymorphism?");
    cppAnswers.add("Forms");
    cppQuestions.add("What is encapsulation?");
    cppAnswers.add("Bundling");
    cppQuestions.add("Use of `this` pointer?");
    cppAnswers.add("Current");
    cppQuestions.add("Difference between constructor and destructor?");
    cppAnswers.add("Initialization");
    cppQuestions.add("What is a virtual function?");
    cppAnswers.add("Overridable");
    cppQuestions.add("Explain templates.");
    cppAnswers.add("Generics");
    cppQuestions.add("Access specifiers in C++?");
    cppAnswers.add("Modifiers");
    cppQuestions.add("What is a namespace?");
    cppAnswers.add("Scope");
    cppQuestions.add("Difference between `new` and `malloc`?");
    cppAnswers.add("Constructors");
    cppQuestions.add("What is exception handling?");
    cppAnswers.add("Errors");
    cppQuestions.add("What is a reference variable?");
    cppAnswers.add("Alias");
    cppQuestions.add("What is operator overloading?");
    cppAnswers.add("Custom");
    cppQuestions.add("What are smart pointers?");
    cppAnswers.add("Management");
    cppQuestions.add("Explain `friend` class.");
    cppAnswers.add("Access");
    cppQuestions.add("What is dynamic polymorphism?");
    cppAnswers.add("Runtime");
    cppQuestions.add("What is a pure virtual function?");
    cppAnswers.add("Abstract");
    cppQuestions.add("How to implement linked list?");
    cppAnswers.add("Nodes");
    cppQuestions.add("Difference between `struct` and `class`?");
    cppAnswers.add("Access");
    cppQuestions.add("What is multiple inheritance?");
    cppAnswers.add("Complexity");
    cppQuestions.add("Explain `static` keyword.");
    cppAnswers.add("Restriction");
    cppQuestions.add("What are STL containers?");
    cppAnswers.add("Collections");

    // Add questions and answers to the question and answer banks
    questionBank.put("C++", cppQuestions);
    answerBank.put("C++", cppAnswers);

    // Questions and answers for Java
    ArrayList<String> javaQuestions = new ArrayList<>();
    ArrayList<String> javaAnswers = new ArrayList<>();
    javaQuestions.add("Purpose of Java Virtual Machine?");
    javaAnswers.add("Platform");
    javaQuestions.add("Concept of garbage collection?");
    javaAnswers.add("Cleanup");
    javaQuestions.add("Difference between JDK, JRE, and JVM?");
    javaAnswers.add("Components");
    javaQuestions.add("What is an interface?");
    javaAnswers.add("Signature");
    javaQuestions.add("What is method overloading?");
    javaAnswers.add("Duplicates");
    javaQuestions.add("Concept of encapsulation?");
    javaAnswers.add("Protection");
    javaQuestions.add("Access modifiers in Java?");
    javaAnswers.add("Visibility");
    javaQuestions.add("What is inheritance?");
    javaAnswers.add("Reusability");
    javaQuestions.add("Difference between abstract class and interface?");
    javaAnswers.add("Implementation");
    javaQuestions.add("What is a constructor?");
    javaAnswers.add("Initializer");
    javaQuestions.add("Concept of exception handling?");
    javaAnswers.add("Errors");
    javaQuestions.add("What are Java Collections?");
    javaAnswers.add("Framework");
    javaQuestions.add("Purpose of `static` keyword?");
    javaAnswers.add("Class");
    javaQuestions.add("What is a lambda expression?");
    javaAnswers.add("Function");
    javaQuestions.add("What is multithreading?");
    javaAnswers.add("Concurrency");
    javaQuestions.add("What are enums?");
    javaAnswers.add("Constants");
    javaQuestions.add("Difference between `==` and `equals()`?");
    javaAnswers.add("Comparison");
    javaQuestions.add("What is the `final` keyword?");
    javaAnswers.add("Constant");
    javaQuestions.add("Concept of serialization?");
    javaAnswers.add("Bytes");
    javaQuestions.add("Purpose of `volatile` keyword?");
    javaAnswers.add("Changes");
    javaQuestions.add("What is a thread pool?");
    javaAnswers.add("Reuse");
    javaQuestions.add("What is `synchronized` keyword?");
    javaAnswers.add("Access");
    javaQuestions.add("Java's memory model?");
    javaAnswers.add("Allocation");

    // Add questions and answers to the question and answer banks
    questionBank.put("Java", javaQuestions);
    answerBank.put("Java", javaAnswers);
}


    public QuizPage(String name, String domain) {
        this.name = name;
        this.domain = domain;

// Randomly select 10 questions from the question bank
        ArrayList<String> questions = new ArrayList<>(questionBank.get(domain));
        ArrayList<String> answers = new ArrayList<>(answerBank.get(domain));

        ArrayList<Integer> indices = new ArrayList<>();
        for (int i = 0; i < questions.size(); i++) indices.add(i);
        Collections.shuffle(indices);

        for (int i = 0; i < 10; i++) {
            selectedQuestions.add(questions.get(indices.get(i)));
            selectedAnswers.add(answers.get(indices.get(i)));
        }

        setTitle("Quiz - " + domain);
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set layout and panel style
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(11, 1, 10, 10));
        panel.setBackground(Color.LIGHT_GRAY);
        Font questionFont = new Font("Arial", Font.PLAIN, 14);
        Font answerFont = new Font("Arial", Font.BOLD, 14);
        for (int i = 0; i < 10; i++) {
            questionLabels[i] = new JLabel((i + 1) + ". " + selectedQuestions.get(i));
            questionLabels[i].setFont(questionFont);
            answerFields[i] = new JTextField(20);
            answerFields[i].setFont(answerFont);
            panel.add(questionLabels[i]);
            panel.add(answerFields[i]);
        }

        submitButton.setFont(new Font("Arial", Font.BOLD, 16));
        submitButton.setBackground(Color.GREEN);
        submitButton.setForeground(Color.WHITE);
        submitButton.setFocusPainted(false);
        submitButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkAnswers();
            }
        });

        panel.add(submitButton);
        add(panel);
        setVisible(true);
    }

private void checkAnswers() {
        for (int i = 0; i < 10; i++) {
            String userAnswer = answerFields[i].getText().trim();
            if (userAnswer.equalsIgnoreCase(selectedAnswers.get(i))) {
                score++;
            }
        }
        saveScore();
        JOptionPane.showMessageDialog(this, "Quiz Complete! Your Score: " + score);
        new MainMenu();
        dispose();
    }

    private void saveScore() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/QuizGameDB", "username", "password");
            PreparedStatement ps = con.prepareStatement("INSERT INTO scores (name, domain, marks) VALUES (?, ?, ?)");
            ps.setString(1, name);
            ps.setString(2, domain);
            ps.setInt(3, score);
            ps.executeUpdate();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
