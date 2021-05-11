package GUI.Login;

import GUI.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ForgetInfo implements Initializable {
    @FXML
    private Label emailtext;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        emailtext.setText("12121121@qq.com");

    }
    @FXML
    private void backAction(){
        App.decorator.setContent(global.ViewManager.getInstance().get("login"));
    }

}
