package gui;

import com.gn.decorator.GNDecorator;
import com.gn.decorator.options.ButtonType;
import control.global.ViewManager;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;
import org.json.JSONException;

import java.io.IOException;
/**
 * This class can run the whole program.
 * @author Yucheng Zhang
 */
public class App extends Application {
    public static final GNDecorator decorator = new GNDecorator();
    private Stage primaryStage;
    private BorderPane rootLayout;
    public static GNDecorator getDecorator(){
        return decorator;
    }
    private SVGPath icon;
    private Button btn_ico;

    @FXML
    public ScrollPane body;

    private void initialScene(){

        decorator.setTitle("London Fitness");
        decorator.addButton(ButtonType.FULL_EFFECT);
        decorator.initTheme(GNDecorator.Theme.DEFAULT);
    }
    /**
     * This method is used to change the color of heart.
     * @return Nothing.
     * @param module package name
     * @param name fxml file name
     */
    private void load(String module, String name){
        try {
            ViewManager.getInstance().put(
                    name,
                    FXMLLoader.load(getClass().getResource("/gui/" + module + "/" + name + ".fxml"))
            );
            //preloaderNotify();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void start(Stage primaryStage) {
        initialScene();


        decorator.setMaximized(true);
        decorator.getStage().getIcons().add(new Image("gui/imagesrc/icon.png"));

        this.icon=new SVGPath();
        String svgCode=new String("M4.83,15.05H1.28L0,13.77V4L1.28,2.7H4.83ZM1.94,13.44H3.22V4.31H1.94l-.33.34V13.1Z M9.66,17.73H4.5L3.22,16.45V1.3L4.5,0H9.66Zm-4.5-1.61H8.05V1.63H5.16L4.83,2V15.79Zm19.32,1.61H19.32V0h5.16L25.76,1.3V16.45Zm-3.55-1.61h2.89l.33-.33V2l-.33-.33H20.93Z M20.93,11.29H8.05V6.46H20.93ZM9.66,9.68h9.66V8.07H9.66ZM27.37,12.9v.2l-.33.34H25.76V4.31H27l.33.34v6.64H29V4L27.7,2.7H24.15V15.05H27.7L29,13.77V12.9Z" );
        this.icon.setContent(svgCode);
        this.btn_ico = new Button();
        this.btn_ico.setStyle("-fx-background-color : transparent;");
        this.icon.setId("icon");

        this.btn_ico.setGraphic(this.icon);
        Node node1=(Node)icon;
        decorator.setIcon(node1);
        decorator.show();
        // decorator.getStage().getIcons().add();
        //decorator.getStage().getIcons();
        load("login", "login");
        load("login","createaccount");
        load("login","ForgetInfo");
//
//        load("Carousel","carousel");
        load("mainpage","mainpage");
//
        load("community","community");
//
        load("livevideo", "LiveVideoPage0");
        load("livevideo", "LiveVideoPage1");
        load("livevideo", "mediaview");
//
        load("course","videoCategories");
        load("course","videoCategories2");
        load("course","videoPage");
//
        load("publish","Publishment");
//
//        load("About","About");
        load("subscriptionpage","Orderdetail");

        load("profilepage","profile");
        decorator.setContent(ViewManager.getInstance().get("login"));
        //body.setContent(ViewManager.getInstance().get("login"));
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
    /**
     * This method runs the whole program.
     * @return Nothing.
     * @param args initial input
     */
    public static void main(String[] args) {
        launch(args);
    }
}
