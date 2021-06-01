package gui.login;

import control.MyMD5;
import control.UserDaoImp;
import entity.User;
import gui.App;
import control.global.ViewManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * Title: ForgetInfo
 * Description: The FXML controller for the find-back password page
 *
 * @author MingdaJia
 * @version 1.0.1
 */
public class ForgetInfo {

    @FXML
    private TextField E_mail;
    @FXML
    private TextField username;
    @FXML
    private Label label;
    @FXML
    private HBox boxEmail;
    @FXML
    private HBox boxUsername;

    /**
     * Title: findBack
     * Description: Find back password
     *
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
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

    /**
     * Title: backAction
     * Description: back to last page
     */
    @FXML
    private void backAction(){
        App.decorator.setContent(ViewManager.getInstance().get("login"));
    }

}
