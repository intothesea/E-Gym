package Test.login;

import gui.login.CreateAccount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateAccountTest {

    CreateAccount ca = new CreateAccount();

    @Test
    void isAlphaNumeric() {
        String password = "123abCD";
        String password1 = "12345678";
        String password2 = "abcdEF";
        String password3 = " $#ab_";
        assertEquals(true, ca.isAlphaNumeric(password));
        assertEquals(true, ca.isAlphaNumeric(password1));
        assertEquals(true, ca.isAlphaNumeric(password2));
        assertEquals(false, ca.isAlphaNumeric(password3));

    }

    @Test
    void isAlphaAndNumeric() {
        String password = "123abCD";
        String password1 = "12334567";
        String password2 = "abcdEF";
        assertEquals(true, ca.isAlphaAndNumeric(password));
        assertEquals(false, ca.isAlphaAndNumeric(password1));
        assertEquals(false, ca.isAlphaAndNumeric(password2));
    }

    @Test
    void isValidEmail() {
        String email = "2018213173@gmail.com";
        String email2 = "2018213173@";
        String email3 = "2018213173";
        assertEquals(true,ca.isValidEmail(email));
        assertEquals(false,ca.isValidEmail(email2));
        assertEquals(false,ca.isValidEmail(email3));

    }
}