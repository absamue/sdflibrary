package cu.cs.cpsc2150.project3;

@SuppressWarnings("serial")
public class Account implements java.io.Serializable{
	String myUsername;
	String myPassword;
	String myType;
	String myName;
	String myEmail;
	String myPhone;
	boolean staff;
	int myId;
	
	public Account(String uname, String pword, String type, String name, String email, String phone, int id){
		this.myUsername = uname;
		this.myPassword = pword;
		this.myType = type;
		this.myName = name;
		this.myEmail = email;
		this.myPhone = phone;
		this.myId = id;
		
		if(type.equals("Staff"))
			this.staff = true;
	}
	
}
