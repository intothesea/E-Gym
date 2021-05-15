package GUI.Login;

import Entity.User;
import GUI.App;
import global.ViewManager;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import Control.MyMD5;
import Control.UserDaoImp;


import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class createaccount {

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
    @FXML
    private void backAction() {
        App.decorator.setContent(ViewManager.getInstance().get("login"));
    }

    @FXML
    private void RegisterAction() {
        String tel = telephone.getText();
        String Email = E_mail.getText();
        String userName = username.getText();
        String password1 = password.getText();


        Random rand = new Random();

        try {

            User u = new User(rand.nextInt(10000), MyMD5.getEncryptedPwd(password1),
                    userName, "2000-01-01", 0.0, Email, tel);

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
