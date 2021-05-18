package GUI.Orderlist;

import GUI.Main.Main;
import GUI.global.json.JsonTool;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

public class Orderdetail {
    @FXML
    private VBox cardlist;

    private Main main=Main.ctrl;

    public void setMain(Main main){this.main=main;}


    public static JSONArray input;

    public static int jsonindex;

    @FXML
    private void initialize() throws IOException, JSONException {

        //System.out.println(1111111111);

        //Todo
        input=new JsonTool("src/GUI/Orderlist/orderinfo.json").read();

        for(jsonindex=0;jsonindex<input.length();jsonindex++){
            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(Orderdetail.class.getResource("templatecourse.fxml"));
            AnchorPane cardtemp = (AnchorPane) loader.load();

            cardlist.getChildren().add(cardtemp);

            //System.out.println(main);
        }

        //System.out.println(1);
    }

}
