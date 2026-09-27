package Travel.Tourism.System;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.sql.PreparedStatement;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class SignUp extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JPasswordField textField_2;
    private JTextField textField_3;
    private JButton b1, b2;
    private JComboBox<String> comboBox;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                SignUp frame = new SignUp();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public SignUp() {

        setTitle("Create Account");
        setBounds(600, 250, 700, 406);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(Color.WHITE);
        contentPane.setLayout(null);
        setContentPane(contentPane);


        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(
                new LineBorder(new Color(128, 128, 0), 2),
                "Create-Account",
                TitledBorder.LEADING,
                TitledBorder.TOP,
                null,
                new Color(34, 139, 34)
        ));
        panel.setBounds(31, 30, 640, 310);
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);
        contentPane.add(panel);


        JLabel lblUsername = new JLabel("Username :");
        lblUsername.setForeground(Color.DARK_GRAY);
        lblUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblUsername.setBounds(65, 55, 100, 26);
        panel.add(lblUsername);

        textField = new JTextField();
        textField.setBounds(230, 60, 148, 20);
        panel.add(textField);


        JLabel lblName = new JLabel("Name :");
        lblName.setForeground(Color.DARK_GRAY);
        lblName.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblName.setBounds(65, 92, 100, 26);
        panel.add(lblName);

        textField_1 = new JTextField();
        textField_1.setBounds(230, 97, 148, 20);
        panel.add(textField_1);


        JLabel lblPassword = new JLabel("Password :");
        lblPassword.setForeground(Color.DARK_GRAY);
        lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblPassword.setBounds(65, 129, 100, 26);
        panel.add(lblPassword);

        textField_2 = new JPasswordField();
        textField_2.setBounds(230, 134, 148, 20);
        panel.add(textField_2);


        JLabel lblSecurityQuestion = new JLabel("Security Question :");
        lblSecurityQuestion.setForeground(Color.DARK_GRAY);
        lblSecurityQuestion.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblSecurityQuestion.setBounds(65, 166, 155, 26);
        panel.add(lblSecurityQuestion);

        comboBox = new JComboBox<>();
        comboBox.setModel(new DefaultComboBoxModel<>(
                new String[]{
                        "Your NickName?",
                        "Your Lucky Number?",
                        "Your child SuperHero?",
                        "Your childhood Name?"
                }
        ));
        comboBox.setBounds(230, 171, 148, 20);
        panel.add(comboBox);


        JLabel lblAnswer = new JLabel("Answer :");
        lblAnswer.setForeground(Color.DARK_GRAY);
        lblAnswer.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblAnswer.setBounds(65, 203, 100, 26);
        panel.add(lblAnswer);

        textField_3 = new JTextField();
        textField_3.setBounds(230, 208, 148, 20);
        panel.add(textField_3);


        b1 = new JButton("Create");
        b1.setFont(new Font("Tahoma", Font.BOLD, 13));
        b1.setBounds(100, 255, 100, 30);
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        panel.add(b1);


        b2 = new JButton("Back");
        b2.setFont(new Font("Tahoma", Font.BOLD, 13));
        b2.setBounds(260, 255, 100, 30);
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        b2.addActionListener(this);
        panel.add(b2);


        URL imageUrl = ClassLoader.getSystemResource(
                "icons/SignUp.png"
        );

        if (imageUrl != null) {

            ImageIcon c1 = new ImageIcon(imageUrl);

            Image i1 = c1.getImage().getScaledInstance(
                    200,
                    200,
                    Image.SCALE_SMOOTH
            );

            ImageIcon i2 = new ImageIcon(i1);

            JLabel l6 = new JLabel(i2);
            l6.setBounds(420, 55, 200, 200);

            panel.add(l6);

        } else {


            JLabel imageError = new JLabel("Image not found");
            imageError.setForeground(Color.RED);
            imageError.setFont(new Font("Tahoma", Font.BOLD, 12));
            imageError.setBounds(470, 140, 120, 30);
            panel.add(imageError);

            System.out.println(
                    "ERROR: SignUp.png not found at " +
                            "icons/SignUp.png"
            );
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == b1) {

            String username = textField.getText().trim();
            String name = textField_1.getText().trim();
            String password = new String(textField_2.getPassword());
            String question = (String) comboBox.getSelectedItem();
            String answer = textField_3.getText().trim();


            if (username.isEmpty() ||
                    name.isEmpty() ||
                    password.isEmpty() ||
                    answer.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please fill all the fields.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            try {

                Conn con = new Conn();

                String sql =
                        "INSERT INTO account " +
                                "(username, name, password, question, answer) " +
                                "VALUES (?, ?, ?, ?, ?)";

                PreparedStatement st = con.c.prepareStatement(sql);

                st.setString(1, username);
                st.setString(2, name);
                st.setString(3, password);
                st.setString(4, question);
                st.setString(5, answer);

                int i = st.executeUpdate();

                if (i > 0) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Account Created Successfully!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE
                    );


                    textField.setText("");
                    textField_1.setText("");
                    textField_2.setText("");
                    textField_3.setText("");


                    setVisible(false);
                    new Login().setVisible(true);
                }

                st.close();

            } catch (Exception e) {

                e.printStackTrace();

                JOptionPane.showMessageDialog(
                        this,
                        "Error creating account:\n" + e.getMessage(),
                        "Database Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }


        if (ae.getSource() == b2) {

            setVisible(false);
            new Login().setVisible(true);
        }
    }
}
