package Entity;

public class User {
	private int uid;
	private String password;
	private String name;
	private String birthday;
	private double balance;
	private String email;
	private String tel;

	public User() {
		super();
	}

	public User(int uid, String password, String name, String birthday, double balance, String email, String tel) {
		super();
		this.uid = uid;
		this.password = password;
		this.name = name;
		this.birthday = birthday;
		this.balance = balance;
		this.email = email;
		this.tel = tel;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
}
