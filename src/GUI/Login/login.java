package GUI.Login;

import GUI.App;
import com.gn.GNAvatarView;
import com.gn.GNCarousel;
import global.ViewManager;
import javafx.animation.RotateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class login implements Initializable {

    @FXML private GNAvatarView avatar;
    @FXML private HBox box_username;
    @FXML private HBox box_password;
    @FXML private TextField username;
    @FXML private TextField password;
    @FXML private Button login;

    @FXML private Label lbl_password;
    @FXML private Label lbl_username;
    @FXML private Label lbl_error;
    @FXML
    public ScrollPane body;
    private RotateTransition rotateTransition = new RotateTransition();
    private void load(String module, String name){
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
    @FXML private GNCarousel carousel;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        rotateTransition.setNode(avatar);
        rotateTransition.setByAngle(360);
        rotateTransition.setDuration(Duration.seconds(1));
        rotateTransition.setAutoReverse(true);

    }


    private boolean validPassword(){
        return !password.getText().isEmpty() && password.getLength() > 3;
    }

    private boolean validUsername(){
        return !username.getText().isEmpty() && username.getLength() > 3;
    }

    @FXML
    private void loginAction(){
        enter();
//        if(validPassword() && validUsername())
//            enter();
//        else {
//            lbl_password.setVisible(true);
//            lbl_username.setVisible(true);
//        }

    }
    @FXML
    private void ForgetAction(){
        App.decorator.setContent(ViewManager.getInstance().get("ForgetInfo"));

    }

    @FXML
    private void createAction(){
        App.decorator.setContent(ViewManager.getInstance().get("createaccount"));
    }

    private void enter() {

        App.decorator.setContent(ViewManager.getInstance().get("mainpage"));

    }

}

