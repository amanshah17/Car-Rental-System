import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;

public class CustomerLogin extends JFrame implements ActionListener {
    private JLabel CustomerID, Password;
    private JTextField CustomerIDText, passwordText;
    private JButton LoginButton, backButton, forgotPassword;

    public CustomerLogin() {
        super("Customer Login");

        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        // Background Image
        ImageIcon backgroundIcon = new ImageIcon("f1.jpg"); // Update with your image path
        JLabel backgroundLabel = new JLabel(backgroundIcon);
        backgroundLabel.setLayout(new GridBagLayout());
        setContentPane(backgroundLabel);

        // Panel to hold form components
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        loginPanel.setOpaque(false); // Transparent background

        // Labels
        CustomerID = new JLabel("Customer ID");
        CustomerID.setForeground(Color.WHITE);
        CustomerID.setAlignmentX(Component.CENTER_ALIGNMENT);

        Password = new JLabel("Password");
        Password.setForeground(Color.WHITE);
        Password.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Text Fields (increased width)
        CustomerIDText = new JTextField(15);
        CustomerIDText.setPreferredSize(new Dimension(250, 30)); // Wide text field
        CustomerIDText.setMaximumSize(new Dimension(250, 30));

        passwordText = new JTextField(15);
        passwordText.setPreferredSize(new Dimension(250, 30)); // Wide text field
        passwordText.setMaximumSize(new Dimension(250, 30));

        // Buttons (using createRoundedButton method for rounded buttons)
        LoginButton = createRoundedButton("Login");
        backButton = createRoundedButton("Back");
        forgotPassword = new JButton("Forgot Password");

        // Customize Forgot Password button to be transparent
        forgotPassword.setContentAreaFilled(false); // Makes button transparent
        forgotPassword.setBorderPainted(false); // Removes border
        forgotPassword.setForeground(Color.WHITE); // Sets text color to white for visibility

        // Panel for Login and Back buttons side by side
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0)); // 10 px gap between buttons
        buttonPanel.setOpaque(false); // Transparent background
        buttonPanel.add(backButton);
        buttonPanel.add(LoginButton);

        // Add components to login panel
        loginPanel.add(Box.createVerticalStrut(80)); // Adds space from top
        loginPanel.add(CustomerID);
        loginPanel.add(Box.createVerticalStrut(5));
        loginPanel.add(CustomerIDText);
        loginPanel.add(Box.createVerticalStrut(20)); // Space between fields
        loginPanel.add(Password);
        loginPanel.add(Box.createVerticalStrut(5));
        loginPanel.add(passwordText);
        loginPanel.add(Box.createVerticalStrut(30)); // Space above buttons
        loginPanel.add(buttonPanel); // Add side-by-side Login and Back buttons
        loginPanel.add(Box.createVerticalStrut(10)); // Space between button panel and forgot password
        loginPanel.add(forgotPassword); // Add Forgot Password below the buttons

        // Center-align all components
        CustomerID.setAlignmentX(Component.CENTER_ALIGNMENT);
        CustomerIDText.setAlignmentX(Component.CENTER_ALIGNMENT);
        Password.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordText.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        forgotPassword.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add login panel to background
        backgroundLabel.add(loginPanel);

        // Action listeners
        LoginButton.addActionListener(this);
        backButton.addActionListener(this);
        forgotPassword.addActionListener(this);

        setVisible(true);
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
        button.setBackground(new Color(68, 72, 170, 157)); //background color
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setPreferredSize(new Dimension(150, 40));  // Smaller button size
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == LoginButton) {
            CustomerOperations ho = new CustomerOperations();
            String customerID = CustomerIDText.getText();
            String password = passwordText.getText();
            boolean login = ho.credentialsFound(customerID, password);

            if (login) {
                JOptionPane.showMessageDialog(this, "Login Successful");
                dispose();
                new CustomerPage(customerID);
            } else {
                JOptionPane.showMessageDialog(this, "Login Failed. Try Again!");
            }
        } else if (e.getSource() == backButton) {
            dispose();
            new Main();
        } else if (e.getSource() == forgotPassword) {
            dispose();
            new AdminLogin();
        }
    }

    public static void main(String[] args) {
        new CustomerLogin();
    }
}
