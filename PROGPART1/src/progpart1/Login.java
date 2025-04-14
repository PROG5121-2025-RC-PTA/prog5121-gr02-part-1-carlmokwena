import javax.swing.JOptionPane;
import java.util.regex.Pattern;

public class Login {
    private String storedUsername;
    private String storedPassword;
    private String storedPhone;

    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity(String password) {
        return password.matches("^(?=.[A-Z])(?=.\\d)(?=.*[@#$%^&+=!]).{8,}$");
    }

    public boolean checkCellPhoneNumber(String cellNumber) {
        return cellNumber.matches("^\\+\\d{10}$");
    }

    public String registerUser() {
        String username = JOptionPane.showInputDialog("Enter a username:");
        if (!checkUserName(username)) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";
        }

        String password = JOptionPane.showInputDialog("Enter a password:");
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }

        String cell = JOptionPane.showInputDialog("Enter your cellphone number (with +country code):");
        if (!checkCellPhoneNumber(cell)) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }

        storedUsername = username;
        storedPassword = password;
        storedPhone = cell;

        return "Registration successful!";
    }

    public boolean loginUser() {
        String username = JOptionPane.showInputDialog("Enter your username:");
        String password = JOptionPane.showInputDialog("Enter your password:");
        return username.equals(storedUsername) && password.equals(storedPassword);
    }

    public String returnLoginStatus(boolean loginStatus, String firstName, String lastName) {
        if (loginStatus) {
            return "Welcome " + firstName + ", " + lastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }

    public static void main(String[] args) {
        Login login = new Login();
        JOptionPane.showMessageDialog(null, login.registerUser());
        boolean success = login.loginUser();
        String firstName = JOptionPane.showInputDialog("Enter your first name:");
        String lastName = JOptionPane.showInputDialog("Enter your last name:");
        JOptionPane.showMessageDialog(null, login.returnLoginStatus(success, firstName, lastName));
    }
}
