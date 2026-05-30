import javax.swing.*;
import java.awt.*;

public class LOGINFORM {
    private int attempts = 3;

    public LOGINFORM(JFrame parent, MAINSYSTEM mainsystem, String role) {
        // ✅ Check if there are any registered users for this profession
        if (!mainsystem.CHECK_ANY_PROFESSION_REGISTERED(role)) {
            JOptionPane.showMessageDialog(parent,
                    "No registered user of " + role + " in the system.");
            return; // stop here
        }

        while (attempts > 0) {
            JPanel panel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);

            JTextField userField = new JTextField(15);
            JPasswordField passField = new JPasswordField(15);

            JButton eyeBtn = new JButton("👁");
            eyeBtn.setContentAreaFilled(false);
            eyeBtn.setBorderPainted(false);
            eyeBtn.setFocusPainted(false);
            eyeBtn.setOpaque(false);
            eyeBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

            passField.setEchoChar('\u2022');
            eyeBtn.addActionListener(e -> {
                if (passField.getEchoChar() == '\u2022') {
                    passField.setEchoChar((char) 0); // show password
                } else {
                    passField.setEchoChar('\u2022'); // hide password
                }
            });

            gbc.gridx = 0; gbc.gridy = 0; panel.add(new JLabel("Username:"), gbc);
            gbc.gridx = 1; panel.add(userField, gbc);
            gbc.gridx = 0; gbc.gridy = 1; panel.add(new JLabel("Password:"), gbc);
            gbc.gridx = 1; panel.add(passField, gbc);
            gbc.gridx = 2; panel.add(eyeBtn, gbc);

            int result = JOptionPane.showConfirmDialog(parent, panel, role + " Login",
                    JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION) {
                String username = userField.getText().trim();
                String password = new String(passField.getPassword());

                if (mainsystem.TEST_LOGIN(username, password, role)) {
                    String fullName = mainsystem.GET_FULLNAME(username, role);

                    // ✅ store the actual user context
                    mainsystem.SET_CURRENT_USER(username, role, fullName);

                    JOptionPane.showMessageDialog(parent, "Login Successful!\nWelcome " + fullName);
                    EMHASAPPGUI.showMenu(role, mainsystem, fullName, role);
                    break;


                } else {
                    attempts--;
                    JOptionPane.showMessageDialog(parent,
                            "Invalid Credentials. Attempts remaining: " + attempts);
                }
            } else break;
        }

        if (attempts == 0) {
            JOptionPane.showMessageDialog(parent, "Too Many Failed Attempts.");
        }
    }
}
