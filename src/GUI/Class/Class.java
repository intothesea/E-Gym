/*
 * Copyright (C) Gleidson Neves da Silveira
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package GUI.Class;

import GUI.Main.Main;
import GUI.Community.*;
import GUI.global.json.JsonTool;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Class implements Initializable {

    @FXML
    ImageView imageView1=new ImageView();
    @FXML ImageView imageView2=new ImageView();
    @FXML ImageView imageView3=new ImageView();
    @FXML ImageView imageView4=new ImageView();
    @FXML ImageView imageView5=new ImageView();
    @FXML ImageView imageView6=new ImageView();
    @FXML ImageView imageView7=new ImageView();
    @FXML ImageView imageView8=new ImageView();
    @FXML ImageView imageView9=new ImageView();
    @FXML
    ImageView[] imageViews=new ImageView[6];
    @FXML
    public AnchorPane anchorPane;
    @FXML
    public TextArea title;
    @FXML
    public TextArea content;
    @FXML
    FileChooser fileChooser=new FileChooser();
    int n=0;
    public File file;
    JsonTool a=new JsonTool("src/JSON/video.json");

    public JSONArray input;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imageViews[0]=imageView1;
        imageViews[1]=imageView2;
        imageViews[2]=imageView3;
        imageViews[3]=imageView4;
        imageViews[4]=imageView5;
        imageViews[5]=imageView6;
       // imageViews[6]=imageView7;
       // imageViews[7]=imageView8;
        // imageViews[8]=imageView9;
        try {
            input=a.read();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void getLive(){
        //Entry.setText("Cards");
        Main.ctrl.body.setContent(global.ViewManager.getInstance().get("mediaview"));
        System.out.println("test button");
    }

    @FXML
    private void freshCard(){
        //Entry.setText("Cards");
        Main.ctrl.body.setContent(global.ViewManager.getInstance().get("card"));
    }

    @FXML
    private void jogging(){
        //Entry.setText("Cards");
        Main.ctrl.body.setContent(global.ViewManager.getInstance().get("jogging"));
    }

}
