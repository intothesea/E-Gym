package gui.course;

import gui.mainpage.Main;
import control.global.ViewManager;
import control.jsontool.JsonTool;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
 * @Version 1.0
 * @author Mingda Jia
 * @version 2.0
 */

public class VideoCategoris {

    @FXML
    private ImageView[] pic = new ImageView[9];
    @FXML
    private ImageView imageView1;
    @FXML
    private ImageView imageView2;
    @FXML
    private ImageView imageView3;
    @FXML
    private ImageView imageView4;
    @FXML
    private ImageView imageView5;
    @FXML
    private ImageView imageView6;
    @FXML
    private ImageView imageView7;
    @FXML
    private ImageView imageView8;
    @FXML
    private ImageView imageView9;

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
    private Button jogging;
    @FXML
    private  Button briskWalking;
    @FXML
    private Button swimming;
    @FXML
    private  Button yoga;


    @FXML
    /**
     * Title: initialize
     * Description: The initialize method of the video preview pages, load 9 videos into the preview page from the JSON file.
     *
     * @return void
     */
    public void initialize() throws IOException, JSONException {
        input = new JsonTool("src/gui/course/course.json").read();
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
            file = new File("src/gui/imagesrc/LiveVideoPic/" + jsonObject.getString("pname"));
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
    /**
     * Title: getLive
     * Description: get a recorded vodeo and get into the videoPage.fxml
     *
     * @param e The action listener
     * @return void
     */
    private void getLive(ActionEvent e) throws JSONException, IOException {
        Button btn = (Button) e.getSource();
        if(page1 != null)
            vid = Integer.parseInt(btn.getId().substring(3));
        else if(page2 != null)
            vid = Integer.parseInt(btn.getId().substring(3))+9;
        //System.out.println(vid);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(VideoCategoris.class.getResource("videoPage.fxml"));
        System.out.println(vid);
        StackPane media =loader.load();
        gui.course.VideoPage controller1=loader.getController();
        controller1.setPosition(vid);
        controller1.show();
        Main.ctrl.body.setContent(media);
    }

    @FXML
    /**
     * Title: freshCard
     * Description: The method to fresh the current preview page
     *
     * @return void
     */
    private  void freshCard()  {
        Main.ctrl.body.setContent(ViewManager.getInstance().get("videoCategories"));
    }
    @FXML
    /**
     * Title: PageUP
     * Decription: Change page from different categories
     */
    private void PageUP() {
        Main.ctrl.body.setContent(ViewManager.getInstance().get("videoCategories"));
        page2=null;
    }
    /**
     * Title: PageDown
     * Decription: Change page from different categories
     */
    @FXML
    private void PageDown() {
        Main.ctrl.body.setContent(ViewManager.getInstance().get("videoCategories2"));
        page1=null;
    }

    private void change() {
        pic[0] = imageView1;
        pic[1] = imageView2;
        pic[2] = imageView3;
        pic[3] = imageView4;
        pic[4] = imageView5;
        pic[5] = imageView6;
        pic[6] = imageView7;
        pic[7] = imageView8;
        pic[8] = imageView9;
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

    @FXML
    private void changeCategories(){

    }
}
