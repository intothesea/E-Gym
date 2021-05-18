package JSON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TextRead {
    public static void main(String[] args) throws IOException, JSONException {

        File file = new File("user.json");
        if (!file.exists()) {
            System.out.println("File not exists!");
        }
        FileInputStream fileInputStream = new FileInputStream(file);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "utf-8");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String Js = "";
        String Line = null;
        while ((Line = bufferedReader.readLine()) != null) {
            Js += Line;
        }
        bufferedReader.close();
        JSONArray WE = new JSONArray(Js);
        JSONArray output = new JSONArray();
        for (int j = 0; j < WE.length(); j++) {
            JSONObject we = WE.getJSONObject(j);

            System.out.println(we.toString());
            output.put(we);
        }
        Tool tool = new Tool();
        JSONArray jsonArray1 = output;
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "utf-8");
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

        String jsonString = jsonArray1.toString();
        String JsonString = tool.stringToJSON(jsonString);
        bufferedWriter.write(JsonString);
        bufferedWriter.flush();
        bufferedWriter.close();
    }
}
