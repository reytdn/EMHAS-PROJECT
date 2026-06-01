import javax.swing.*;
import java.awt.*;

public class MAINMENUGUI extends JFrame {
    private MAINSYSTEM mainsystem;

    public MAINMENUGUI() {
        mainsystem = new MAINSYSTEM();
        setTitle("EMHAS");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel title = new JLabel("<html><center>Welcome to <br> <font color='red'>MEDI</font> <font color='blue'>RUSH!</center></html>", SwingConstants.CENTER);
        title.setFont(new Font("Helvetica", Font.BOLD, 30));

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        JButton adminBtn = new JButton("ADMIN ROLE");
        JButton userBtn = new JButton("USER ROLE");
        JButton exitBtn = new JButton("EXIT");

        // Admin login
        adminBtn.addActionListener(e -> new LOGINFORM(this, mainsystem, "Admin"));

        // User login with profession selection
        userBtn.addActionListener(e -> {
            String[] professions = {"Medical Technician", "ER Physician", "ER Nurse", "Paramedic"};
            String profession = (String) JOptionPane.showInputDialog(this, "Select Profession:", "User Login",
                    JOptionPane.QUESTION_MESSAGE, null, professions, professions[0]);
            if (profession != null) new LOGINFORM(this, mainsystem, profession);
        });

        exitBtn.addActionListener(e -> System.exit(0));

        buttonPanel.add(adminBtn);
        buttonPanel.add(userBtn);
        buttonPanel.add(exitBtn);

        add(title, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MAINMENUGUI::new);
    }
}
