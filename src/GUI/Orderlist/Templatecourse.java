package GUI.Orderlist;

import GUI.Community.cards;
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


public class Templatecourse {
    @FXML
    private ImageView pic;
    @FXML
    private TextFlow text;
    @FXML
    private Label title;
    @FXML
    private MaterialDesignIconView like;
    @FXML
    private  Label timelabel;
    @FXML
    private  Label coursetype;

    private Main main=Main.ctrl;
    public void setMain(Main main){this.main=main;}
    public void setHeart(Paint color){like.setFill(color);}
    public int position= Orderdetail.jsonindex;

    public int getPosition(){
        return position;
    }

    @FXML
    private void initialize() throws JSONException {
        //System.out.println(1);
        //main=main.ctrl;
        JSONObject we = Orderdetail.input.getJSONObject(position);
        File file=new File("src/GUI/imagesrc/cpic/"+we.getString("pid")+".jpg");
        //System.out.println("test"+file.toURI().toString());
        Image image=new Image(file.toURI().toString());
        pic.setImage(image);
        title.setText(we.getString("title"));
        timelabel.setText(we.getString("time"));
        coursetype.setText(we.getString("Course type"));
        Text content=new Text(we.getString("content"));
        content.setFont(Font.font(20));
        text.getChildren().add(content);
        //System.out.println(main);



    }

    @FXML
    private void exchangecolor() throws IOException, JSONException {

        if(like.getFill().equals(Paint.valueOf("#000000"))){
            like.setFill(Paint.valueOf("#E55C5C"));

        }
        else{
            like.setFill(Paint.valueOf("#000000"));

        }
    }
}

