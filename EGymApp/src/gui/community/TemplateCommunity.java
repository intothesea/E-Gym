package gui.community;

import gui.mainpage.Main;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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
 * This class is the template block of community.
 * @author Jiaxuan Wang
 */
public class TemplateCommunity {
    @FXML
    private ImageView pic;
    @FXML
    private TextFlow text;
    @FXML
    private Label title;
    @FXML
    private MaterialDesignIconView like;

    private Main main=Main.ctrl;
    public void setMain(Main main){this.main=main;}
    public void setHeart(Paint color){like.setFill(color);}
    public int position= Community.jsonIndex;

    public int getPosition(){
        return position;
    }
    /**
     * This method is used to initialize the fxml page.
     * @return Nothing.
     * @see JSONException
     */
    @FXML
    private void initialize() throws JSONException {
        //System.out.println(1);
        //main=main.ctrl;
        JSONObject we = Community.input.getJSONObject(position);
        File file=new File("src/gui/imagesrc/cpic/"+we.getString("pid")+".jpg");
        //System.out.println("test"+file.toURI().toString());
        Image image=new Image(file.toURI().toString());
        pic.setImage(image);
        title.setText(we.getString("title"));
        Text content=new Text(we.getString("content"));
        content.setFont(Font.font(20));
        text.getChildren().add(content);
        //System.out.println(main);



    }
    /**
     * This method is used to deal with the button click.
     * @return Nothing.
     * @see JSONException,IOException
     */
    @FXML
    private void handleClick() throws IOException, JSONException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Community.class.getResource("cards.fxml"));

        ScrollPane card = (ScrollPane) loader.load();
        Cards controller=loader.getController();
        controller.setPosition(position);
        controller.setHeart(like.getFill());
        controller.setpController(this);
        controller.show();
        //System.out.println(controller);
        main.body.setContent(card);
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

        }
        else{
            like.setFill(Paint.valueOf("#000000"));

        }
    }
}

