package GUI.Login;

import GUI.App;
import global.ViewManager;
import javafx.fxml.FXML;

public class createaccount {

    @FXML
    private void backAction(){
        App.decorator.setContent(ViewManager.getInstance().get("login"));
    }

    @FXML
    private void RegisterAction(){

    }
}
