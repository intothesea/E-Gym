package Control;

import java.util.List;
import Entity.User;

/**
 * Title: userDao Description:
 * 
 * @author Dell
 *
 */
public interface UserDao {
	int insert(User user);

	int delete(String userNo);

	User selectByName(String userName);

	int update(int userNo, String password, String sex, int age, String firstName, String lastName, String Telephone);

	List<User> selectAll();

	int updatePass(int userNo);

}