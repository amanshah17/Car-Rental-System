

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;



public class Display extends JFrame implements ActionListener {
    
    JButton profiles;
    JButton back;

    public Display() {
        super("View All Profiles");
        this.setSize(600, 600);
        this.setForeground(Color.BLACK);
        this.setVisible(true);
        this.setLayout(new GridLayout(2, 1));
        
    
        profiles = new JButton("Profiles");
        back = new JButton("Back");
        
        this.add(profiles);
        this.add(back);


        profiles.addActionListener(this);
        back.addActionListener(this);


        

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == profiles) {
            AdminOperations ao = new AdminOperations();
            ArrayList <Customer> customers = null;
            customers = ao.viewAllProfiles();

            if (customers.size() == 0) {
                JOptionPane.showMessageDialog(null, "No Data Found");
            }
            
            if (customers.size() >= 1) {
                for (int i = 0; i < customers.size(); i++) {
                    String details = customers.get(i).toString();
                    JOptionPane.showMessageDialog(null ,details);
                }
            }            

        }
        if (e.getSource() == back) {
            this.dispose();
            new Admin();
        }
        
    }
}

