import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class CustomerPage extends JFrame implements ActionListener {
    JButton getProfile, changePassword, updateEmail, updatePhone, back;
    private String ID;

    public CustomerPage(String ID) {
        super("Customer Portal");
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

        // Create rounded buttons
        getProfile = createRoundedButton("Show Profile");
        changePassword = createRoundedButton("Change Password");
        updateEmail = createRoundedButton("Update Email");
        updatePhone = createRoundedButton("Update Phone");
        back = createRoundedButton("Back");

        // Add buttons to the panel
        bgPanel.add(getProfile, gbc);
        gbc.gridy++;
        bgPanel.add(changePassword, gbc);
        gbc.gridy++;
        bgPanel.add(updateEmail, gbc);
        gbc.gridy++;
        bgPanel.add(updatePhone, gbc);
        gbc.gridy++;
        bgPanel.add(back, gbc);

        // Add action listeners
        getProfile.addActionListener(this);
        changePassword.addActionListener(this);
        updateEmail.addActionListener(this);
        updatePhone.addActionListener(this);
        back.addActionListener(this);

        add(bgPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == getProfile) {
            dispose();
            new ShowProfile(ID);
        } else if (e.getSource() == changePassword) {
            dispose();
            new ChangePassword(ID);
        } else if (e.getSource() == updateEmail) {
            dispose();
            new ChangeEmail(ID);
        } else if (e.getSource() == updatePhone) {
            dispose();
            new ChangePhone(ID);
        } else if (e.getSource() == back) {
            dispose();
            new Main();
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
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(64, 68, 156, 213));
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
        new CustomerPage("12345");
    }
}
