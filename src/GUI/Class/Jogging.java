package GUI.Class;

import GUI.Main.Main;
import global.ViewManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class Jogging implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void getLive(){
        //Entry.setText("Cards");
        Main.ctrl.body.setContent(ViewManager.getInstance().get("mediaview"));
    }

    @FXML
    private void freshCard(){
        //Entry.setText("Cards");
        Main.ctrl.body.setContent(ViewManager.getInstance().get("jogging"));
    }

}
