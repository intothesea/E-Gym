package gui.livevideo;

import control.LoadPage;
import control.jsontool.JsonTool;
import gui.mainpage.Main;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import gui.subscriptionpage.Orderdetail;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

/**
 * This class is used to show the live video list page.
 *
 * @author Haitian Wu
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

    public static JSONArray output;

    public int jsonIndex;

    private JSONObject jsonObject;

    private File file;

    public static int vid;
    @FXML
    private HBox hboxt;
    @FXML
    private VBox containerVbox;

    /**
     * This method is used to set the color of the heart button.
     * The color of button means whether the video is subscribed
     * or not.
     *
     * @throws JSONException
     * @throws IOException
     */
    private void sceneInit() throws JSONException, IOException {
        JSONArray jsonObject = new JsonTool("src/gui/livevideo/LiveVideo.json").read();
        for (int jsonindex = 0; jsonindex < jsonObject.length(); jsonindex++) {
            if (jsonObject.getJSONObject(jsonindex).getString("islike").equals("true")) {
                Button btn = null;
                if (containerVbox != null) {
                    btn = (Button) containerVbox.lookup("#like" + jsonindex);
                }
                if (btn != null) {
                    MaterialDesignIconView like = (MaterialDesignIconView) btn.getGraphic();
                    like.setFill(Paint.valueOf("#E55C5C"));
                }

            }

        }
    }

    /**
     * This method is used for initializing the LiveVideoPage0 and LiveVideoPage1
     *
     * @throws IOException   Handling file reading and writing related exceptions.
     * @throws JSONException Handling json file reading and writing related exceptions.
     */
    @FXML
    public void initialize() throws IOException, JSONException {
        sceneInit();
        input = new JsonTool("src/gui/livevideo/LiveVideo.json").read();
        change();

        if (page1 != null) {
            jsonIndex = 0;
            maxindex = 9;
        } else if (page2 != null) {
            jsonIndex = 9;
            maxindex = 18;
        }

        for (; jsonIndex < input.length() && jsonIndex < maxindex; jsonIndex++) {
            jsonObject = input.getJSONObject(jsonIndex);
            file = new File("src/gui/imagesrc/LiveVideoPic/" + jsonObject.getString("pname"));
            Image image = new Image(file.toURI().toString());
            if (page1 != null) {
                pic[jsonIndex].setImage(image);
                title[jsonIndex].setText(jsonObject.getString("title") + " || " + jsonObject.getString("time"));
            } else if (page2 != null) {
                pic[jsonIndex - 9].setImage(image);
                title[jsonIndex - 9].setText(jsonObject.getString("title") + " || " + jsonObject.getString("time"));
            }
        }
    }

    /**
     * This method is used when the button is clicked.
     * It will check the user identity, ordinary users
     * only can view ordinary live video, VIP user can
     * view all videos. Then it will open the detailed
     * video playing page.
     *
     * @param e The event listener of the buttons.
     * @throws JSONException Handling json file reading and writing related exceptions.
     * @throws IOException   Handling file reading and writing related exceptions.
     */
    @FXML
    private void getLive(ActionEvent e) throws JSONException, IOException {
        String vipStatus = Main.ctrl.userVipStatus;
        Button btn = (Button) e.getSource();
        if (page1 != null)
            vid = Integer.parseInt(btn.getId().substring(3));
        else if (page2 != null)
            vid = Integer.parseInt(btn.getId().substring(3)) + 9;

        if (vid == 0 && vipStatus.equals("Ultra VIP") == false) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Inaccessible");
            alert.setContentText("You are not Ultra VIP!");
            alert.showAndWait();
        } else if (vid == 1 && vipStatus.equals("Ultra VIP") == false && vipStatus.equals("Ordinary VIP") == false) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Inaccessible");
            alert.setContentText("You are not Ultra VIP or Ordinary VIP!");
            alert.showAndWait();
        } else {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(LiveVideo.class.getResource("mediaview.fxml"));
            StackPane media = loader.load();
            MediaView controller1 = loader.getController();
            controller1.setPosition(vid);
            controller1.show();
            Main.ctrl.body.setContent(media);
        }


    }

    /**
     * This method is used when the heart button is clicked.
     * According to whether is the user subscribed the video
     * or not, it will change the color of the heart button
     * and add the video to the subscription list.
     *
     * @param e The event listener of the buttons.
     * @throws JSONException Handling json file reading and writing related exceptions.
     * @throws IOException   Handling file reading and writing related exceptions.
     */
    @FXML
    private void handleLike(ActionEvent e) throws JSONException, IOException {
        Button btn = (Button) e.getSource();
        JsonTool tool = new JsonTool("src/gui/livevideo/LiveVideo.json");
        jsonIndex = Integer.parseInt(btn.getId().substring(4));
        jsonObject = input.getJSONObject(jsonIndex);
        MaterialDesignIconView like = (MaterialDesignIconView) btn.getGraphic();
        if (like.getFill().equals(Paint.valueOf("#000000"))) {
            like.setFill(Paint.valueOf("#E55C5C"));
            jsonObject.put("islike", "true");
        } else {
            like.setFill(Paint.valueOf("#000000"));
            jsonObject.put("islike", "false");
        }
        tool.write(input);
        Main.ctrl.removeAllList();
        Main.ctrl.popListVbox();
        LoadPage loadMethod = new LoadPage();
        loadMethod.load("subscriptionpage", "Orderdetail");
    }

    /**
     * When the user clicks the pageup button, the method will change the page to the previous page.
     */
    @FXML
    private void PageUP() {
        Main.ctrl.body.setContent(control.global.ViewManager.getInstance().get("LiveVideoPage0"));
    }

    /**
     * When the user clicks the pagedown button, the method will change the page to the next page.
     */
    @FXML
    private void PageDown() {
        Main.ctrl.body.setContent(control.global.ViewManager.getInstance().get("LiveVideoPage1"));
    }

    /**
     * Use array to save the information of instances.
     */
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
