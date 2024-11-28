import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class ShowPassword extends JFrame implements ActionListener {

    private JLabel ID;
    private JTextField IDTextField;
    private RoundedButton search, back;
    private BackgroundPanel backgroundPanel;

    public ShowPassword() {
        super("Show Password");
        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        // Set the background image
        backgroundPanel = new BackgroundPanel("b6.png"); // Replace with your image path
        backgroundPanel.setLayout(new BoxLayout(backgroundPanel, BoxLayout.Y_AXIS));
        this.add(backgroundPanel);

        // Common button color and font
        Color buttonColor = new Color(64, 68, 156, 178); // Steel blue
        Font buttonFont = new Font("Arial", Font.BOLD, 16);

        Dimension buttonSize = new Dimension(150, 50); // Fixed size for buttons
        Dimension textFieldSize = new Dimension(150, 25); // Smaller text field size

        // Initialize label and text field
        ID = new JLabel("Customer ID");
        ID.setForeground(Color.WHITE); // Set the text color to white
        ID.setFont(new Font("Arial", Font.BOLD, 16)); // Optional: Set a bold font
        ID.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align the label

        IDTextField = new JTextField();
        IDTextField.setMaximumSize(textFieldSize); // Fix the maximum size
        IDTextField.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align the text field

        // Initialize buttons
        search = createButton("Search", buttonColor, buttonFont, buttonSize);
        back = createButton("Back", buttonColor, buttonFont, buttonSize);

        // Add components to the background panel
        backgroundPanel.add(Box.createVerticalGlue());
        backgroundPanel.add(ID);
        backgroundPanel.add(Box.createVerticalStrut(10));
        backgroundPanel.add(IDTextField);
        backgroundPanel.add(Box.createVerticalStrut(20));
        backgroundPanel.add(search);
        backgroundPanel.add(Box.createVerticalStrut(20));
        backgroundPanel.add(back);
        backgroundPanel.add(Box.createVerticalGlue());

        // Add ActionListeners
        search.addActionListener(this);
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
        if (e.getSource() == search) {
            AdminOperations ao = new AdminOperations();
            String ID = IDTextField.getText();
            String details = null;
            details = ao.getPass(ID);

            JOptionPane.showMessageDialog(null, details);
        } else if (e.getSource() == back) {
            this.dispose();
            new Admin();
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
        new ShowPassword(); // Test the functionality
    }
}
