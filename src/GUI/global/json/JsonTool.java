package GUI.global.json;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.*;
public class JsonTool {
    private String localpath;
    public String getLocalpath() {
        return localpath;
    }
    public void setLocalpath(String localpath) {
        this.localpath = localpath;
    }
    public JsonTool(String localpath) {
        super();
        this.localpath = localpath;
    }
    public JSONArray read() throws IOException, JSONException {
        JSONArray jsonArray=new JSONArray();//创建JSONArray对象
        File file=new File(localpath);
        if(!file.exists()){
            System.out.println("未找到文件");
        }
        FileInputStream fileInputStream=new FileInputStream(file);//创建FileInputStream对象
        InputStreamReader inputStreamReader=new InputStreamReader(fileInputStream,"utf-8");//创建InputStreamReader对象
        BufferedReader bufferedReader=new BufferedReader(inputStreamReader);//创建字符输入流
        String Js ="";
        String Line = null;
        while ((Line = bufferedReader.readLine()) != null) {
            Js += Line;
        }
        JSONArray WE=new JSONArray(Js);//用读取到的字符串实例化JSONArray数组
        return WE;
    }

    public void write(JSONArray output) throws IOException, JSONException{
        Tool tool=new Tool();//创建格式化json字符串工具类
        JSONArray jsonArray=output;//创建JSONArray对象
        File file=new File(localpath);
        if(!file.exists())//判断文件是否存在，若不存在则新建
        {
            file.createNewFile();
        }
        FileOutputStream fileOutputStream=new FileOutputStream(file);//实例化FileOutputStream
        OutputStreamWriter outputStreamWriter=new OutputStreamWriter(fileOutputStream,"utf-8");//将字符流转换为字节流
        BufferedWriter bufferedWriter= new BufferedWriter(outputStreamWriter);//创建字符缓冲输出流对象


        String jsonString=jsonArray.toString();//将jsonarray数组转化为字符串
        String JsonString=tool.stringToJSON(jsonString);//将jsonarrray字符串格式化
        bufferedWriter.write(JsonString);//将格式化的jsonarray字符串写入文件
        bufferedWriter.flush();//清空缓冲区，强制输出数据
        bufferedWriter.close();//关闭输出流
    }
//	public static void main(String[] args) throws IOException, JSONException {
//		JsonTool a=new JsonTool("Test.json");
//		JSONArray input=a.read();
//		JSONArray output=new JSONArray();
//		for(int j=0;j<input.length();j++)
//        {
//            JSONObject we = input.getJSONObject(j);//将JSONArray中的json数据赋值给jsonobject对象
//            System.out.println(we.toString());//以字符串形式输出jsonobject对象
//            output.put(we);
//        }
//		a.write(output);
//	}

}

