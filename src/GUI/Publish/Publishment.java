package GUI.Publish;

import GUI.Main.Main;
import GUI.global.json.JsonTool;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import GUI.App;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


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
    JsonTool a=new JsonTool("src/GUI/Community/community.json");

    public JSONArray input;
    @FXML
    public void OnButtonClick(javafx.event.ActionEvent actionEvent) {
        configureFileChooser(fileChooser);
        file=fileChooser.showOpenDialog(anchorPane.getScene().getWindow());
        System.out.println(n);
        FileChoosing(file,imageViews[n]);
        n++;
        //imageViews[n].setImage(new Image("GUI/Publish/Pic/11.png"));

    }
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
    @FXML
    private void handleclick() throws IOException, JSONException {
        if(n==1){
            copyFile(file.getPath(),"src/GUI/imagesrc/cpic/"+String.valueOf(input.length()+1)+".jpg");
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
            load("Community","community");
        }
    }
    public static void copyFile(String srcPath, String destPath) throws IOException {

        // 打开输入流
        FileInputStream fis = new FileInputStream(srcPath);
        // 打开输出流
        FileOutputStream fos = new FileOutputStream(destPath);

        // 读取和写入信息
        int len = 0;
        // 创建一个字节数组，当做缓冲区
        byte[] b = new byte[1024];
        while ((len = fis.read(b)) != -1) {
            fos.write(b, 0, len);
        }

        // 关闭流  先开后关  后开先关
        fos.close(); // 后开先关
        fis.close(); // 先开后关

    }
    private void load(String module, String name){
        //System.out.println(getClass().getResource("GUI/" + module + "/" + name + ".fxml"));
        try {
            global.ViewManager.getInstance().put(
                    name,
                    FXMLLoader.load(getClass().getResource("/GUI/" + module + "/" + name + ".fxml"))
            );
            //preloaderNotify();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
