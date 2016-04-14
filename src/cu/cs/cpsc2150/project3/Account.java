package cu.cs.cpsc2150.project3;

public class Account implements java.io.Serializable{
	String username;
	String password;
	String type;
	String name;
	String email;
	String phone;
	boolean staff;
	int id;
	
	public Account(String uname, String pword){
		this.username = uname;
		this.password = pword;
	}
}
