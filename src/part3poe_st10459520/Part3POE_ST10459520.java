/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package part3poe_st10459520;

/**
 *
 * @author RC_Student_Lab
 */
import javax.swing.JOptionPane;

public class Part3POE_ST10459520 {
    private static String registeredUsername = "";
    private static String registeredPassword = "";
    private static String registeredCellphoneNumber = "";
    private static String registeredFirstName = "";
    private static String registeredSurname = "";

    public static void main(String[] args) {
        Login input = new Login();
        Messages messageSystem = new Messages();
        boolean running = true;

        while (running) {
            String[] options = {"Sign Up", "Sign In", "Exit"};
            int choice = JOptionPane.showOptionDialog(
                    null,
                    "Welcome to QuickChat\nPlease choose an option:",
                    "QuickChat",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            switch (choice) {
                case 0 -> signUp();
                case 1 -> signIn(messageSystem);
                case 2, JOptionPane.CLOSED_OPTION -> {
                    running = false;
                    JOptionPane.showMessageDialog(null, "Goodbye!");
                }
            }
        }
    }

    // ---------------------- SIGN UP ----------------------
    public static void signUp() {
    String firstName;
    String surname;
    String userName;
    String userPassword;
    String userCellphoneNumber;
    Login input = new Login();

    // Full Name
    do {
        firstName = JOptionPane.showInputDialog("Enter your first name:");
        if (firstName == null) return;

        if (firstName.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "First name cannot be empty.");
        }
    } while (firstName.trim().isEmpty());
    registeredFirstName = firstName;

    // Surname
    do {
        surname = JOptionPane.showInputDialog("Enter your surname:");
        if (surname == null) return;

        if (surname.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Surname cannot be empty.");
        }
    } while (surname.trim().isEmpty());
    registeredSurname = surname;

    // Username
    do {
        userName = JOptionPane.showInputDialog("Enter your username");
        if (userName == null) return;

        if (!input.checkUserName(userName)) {
            JOptionPane.showMessageDialog(
                null,
                "Invalid username. Username must contain '_' and be at most 5 characters long."
            );
        }
    } while (!input.checkUserName(userName));
    registeredUsername = userName;

    // Password
    do {
        userPassword = JOptionPane.showInputDialog("Enter your password");
        if (userPassword == null) return;

        if (!input.checkPasswordComplexity(userPassword)) {
            JOptionPane.showMessageDialog(
                null,
                "Weak password! Must have 8+ chars, 1 uppercase, 1 number, and 1 special char."
            );
        }
    } while (!input.checkPasswordComplexity(userPassword));
    registeredPassword = userPassword;

    // Cellphone number
    do {
        userCellphoneNumber = JOptionPane.showInputDialog(
            "Enter your cellphone number (e.g., +27612345678)"
        );
        if (userCellphoneNumber == null) return;

        if (!input.checkCellPhoneNumber(userCellphoneNumber)) {
            JOptionPane.showMessageDialog(
                null,
                "Invalid cellphone number! Must start with +27 and have 11 digits."
            );
        }
    } while (!input.checkCellPhoneNumber(userCellphoneNumber));
    registeredCellphoneNumber = userCellphoneNumber;

    JOptionPane.showMessageDialog(
        null,
        "Registration successful!\n\n" +
        "Full Name: " + registeredFirstName + "\n" +
        "Surname: " + registeredSurname + "\n" +
        "Username: " + registeredUsername + "\n" +
        "Cellphone: " + registeredCellphoneNumber
    );
}


    // ---------------------- SIGN IN ----------------------
    public static void signIn(Messages messageSystem) {
        if (registeredUsername.isEmpty() || registeredPassword.isEmpty()) {
            JOptionPane.showMessageDialog(null, "You need to sign up before logging in.");
            return;
        }

        String userName = JOptionPane.showInputDialog("Enter your username to login");
        String userPassword = JOptionPane.showInputDialog("Enter your password to login");

        if (userName.equals(registeredUsername) && userPassword.equals(registeredPassword)) {
            JOptionPane.showMessageDialog(null, "Welcome back, " + userName + "!");

            boolean loggedIn = true;
            while (loggedIn) {
                String[] options = {"Send Messages", "Message Management", "Exit"};
                int choices = JOptionPane.showOptionDialog(
                        null,
                        "Welcome To QuickChat",
                        "QuickChat",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        options,
                        options[0]
                );

                switch (choices) {
                    case 0 -> messageSystem.sendMessage();  // Updated to sendMessage()
                    case 1 -> messageSystem.messageManagement();
                    case 2, JOptionPane.CLOSED_OPTION -> loggedIn = false;
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Your credentials are incorrect!");
        }
    }
}
