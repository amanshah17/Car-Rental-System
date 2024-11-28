import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class UpdateCustomer extends JFrame implements ActionListener {
    private JLabel lID, CarNo, CarReg;
    private JTextField tID, CarNoText, CarRegText;
    private JButton update, back;
    private JLabel background;

    public UpdateCustomer() {
        super("Update Customer Car");
        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        ImageIcon bgImage = new ImageIcon("b6.png");
        background = new JLabel(bgImage);
        background.setLayout(new GridBagLayout());
        this.setContentPane(background);

        // Create and configure labels
        lID = new JLabel("ID:");
        CarNo = new JLabel("Car Name:");
        CarReg = new JLabel("Car Reg:");
        lID.setForeground(Color.WHITE);
        CarNo.setForeground(Color.WHITE);
        CarReg.setForeground(Color.WHITE);

        // Create larger text fields
        tID = new JTextField(20);
        CarNoText = new JTextField(20);
        CarRegText = new JTextField(20);

        // Create custom rounded buttons with background color
        update = new RoundedButton("Update");
        back = new RoundedButton("Back");
        update.setBackground(new Color(64, 68, 156, 178)); // Blue
        back.setBackground(new Color(68, 72, 170, 192));     // Orange
        update.setForeground(Color.WHITE);
        back.setForeground(Color.WHITE);

        // Use GridBagLayout for centering components
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15); // Add padding
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        background.add(lID, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        background.add(tID, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        background.add(CarNo, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        background.add(CarNoText, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        background.add(CarReg, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        background.add(CarRegText, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false); // Make panel transparent
        buttonPanel.add(update);
        buttonPanel.add(back);
        background.add(buttonPanel, gbc);

        // Add ActionListeners
        update.addActionListener(this);
        back.addActionListener(this);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == update) {
            String ID = tID.getText();
            String CarNo = CarNoText.getText();
            String RegNo = CarRegText.getText();

            AdminOperations ao = new AdminOperations();
            boolean found = false;
            found = ao.updateCar(ID, CarNo, RegNo);

            if (found) {
                JOptionPane.showMessageDialog(this, "Car updated successfully");
            } else {
                JOptionPane.showMessageDialog(this, "No such ID found");
            }
        } else if (e.getSource() == back) {
            this.dispose();
            new Admin();
        }
    }

    // Custom Button Class for Rounded Corners
    class RoundedButton extends JButton {
        public RoundedButton(String text) {
            super(text);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setPreferredSize(new Dimension(150, 40)); // Larger button size
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20); // Adjust corner radius here
            super.paintComponent(g2);
            g2.dispose();
        }

        @Override
        public void paintBorder(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getForeground());
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20); // Adjust corner radius here
            g2.dispose();
        }

        @Override
        public boolean contains(int x, int y) {
            int width = getWidth();
            int height = getHeight();
            return new java.awt.geom.RoundRectangle2D.Float(0, 0, width, height, 20, 20).contains(x, y);
        }
    }

    public static void main(String[] args) {
        new UpdateCustomer();
    }
}
