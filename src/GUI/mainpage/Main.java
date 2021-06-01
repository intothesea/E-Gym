package gui.mainpage;

import control.jsontool.JsonTool;
import com.gn.GNAvatarView;
import com.jfoenix.controls.JFXBadge;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import gui.profilepage.profile;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import org.controlsfx.control.PopOver;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
/**
 * This class is the main page.
 * @author Yucheng Zhang
 */
public class Main implements Initializable {



    @FXML private GNAvatarView avatar;
    @FXML public  VBox sideBar;
    @FXML private HBox searchBox;
    @FXML private HBox boxStatus;
    @FXML private VBox info;
    @FXML private VBox views;
    @FXML private Circle cStatus;
    @FXML private Label status;
    @FXML public  ScrollPane body;
    @FXML public  Label title;
    @FXML private TextField search;
    @FXML private ScrollPane scroll;
    @FXML private Button hamburger;
    @FXML private SVGPath searchIcon;
    @FXML private Button clear;
    @FXML private VBox drawer;
    @FXML private VBox listvbox;
    @FXML private Label userNameLabel;
    public static String userName;
    public static String userVipStatus;
    public static String userIconpath;
    @FXML public SVGPath courseSvg;
    @FXML public SVGPath liveSvg;
    @FXML public SVGPath publishSvg;
    @FXML public SVGPath comSvg;
    @FXML public SVGPath iSvg;
    @FXML public SVGPath orderSvg;

    @FXML private Button course;
    @FXML private Button liveVideo;
    @FXML private Button publish;
    @FXML private Button community;
    @FXML private Button about;

    private FilteredList<Button> filteredList = null;
    public static  final PopOver popup     = new PopOver();

    private ObservableList<Button> items         = FXCollections.observableArrayList();


    public static Main ctrl;
    /**
     * This method is used to initialize the main page.
     * @return Nothing.
     */
    public void sceneInit(){
        userNameLabel.setText(userName);
        status.setText(userVipStatus);

        switch (userVipStatus){
            case "Ordinary User":
                cStatus.setStyle("-fx-fill:green");
                break;
            case "Ordinary VIP":
                cStatus.setStyle("-fx-fill:#FF69B4");
                break;
            case "Ultra VIP":
                cStatus.setStyle("-fx-fill: #D9D919");
                break;
        }
        File file=new File(userIconpath);
        Image image=new Image(file.toURI().toString());

        avatar.setImage(image);
        courseSvg.setContent("M29,9.52V29a4.13,4.13,0,0,1-1.21,2.93,4.09,4.09,0,0,1-2.93,1.21H4.14a4.09,4.09,0,0,1-2.93-1.21A4.13,4.13,0,0,1,0,29V4.14A4.13,4.13,0,0,1,4.14,0H29V5.18H7.45a2.27,2.27,0,0,0-1.59.68,2.19,2.19,0,0,0-.65,1.6,2.17,2.17,0,0,0,.65,1.6,2.23,2.23,0,0,0,1.59.68H19.06v8.48l.31.08a.3.3,0,0,0,.31-.08l2.49-1.84a1.35,1.35,0,0,1,.6-.16.6.6,0,0,1,.44.16l2.49,1.84h.2a.39.39,0,0,0,.29-.13.41.41,0,0,0,.13-.29V9.52Z");
        liveSvg.setContent("M23.44,12.73l4.14-1.84a.9.9,0,0,1,1.19.46.77.77,0,0,1,.08.37V21.78a.91.91,0,0,1-.9.91,1,1,0,0,1-.37-.08l-4.14-1.84v2.67a1.8,1.8,0,0,1-1.8,1.78H1.77A1.8,1.8,0,0,1,0,23.44V9A1.81,1.81,0,0,1,1.8,7.21H21.67A1.81,1.81,0,0,1,23.48,9ZM14,6.31a3.16,3.16,0,1,1,3.16-3.15h0A3.16,3.16,0,0,1,14,6.31Zm-6.34,0a2.26,2.26,0,1,1,2.3-2.25,2.25,2.25,0,0,1-2.3,2.25ZM9,19.87a.9.9,0,0,0,.92.88.88.88,0,0,0,.42-.11L16.68,17A.9.9,0,0,0,17,15.79a.86.86,0,0,0-.28-.29l-6.32-3.61A.89.89,0,0,0,9,12.66Zm1.8-5.65,3.59,2-3.59,2.06Z");
        publishSvg.setContent("M24.39,22.58,12.65,19,24.39,5.42,9,19,0,15.35,28.9,0ZM12.65,28V21.68l3.61,1.8Z");
        comSvg.setContent("M24.79,19.49a1.73,1.73,0,0,0-1-1.46L18.9,15.58,29.15,9.27A1.75,1.75,0,0,0,30,7.68a1.83,1.83,0,0,0-1-1.53l-12-6a1.77,1.77,0,0,0-2.21.52L3.08,16.14a1.87,1.87,0,0,0-.32,1.49,1.82,1.82,0,0,0,.94,1.21l4.56,2.28-8,12.23A1.8,1.8,0,0,0,.54,35.6a1.83,1.83,0,0,0,2.28.2L24,21.08a1.65,1.65,0,0,0,.75-1.59Z");
        iSvg.setContent("M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm0 3c1.66 0 3 1.34 3 3s-1.34 3-3 3-3-1.34-3-3 1.34-3 3-3zm0 14.2c-2.5 0-4.71-1.28-6-3.22.03-1.99 4-3.08 6-3.08 1.99 0 5.97 1.09 6 3.08-1.29 1.94-3.5 3.22-6 3.22z");
        orderSvg.setContent("M25.84,26.85v5.91a.7.7,0,0,1-1.05.6l-7.28-4.44a4.16,4.16,0,0,0-4.27,0L6.3,33.36a.7.7,0,0,1-1-.6V6.56a1.27,1.27,0,0,1,.41-.92,1.52,1.52,0,0,1,1-.4H24.38a1.54,1.54,0,0,1,1.06.4,1.26,1.26,0,0,1,.4.92v1.1a.7.7,0,0,0,.7.69h3.51a.69.69,0,0,0,.7-.69V4.13a4,4,0,0,0-1.29-2.94,4.47,4.47,0,0,0-3-1.19H4.33a4.47,4.47,0,0,0-3,1.19A4,4,0,0,0,0,4.13v35.5a1.38,1.38,0,0,0,.2.7,1.42,1.42,0,0,0,1.91.47l12.45-7.26a1.58,1.58,0,0,1,.81-.23,1.61,1.61,0,0,1,.82.23l12.34,7.2,0,0a1.4,1.4,0,0,0,2.18-1.13V26.85a.7.7,0,0,0-.7-.7H26.54a.7.7,0,0,0-.7.7ZM15.12,19.09V16.2a.85.85,0,0,1,.84-.84H29.82a.84.84,0,0,1,.84.84v2.89a.84.84,0,0,1-.84.84H16a.85.85,0,0,1-.84-.84Zm9.22,6.32H21.45a.83.83,0,0,1-.84-.83V10.71a.83.83,0,0,1,.84-.83h2.89a.83.83,0,0,1,.83.83V24.58a.83.83,0,0,1-.83.83Z");
        resize(courseSvg, 30, 30);
        resize(liveSvg, 30, 30);
        resize(publishSvg, 30, 30);
        resize(comSvg, 30, 32);
        resize(iSvg, 30, 30);
        resize(orderSvg, 22, 26);

        try {
            popListVbox();
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * This method is used to resize the component of the main page.
     * @return Nothing.
     */
    private void resize(SVGPath svg, double width, double height) {

        double originalWidth = svg.prefWidth(-1);
        double originalHeight = svg.prefHeight(originalWidth);

        double scaleX = width / originalWidth;
        double scaleY = height / originalHeight;

        svg.setScaleX(scaleX);
        svg.setScaleY(scaleY);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources)  {
        ctrl = this;

        populateItems();
        filteredList = new FilteredList<>(items, s -> true);

        filteredList = new FilteredList<>(items, s -> true);

        search.textProperty().addListener(obs -> {

            String filter = search.getText();
            if (filter == null || filter.length() == 0) {
                barInitial();
                clear.setMouseTransparent(true);
                searchIcon.setContent("M15.5 14h-.79l-.28-.27C15.41 12.59 16 11.11 16 9.5 16 5.91 13.09 3 9.5 3S3 5.91 3 9.5 5.91 16 9.5 16c1.61 0 3.09-.59 4.23-1.57l.27.28v.79l5 4.99L20.49 19l-4.99-5zm-6 0C7.01 14 5 11.99 5 9.5S7.01 5 9.5 5 14 7.01 14 9.5 11.99 14 9.5 14z");
            } else {
                barFiltered(filter);
                clear.setMouseTransparent(false);
                searchIcon.setContent("M19 6.41L17.59 5 12 10.59 6.41 5 5 6.41 10.59 12 5 17.59 6.41 19 12 13.41 17.59 19 19 17.59 13.41 12z");

            }
        });

        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * This method is used to remove all children in the list.
     * @return Nothing.
     * @see JSONException,IOException
     */
    @FXML
    public void removeAllList() throws JSONException, IOException{
        listvbox.getChildren().clear();
    }
    /**
     * This method is used to pop the component in the vbox.
     * @return Nothing.
     * @see JSONException,IOException
     */
    @FXML
    public void popListVbox() throws JSONException, IOException {
        JSONArray input;
        int jsonIndex;
        int numInList=0;
        input=new JsonTool("src/gui/livevideo/Livevideo.json").read();
        JSONObject we ;
        String labeltext[]=new String[10];
        for(jsonIndex=0;jsonIndex<input.length()&&numInList<=9;jsonIndex++){
            we = input.getJSONObject(jsonIndex);
            if(we.getBoolean("islike")){
                labeltext[numInList]=we.getString("title");
                numInList++;
            }
            

        }
        labeltext[numInList]="...";
        numInList++;
        Label labelarray[]=new Label[10];
        for(int i=0;i< numInList;i++){
            labelarray[i]=new Label(labeltext[i]);
            labelarray[i].setStyle("-fx-text-fill: black");
            labelarray[i].setFont(Font.font(12));
        }
        for(int i=0;i<numInList;i++){
            VBox vboxTmp=new VBox();
            vboxTmp.getChildren().add(labelarray[i]);
            vboxTmp.setAlignment(Pos.CENTER);
            listvbox.getChildren().add(vboxTmp);
        }
        Button seeMore=new Button("View all");
        seeMore.setId("seeMoreButton");
        seeMore.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event){
                title.setText("Orderdetail");
                body.setContent(control.global.ViewManager.getInstance().get("Orderdetail"));

            }
        });
        VBox vboxTmp=new VBox();
        vboxTmp.getChildren().add(seeMore);
        vboxTmp.setAlignment(Pos.CENTER);
        listvbox.getChildren().add(vboxTmp);
    }

    /**
     * This method is used to change the layout.
     * @return Nothing.
     */
    @FXML
    private void altLayout() {


        int minimum = 70;
        int max = 250;

        if(drawer.getPrefWidth() == max){

            drawer.setPrefWidth(minimum);

            drawer.getChildren().remove(info);
            drawer.getChildren().remove(searchBox);

            for(Node node : views.getChildren()) {
                if(node instanceof Button){
                    ((Button) node).setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                    ((Button) node).setAlignment(Pos.BASELINE_CENTER);
                } else if(node instanceof TitledPane){
                    ((TitledPane) node).setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                    ((TitledPane) node).setAlignment(Pos.BASELINE_CENTER);
                    ((TitledPane) node).setExpanded(false);
                    ((TitledPane) node).setCollapsible(false);
                } else {
                    break;
                }
            }

            avatar.setStrokeWidth(0);
            addEvents();

        } else {
            drawer.setPrefWidth(max);

            drawer.getChildren().addAll(info, searchBox);
            searchBox.toBack();
            info.toBack();
            avatar.toBack();
            avatar.setStrokeWidth(2);
            for(Node node : views.getChildren()){
                if(node instanceof Button){
                    ((Button) node).setContentDisplay(ContentDisplay.LEFT);
                    ((Button) node).setAlignment(Pos.BASELINE_LEFT);
                } else if(node instanceof TitledPane){
                    ((TitledPane) node).setContentDisplay(ContentDisplay.RIGHT);
                    ((TitledPane) node).setAlignment(Pos.BASELINE_RIGHT);
                    ((TitledPane) node).setCollapsible(true);
                } else {
                    break;
                }
            }
        }
    }
    /**
     * This method is used to add events.
     * @return Nothing.
     */
    private void addEvents(){
        VBox drawerContent;

        for (Node node : drawer.getChildren()) { // root
            if (node instanceof ScrollPane){

                drawerContent = (VBox) ((ScrollPane) node).getContent();

                for(Node child : drawerContent.getChildren()){
                    if(child instanceof Button){
                        child.setOnMouseEntered(e -> {
                            popup.setAutoHide(true);
                            if(popup.isShowing())
                                popup.hide();
                        });
                    }

                    else if(child instanceof TitledPane){
                        addEvent(child);
                    }
                }
            }

            else {
                // for others layouts
            }
        }
    }



    private void addEvent(Node node) {

    }
    /**
     * This method is used to initialize the left bar.
     * @return Nothing.
     */
    private void barInitial(){
        filteredList.setPredicate(s -> true);
        scroll.setContent(views);
        VBox tmpView=views;

        views.getChildren().removeAll(course,liveVideo,publish,community,about);


        views.getChildren().add(course);
        views.getChildren().add(liveVideo);
        views.getChildren().add(publish);
        views.getChildren().add(community);
        views.getChildren().add(about);
        course.setContentDisplay(ContentDisplay.LEFT);
        liveVideo.setContentDisplay(ContentDisplay.LEFT);
        publish.setContentDisplay(ContentDisplay.LEFT);
        community.setContentDisplay(ContentDisplay.LEFT);
        about.setContentDisplay(ContentDisplay.LEFT);

        course.setAlignment(Pos.CENTER_LEFT);
        liveVideo.setAlignment(Pos.CENTER_LEFT);
        publish.setAlignment(Pos.CENTER_LEFT);
        community.setAlignment(Pos.CENTER_LEFT);
        about.setAlignment(Pos.CENTER_LEFT);

        about.toBack();
        community.toBack();
        publish.toBack();

        liveVideo.toBack();
        course.toBack();



        hamburger.setMouseTransparent(false);
    }
    /**
     * This method is used to initialize the left bar.
     * @return Nothing.
     * @param filter filter string
     */
    private void barFiltered(String filter){
        views.getChildren().removeAll(about,course,liveVideo,community,publish);
        filteredList.setPredicate(s -> s.getText().toUpperCase().contains(filter.toUpperCase()));
        scroll.setContent(filter(filteredList));

        hamburger.setMouseTransparent(true);
    }
    /**
     * This method is used to set buttton nodes.
     * @return VBox.
     * @param nodes button nodes
     */
    private VBox filter(ObservableList<Button>  nodes){
        VBox vBox = new VBox();
        vBox.getStyleClass().add("drawer-content");
        vBox.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        vBox.setAlignment(Pos.TOP_RIGHT);
        VBox.setVgrow(vBox, Priority.ALWAYS);
        for (Button node : nodes){
            if (node.getGraphic() != null) node.setContentDisplay(ContentDisplay.TEXT_ONLY);
            node.setAlignment(Pos.CENTER_LEFT);
        }
        vBox.getChildren().setAll(nodes);
        return vBox;
    }
    /**
     * This method is used to populate item.
     * @return Nothing.
     */
    private void populateItems() {

        for (Node node : views.getChildren()) {
            if (node instanceof Button) {
                items.add( (Button) node);
            }
        }

    }

    @FXML
    private void clearText(){
        search.clear();
    }

    /**
     * This method is used to set the course page.
     * @return Nothing.
     */
    @FXML
    private void coursePage() {
        title.setText("On-line Course");
        body.setContent(control.global.ViewManager.getInstance().get("videoCategories"));
    }
    /**
     * This method is used to set the live video page.
     * @return Nothing.
     */
    @FXML
    private void liveVideoPage(){
        title.setText("Live Video");
        body.setContent(control.global.ViewManager.getInstance().get("LiveVideoPage0"));
    }
    /**
     * This method is used to set the community page.
     * @return Nothing.
     */
    @FXML
    private void communityPage(){
        title.setText("Community");
        body.setContent(control.global.ViewManager.getInstance().get("community"));
    }
    /**
     * This method is used to set the Publish page.
     * @return Nothing.
     */
    @FXML
    private void publishPage(){
        title.setText("Publish");
        body.setContent(control.global.ViewManager.getInstance().get("Publishment"));
    }

    /**
     * This method is used to enter the profile page.
     * @return Nothing.
     * @see IOException
     */
    @FXML
    private void profileEnter() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(profile.class.getResource("profile.fxml"));
        StackPane profile = (StackPane) loader.load();
        body.setContent(profile);

    }

    private PopOver pop = new PopOver();


    public void openNotification(MouseEvent mouseEvent) {
    }

    public void openMessages(MouseEvent mouseEvent) {
    }
}
