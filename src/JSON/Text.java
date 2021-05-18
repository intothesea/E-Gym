package JSON;

import java.io.*;
import java.util.Random;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

public class Text {

    public static void editJson() throws IOException, JSONException {

        Random random = new Random();
        Tool tool = new Tool();
        JSONArray jsonArray = new JSONArray();
        File file = new File("Test.json");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "utf-8");
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

        for (int i = 0; i < 5; i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Num", random.nextInt(100) + 1);
            jsonObject.put("age", random.nextInt(8) + 18);
            jsonObject.put("Goal", random.nextInt(41) + 60);
            jsonArray.put(jsonObject);
        }
        String jsonString = jsonArray.toString();
        String JsonString = tool.stringToJSON(jsonString);
        bufferedWriter.write(JsonString);
        bufferedWriter.flush();
        bufferedWriter.close();
    }
}
