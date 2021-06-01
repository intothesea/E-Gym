package Test;

import control.MyMD5;
import control.UserDaoImp;
import entity.User;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoImpTest {
    UserDaoImp userImp = new UserDaoImp();
    User u = new User(100001, MyMD5.getEncryptedPwd("1234ABcd"),"testUser","2020-01-01", 0.0,"2018213173@gmail.com","18810512178","");

    UserDaoImpTest() throws UnsupportedEncodingException, NoSuchAlgorithmException {
    }

    @Test
    void insert() {
        userImp.insert(u);
        assertEquals(100001, userImp.selectByName("testUser").getUid());
        assertEquals("testUser", userImp.selectByName("testUser").getName());
        assertEquals("18810512178", userImp.selectByName("testUser").getTel());
        assertEquals("2018213173@gmail.com", userImp.selectByName("testUser").getEmail());
        assertEquals("2020-01-01",userImp.selectByName("testUser").getBirthday());
    }

    @Test
    void selectByName() {
        User test = userImp.selectByName("Luyi");
        assertEquals("Luyi",test.getName());

    }


    @Test
    void selectAll() {
        int userId = 0;
        List<User> userList = userImp.selectAll();
        for(int i = 0; i < userList.size(); i++){
            userId = userList.get(i).getUid();
            if(userId == u.getUid()){
                break;
            }
        }
        assertEquals("testUser",u.getName());

    }
}