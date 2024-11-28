import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class Main extends JFrame implements ActionListener {
    private JLabel heading;
    private JButton adminButton;
    private JButton customerButton;

    public Main() {
        super("Car Rental System");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set up background panel with an image
        ImageIcon backgroundIcon = new ImageIcon("f1.jpg"); // Replace with your image path
        JLabel backgroundLabel = new JLabel(backgroundIcon);
        backgroundLabel.setLayout(new BorderLayout());
        setContentPane(backgroundLabel);

        // Header with centered text
        heading = new JLabel("Welcome to Car Rental System", JLabel.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 22));
        heading.setForeground(Color.WHITE); // White text to contrast background
        heading.setOpaque(false);

        // Button panel with FlowLayout to center the buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 15));
        buttonPanel.setOpaque(false); // Make panel transparent to show background

        // Styled buttons with rounded corners
        adminButton = createRoundedButton("Admin");
        customerButton = createRoundedButton("Customer");

        // Add action listeners for sound effect
        adminButton.addActionListener(this);
        customerButton.addActionListener(this);

        buttonPanel.add(adminButton);
        buttonPanel.add(customerButton);

        // Center panel for both header and button panel
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setOpaque(false);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(150, 0, 0, 0)); // Increased top padding to 150
        centerPanel.add(heading, BorderLayout.NORTH);
        centerPanel.add(buttonPanel, BorderLayout.CENTER);

        // Add center panel to main frame
        add(centerPanel, BorderLayout.CENTER);

        setVisible(true);
    }

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
        button.setBackground(new Color(72, 147, 147, 231)); //background color
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
        if (e.getSource() == adminButton) {
            new AdminLogin();  // Replace with your actual AdminLogin class
        } else if (e.getSource() == customerButton) {
            new CustomerLogin();  // Replace with your actual CustomerLogin class
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
