package GUI.Login;

import Control.MyMD5;
import Control.UserDaoImp;
import Entity.User;
import GUI.App;
import com.gn.GNAvatarView;
import com.gn.GNCarousel;
import global.ViewManager;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.ResourceBundle;

public class login implements Initializable {

    @FXML
    private GNAvatarView avatar;
    @FXML
    private HBox box_username;
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

    private void load(String module, String name) {
        //System.out.println(getClass().getResource("GUI/" + module + "/" + name + ".fxml"));
        try {
            ViewManager.getInstance().put(
                    name,
                    FXMLLoader.load(getClass().getResource("/GUI/" + module + "/" + name + ".fxml"))
            );
            //preloaderNotify();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private GNCarousel carousel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        rotateTransition.setNode(avatar);
        rotateTransition.setByAngle(360);
        rotateTransition.setDuration(Duration.seconds(1));
        rotateTransition.setAutoReverse(true);

    }


    private boolean valid() throws UnsupportedEncodingException, NoSuchAlgorithmException {

        String name = username.getText();
        String password1 = password.getText();

        if (password.getText().isEmpty() || password.getLength() < 3)
            return false;

        UserDaoImp udi = new UserDaoImp();
        User user = udi.selectByName(name);
        String passwprdInDb = user.getPassword();

        if (MyMD5.validPassword(password1, passwprdInDb)) {

            System.out.println("Login Successfully");
            return true;
        } else {
            username.setText("");
            password.setText("");
            //lbl_error.setVisible(true);
            System.out.println("Your name or password may wrong. Please try again.");
            return false;

        }

    }

    @FXML
    private void loginAction() throws UnsupportedEncodingException, NoSuchAlgorithmException {

        if (valid())
            enter();
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

