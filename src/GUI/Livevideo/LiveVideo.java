package GUI.Livevideo;

import GUI.Community.cards;
import GUI.Community.community;
import GUI.Main.Main;
import GUI.global.json.JsonTool;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

/**
 * @author Haitian Wu
 * Create on  11/5/2020
 * Version 1.0
 */

public class LiveVideo {

    @FXML
    private ImageView[] pic = new ImageView[9];
    @FXML
    private ImageView pic0;
    @FXML
    private ImageView pic1;
    @FXML
    private ImageView pic2;
    @FXML
    private ImageView pic3;
    @FXML
    private ImageView pic4;
    @FXML
    private ImageView pic5;
    @FXML
    private ImageView pic6;
    @FXML
    private ImageView pic7;
    @FXML
    private ImageView pic8;

    @FXML
    private Label[] title = new Label[9];
    @FXML
    private Label title0;
    @FXML
    private Label title1;
    @FXML
    private Label title2;
    @FXML
    private Label title3;
    @FXML
    private Label title4;
    @FXML
    private Label title5;
    @FXML
    private Label title6;
    @FXML
    private Label title7;
    @FXML
    private Label title8;
    @FXML
    private Label page1;
    @FXML
    private Label page2;

    private int maxindex = 0;

    public static JSONArray input;

    public int jsonindex;

    private JSONObject jsonObject;

    private File file;

    public static int vid;

    @FXML
    public void initialize() throws IOException, JSONException {
        input = new JsonTool("src/GUI/Livevideo/LiveVideo.json").read();
        change();

        if (page1 != null) {
            jsonindex = 0;
            maxindex = 9;
        } else if (page2 != null) {
            jsonindex = 9;
            maxindex = 18;
        }

        for (; jsonindex < input.length() && jsonindex < maxindex; jsonindex++) {
            jsonObject = input.getJSONObject(jsonindex);
            file = new File("src/GUI/imagesrc/LiveVideoPic/" + jsonObject.getString("pname"));
            //System.out.println("test "+file.toURI().toString());
            Image image = new Image(file.toURI().toString());
            if (page1 != null) {
                pic[jsonindex].setImage(image);
                title[jsonindex].setText(jsonObject.getString("title"));
            } else if (page2 != null) {
                pic[jsonindex - 9].setImage(image);
                title[jsonindex - 9].setText(jsonObject.getString("title"));
            }
        }
    }

    @FXML
    private void getLive(ActionEvent e) throws JSONException, IOException {
        Button btn = (Button) e.getSource();
        if(page1 != null)
            vid = Integer.parseInt(btn.getId().substring(3));
        else if(page2 != null)
            vid = Integer.parseInt(btn.getId().substring(3))+9;
        //System.out.println(vid);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(LiveVideo.class.getResource("MediaView.fxml"));
        System.out.println(vid);
        StackPane media =loader.load();
        MediaView controller1=loader.getController();
        controller1.setPosition(vid);
        controller1.show();
        Main.ctrl.body.setContent(media);
    }

    @FXML
    private void PageUP() {
        Main.ctrl.body.setContent(global.ViewManager.getInstance().get("LiveVideoPage0"));
    }

    @FXML
    private void PageDown() {
        Main.ctrl.body.setContent(global.ViewManager.getInstance().get("LiveVideoPage1"));
    }

    private void change() {
        pic[0] = pic0;
        pic[1] = pic1;
        pic[2] = pic2;
        pic[3] = pic3;
        pic[4] = pic4;
        pic[5] = pic5;
        pic[6] = pic6;
        pic[7] = pic7;
        pic[8] = pic8;
        title[0] = title0;
        title[1] = title1;
        title[2] = title2;
        title[3] = title3;
        title[4] = title4;
        title[5] = title5;
        title[6] = title6;
        title[7] = title7;
        title[8] = title8;
    }
}
