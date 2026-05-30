import javax.swing.*;
import java.awt.*;

public class EMHASREGISTERUSERGUI extends JFrame {
    private MAINSYSTEM mainsystem;

    public EMHASREGISTERUSERGUI(MAINSYSTEM mainsystem) {
        this.mainsystem = mainsystem;
        setTitle("Register User");

        // Portrait window
        setSize(300, 500);
        setLocationRelativeTo(null);

        // GridLayout for clean vertical alignment
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));

        JTextField fnameField = new JTextField(15);
        JTextField lnameField = new JTextField(15);

        // ✅ MI scroll box with empty option + A–Z
        String[] letters = new String[27];
        letters[0] = ""; // blank option
        for (int i = 1; i <= 26; i++) {
            letters[i] = String.valueOf((char) ('A' + (i - 1)));
        }
        JComboBox<String> miBox = new JComboBox<>(letters);

        String[] professions = {"Medical Technician", "ER Physician", "ER Nurse", "Paramedic"};
        JComboBox<String> professionBox = new JComboBox<>(professions);
        JTextField usernameField = new JTextField(15);
        JPasswordField passwordField = new JPasswordField(15);

        panel.add(new JLabel("First Name:")); panel.add(fnameField);
        panel.add(new JLabel("Last Name:")); panel.add(lnameField);
        panel.add(new JLabel("MI:")); panel.add(miBox);
        panel.add(new JLabel("Profession:")); panel.add(professionBox);
        panel.add(new JLabel("Username:")); panel.add(usernameField);
        panel.add(new JLabel("Password:")); panel.add(passwordField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Register User", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            // ✅ Validation
            String fname = fnameField.getText().trim();
            String lname = lnameField.getText().trim();
            String mi = (String) miBox.getSelectedItem(); // can be blank or one letter
            String profession = (String) professionBox.getSelectedItem();
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            if (fname.isEmpty() || lname.isEmpty() ||
                username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields must be filled in!");
                return;
            }

            // ✅ Register user if validation passes
            boolean success = mainsystem.REGISTER_USER(
                fname,
                lname,
                mi, // may be "" or A–Z
                profession,
                username,
                password
            );
            JOptionPane.showMessageDialog(this, success ? "User Registered!" : "Error registering user.");
        }
    }
}
