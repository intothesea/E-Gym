package gui.profilepage;

import control.UserDaoImp;
import entity.User;
import gui.App;
import gui.mainpage.Main;
import control.jsontool.Tool;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class is the profile page.
 * @author Yucheng Zhang
 */
public class profile implements Initializable{
    @FXML public  Text name;
    @FXML public  Text id;
    @FXML public  TextField email;
    @FXML public  TextField telephone;
    @FXML public  Text birthday;
    @FXML public  Text balance;
    @FXML Text friend1;
    @FXML Text friend2;
    @FXML Text friend3;
    @FXML Button normal;
    @FXML Button vip;
    @FXML Button ultra;


    String currentName=Main.ctrl.userName;
    /**
     * This method loads json file.
     * @return Nothing.
     */
    public void loadjson(String name)
    {
        try
        {
            File file = new File("src/JSON/user.json");
            if (!file.exists()) {
                System.out.println("file not exist!");
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
            JSONArray originJson = new JSONArray(Js);

            for (int i = 0; i < originJson.length(); i++) {

                JSONObject jsonObject = originJson.getJSONObject(i);
                JSONObject userInfo = jsonObject.getJSONObject("userInfo");
                String yourname = userInfo.getString("name");
                if (yourname.equals(name)) {
                    String birthday = userInfo.getString("birthday");
                    String password = userInfo.getString("password");
                    double balance = userInfo.getDouble("balance");
                    String balance_string=Double.toString(balance);
                    String tel = userInfo.getString("tel");
                    String email = userInfo.getString("email");

                    int uId = jsonObject.getInt("id");
                    String uId_string=Integer.toString(uId);
                    System.out.println(birthday);
                    System.out.println(balance);
                    System.out.println(tel);
                    System.out.println(email);
                    System.out.println(uId);
                    this.name.setText(yourname);
                    this.id.setText(uId_string);
                    this.email.setText(email);
                    this.telephone.setText(tel);
                    this.birthday.setText(birthday);
                    this.balance.setText(balance_string);
                    System.out.println(this.name.getText());
                    System.out.println(this.id.getText());
                    System.out.println(this.email.getText());
                    System.out.println(this.telephone.getText());
                    System.out.println(this.balance.getText());
                }
            }
            bufferedReader.close();
            inputStreamReader.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * This method loads username.
     * @return Nothing.
     */
    @FXML public void information()
    {
        loadjson(Main.ctrl.userName);
    }
    /**
     * This method loads friends information.
     * @return Nothing.
     */
    @FXML public void friendInformation()
    {
        App.decorator.setContent(control.global.ViewManager.getInstance().get("friend_profile1"));

    }
    /**
     * This method loads email information and offer functions of changing email.
     * @return Nothing.
     */
    @FXML public void emailModify()
    {
        String email_str=email.getText();
        System.out.println(email_str);
        try
        {
            File file = new File("src/JSON/user.json");
            if (!file.exists()) {
                System.out.println("file not exist!");
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
            JSONArray originJson = new JSONArray(Js);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
             OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "utf-8");
             BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            for (int i=0;i<originJson.length();i++)
            {
                JSONObject jsonObject = originJson.getJSONObject(i);
                JSONObject userInfo = jsonObject.getJSONObject("userInfo");
                String yourname = userInfo.getString("name");
                if (yourname.equals(Main.ctrl.userName))
                {
                    userInfo.put("email",email_str);
                    System.out.println(userInfo.getString("email"));
                     String jsonString = originJson.toString();
                         Tool tool = new Tool();

                        String JsonString = tool.stringToJSON(jsonString);
                        bufferedWriter.write(JsonString);
                        bufferedWriter.flush();
                        bufferedWriter.close();
                }
            }



            bufferedReader.close();
            inputStreamReader.close();
            fileInputStream.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        loadjson(Main.ctrl.userName);
    }
    /**
     * This method loads telephone number information and offer functions of changing telephone number.
     * @return Nothing.
     */
    @FXML public void telModify()
    {
        String tel_str=telephone.getText();
        System.out.println(tel_str);
        try
        {
            File file = new File("src/JSON/user.json");
            if (!file.exists()) {
                System.out.println("file not exist!");
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
            JSONArray originJson = new JSONArray(Js);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "utf-8");
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            for (int i=0;i<originJson.length();i++)
            {
                JSONObject jsonObject = originJson.getJSONObject(i);
                JSONObject userInfo = jsonObject.getJSONObject("userInfo");
                String yourname = userInfo.getString("name");
                if (yourname.equals(Main.ctrl.userName))
                {
                    userInfo.put("tel",tel_str);
                    System.out.println(userInfo.getString("tel"));
                    String jsonString = originJson.toString();
                    Tool tool = new Tool();

                    String JsonString = tool.stringToJSON(jsonString);
                    bufferedWriter.write(JsonString);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                }
            }



            bufferedReader.close();
            inputStreamReader.close();
            fileInputStream.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        loadjson(Main.ctrl.userName);
    }
    /**
     * This method changes status to ordinary user.
     * @return Nothing.
     */
    @FXML
    private  void statusToNormal() throws JSONException, IOException {
        UserDaoImp udi = new UserDaoImp();
        User usr = udi.selectByName(currentName);
        udi.updateStatus(usr,"Ordinary User");
        Main.ctrl.userVipStatus="Ordinary User";
        Main.ctrl.removeAllList();
        Main.ctrl.sceneInit();
    }
    /**
     * This method changes status to ordinary VIP.
     * @return Nothing.
     */
    @FXML
    private  void statusToVIP() throws JSONException, IOException {
        UserDaoImp udi = new UserDaoImp();
        User usr = udi.selectByName(currentName);
        System.out.println(usr.getName());
        udi.updateStatus(usr,"Ordinary VIP");
        Main.ctrl.userVipStatus="Ordinary VIP";
        Main.ctrl.removeAllList();
        Main.ctrl.sceneInit();
    }
    /**
     * This method changes status to Ultra VIP.
     * @return Nothing.
     */
    @FXML
    private  void statusToUltraVIP() throws JSONException, IOException {
        UserDaoImp udi = new UserDaoImp();
        User usr = udi.selectByName(currentName);
        udi.updateStatus(usr,"Ultra VIP");
        Main.ctrl.userVipStatus="Ultra VIP";
        Main.ctrl.removeAllList();
        Main.ctrl.sceneInit();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.loadjson(currentName);
    }
    public void reinitalize(){
        this.loadjson(currentName);
    }
}


