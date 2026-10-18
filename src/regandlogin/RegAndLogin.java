/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package regandlogin;

import java.util.Scanner;

public class RegAndLogin {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("CHATAPP REGISTRATION PAGE");

        System.out.println("Enter first name");
        String firstName = input.nextLine();

        System.out.println("Enter last name");
        String lastName = input.nextLine();

        //USERNAME LOOP - must have _ and <=5 chars
        String username = "";
        while (true) {
            System.out.println("Enter username (must contain _ and max 5 chars)");
            username = input.nextLine();
            if (username.contains("_") && username.length() <= 5) {
                System.out.println("Username successfully captured");
                break;
            } else {
                System.out.println("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than 5 characters in length.");
            }
        }

        //PASSWORD LOOP - 8 chars, Capital, Number, Special char
        String password = "";
        while (true) {
            System.out.println("Enter password (8+ characters, uppercase, a number, va special character)");
            password = input.nextLine();
            boolean hasUpper = password.matches(".*[A-Z].*");
            boolean hasDigit = password.matches(".*[0-9].*");
            boolean hasSpecial = password.matches(".*[!@#$%^&*()_+\\-=:;\"'<>,.?].*");

            if (password.length() >= 8 && hasUpper && hasDigit && hasSpecial) {
                System.out.println("Password successfully captured");
                break;
            } else {
                System.out.println("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number and a special character.");
            }
        }

        //CELLPHONE LOOP - must start with +27 and length 12
        String cellNumber = "";
        while (true) {
            System.out.println("Enter cellphone number (must start with +27, e.g., +27831234567)");
            cellNumber = input.nextLine();
            if (cellNumber.startsWith("+27") && cellNumber.length() == 12) {
                System.out.println("Cell phone number successfully added.");
                break;
            } else {
                System.out.println("Cell phone number incorrectly formatted or does not contain international code.");
            }
        }

        //create the user
        Login user = new Login(firstName, lastName, username, password, cellNumber);
        System.out.println(user.registerUser());

        // Login part
        System.out.println("\n--- LOGIN ---");
        System.out.println("Enter username to login");
        String loginUsername = input.nextLine();
        System.out.println("Enter password to login");
        String loginPassword = input.nextLine();

        user.userLogin(loginUsername, loginPassword);
        String loginStatus = user.returnLoginStatus(firstName, lastName);
        System.out.println(loginStatus);

        // ===== PART 2 - QUICKCHAT - Added after your Part 1 =====
        // Only continue if login was successful
        if (loginStatus.toLowerCase().contains("failed") || loginStatus.toLowerCase().contains("incorrect") || loginStatus.toLowerCase().contains("not")) {
            System.out.println("Login failed. Cannot proceed to QuickChat.");
            input.close();
            return;
        }

        System.out.println("\nWelcome to QuickChat.");

        int option = 0;
        do {
            System.out.println("\nChoose an option:\n1) Send Messages\n2) Show recently sent messages\n3) Quit");
            try {
                option = Integer.parseInt(input.nextLine());
            } catch (Exception e) {
                option = 0;
            }

            if (option == 1) {
                System.out.println("How many messages do you wish to enter?");
                int numMessages = Integer.parseInt(input.nextLine());

                for (int i = 0; i < numMessages; i++) {
                    System.out.println("\nMessage " + (i + 1) + ":");
                    System.out.print("Enter Recipient (+27...): ");
                    String recipient = input.nextLine();
                    System.out.print("Enter Message (max 250 chars): ");
                    String text = input.nextLine();

                    Message msg = new Message(recipient, text, i);

                    System.out.println(msg.checkRecipientCell());
                    if (!msg.checkRecipientCell().contains("successfully captured")) {
                        System.out.println("Invalid recipient, try again.");
                        i--;
                        continue;
                    }

                    System.out.println(msg.checkMessageLength());
                    if (msg.checkMessageLength().contains("exceeds")) {
                        System.out.println("Message too long, try again.");
                        i--;
                        continue;
                    }

                    msg.createMessageHash();
                    System.out.println("MessageID: " + msg.getMessageID());
                    System.out.println("Hash: " + msg.createMessageHash());

                    System.out.println("Choose: 1) Send 2) Disregard 3) Store");
                    int choice = Integer.parseInt(input.nextLine());
                    System.out.println(msg.SentMessage(choice));

                    if (choice == 1) {
                        System.out.println("\n--- FULL DETAILS ---");
                        System.out.println(msg.printMessages());
                    }
                }
                System.out.println("\nTotal messages sent: " + new Message("", "", 0).returnTotalMessages());

            } else if (option == 2) {
                System.out.println("Coming Soon - Part 3 will show all messages.");
            }

        } while (option != 3);

        System.out.println("Goodbye!");
        input.close();
    }
}