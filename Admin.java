import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Admin extends JFrame implements ActionListener {
    private RoundedButton addCustomer, readAllCustomers, searchCustomer, updateCustomer, deleteCustomer, showPasswords, monthButton, back;
    private BackgroundPanel backgroundPanel;

    public Admin() {
        super("Admin Screen");
        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        // Set the background image
        backgroundPanel = new BackgroundPanel("b6.png"); // Replace with your image path
        backgroundPanel.setLayout(new BoxLayout(backgroundPanel, BoxLayout.Y_AXIS));
        this.add(backgroundPanel);

        // Common button color and font
        Color buttonColor = new Color(64, 68, 156, 213); // Steel blue
        Font buttonFont = new Font("Arial", Font.BOLD, 16);

        Dimension buttonSize = new Dimension(200, 50); // Fixed size for all buttons

        // Initialize buttons
        addCustomer = createButton("Add Profile", buttonColor, buttonFont, buttonSize);
        readAllCustomers = createButton("View All Customers", buttonColor, buttonFont, buttonSize);
        searchCustomer = createButton("Search Info", buttonColor, buttonFont, buttonSize);
        updateCustomer = createButton("Update Info", buttonColor, buttonFont, buttonSize);
        deleteCustomer = createButton("Delete Profile", buttonColor, buttonFont, buttonSize);
        showPasswords = createButton("Get Passwords", buttonColor, buttonFont, buttonSize);
        monthButton = createButton("More than Month", buttonColor, buttonFont, buttonSize);
        back = createButton("Back", buttonColor, buttonFont, buttonSize);

        // Add buttons to the background panel
        backgroundPanel.add(Box.createVerticalStrut(30)); // Add some spacing
        backgroundPanel.add(addCustomer);
        backgroundPanel.add(Box.createVerticalStrut(20));
        backgroundPanel.add(readAllCustomers);
        backgroundPanel.add(Box.createVerticalStrut(20));
        backgroundPanel.add(searchCustomer);
        backgroundPanel.add(Box.createVerticalStrut(20));
        backgroundPanel.add(updateCustomer);
        backgroundPanel.add(Box.createVerticalStrut(20));
        backgroundPanel.add(deleteCustomer);
        backgroundPanel.add(Box.createVerticalStrut(20));
        backgroundPanel.add(showPasswords);
        backgroundPanel.add(Box.createVerticalStrut(20));
        backgroundPanel.add(monthButton);
        backgroundPanel.add(Box.createVerticalStrut(20));
        backgroundPanel.add(back);
        backgroundPanel.add(Box.createVerticalStrut(30));

        // Add ActionListeners
        addCustomer.addActionListener(this);
        readAllCustomers.addActionListener(this);
        searchCustomer.addActionListener(this);
        updateCustomer.addActionListener(this);
        deleteCustomer.addActionListener(this);
        showPasswords.addActionListener(this);
        monthButton.addActionListener(this);
        back.addActionListener(this);

        this.setVisible(true);
    }

    // Helper method to create a rounded button
    private RoundedButton createButton(String text, Color color, Font font, Dimension size) {
        RoundedButton button = new RoundedButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(font);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align buttons
        button.setPreferredSize(size); // Set uniform size
        button.setMaximumSize(size); // Ensure all buttons stay the same size
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addCustomer) {
            this.dispose();
            new AddCustomer();
        } else if (e.getSource() == readAllCustomers) {
            this.dispose();
            new Display();
        } else if (e.getSource() == searchCustomer) {
            this.dispose();
            new SearchCustomer();
        } else if (e.getSource() == updateCustomer) {
            this.dispose();
            new UpdateCustomer();
        } else if (e.getSource() == deleteCustomer) {
            this.dispose();
            new DeleteCustomer();
        } else if (e.getSource() == showPasswords) {
            this.dispose();
            new ShowPassword();
        } else if (e.getSource() == monthButton) {
            this.dispose();
            new Month();
        } else if (e.getSource() == back) {
            this.dispose();
            new Main();
        }
    }

    // Inner class to create a custom background panel
    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(String imagePath) {
            try {
                backgroundImage = new ImageIcon(imagePath).getImage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

    // Custom button class for rounded buttons
    class RoundedButton extends JButton {
        public RoundedButton(String text) {
            super(text);
            setContentAreaFilled(false); // Disable default background rendering
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Draw rounded rectangle background
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);

            // Draw text manually
            g2.setColor(getForeground());
            FontMetrics fm = g2.getFontMetrics();
            int textWidth = fm.stringWidth(getText());
            int textHeight = fm.getAscent();
            int x = (getWidth() - textWidth) / 2;
            int y = (getHeight() + textHeight) / 2 - 2;
            g2.drawString(getText(), x, y);

            g2.dispose();
        }

        @Override
        public void paintBorder(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Draw white border
            g2.setColor(Color.WHITE);
            g2.setStroke(new BasicStroke(2)); // Adjust thickness of the outline
            g2.drawRoundRect(1, 1, getWidth() - 3, getHeight() - 3, 30, 30);

            g2.dispose();
        }
    }

    public static void main(String[] args) {
        new Admin();
    }
}
