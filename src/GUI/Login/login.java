package gui.login;

import control.MyMD5;
import control.UserDaoImp;
import entity.User;
import gui.App;
import gui.mainpage.Main;
import control.global.ViewManager;
import com.gn.GNAvatarView;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

/**
 * Title: Login
 * Description: The FXML controller of login page
 *
 * @author MingdaJia
 * @version 1.0.1
 */
public class Login implements Initializable {

    @FXML
    private GNAvatarView avatar;
    @FXML
    private HBox boxUsername;
    @FXML
    private HBox box_password;
    @FXML
    private HBox box_error;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button login;
    @FXML
    private Button forget;

    @FXML
    private Label lbl_error;
    @FXML
    public ScrollPane body;

    private RotateTransition rotateTransition = new RotateTransition();
    private String userName;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        rotateTransition.setNode(avatar);
        rotateTransition.setByAngle(360);
        rotateTransition.setDuration(Duration.seconds(1));
        rotateTransition.setAutoReverse(true);

    }

    /**
     * Title: valid
     * Description: The valid function to check if the username and password is match
     *
     *
     * @return boolean if match, return true, else return false
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    private boolean valid() throws UnsupportedEncodingException, NoSuchAlgorithmException {

        String name = username.getText();
        String password1 = password.getText();

        if (password.getText().isEmpty() || password.getLength() < 3)
            return false;
        this.userName=name;
        UserDaoImp udi = new UserDaoImp();
        User user = udi.selectByName(name);
        String passwprdInDb = user.getPassword();

        if (MyMD5.validPassword(password1, passwprdInDb)) {

            System.out.println("Login Successfully");
            return true;
        } else {
            username.setText("");
            password.setText("");
            System.out.println("Your name or password may wrong. Please try again.");
            return false;

        }

    }

    /**
     * Title: loginAction
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    @FXML
    private void loginAction() throws UnsupportedEncodingException, NoSuchAlgorithmException {

        if (valid()){
            Main.ctrl.userName=userName;
            Main.ctrl.userIconpath="src/gui/imagesrc/user/user1.png";
            UserDaoImp udi = new UserDaoImp();
            User user = udi.selectByName(Main.ctrl.userName);
            Main.ctrl.userVipStatus=user.getVIPstatus();
            Main.ctrl.sceneInit();
            enter();
        }
        else {

            lbl_error.setVisible(true);
        }

    }

    @FXML
    private void ForgetAction() {
        App.decorator.setContent(ViewManager.getInstance().get("ForgetInfo"));

    }

    @FXML
    private void createAction() {
        App.decorator.setContent(ViewManager.getInstance().get("createaccount"));
    }

    private void enter() {

        App.decorator.setContent(ViewManager.getInstance().get("mainpage"));

    }

}

