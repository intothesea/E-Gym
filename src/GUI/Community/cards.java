package gui.community;

import gui.mainpage.Main;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
/**
 * This class is the inner page of community.
 * @author Jiaxuan Wang
 */
public class Cards {
    @FXML
    private ImageView pic;
    @FXML
    private TextFlow text;
    @FXML
    private Label title;
    @FXML
    private MaterialDesignIconView like;

    private Main main=Main.ctrl;
    private TemplateCommunity pController;

    public int position;
    public void setPosition(int position){
        this.position=position;
    }
    public void setHeart(Paint color){like.setFill(color);}
    public void setpController(TemplateCommunity pController){this.pController=pController;}
    @FXML
    private void initialize() throws JSONException { }
    /**
     * This method is used to show the page.
     * @return Nothing.
     * @see JSONException
     */
    public void show() throws JSONException {
        //main=main.ctrl;
        JSONObject we = Community.input.getJSONObject(position);
        File file=new File("src/gui/imagesrc/cpic/"+we.getString("pid")+".jpg");
        Image image=new Image(file.toURI().toString());
        pic.setImage(image);
        title.setText(we.getString("title"));
        Text content=new Text(we.getString("content"));
        content.setFont(Font.font(20));
        text.getChildren().add(content);
    }
    /**
     * This method is used to deal with the button click.
     * @return Nothing.
     * @see JSONException,IOException
     */
    @FXML
    private void handleClick() throws IOException, JSONException {
        title.setText("Community");
        main.body.setContent(control.global.ViewManager.getInstance().get("community"));
    }
    /**
     * This method is used to change the color of heart.
     * @return Nothing.
     * @see JSONException,IOException
     */
    @FXML
    private void exchangeColor() throws IOException, JSONException {

        if(like.getFill().equals(Paint.valueOf("#000000"))){
            like.setFill(Paint.valueOf("#E55C5C"));
            pController.setHeart(Paint.valueOf("#E55C5C"));
        }
        else{
            like.setFill(Paint.valueOf("#000000"));
            pController.setHeart(Paint.valueOf("#000000"));
        }
    }
}
