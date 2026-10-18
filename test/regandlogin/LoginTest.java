/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package regandlogin;

import org.junit.Test;
import static org.junit.Assert.*;

public class LoginTest {

    @Test
    public void testCheckPasswordComplexity() {
        System.out.println("checkPasswordComplexity");
        Login instance = new Login();
        
        // Valid password 
        assertTrue(instance.checkPasswordComplexity("Ch&&sec@ke99!"));
        // Invalid
        assertFalse(instance.checkPasswordComplexity("password"));
    }

    @Test
    public void testCheckCellPhoneNumber() { //Testing the cellphone number
        System.out.println("checkCellPhoneNumber");
        Login instance = new Login();
        
        assertTrue(instance.checkCellPhoneNumber("+27838968976"));
        assertFalse(instance.checkCellPhoneNumber("08966553"));
    }

    @Test
    public void testRegisterUser() {  // Testing if we can register the 
        System.out.println("registerUser");
        Login instance = new Login();
        String result = instance.registerUser();
        assertNotNull(result);
        System.out.println(result);
    }

    @Test
    public void testUserLogin() {  //Testing the user login details
        System.out.println("userLogin");
        Login instance = new Login();
       
        boolean result = instance.userLogin("kyl_1", "Ch&&sec@ke99!");
        
        assertNotNull(result);
    }

    @Test
    public void testReturnLoginStatus() { //Testing if the Login  status returns something and is not empty
        System.out.println("returnLoginStatus");
        Login instance = new Login();
        
        String result = instance.returnLoginStatus("Kyle", "Smith");
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }
    
    @Test
    public void testCheckUserName() {  //Testing the username with data that meets the condition and data that does not meet the condition
        System.out.println("checkUserName");
        Login instance = new Login();
        assertTrue(instance.checkUserName("kyl_1"));
        assertFalse(instance.checkUserName("kyle!!!!!!!"));
    }
}