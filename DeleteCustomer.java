import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DeleteCustomer extends JFrame implements ActionListener {
    private JLabel ID;
    private JTextField IDText;
    private JButton delete, back;

    public DeleteCustomer() {
        super("Delete By ID");
        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);  // Use null layout for custom positioning

        // Load background image
        JLabel background = new JLabel(new ImageIcon("b6.png"));
        background.setBounds(0, 0, 600, 600);
        this.setContentPane(background);

        // Create components
        ID = new JLabel("Customer ID:");
        ID.setBounds(100, 100, 150, 30);
        ID.setForeground(Color.WHITE);  // White text color for label
        ID.setFont(new Font("Arial", Font.BOLD, 16));

        IDText = new JTextField();
        IDText.setBounds(250, 100, 200, 30);

        delete = createRoundedButton("Delete");
        delete.setBounds(150, 200, 120, 40);

        back = createRoundedButton("Back");
        back.setBounds(300, 200, 120, 40);

        // Add components
        this.add(ID);
        this.add(IDText);
        this.add(delete);
        this.add(back);

        delete.addActionListener(this);
        back.addActionListener(this);
        this.setVisible(true);
    }

    // Action handling
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == delete) {
            // Placeholder for AdminOperations (simulate deletion success)
            String ID = IDText.getText();
            boolean found = !ID.isEmpty();  // Simulated logic

            if (found) {
                JOptionPane.showMessageDialog(this, "Customer deleted successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "User not found.");
            }
        } else if (e.getSource() == back) {
            this.dispose();
            new Admin();

        }
    }

    // Helper method to create buttons with rounded corners and white outline
    private JButton createRoundedButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(64, 145, 242));  // Button background color
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);  // White outline
                g2.setStroke(new BasicStroke(2));  // Thickness of the outline
                g2.drawRoundRect(1, 1, getWidth() - 2, getHeight() - 2, 30, 30);
            }

            @Override
            public boolean isContentAreaFilled() {
                return false;
            }
        };

        button.setForeground(Color.WHITE);  // Text color
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(false);
        return button;
    }

    public static void main(String[] args) {
        new DeleteCustomer();
    }
}
