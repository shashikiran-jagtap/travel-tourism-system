
package Travel.Tourism.System;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class AddCustomer extends JFrame {

    private JPanel contentPane;

    private JTextField t1;
    private JTextField t2;
    private JTextField t3;
    private JTextField t5;
    private JTextField t6;
    private JTextField t7;
    private JTextField t8;

    private JComboBox<String> comboBox;

    private JRadioButton r1;
    private JRadioButton r2;

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AddCustomer frame = new AddCustomer("");
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public AddCustomer(String username) {

        setTitle("Add Customer");
        setBounds(580, 220, 850, 550);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setLayout(null);
        setContentPane(contentPane);

        URL imageUrl = ClassLoader.getSystemResource(
                "icons/newcustomer.jpg"
        );

        if (imageUrl != null) {

            ImageIcon i1 = new ImageIcon(imageUrl);

            Image i3 = i1.getImage().getScaledInstance(
                    450,
                    500,
                    Image.SCALE_SMOOTH
            );

            ImageIcon i2 = new ImageIcon(i3);

            JLabel l1 = new JLabel(i2);
            l1.setBounds(450, 40, 450, 420);

            contentPane.add(l1);

        } else {

            JLabel imageError = new JLabel("Image not found");
            imageError.setForeground(Color.RED);
            imageError.setFont(new Font("Tahoma", Font.BOLD, 14));
            imageError.setBounds(560, 220, 150, 30);

            contentPane.add(imageError);

            System.out.println(
                    "ERROR: newcustomer.jpg not found!"
            );
            System.out.println(
                    "Expected path: Travel/Tourism/System/icons/newcustomer.jpg"
            );
        }

        JLabel lblName = new JLabel("NEW CUSTOMER FORM");
        lblName.setFont(new Font("Yu Mincho", Font.PLAIN, 20));
        lblName.setBounds(118, 11, 260, 53);
        contentPane.add(lblName);

        // ---------------------------------------------------------
        // Username
        // ---------------------------------------------------------

        JLabel l3 = new JLabel("Username :");
        l3.setBounds(35, 70, 200, 14);
        contentPane.add(l3);

        t7 = new JTextField();
        t7.setBounds(271, 70, 150, 20);
        contentPane.add(t7);

        JLabel lblId = new JLabel("ID :");
        lblId.setBounds(35, 110, 200, 14);
        contentPane.add(lblId);

        comboBox = new JComboBox<>(
                new String[]{
                        "Passport",
                        "Aadhar Card",
                        "Voter Id",
                        "Driving license"
                }
        );

        comboBox.setBounds(271, 110, 150, 20);
        contentPane.add(comboBox);

        JLabel l2 = new JLabel("Number :");
        l2.setBounds(35, 150, 200, 14);
        contentPane.add(l2);

        t1 = new JTextField();
        t1.setBounds(271, 150, 150, 20);
        contentPane.add(t1);

        JLabel lblName1 = new JLabel("Name :");
        lblName1.setBounds(35, 190, 200, 14);
        contentPane.add(lblName1);

        t2 = new JTextField();
        t2.setBounds(271, 190, 150, 20);
        contentPane.add(t2);


        JLabel lblGender = new JLabel("Gender :");
        lblGender.setBounds(35, 230, 200, 14);
        contentPane.add(lblGender);

        r1 = new JRadioButton("Male");
        r1.setFont(new Font("Raleway", Font.BOLD, 14));
        r1.setBackground(Color.WHITE);
        r1.setBounds(271, 230, 80, 20);
        contentPane.add(r1);

        r2 = new JRadioButton("Female");
        r2.setFont(new Font("Raleway", Font.BOLD, 14));
        r2.setBackground(Color.WHITE);
        r2.setBounds(350, 230, 100, 20);
        contentPane.add(r2);


        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(r1);
        genderGroup.add(r2);

        JLabel lblCountry = new JLabel("Country :");
        lblCountry.setBounds(35, 270, 200, 14);
        contentPane.add(lblCountry);

        t3 = new JTextField();
        t3.setBounds(271, 270, 150, 20);
        contentPane.add(t3);

        JLabel lblAddress = new JLabel("Permanent Address :");
        lblAddress.setBounds(35, 310, 200, 14);
        contentPane.add(lblAddress);

        t5 = new JTextField();
        t5.setBounds(271, 310, 150, 20);
        contentPane.add(t5);

        JLabel lblPhone = new JLabel("Phone :");
        lblPhone.setBounds(35, 350, 200, 14);
        contentPane.add(lblPhone);

        t6 = new JTextField();
        t6.setBounds(271, 350, 150, 20);
        contentPane.add(t6);

        JLabel lblEmail = new JLabel("Email :");
        lblEmail.setBounds(35, 390, 200, 14);
        contentPane.add(lblEmail);

        t8 = new JTextField();
        t8.setBounds(271, 390, 150, 20);
        contentPane.add(t8);


        if (username != null && !username.trim().isEmpty()) {

            try {

                Conn c = new Conn();

                String query =
                        "SELECT * FROM account WHERE username = ?";

                PreparedStatement pst = c.c.prepareStatement(query);

                pst.setString(1, username);

                ResultSet rs = pst.executeQuery();

                if (rs.next()) {

                    t7.setText(rs.getString("username"));
                    t2.setText(rs.getString("name"));
                }

                rs.close();
                pst.close();

            } catch (Exception e) {

                e.printStackTrace();

                JOptionPane.showMessageDialog(
                        this,
                        "Could not load account information:\n"
                                + e.getMessage(),
                        "Database Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }


        JButton btnNewButton = new JButton("Add");

        btnNewButton.setBounds(100, 430, 120, 30);
        btnNewButton.setBackground(Color.BLACK);
        btnNewButton.setForeground(Color.WHITE);

        btnNewButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                addCustomer();
            }
        });

        contentPane.add(btnNewButton);


        JButton btnExit = new JButton("Back");

        btnExit.setBounds(260, 430, 120, 30);
        btnExit.setBackground(Color.BLACK);
        btnExit.setForeground(Color.WHITE);

        btnExit.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                setVisible(false);
                dispose();
            }
        });

        contentPane.add(btnExit);
    }


    private void addCustomer() {

        String username = t7.getText().trim();
        String idType = (String) comboBox.getSelectedItem();
        String number = t1.getText().trim();
        String name = t2.getText().trim();
        String country = t3.getText().trim();
        String address = t5.getText().trim();
        String phone = t6.getText().trim();
        String email = t8.getText().trim();

        String gender = "";

        if (r1.isSelected()) {
            gender = "Male";
        } else if (r2.isSelected()) {
            gender = "Female";
        }


        if (username.isEmpty() ||
                number.isEmpty() ||
                name.isEmpty() ||
                gender.isEmpty() ||
                country.isEmpty() ||
                address.isEmpty() ||
                phone.isEmpty() ||
                email.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill all the fields.",
                    "Missing Information",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        try {

            Conn c = new Conn();

            String query =
                    "INSERT INTO customer " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pst =
                    c.c.prepareStatement(query);

            pst.setString(1, username);
            pst.setString(2, idType);
            pst.setString(3, number);
            pst.setString(4, name);
            pst.setString(5, gender);
            pst.setString(6, country);
            pst.setString(7, address);
            pst.setString(8, phone);
            pst.setString(9, email);

            int result = pst.executeUpdate();

            if (result > 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Customer Added Successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                // Clear fields
                t1.setText("");
                t2.setText("");
                t3.setText("");
                t5.setText("");
                t6.setText("");
                t8.setText("");

                r1.setSelected(false);
                r2.setSelected(false);

                setVisible(false);
                dispose();
            }

            pst.close();

        } catch (SQLException e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Error adding customer:\n" + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
