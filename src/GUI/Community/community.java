package gui.community;

import control.jsontool.JsonTool;
import gui.mainpage.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
/**
 * This class is the outer frame of community.
 * @author Jiaxuan Wang
 */
public class Community {
    @FXML
    private VBox cardList;

    private Main main=Main.ctrl;

    public void setMain(Main main){this.main=main;}

    public static JSONArray input;

    public static int jsonIndex;
    /**
     * This method is used to initialize the fxml page.
     * @return Nothing.
     * @see JSONException,IOException
     */
    @FXML
    private void initialize() throws IOException, JSONException {

        input=new JsonTool("src/gui/community/community.json").read();

        for(jsonIndex=0;jsonIndex<input.length();jsonIndex++){
            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(Community.class.getResource("template.fxml"));
            AnchorPane cardtemp = (AnchorPane) loader.load();

            cardList.getChildren().add(cardtemp);

        }

    }

}
