import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ChangePhone extends JFrame implements ActionListener {

    private JLabel phoneNumber;
    private JTextField phoneNumberText;
    private JButton update, back;
    private String ID;

    public ChangePhone(String ID) {
        super("Update Phone Number");
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
        phoneNumber = new JLabel("Phone Number");
        phoneNumber.setForeground(Color.WHITE);  // Set label text color to white
        phoneNumberText = new JTextField();
        phoneNumberText.setPreferredSize(new Dimension(250, 30));
        phoneNumberText.setForeground(Color.WHITE);  // Set text field text color to white
        phoneNumberText.setBackground(new Color(50, 50, 50));  // Dark background for the text field

        update = createRoundedButton("Update");
        back = createRoundedButton("Back");

        // Add components to the panel
        bgPanel.add(phoneNumber, gbc);
        gbc.gridy++;
        bgPanel.add(phoneNumberText, gbc);
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
            String phoneNumber = phoneNumberText.getText();

            CustomerOperations ho = new CustomerOperations();
            boolean updated = ho.updatePhoneNumber(ID, phoneNumber);

            if (updated) {
                JOptionPane.showMessageDialog(null, "Phone Number Updated");
            } else {
                JOptionPane.showMessageDialog(null, "Phone Number Not Updated");
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
        new ChangePhone("12345");
    }
}

