package control;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import entity.User;
import control.jsontool.Tool;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Title: UserDaoImp
 * Description: A implementation class containing methods for users, implements Interface UserDao
 *
 * @author https://blog.csdn.net/qq_40180411/article/details/81321424 available
 * on 2020-07-01
 * @version 1.0.1
 * @author Mingda Jia
 * @version 1.0.2
 * @author Mingda Jia
 * @version 2.0.1
 */
public class UserDaoImp implements UserDao {

    /**
     * Title: insert
     * Description: Insert a user into the JSON file
     *
     * @param user The user to be inserted.
     * @return int The affected user, if return 1, insert 1 user successfully, if 0, insert with error.
     */
    @Override
    public int insert(User user) {

        try {
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

            JSONObject obj = new JSONObject();
            JSONObject jo = new JSONObject();
            JSONArray appointment = new JSONArray();
            JSONArray collection = new JSONArray();

            obj.put("id", user.getUid());

            jo.put("birthday", user.getBirthday());
            jo.put("password", user.getPassword());
            jo.put("balance", user.getBalance());
            jo.put("name", user.getName());
            jo.put("tel", user.getTel());
            jo.put("email", user.getEmail());
            jo.put("VIPstatus", user.getVIPstatus());
            jo.put("filepath", user.getFilePath());
            obj.accumulate("userInfo", jo);

            obj.put("appointment", appointment);
            obj.put("collection", collection);

            originJson.put(obj);

            String jsonString = originJson.toString();
            Tool tool = new Tool();
            ;
            String JsonString = tool.stringToJSON(jsonString);
            bufferedWriter.write(JsonString);
            bufferedWriter.flush();
            bufferedWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return 0;
    }

    /**
     * Title: delete
     * Description: The method to delete a user from JSON file
     *
     * @param userNo The user to be deleted.
     * @return int Affected user number,  if return 1, delete 1 user successfully, if 0, delete with error.
     */
    @Override
    public int delete(String userNo) {

        try {

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return 0;
    }

    /**
     * Title:selectById
     * Description: The method to select a user from JSON by user's name.
     *
     * @param userName The name of the user we want to find
     * @return User The founded user
     */
    @Override
    public User selectByName(String userName) {

        UserDaoImp udi = new UserDaoImp();
        List<User> userList = udi.selectAll();

        for (int i = 0; i < userList.size(); i++) {
            if (userName.equals(userList.get(i).getName())) {

                //System.out.println("Login Successfully");
                return userList.get(i);
            } else {
                System.out.println("Error!");
            }
        }
        return null;
        //System.out.println("password:" + pwdInDb);
    }

    /**
     * Title: update
     * Description: The method to update user information, not useful by now
     *
     * @param userNo
     * @param password
     * @param sex
     * @param age
     * @param firstName
     * @param lastName
     * @param telephone
     * @return int
     */
    @Override
    public int update(int userNo, String password, String sex, int age, String firstName, String lastName,
                      String telephone) {

        int affectedRow = 0;

        try {

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return affectedRow;
    }

    /**
     * Title: updatePass
     * Description: The method to update a password of a user.
     *
     * @param userNo String The id of the user
     * @return int Affected user number,  if return 1, update 1 user successfully, if 0, update with error.
     */
    @Override
    public int updatePass(int userNo) {

        int affectedRow = 0;
        // String newPass = getPwd();
        try {

        } finally {

        }
        return affectedRow;
    }

    public static String getPwd() {
        Random rand = new Random();
        String cardNnumer = "";
        for (int a = 0; a < 8; a++) {
            cardNnumer += rand.nextInt(10);
        }
        return cardNnumer;
    }

    /**
     * Title: selectAll
     * Description: The method to select all the users from the JSON file.
     *
     * @return List<User> arraylist The list of all the users in the system.
     */
    @Override
    public List<User> selectAll() {

        List<User> userList = new ArrayList<>();

        try {
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
                String birthday = userInfo.getString("birthday");
                String password = userInfo.getString("password");
                double balance = userInfo.getDouble("balance");
                String name = userInfo.getString("name");
                String tel = userInfo.getString("tel");
                String email = userInfo.getString("email");
                String filePath = userInfo.getString("filepath");
                String status = userInfo.getString("VIPstatus");


                int uId = jsonObject.getInt("id");

                //System.out.println("name:" + name + ";id:" + uId);
                User user = new User(uId, password, name, birthday, balance, email, tel, filePath, status);
                userList.add(user);
            }

            bufferedReader.close();
            inputStreamReader.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return userList;
    }

    /**
     * Title: updateStatus
     * Description: The method updating the VIP status of a certain user
     *
     * @param user The user whose status will be changed.
     * @param status The status the user will be changed to.
     * @return int The affected data rows, int == 1, update a user successfully, int == 0, failed.
     */
    @Override
    public int updateStatus(User user, String status) {
        int affectedRow = 0;
        try {
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

            JSONObject obj = new JSONObject();
            JSONObject jo = new JSONObject();
            JSONArray appointment = new JSONArray();
            JSONArray collection = new JSONArray();

            obj.put("id", user.getUid());

            jo.put("birthday", user.getBirthday());
            jo.put("password", user.getPassword());
            jo.put("balance", user.getBalance());
            jo.put("name", user.getName());
            jo.put("tel", user.getTel());
            jo.put("email", user.getEmail());
            jo.put("VIPstatus",status);
            jo.put("filepath", user.getFilePath());
            obj.accumulate("userInfo", jo);

            obj.put("appointment", appointment);
            obj.put("collection", collection);

            originJson.put(obj);

            String jsonString = originJson.toString();
            Tool tool = new Tool();
            ;
            String JsonString = tool.stringToJSON(jsonString);
            bufferedWriter.write(JsonString);
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return affectedRow;
    }





}
