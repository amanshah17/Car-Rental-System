import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.sql.*;

public class AdminLogin extends JFrame implements ActionListener {
    private JLabel Username, Password;
    private JTextField UsernameText, passwordText;
    private JButton LoginButton, backButton;

    public AdminLogin() {
        super("Car Rental System");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Background Image
        ImageIcon backgroundIcon = new ImageIcon("f1.jpg");
        JLabel backgroundLabel = new JLabel(backgroundIcon);
        backgroundLabel.setLayout(new GridBagLayout());
        setContentPane(backgroundLabel);

        // Panel to hold form components
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        loginPanel.setOpaque(false);

        // Set up labels and text fields
        Username = new JLabel("Username");
        Username.setForeground(Color.WHITE);
        Username.setAlignmentX(Component.CENTER_ALIGNMENT);

        Password = new JLabel("Password");
        Password.setForeground(Color.WHITE);
        Password.setAlignmentX(Component.CENTER_ALIGNMENT);

        UsernameText = new JTextField(20);
        UsernameText.setMaximumSize(new Dimension(250, 40));

        passwordText = new JTextField(20);
        passwordText.setMaximumSize(new Dimension(250, 40));

        // Create rounded buttons
        LoginButton = createRoundedButton("Login");
        backButton = createRoundedButton("Back");

        // Button Panel for alignment
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.add(backButton);
        buttonPanel.add(LoginButton);

        // Add components to login panel
        loginPanel.add(Box.createVerticalStrut(80));
        loginPanel.add(Username);
        loginPanel.add(Box.createVerticalStrut(5));
        loginPanel.add(UsernameText);
        loginPanel.add(Box.createVerticalStrut(20));
        loginPanel.add(Password);
        loginPanel.add(Box.createVerticalStrut(5));
        loginPanel.add(passwordText);
        loginPanel.add(Box.createVerticalStrut(30));
        loginPanel.add(buttonPanel);

        // Add login panel to the background
        backgroundLabel.add(loginPanel);

        // Add action listeners
        LoginButton.addActionListener(this);
        backButton.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == LoginButton) {
            String username = UsernameText.getText();
            String password = passwordText.getText();

            if (validateLogin(username, password)) {
                JOptionPane.showMessageDialog(this, "Login Successful");
                dispose();
                new Admin();  // Navigate to the admin dashboard or next screen
            } else {
                JOptionPane.showMessageDialog(this, "Login Failed. Invalid credentials.");
            }
        } else if (e.getSource() == backButton) {
            dispose();
            new Main();  // Navigate to the main page
        }
    }

    private boolean validateLogin(String username, String password) {
        boolean isValid = false;
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Get the connection using the DatabaseConnectivity class
            connection = database.connect();

            if (connection != null) {
                // Create the SQL query
                String query = "SELECT * FROM admin1 WHERE username = ? AND password = ?";


                // Prepare the statement
                stmt = connection.prepareStatement(query);
                stmt.setString(1, username);
                stmt.setString(2, password);

                // Execute the query
                rs = stmt.executeQuery();

                // If there's a result, login is valid
                if (rs.next()) {
                    isValid = true;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        } finally {
            // Close the resources
            database.close(connection, stmt, rs);
        }
        return isValid;
    }

    public static void main(String[] args) {
        new AdminLogin();  // Run the Admin Login screen
    }

    // Method to create rounded buttons
    private JButton createRoundedButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                if (g instanceof Graphics2D) {
                    Graphics2D g2 = (Graphics2D) g;
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(getBackground());
                    g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 20, 20));
                }
                super.paintComponent(g);
            }
        };

        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(68, 72, 170, 157));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setPreferredSize(new Dimension(150, 40));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return button;
    }
}

