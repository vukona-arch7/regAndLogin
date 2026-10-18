/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package regandlogin;

/**
 *
 * @author Baloyi
 */
public class Login {
    String firstName;
    String lastName;
    String username;
    String password;
    String cellphoneNumber;
    boolean successfulLogin;

    public Login() {
    }

    public Login(String firstName, String lastName, String username, String password, String cellphoneNumber) {
        //Constructor
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.cellphoneNumber = cellphoneNumber;
    }
    //Method that checks if the username meets the conditions
    public boolean checkUserName(String username) { 
        if (username == null) return false;
        return username.contains("_") && username.length() <= 5;
    }
    //Method thata checks the password complexity
    public boolean checkPasswordComplexity(String password) {
    if(password == null) return false;
    boolean hasUpper = !password.equals(password.toLowerCase());
    boolean hasDigit = password.matches(".*\\d.*");
    boolean hasSpecial = !password.matches("[A-Za-z0-9]*"); // anything not letter/number
    boolean longEnough = password.length() >= 8;
    
    return longEnough && hasUpper && hasDigit && hasSpecial;
}
    //Method that checks the cellphone number
    public boolean checkCellPhoneNumber(String cellphoneNumber) { 
        if (cellphoneNumber == null) return false;
        return cellphoneNumber.startsWith("+27") && cellphoneNumber.length() <= 13;
    }

    //Method that registers the user if the inputed details meet the conditions
    public String registerUser() {
        if (!checkUserName(username)) {
            return "Username is incorrectly formatted; please make sure that your username contains an underscore and is no more than 5 characters.";
        }
        if (!checkPasswordComplexity(password)) { 
            return "Password is incorrectly formatted; please make sure that your password contains at least eight characters, a capital letter, a number and a special character.";
        }
        if (!checkCellPhoneNumber(cellphoneNumber)) {
            return "Cellphone number is incorrectly formatted or does not contain international code.";
        }
        return "User is registered successfully";
    }

    //Method that registers the user
    public String registerUser(String username, String password, String cellphoneNumber) {
        this.username = username;
        this.password = password;
        this.cellphoneNumber = cellphoneNumber;
        return registerUser();
    }
    //User Login method
    public boolean userLogin(String enteredUsername, String enteredPassword) {
        if (username == null || password == null) return false;
        if (enteredUsername == null || enteredPassword == null) return false;
        successfulLogin = username.equals(enteredUsername) && password.equals(enteredPassword);
        return successfulLogin;
    }
    //Returns the login status and displays the welcome message
    public String returnLoginStatus(String firstName, String lastName) { 
        if (successfulLogin) {
            return "Welcome " + firstName + " " + lastName + "," + " it is great to see you again";
        } else {
            return "Username or Password is incorrect, please try again.";
        }
    }
}