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
package GUI.Main;

import com.gn.GNAvatarView;
import com.jfoenix.controls.JFXBadge;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.SVGPath;
import org.controlsfx.control.PopOver;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  08/10/2018
 * Version 2.0
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
    @FXML private TitledPane design;
    @FXML private TitledPane controls;
    @FXML private TitledPane charts;
    @FXML private Button home;
    @FXML private Button  about;
    @FXML private Button hamburger;
    @FXML private SVGPath searchIcon;
    @FXML private StackPane root;
    @FXML private Button clear;
    @FXML private JFXButton config;
    @FXML private VBox drawer;
    @FXML private JFXBadge messages;
    @FXML private JFXBadge notifications;
    @FXML private JFXBadge bg_info;
    @FXML private ToggleGroup group;
    @FXML private TitledPane list;
    @FXML private RadioButton available;
    @FXML private VBox listvbox;
    private FilteredList<Button> filteredList = null;

    public static  final PopOver popConfig = new PopOver();
    public static  final PopOver popup     = new PopOver();

    private ObservableList<Button> items         = FXCollections.observableArrayList();
    private ObservableList<Button> designItems   = FXCollections.observableArrayList();
    private ObservableList<Button> controlsItems = FXCollections.observableArrayList();
    private ObservableList<Button> chartsItems   = FXCollections.observableArrayList();

    private JFXDialog       dialog          = new JFXDialog();
    private JFXDialogLayout dialog_layout   = new JFXDialogLayout();

    public static Main ctrl;

    @Override
    public void initialize(URL location, ResourceBundle resources)  {
        ctrl = this;

        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                cStatus.setFill( ((RadioButton) newValue).getTextFill());
                status.setText(((RadioButton)newValue).getText());
            }
        });
        populateItems();
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

    private void barInitial(){
        filteredList.setPredicate(s -> true);
        scroll.setContent(views);
        ( (VBox) design.getContent()).getChildren().setAll(designItems);
        ( (VBox) controls.getContent()).getChildren().setAll(controlsItems);
        ( (VBox) charts.getContent()).getChildren().setAll(chartsItems);

        views.getChildren().removeAll(home, about);
        views.getChildren().add(home);
        views.getChildren().add(about);
        home.setContentDisplay(ContentDisplay.LEFT);
        about.setContentDisplay(ContentDisplay.LEFT);
        home.setAlignment(Pos.CENTER_LEFT);
        about.setAlignment(Pos.CENTER_LEFT);

        home.toBack();
        about.toFront();
        hamburger.setMouseTransparent(false);
    }

    private void barFiltered(String filter){
        views.getChildren().removeAll(home, about);
        filteredList.setPredicate(s -> s.getText().toUpperCase().contains(filter.toUpperCase()));
        scroll.setContent(filter(filteredList));

        hamburger.setMouseTransparent(true);
    }

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


    @FXML
    private void Class() {
        title.setText("Class list");
        body.setContent(global.ViewManager.getInstance().get("Class"));
    }

    @FXML
    private void Livevideo(){
        title.setText("Live Video");
        body.setContent(global.ViewManager.getInstance().get("LiveVideoPage0"));
    }

    @FXML
    private void Community(){
        title.setText("Community");
        body.setContent(global.ViewManager.getInstance().get("community"));
    }

    @FXML
    private void Publish(){
        title.setText("Publish");
        body.setContent(global.ViewManager.getInstance().get("Publishment"));
    }

    @FXML
    private void About(){
        title.setText("About");
        body.setContent(global.ViewManager.getInstance().get("cards"));
    }

    private PopOver pop = new PopOver();


    public void openNotification(MouseEvent mouseEvent) {
    }

    public void openMessages(MouseEvent mouseEvent) {
    }
}
