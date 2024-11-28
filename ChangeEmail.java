import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ChangeEmail extends JFrame implements ActionListener {

    private JLabel email;
    private JTextField emailText;
    private JButton update, back;
    private String ID;

    public ChangeEmail(String ID) {
        super("Update Email");
        this.ID = ID;

        // Frame settings
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLayout(new BorderLayout());

        // Custom panel with background image
        BackgroundPanel bgPanel = new BackgroundPanel("b6.png");
        bgPanel.setLayout(new GridBagLayout());

        // GridBagConstraints for button positioning
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 10, 0);

        // Create components
        email = new JLabel("Email");
        email.setForeground(Color.WHITE);  // Set label text color to white
        emailText = new JTextField();
        emailText.setPreferredSize(new Dimension(250, 30));
        emailText.setForeground(Color.WHITE);  // Set text field text color to white
        emailText.setBackground(new Color(50, 50, 50));  // Dark background for the text field

        update = createRoundedButton("Update");
        back = createRoundedButton("Back");

        // Add components to the panel
        bgPanel.add(email, gbc);
        gbc.gridy++;
        bgPanel.add(emailText, gbc);
        gbc.gridy++;
        bgPanel.add(update, gbc);
        gbc.gridy++;
        bgPanel.add(back, gbc);

        // Add action listeners
        update.addActionListener(this);
        back.addActionListener(this);

        add(bgPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == update) {
            String email = emailText.getText();

            CustomerOperations ho = new CustomerOperations();
            boolean updated = ho.updateEmail(ID, email);

            if (updated) {
                JOptionPane.showMessageDialog(null, "Email Updated");
            } else {
                JOptionPane.showMessageDialog(null, "Email Not Updated");
            }
        } else if (e.getSource() == back) {
            this.dispose();
            new CustomerPage(ID);
        }
    }

    private JButton createRoundedButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2.dispose();
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
                g2.dispose();
            }
        };

        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
        button.setForeground(Color.WHITE);  // Set button text color to white
        button.setBackground(new Color(64, 68, 156, 213));  // Button color
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setPreferredSize(new Dimension(200, 40));
        return button;
    }

    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(String filePath) {
            try {
                backgroundImage = ImageIO.read(new File(filePath));
            } catch (IOException e) {
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

    public static void main(String[] args) {
        new ChangeEmail("12345");
    }
}

