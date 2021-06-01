package gui.subscriptionpage;

import javafx.fxml.FXML;
import org.json.JSONException;

import java.io.IOException;
/**
 * The interface Template contains the interfaces of methods about TemplateCourse.
 * @author Yucheng Zhang
 */
public interface Template {
    int position= Orderdetail.jsonindex;

    int getPosition();
    @FXML
    void initialize() throws JSONException;
    void exchangeColor() throws IOException, JSONException;
}
