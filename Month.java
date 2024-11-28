import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class Month extends JFrame implements ActionListener {

    private RoundedButton profiles, back;
    private BackgroundPanel backgroundPanel;

    public Month() {
        super("View All Month Profiles");
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

        Dimension buttonSize = new Dimension(200, 50); // Fixed size for buttons

        // Initialize buttons
        profiles = createButton("Profiles", buttonColor, buttonFont, buttonSize);
        back = createButton("Back", buttonColor, buttonFont, buttonSize);

        // Add components to the background panel
        backgroundPanel.add(Box.createVerticalGlue());
        backgroundPanel.add(profiles);
        backgroundPanel.add(Box.createVerticalStrut(20));
        backgroundPanel.add(back);
        backgroundPanel.add(Box.createVerticalGlue());

        // Add ActionListeners
        profiles.addActionListener(this);
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
        if (e.getSource() == profiles) {
            AdminOperations ao = new AdminOperations();
            ArrayList<Customer> s = null;
            s = ao.viewAllProfiles();

            if (s.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No Data Found");
            } else {
                boolean hasData = false;
                for (Customer customer : s) {
                    int month = Integer.parseInt(customer.getDayForRent());
                    if (month >= 30) {
                        String details = customer.toString();
                        JOptionPane.showMessageDialog(this, details);
                        hasData = true;
                    }
                }
                if (!hasData) {
                    JOptionPane.showMessageDialog(this, "No Profiles Found for More than 30 Days");
                }
            }
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
        new Month(); // Test the functionality
    }
}
