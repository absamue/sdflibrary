package cu.cs.cpsc2150.project3;

import java.util.ArrayList;

/**
 * The Account class is used to store multiple attributes of a user account in
 * the library.
 * 
 * @author Andrew
 *
 */
@SuppressWarnings("serial")
public class Account implements java.io.Serializable {
	String myUsername;
	String myPassword;
	String myType;
	String myName;
	String myEmail;
	String myPhone;
	boolean staff;
	int myId;

	/**
	 * List to hold the books that this account has checked out.
	 */
	ArrayList<Book> checkedOut;

	/**
	 * Creates an account initializing all of the fields.
	 * 
	 * @param uname
	 *            Username
	 * @param pword
	 *            Password
	 * @param type
	 *            Staff or member type
	 * @param name
	 *            Name of user.
	 * @param email
	 *            Email in valid format. (See AccountValidifier)
	 * @param phone
	 *            Phone # in valid format. (See AccountValidifier)
	 * @param id
	 *            ID # of account.
	 */
	public Account(String uname, String pword, String type, String name, String email, String phone, int id) {
		this.myUsername = uname;
		this.myPassword = pword;
		this.myType = type;
		this.myName = name;
		this.myEmail = email;
		this.myPhone = phone;
		this.myId = id;

		if (type.equals("Staff"))
			this.staff = true;

		checkedOut = new ArrayList<Book>();
	}

	/**
	 * addBook(Book in) will add the given book to this account's checkedOut
	 * list.
	 * 
	 * @param in
	 *            Book to add to list.
	 */
	public void addBook(Book in) {
		checkedOut.add(in);
	}

	/**
	 * remBook(Book out) will remove the given book from this account's
	 * checkedOut list.
	 * 
	 * @param out
	 *            Book to be removed from list.
	 */
	public void remBook(Book out) {
		checkedOut.remove(out);
	}

}
