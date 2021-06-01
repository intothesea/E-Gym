package control;

import java.util.List;
import entity.User;

/**
 * Title: userDao
 * Description: The interface userDao contains the interfaces of methods about users,
 * 	including the user selection method, insert method, delete method and upgrade method
 * 
 * @author MingdaJia190019786
 * @version 1.0.1
 * @author MingdaJia190019786
 * @version 2.0.1
 * @author MingdaJia190018786
 * @version 2.5.1
 */
public interface UserDao {
	int insert(User user);

	int delete(String userNo);

	User selectByName(String userName);

	int update(int userNo, String password, String sex, int age, String firstName, String lastName, String Telephone);

	List<User> selectAll();

	int updateStatus(User user, String status);

	int updatePass(int userNo);

}