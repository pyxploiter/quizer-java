package assign;

public class User implements java.io.Serializable {
	public String name;
	private String passwd;
	public String role;
	public int score;
	
	public User(String uname) {
		this.name = uname;
	}
	
	public User(String uname, String password, String user_role) {
		this.name = uname;
		this.passwd = password;
		this.role = user_role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String passwd) {
		this.passwd = passwd;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public boolean login(String uname, String password) {
		if (this.name.equals(uname) && this.passwd.equals(password)) {
			return true;
		}
		else {
			return false;
		}
	}
}
