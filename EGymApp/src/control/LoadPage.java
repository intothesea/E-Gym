package control;

import javafx.fxml.FXMLLoader;

import java.io.IOException;
/**
 * This class is used to load pages in advance.
 * @author Jiaxuan Wang
 */
public class LoadPage {
    /**
     * This method is used to load pages in advance.
     * @param module package name
     * @param name file name
     * @return Nothing.
     */
    public void load(String module, String name){
        //System.out.println(getClass().getResource("gui/" + module + "/" + name + ".fxml"));
        try {
            control.global.ViewManager.getInstance().put(
                    name,
                    FXMLLoader.load(getClass().getResource("/gui/" + module + "/" + name + ".fxml"))
            );
            //preloaderNotify();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
