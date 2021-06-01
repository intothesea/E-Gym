package control.jsontool;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.*;
/**
 * This class is used to read and write json files.
 * @author Jiaxuan Wang
 */
public class JsonTool {
    private String localPath;
    public String getLocalPath() {
        return localPath;
    }
    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }
    public JsonTool(String localPath) {
        super();
        this.localPath = localPath;
    }
    /**
     * This method reads the json files and returns the JSONArray.
     * @return JSONArray.
     * @see IOException,JSONException
     */
    public JSONArray read() throws IOException, JSONException {
        JSONArray jsonArray=new JSONArray();
        File file=new File(localPath);
        if(!file.exists()){
            System.out.println("Cannot find files");
        }
        FileInputStream fileInputStream=new FileInputStream(file);
        InputStreamReader inputStreamReader=new InputStreamReader(fileInputStream,"utf-8");
        BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
        String Js ="";
        String Line = null;
        while ((Line = bufferedReader.readLine()) != null) {
            Js += Line;
        }
        JSONArray WE=new JSONArray(Js);
        return WE;
    }
    /**
     * This method writes the JSONArray into the json file.
     * @param output the JSONArray output
     * @return JSONArray.
     * @see IOException,JSONException
     */
    public void write(JSONArray output) throws IOException, JSONException{
        Tool tool=new Tool();
        JSONArray jsonArray=output;
        File file=new File(localPath);
        if(!file.exists())
        {
            file.createNewFile();
        }
        FileOutputStream fileOutputStream=new FileOutputStream(file);
        OutputStreamWriter outputStreamWriter=new OutputStreamWriter(fileOutputStream,"utf-8");
        BufferedWriter bufferedWriter= new BufferedWriter(outputStreamWriter);


        String jsonString=jsonArray.toString();
        String JsonString=tool.stringToJSON(jsonString);
        bufferedWriter.write(JsonString);
        bufferedWriter.flush();
        bufferedWriter.close();
    }
}

