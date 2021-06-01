package gui.publish;

import control.Copyfile;
import control.LoadPage;
import control.jsontool.JsonTool;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class is the template block of community.
 * @author Jiaxuan Wang
 */
public class Publishment implements Initializable {
    @FXML
    public Button Picture;
    @FXML ImageView imageView1=new ImageView();
    @FXML ImageView imageView2=new ImageView();
    @FXML ImageView imageView3=new ImageView();
    @FXML ImageView imageView4=new ImageView();
    @FXML ImageView imageView5=new ImageView();
    @FXML ImageView imageView6=new ImageView();
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
    @FXML
    int n=0;
    public File file;
    JsonTool a=new JsonTool("src/gui/Community/community.json");

    public JSONArray input;
    /**
     * This method is used to deal with the button click.
     * @return Nothing.
     * @param actionEvent the button click
     */
    @FXML
    public void OnButtonClick(javafx.event.ActionEvent actionEvent) {
        configureFileChooser(fileChooser);
        file=fileChooser.showOpenDialog(anchorPane.getScene().getWindow());
        System.out.println(n);
        FileChoosing(file,imageViews[n]);
        n++;
        //imageViews[n].setImage(new Image("gui/Publish/Pic/11.png"));

    }
    /**
     * This method is used to initialize the filechooser tool.
     * @return Nothing.
     * @param fileChooser filechooser tool
     */
    @FXML
    public void configureFileChooser(FileChooser fileChooser)
    {
        fileChooser.setTitle("View Pictures");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images","*.*"),
                new FileChooser.ExtensionFilter("JPG","*.jpg"),
                new FileChooser.ExtensionFilter("PNG","*.png"),
                new FileChooser.ExtensionFilter("GIF","*.gif"),
                new FileChooser.ExtensionFilter("BMP","*.bmp")
        );
    }
    /**
     * This method is used to choose the file.
     * @return Nothing.
     * @param file the choosed file
     * @param imageView the fxml picture container
     */
    public void FileChoosing(File file,ImageView imageView)
    {
        if (file != null)
        {
            System.out.println(file);
            String filename=String.valueOf(file);
            System.out.println(filename);
            filename=filename.replaceAll("\\\\","\\\\\\\\");
            System.out.println(filename);
            //  System.out.println(fileChooser.getInitialFileName());
            Image image3=new Image("file:"+filename);
            imageViews[n].setImage(image3);
        }

    }

    /**
     * This method is used to initialize the fxml page.
     * @return Nothing.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imageViews[0]=imageView1;
        imageViews[1]=imageView2;
        imageViews[2]=imageView3;
        imageViews[3]=imageView4;
        imageViews[4]=imageView5;
        imageViews[5]=imageView6;
        try {
            input=a.read();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    /**
     * This method is used to deal with the button click.
     * @return Nothing.
     * @see JSONException,IOException
     */
    @FXML
    private void handleClick() throws IOException, JSONException {
        if(n==1){
            Copyfile tool=new Copyfile();
            LoadPage temp=new LoadPage();
            tool.copyFile(file.getPath(),"src/gui/imagesrc/cpic/"+String.valueOf(input.length()+1)+".jpg");
            System.out.println("Done");
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("title",title.getText());
            jsonObject.put("pid",String.valueOf(input.length()+1));
            jsonObject.put("content",content.getText());
            input.put(jsonObject);
            System.out.println(input);
            a.write(input);
            imageView1.setImage(null);
            title.setText(null);
            content.setText(null);
            temp.load("community","community");
        }
    }
}
