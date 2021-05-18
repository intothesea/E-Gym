package GUI;

import com.gn.decorator.GNDecorator;
import com.gn.decorator.options.ButtonType;
import global.ViewManager;
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

import java.io.IOException;

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

        decorator.setTitle("EGym");
        decorator.addButton(ButtonType.FULL_EFFECT);
        decorator.initTheme(GNDecorator.Theme.DEFAULT);
    }
    private void load(String module, String name){
        //System.out.println(getClass().getResource("GUI/" + module + "/" + name + ".fxml"));
        try {
            ViewManager.getInstance().put(
                    name,
                    FXMLLoader.load(getClass().getResource("/GUI/" + module + "/" + name + ".fxml"))
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
        decorator.getStage().getIcons().add(new Image("GUI/imagesrc/icon.png"));

        this.icon=new SVGPath();
        String string=new String("M5 18 c0 -6.3 0.2 -7 2 -7 1.3 0 2 -0.7 2 -2 0 -1.9 0.7 -2 9 -2 l9 0 0 7 c0 6.3 -0.2 7 -2 7 -1.3 0 -2 0.7 -2 2 0 1.9 -0.7 2 -9 2 l-9 0 0 -7z m16 0.5 l0 -4.5 -7 0 -7 0 0 4.5 0 4.5 7 0 7 0 0 -4.5z m4 -4 l0 -5.5 -7 0 c-4 0 -7 0.4 -7 1 0 0.6 2.7 1 6 1 l6 0 0 4.5 c0 2.5 0.5 4.5 1 4.5 0.6 0 1 -2.5 1 -5.5z");
        this.icon.setContent(string);
        this.btn_ico = new Button();
        this.btn_ico.setStyle("-fx-background-color : transparent;");
        this.icon.setId("icon");

        this.btn_ico.setGraphic(this.icon);
        Node node1=(Node)icon;
        decorator.setIcon(node1);
        decorator.show();
        // decorator.getStage().getIcons().add();
        //decorator.getStage().getIcons();
        load("Login", "login");
        load("Login","createaccount");
        load("Login","ForgetInfo");
//
//        load("Carousel","carousel");
        load("Main","mainpage");
//
        load("Community","community");
//
        load("Livevideo", "LiveVideoPage0");
        load("Livevideo", "LiveVideoPage1");
        load("Livevideo", "mediaview");
//
        load("Class","Class");
        load("Class","jogging");
//
        load("Publish","Publishment");
//
//        load("About","About");
        load("Orderlist","Orderdetail");
        decorator.setContent(ViewManager.getInstance().get("login"));
        //body.setContent(ViewManager.getInstance().get("login"));
    }

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
