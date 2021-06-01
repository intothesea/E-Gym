package control.global;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;

import java.util.HashMap;
/**
 * This class is used to control the hopping logic between different pages;
 * reference: https://github.com/Gleidson28/GNControls
 * @author Yucheng Zhang
 */
public class ViewManager {

    private static ViewManager instance;
    private static final HashMap<String, Node> SCREENS = new HashMap<>();
    private static String nameView;

    public ViewManager(){}

    public static ViewManager getInstance() {
        if(instance == null){
            instance =  new ViewManager();
        }
        return instance;
    }

    public void put(String name, Node node){
        nameView = name;
        SCREENS.put(name, node);
    }

    public Node get(String view){
        return SCREENS.get(view);
    }

    public int getSize(){
        return SCREENS.size();
    }

    Node getCurrentView(){
        return SCREENS.get(nameView);
    }

    public ObservableList<Node> getAll(){
        return FXCollections.observableArrayList(SCREENS.values());
    }
}
