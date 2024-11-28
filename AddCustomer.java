import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddCustomer extends JFrame implements ActionListener {
    private JLabel lID, lpassword, lFirstName, lSecondName, lEmail, lPhoneNumber,
            lCNIC, lDayForRent, lBookingID, lCarName, lCarColour, lRegNo;
    private JTextField tID, tpassword, tFirstName, tSecondName, tEmail, tPhoneNumber,
            tCNIC, tDayForRent, tBookingID, tCarName, tCarColour, tRegNo;
    private RoundButton Add, Back;

    public AddCustomer() {
        super("Add Customer");
        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        // Create the background panel
        JPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setLayout(new GridBagLayout());
        backgroundPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Initialize labels and text fields with color
        Color labelColor = Color.WHITE;
        Font font = new Font("Arial", Font.BOLD, 14);

        lID = createCustomLabel("Customer ID", labelColor, font);
        lpassword = createCustomLabel("Password", labelColor, font);
        lFirstName = createCustomLabel("First Name", labelColor, font);
        lSecondName = createCustomLabel("Second Name", labelColor, font);
        lEmail = createCustomLabel("Email", labelColor, font);
        lPhoneNumber = createCustomLabel("Phone Number", labelColor, font);
        lCNIC = createCustomLabel("CNIC", labelColor, font);
        lBookingID = createCustomLabel("Booking ID", labelColor, font);
        lDayForRent = createCustomLabel("Days For Rent", labelColor, font);
        lCarName = createCustomLabel("Car Name", labelColor, font);
        lCarColour = createCustomLabel("Car Colour", labelColor, font);
        lRegNo = createCustomLabel("Car Reg No", labelColor, font);

        tID = createCustomTextField();
        tpassword = createCustomTextField();
        tFirstName = createCustomTextField();
        tSecondName = createCustomTextField();
        tEmail = createCustomTextField();
        tPhoneNumber = createCustomTextField();
        tCNIC = createCustomTextField();
        tBookingID = createCustomTextField();
        tDayForRent = createCustomTextField();
        tCarName = createCustomTextField();
        tCarColour = createCustomTextField();
        tRegNo = createCustomTextField();

        Add = new RoundButton("Add");
        Back = new RoundButton("Back");

        // Adding components to the panel
        gbc.gridx = 0; gbc.gridy = 0; backgroundPanel.add(lID, gbc);
        gbc.gridx = 1; backgroundPanel.add(tID, gbc);
        gbc.gridx = 0; gbc.gridy = 1; backgroundPanel.add(lpassword, gbc);
        gbc.gridx = 1; backgroundPanel.add(tpassword, gbc);
        gbc.gridx = 0; gbc.gridy = 2; backgroundPanel.add(lFirstName, gbc);
        gbc.gridx = 1; backgroundPanel.add(tFirstName, gbc);
        gbc.gridx = 0; gbc.gridy = 3; backgroundPanel.add(lSecondName, gbc);
        gbc.gridx = 1; backgroundPanel.add(tSecondName, gbc);
        gbc.gridx = 0; gbc.gridy = 4; backgroundPanel.add(lEmail, gbc);
        gbc.gridx = 1; backgroundPanel.add(tEmail, gbc);
        gbc.gridx = 0; gbc.gridy = 5; backgroundPanel.add(lPhoneNumber, gbc);
        gbc.gridx = 1; backgroundPanel.add(tPhoneNumber, gbc);
        gbc.gridx = 0; gbc.gridy = 6; backgroundPanel.add(lCNIC, gbc);
        gbc.gridx = 1; backgroundPanel.add(tCNIC, gbc);
        gbc.gridx = 0; gbc.gridy = 7; backgroundPanel.add(lBookingID, gbc);
        gbc.gridx = 1; backgroundPanel.add(tBookingID, gbc);
        gbc.gridx = 0; gbc.gridy = 8; backgroundPanel.add(lDayForRent, gbc);
        gbc.gridx = 1; backgroundPanel.add(tDayForRent, gbc);
        gbc.gridx = 0; gbc.gridy = 9; backgroundPanel.add(lCarName, gbc);
        gbc.gridx = 1; backgroundPanel.add(tCarName, gbc);
        gbc.gridx = 0; gbc.gridy = 10; backgroundPanel.add(lCarColour, gbc);
        gbc.gridx = 1; backgroundPanel.add(tCarColour, gbc);
        gbc.gridx = 0; gbc.gridy = 11; backgroundPanel.add(lRegNo, gbc);
        gbc.gridx = 1; backgroundPanel.add(tRegNo, gbc);
        gbc.gridx = 0; gbc.gridy = 12; backgroundPanel.add(Add, gbc);
        gbc.gridx = 1; backgroundPanel.add(Back, gbc);

        // Set ActionListeners
        Add.addActionListener(this);
        Back.addActionListener(this);

        // Add background panel to frame
        this.add(backgroundPanel);
        this.setVisible(true);
    }

    private JLabel createCustomLabel(String text, Color color, Font font) {
        JLabel label = new JLabel(text);
        label.setForeground(color);
        label.setFont(font);
        return label;
    }

    private JTextField createCustomTextField() {
        JTextField textField = new JTextField(20);
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        return textField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Add) {
            // Example: Validate input
            String id = tID.getText();
            String firstName = tFirstName.getText();
            if (id.isEmpty() || firstName.isEmpty()) {
                JOptionPane.showMessageDialog(null, "ID and First Name are required!");
            } else {
                // Placeholder: Simulate adding customer data to the database
                JOptionPane.showMessageDialog(null, "Customer added successfully!\n"
                        + "ID: " + id + "\nFirst Name: " + firstName);
            }
        } else if (e.getSource() == Back) {
            // Placeholder: Simulate navigation back to previous menu
            this.dispose();
            new Admin();

        }
    }

    // Inner class for background panel
    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel() {
            try {
                backgroundImage = new ImageIcon("b6.png").getImage();
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

    // Inner class for rounded buttons
    class RoundButton extends JButton {
        public RoundButton(String text) {
            super(text);
            setOpaque(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setContentAreaFilled(false);
            setForeground(Color.WHITE);
            setFont(new Font("Arial", Font.BOLD, 14));
            setPreferredSize(new Dimension(100, 40)); // Set a fixed size
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            if (getModel().isPressed()) {
                g2.setColor(new Color(70, 75, 180, 119)); // Darker shade when pressed
            } else {
                g2.setColor(new Color(68, 72, 170, 157)); // Lighter shade
            }
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20); // Rounded corners
            super.paintComponent(g2);
            g2.dispose();
        }
    }

    public static void main(String[] args) {
        new AddCustomer();
    }
}
