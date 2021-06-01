package Test.global;

import control.jsontool.JsonTool;
import org.json.JSONException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JsonToolTest {
    String path = "src/JSON/user.json";
    String path2 = "src/JSON/video.json";
    JsonTool jst = new JsonTool(path);
    @Test
    void getLocalPath() {
        assertEquals(path,jst.getLocalPath());
    }

    @Test
    void setLocalPath() {
        jst.setLocalPath(path2);
        assertEquals(path2,jst.getLocalPath());
    }

    @Test
    void read() throws JSONException, IOException {
        jst.read();

    }

    @Test
    void write() throws JSONException, IOException {

        jst.write(jst.read());
    }
}