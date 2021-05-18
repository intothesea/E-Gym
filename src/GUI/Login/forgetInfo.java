package GUI.Login;

import Control.MyMD5;
import Control.UserDaoImp;
import Entity.User;
import GUI.App;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.ResourceBundle;

public class forgetInfo {

    @FXML
    private TextField E_mail;
    @FXML
    private TextField username;
    @FXML
    private Label label;
    @FXML
    private HBox box_emial;
    @FXML
    private HBox box_username;
    @FXML
    public void findBack() throws UnsupportedEncodingException, NoSuchAlgorithmException {

        Random rand = new Random();
        UserDaoImp udi = new UserDaoImp();
        User user = udi.selectByName(username.getText());

        if(user == null){
            label.setText("There is no such user.");

        }

        if(user.getEmail().equals(E_mail.getText()) ){
            String password = MyMD5.getEncryptedPwd(Integer.toString(12345678));
            user.setPassword(password);
            label.setText("We've sent a reset message to your email");

        }else{
            label.setText("Information is not matched!");
        }

    }
    @FXML
    private void backAction(){
        App.decorator.setContent(global.ViewManager.getInstance().get("login"));
    }

}
