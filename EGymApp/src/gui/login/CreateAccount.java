package gui.login;

import entity.User;
import gui.App;
import control.global.ViewManager;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import control.MyMD5;
import control.UserDaoImp;


import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Title: CreateAccount
 * Description: The FXML controller which could create new accounts
 *
 * @author Mingda Jia
 * @version 2.0
 */
public class CreateAccount {

    @FXML
    private TextField telephone;
    @FXML
    private TextField E_mail;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button Register;
    @FXML
    private Hyperlink BackLink;

    /**
     * Title: backAction
     * Description: back to login page
     */
    @FXML
    private void backAction() {

        App.decorator.setContent(ViewManager.getInstance().get("login"));
        Register.setText("Register");
        BackLink.setText("Back");
    }


    /**
     * Title: RegisterAction
     * Description: Regist a new user
     */
    @FXML
    private void RegisterAction() {
        String tel = telephone.getText();
        String Email = E_mail.getText();
        String userName = username.getText();
        String password1 = password.getText();
        String filePath = "src/gui/imagesrc/user/user1.png";

        if(!validOrNot(password1)){
            Register.setText("Failed, your password should have both digits and letters!");
            BackLink.setText("click here to register again");

            telephone.setText("");
            E_mail.setText("");
            username.setText("");
            password.setText("");
        }else if(!isValidEmail(Email)) {
            Register.setText("Wrong email format");
            BackLink.setText("click here to register again");
            telephone.setText("");
            E_mail.setText("");
            username.setText("");
            password.setText("");

        }else{
            Random rand = new Random();

            try {

                User u = new User(rand.nextInt(10000), MyMD5.getEncryptedPwd(password1),
                        userName, "2000-01-01", 0.0, Email, tel, filePath);

                UserDaoImp udi = new UserDaoImp();
                udi.insert(u);


                Register.setText("Successfully!");
                BackLink.setText("Click here to login your new account");

                // Thread.currentThread().sleep(3000);
                // backAction();

            } catch (NoSuchAlgorithmException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (UnsupportedEncodingException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }




    }

    private boolean validOrNot(String password) {
        if(password.length() < 6 || password.length() > 10)
            return false;
        else if(!isAlphaNumeric(password))
            return false;
        else if(!isAlphaAndNumeric(password))
            return false;
        return true;
    }

    public static boolean isAlphaNumeric(String s){
        Pattern p = Pattern.compile("[0-9a-zA-Z]{1,}");
        Matcher m = p.matcher(s);
        return m.matches();
    }
    public static boolean isAlphaAndNumeric(String s){
        Pattern p = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,10}$");
        Matcher m = p.matcher(s);
        return m.matches();
    }
    public static boolean isValidEmail(String email) {
        if ((email != null) && (!email.isEmpty())) {
            return Pattern.matches("^(\\w+([-.][A-Za-z0-9]+)*){3,18}@\\w+([-.][A-Za-z0-9]+)*\\.\\w+([-.][A-Za-z0-9]+)*$", email);
        }
        return false;
    }

}
