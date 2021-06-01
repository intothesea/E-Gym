package gui.subscriptionpage;

import gui.mainpage.Main;
import control.jsontool.JsonTool;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
/**
 * This class is detail of order.
 * @author Yucheng Zhang
 */
public class Orderdetail {
    @FXML
    private VBox cardList;

    private Main main=Main.ctrl;

    public void setMain(Main main){this.main=main;}


    public static JSONArray input;

    public static int jsonindex;

    @FXML
    private void initialize() throws IOException, JSONException {


        //Todo
        input=new JsonTool("src/gui/livevideo/LiveVideo.json").read();

        for(jsonindex=0;jsonindex<input.length();jsonindex++){
            if(input.getJSONObject(jsonindex).getString("islike").equals("true")){

                FXMLLoader loader = new FXMLLoader();

                loader.setLocation(Orderdetail.class.getResource("templatecourse.fxml"));
                AnchorPane cardtemp = (AnchorPane) loader.load();

                cardList.getChildren().add(cardtemp);
            }

        }

    }

}
