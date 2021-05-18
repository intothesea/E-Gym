package Control;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Entity.User;
import JSON.Tool;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Title: UserDaoImp Description:
 *
 * @author https://blog.csdn.net/qq_40180411/article/details/81321424 available
 * on 2020-07-01
 * @author Mingda Jia
 * @author Mingda Jia
 * @version 2.0.1
 */
public class UserDaoImp implements UserDao {

    /**
     * Description:
     *
     * @param user
     * @return
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
     * Title: delete Description:
     *
     * @param userNo
     * @return int
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
     * Title:selectById Description:
     *
     * @param userName
     * @return User
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
     * Title: update Description:
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
     * Title: updatePass Description:
     *
     * @param userNo String
     * @return int
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
     * Title: selectAll Description:
     *
     * @return List<User> arraylist
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

                int uId = jsonObject.getInt("id");

                //System.out.println("name:" + name + ";id:" + uId);
                User user = new User(uId, password, name, birthday, balance, email, tel);
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

}
