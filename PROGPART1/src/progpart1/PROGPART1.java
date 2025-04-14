import javax.swing.JOptionPane;
import java.util.regex.Pattern;

public class PROGPART1 {
    // Private fields
    private String username;
    private String password;
    private String phoneNumber;

    // Constructor
    public PROGPART1(String username, String password, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    // VALIDATION METHODS

    public boolean checkUserName() {
        return username.contains("_") && username.length() <= 5;
    }
    public boolean checkPasswordComplexity() {
        boolean hasUpper = Pattern.compile("[A-Z]").matcher(password).find();
        boolean hasDigit = Pattern.compile("[0-9]").matcher(password).find();
        boolean hasSpecial = Pattern.compile("[!@#$%^&*]").matcher(password).find();
        return password.length() >= 8 && hasUpper && hasDigit && hasSpecial;
    }

    public boolean checkCellPhoneNumber() {
        return phoneNumber.matches("^\\+27\\d{9}$");
    }

    //REGISTRATION & LOGIN METHODS 

    public String registerUser() {
        if (!checkUserName()) {
            return "❌ Username must contain an underscore (_) and be 5 characters or less.";
        }
        if (!checkPasswordComplexity()) {
            return "❌ Password must be at least 8 characters long and include:\n - A capital letter\n - A number\n - A special character (!@#$%^&*)";
        }
        if (!checkCellPhoneNumber()) {
            return "❌ Cellphone number must start with +27 and be followed by 9 digits.";
        }
        return "✅ Registration successful!";
    }

    public boolean loginUser(String inputUsername, String inputPassword) {
        return inputUsername.equals(username) && inputPassword.equals(password);
    }

    public String returnLoginStatus(String inputUsername, String inputPassword) {
        if (loginUser(inputUsername, inputPassword)) {
            return "✅ Welcome back, " + inputUsername + "! Login successful.";
        } else {
            return "❌ Login failed. Username or password is incorrect.";
        }
    }

    // Main Method

    public static void main(String[] args) {
        // Registration
        String username = JOptionPane.showInputDialog(null,
                "Enter your username:\n(Include _ and max 5 characters)",
                "User Registration",
                JOptionPane.QUESTION_MESSAGE);

        String password = JOptionPane.showInputDialog(null,
                "Enter your password:\n(8+ characters, 1 capital, 1 number, 1 special character)",
                "User Registration",
                JOptionPane.QUESTION_MESSAGE);

        String phoneNumber = JOptionPane.showInputDialog(null,
                "Enter your cellphone number:\n(Format: +27 followed by 9 digits)",
                "User Registration",
                JOptionPane.QUESTION_MESSAGE);

        PROGPART1 user = new PROGPART1(username, password, phoneNumber);

        // Registration Feedback
        String registrationResult = user.registerUser();
        int icon = registrationResult.startsWith("✅") ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE;
        JOptionPane.showMessageDialog(null,
                registrationResult,
                "Registration Result",
                icon);

        // Login Prompt
        String loginUsername = JOptionPane.showInputDialog(null,
                "Enter your username to log in:",
                "User Login",
                JOptionPane.QUESTION_MESSAGE);

        String loginPassword = JOptionPane.showInputDialog(null,
                "Enter your password to log in:",
                "User Login",
                JOptionPane.QUESTION_MESSAGE);

        // Login Feedback
        String loginStatus = user.returnLoginStatus(loginUsername, loginPassword);
        int loginIcon = loginStatus.startsWith("✅") ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE;
        JOptionPane.showMessageDialog(null,
                loginStatus,
                "Login Status",
                loginIcon);
    }
}
