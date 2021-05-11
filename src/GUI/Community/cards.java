package GUI.Community;

import GUI.Main.Main;
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

public class cards {
    @FXML
    private ImageView pic;
    @FXML
    private TextFlow text;
    @FXML
    private Label title;
    @FXML
    private MaterialDesignIconView like;

    private Main main=Main.ctrl;
    private template pcontroller;

    public int position;
    public void setPosition(int position){
        this.position=position;
    }
    public void setHeart(Paint color){like.setFill(color);}
    public void setPcontroller(template pcontroller){this.pcontroller=pcontroller;}
    @FXML
    private void initialize() throws JSONException {
//        //main=main.ctrl;
//        JSONObject we = community.input.getJSONObject(position);
//        System.out.println(1);
//        File file=new File("EGym/src/src/foreground.jpg");
//        System.out.println(file.toURI().toString());
//        Image image=new Image(file.toURI().toString());
//        pic.setImage(image);
//        title.setText(we.getString("title"));
//        Text content=new Text(we.getString("content"));
//        content.setFont(Font.font(20));
//        text.getChildren().add(content);

    }
    public void show() throws JSONException {
        //main=main.ctrl;
        JSONObject we = community.input.getJSONObject(position);
        System.out.println(1);
        File file=new File("src/GUI/imagesrc/cpic/"+we.getString("pid")+".jpg");
        System.out.println(file.toURI().toString());
        Image image=new Image(file.toURI().toString());
        pic.setImage(image);
        title.setText(we.getString("title"));
        Text content=new Text(we.getString("content"));
        content.setFont(Font.font(20));
        text.getChildren().add(content);
    }
    @FXML
    private void handleclick() throws IOException, JSONException {
        title.setText("Community");
        main.body.setContent(global.ViewManager.getInstance().get("community"));
    }
    @FXML
    private void exchangecolor() throws IOException, JSONException {

        if(like.getFill().equals(Paint.valueOf("#000000"))){
            like.setFill(Paint.valueOf("#E55C5C"));
            pcontroller.setHeart(Paint.valueOf("#E55C5C"));
        }
        else{
            like.setFill(Paint.valueOf("#000000"));
            pcontroller.setHeart(Paint.valueOf("#000000"));
        }
    }
}
