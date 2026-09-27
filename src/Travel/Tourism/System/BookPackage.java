package Travel.Tourism.System;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class BookPackage extends JFrame {

    private JPanel contentPane;
    JTextField t1;
    Choice c1;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BookPackage frame = new BookPackage("");
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public BookPackage(String username) {

        setBounds(420, 220, 1100, 450);
        setTitle("Book Package");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBackground(Color.WHITE);

        // ================= IMAGE =================
        java.net.URL imageUrl =
                ClassLoader.getSystemResource(
                        "icons/bookpackage.jpg"
                );

        if (imageUrl != null) {
            ImageIcon i1 = new ImageIcon(imageUrl);
            Image i3 = i1.getImage().getScaledInstance(
                    500, 300, Image.SCALE_DEFAULT
            );
            ImageIcon i2 = new ImageIcon(i3);

            JLabel la1 = new JLabel(i2);
            la1.setBounds(450, 50, 500, 300);
            contentPane.add(la1);
        } else {
            System.out.println(
                    "Image not found: icons/bookpackage.jpg"
            );
        }


        JLabel lblName = new JLabel("BOOK PACKAGE");
        lblName.setFont(new Font("Yu Mincho", Font.PLAIN, 20));
        lblName.setBounds(118, 11, 300, 53);
        contentPane.add(lblName);


        JLabel la2 = new JLabel("Username :");
        la2.setBounds(35, 70, 200, 14);
        contentPane.add(la2);

        JLabel l1 = new JLabel(username);
        l1.setBounds(271, 70, 200, 14);
        contentPane.add(l1);


        JLabel lblId = new JLabel("Select Package :");
        lblId.setBounds(35, 110, 200, 14);
        contentPane.add(lblId);

        c1 = new Choice();
        c1.add("Gold Package");
        c1.add("Silver Package");
        c1.add("Bronze Package");
        c1.setBounds(271, 110, 150, 30);
        contentPane.add(c1);


        JLabel la3 = new JLabel("Total Persons :");
        la3.setBounds(35, 150, 200, 14);
        contentPane.add(la3);

        t1 = new JTextField();
        t1.setText("1");
        t1.setBounds(271, 150, 150, 20);
        contentPane.add(t1);


        JLabel lbl1 = new JLabel("ID :");
        lbl1.setBounds(35, 190, 200, 14);
        contentPane.add(lbl1);

        JLabel l2 = new JLabel();
        l2.setBounds(271, 190, 200, 14);
        contentPane.add(l2);


        JLabel lbl2 = new JLabel("Number :");
        lbl2.setBounds(35, 230, 200, 14);
        contentPane.add(lbl2);

        JLabel l3 = new JLabel();
        l3.setBounds(271, 230, 200, 14);
        contentPane.add(l3);


        JLabel lbl3 = new JLabel("Phone :");
        lbl3.setBounds(35, 270, 200, 14);
        contentPane.add(lbl3);

        JLabel l4 = new JLabel();
        l4.setBounds(271, 270, 200, 14);
        contentPane.add(l4);


        JLabel lblDeposite = new JLabel("Total Price :");
        lblDeposite.setBounds(35, 310, 200, 14);
        contentPane.add(lblDeposite);

        JLabel l5 = new JLabel();
        l5.setBounds(271, 310, 200, 14);
        l5.setForeground(Color.RED);
        contentPane.add(l5);


        try {
            Conn c = new Conn();

            String query =
                    "SELECT id_type, number, phone FROM customer WHERE username = ?";

            PreparedStatement ps = c.c.prepareStatement(query);
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                l2.setText(rs.getString("id_type"));
                l3.setText(rs.getString("number"));
                l4.setText(rs.getString("phone"));
            }

            rs.close();
            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


        JButton b1 = new JButton("Check Price");

        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {

                    int persons = Integer.parseInt(t1.getText().trim());

                    if (persons <= 0) {
                        JOptionPane.showMessageDialog(
                                null,
                                "Please enter a valid number of persons."
                        );
                        return;
                    }

                    String packageName = c1.getSelectedItem();

                    int cost;

                    if (packageName.equals("Gold Package")) {
                        cost = 12000;
                    } else if (packageName.equals("Silver Package")) {
                        cost = 25000;
                    } else {
                        cost = 32000;
                    }

                    int total = cost * persons;

                    l5.setText("Rs " + total);

                } catch (NumberFormatException ex) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Please enter a valid number of persons."
                    );
                }
            }
        });

        b1.setBounds(50, 350, 120, 30);
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        contentPane.add(b1);


        JButton btnNewButton = new JButton("Book");

        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (l5.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Please check the price first."
                    );
                    return;
                }

                try {

                    int persons = Integer.parseInt(t1.getText().trim());

                    if (persons <= 0) {
                        JOptionPane.showMessageDialog(
                                null,
                                "Please enter a valid number of persons."
                        );
                        return;
                    }

                    String packageName = c1.getSelectedItem();

                    Conn c = new Conn();

                    String query =
                            "INSERT INTO bookPackage " +
                                    "VALUES (?, ?, ?, ?, ?, ?, ?)";

                    PreparedStatement ps = c.c.prepareStatement(query);

                    ps.setString(1, username);
                    ps.setString(2, packageName);
                    ps.setInt(3, persons);
                    ps.setString(4, l2.getText());
                    ps.setString(5, l3.getText());
                    ps.setString(6, l4.getText());
                    ps.setString(7, l5.getText());

                    ps.executeUpdate();
                    ps.close();

                    JOptionPane.showMessageDialog(
                            null,
                            "Package Booked Successfully"
                    );

                    dispose();

                } catch (NumberFormatException ex) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Please enter a valid number of persons."
                    );

                } catch (Exception ex) {

                    ex.printStackTrace();

                    JOptionPane.showMessageDialog(
                            null,
                            "Error booking package: " + ex.getMessage()
                    );
                }
            }
        });

        btnNewButton.setBounds(200, 350, 120, 30);
        btnNewButton.setBackground(Color.BLACK);
        btnNewButton.setForeground(Color.WHITE);
        contentPane.add(btnNewButton);


        JButton btnExit = new JButton("Back");

        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        btnExit.setBounds(350, 350, 120, 30);
        btnExit.setBackground(Color.BLACK);
        btnExit.setForeground(Color.WHITE);
        contentPane.add(btnExit);
    }
}