package cu.cs.cpsc2150.project3;

import java.io.*;
import java.util.ArrayList;

/**
 * The AccountData class provides data storage for a collection of Account
 * classes.
 * 
 * @author Andrew
 *
 */

public class AccountData {
	private static ArrayList<Account> userData = new ArrayList<Account>();

	/**
	 * AccountData() will initialize the userData arraylist. The arraylist will
	 * be pull from a serialized arraylist contained in the workspace, or will
	 * initialize an admin account if no file is found.
	 */
	@SuppressWarnings("unchecked")
	public AccountData() {
		try {
			// read from userData.ser to build userData
			FileInputStream fis = new FileInputStream("userData.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);

			userData = (ArrayList<Account>) ois.readObject();
			ois.close();
			fis.close();
		} catch (IOException e) {
			// account data not found, add admin account
			Account admin = new Account("admin", "", "Staff", "Andrew Samuels", "absamue@g.clemson.edu", "843-737-3461",
					userData.size());
			userData.add(admin);
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
		}
	}

	/**
	 * addUser(Account in) takes the given account and adds it to the end of the
	 * user list. The account id of the given account will be set to the id of
	 * the last account in the list plus one.
	 * 
	 * @param in
	 *            Account to add to userData arraylist.
	 */
	public void addUser(Account in) {
		// set id to be 1 + last id
		in.myId = userData.get(userData.size() - 1).myId + 1;
		userData.add(in);
		this.save();
	}

	/**
	 * checkUser(String uname) checks whether an account with the given username
	 * is stored. Requires that identical username accounts cannot be stored in
	 * implementation.
	 * 
	 * @param uname
	 *            Username of the account to find in the list.
	 * @return Returns false if the user is in storage, otherwise returns true.
	 */
	public boolean checkUser(String uname) {
		boolean check = true;

		for (Account acc : userData) {
			if (acc.myUsername.equals(uname))
				check = false;
		}

		return check;
	}

	/**
	 * getUser(String uname) will return the account that has the given
	 * username.
	 * 
	 * @param uname
	 *            Username of the account to find.
	 * @return Returns the account if found, otherwise will return null.
	 */
	public static Account getUser(String uname) {
		Account find = null;
		for (Account acc : userData) {
			if (acc.myUsername.equals(uname)) {
				find = acc;
				break;
			}
		}

		return find;
	}

	/**
	 * getAccount(int id) will return an account that matches the given ID #.
	 * 
	 * @param id
	 *            # of the user to find.
	 * @return returns the found account, or null if asked for an id outside of
	 *         the list.
	 */
	public Account getAccount(int id) {
		if (id >= userData.size() || id < 0)
			return null;
		return userData.get(id);
	}

	/**
	 * removeAccount(Account rem) removes the account from the list specified by
	 * the parameter. Subsequently saves updated array list to disk.
	 * 
	 * @param rem
	 *            Account to remove.
	 */
	public void removeAccount(Account rem) {
		userData.remove(rem);
		int i=0;
		//reset all id numbers in case of not removing last user
		for(Account acc : userData){
			acc.myId = i++;
		}
		this.save();
	}

	/**
	 * verify(String uname, String pass) will determine whether a given
	 * username/password combination is correct.
	 * 
	 * @param uname
	 *            Username of the user to check password of.
	 * @param pass
	 *            Password supplied by caller.
	 * @return Returns true if the combination was successful, otherwise returns
	 *         false.
	 */
	public static boolean verify(String uname, String pass) {
		// get account from username
		Account check = null;
		for (Account acc : userData) {
			if (acc.myUsername.equals(uname)) {
				check = acc;
				break;
			}
		}

		// check if password matches
		if (check != null)
			if (check.myPassword.equals(pass))
				return true;

		return false;

	}

	/**
	 * update(Account old, Account nw) will update an account in the list when
	 * the accounts attributes have changed. Directly replaces the unchanged
	 * account with the new version. Saves account list to disk.
	 * 
	 * @param old
	 *            Old account to be replaced.
	 * @param nw
	 *            New account with updated information to take place of old.
	 */
	public void update(Account old, Account nw) {
		userData.set(old.myId, nw);
		this.save();
	}

	/**
	 * save() utilizes a FileOutputStream in order to save the account
	 * information to disk.
	 */
	public void save() {
		// save user data to file
		FileOutputStream fos;
		try {
			fos = new FileOutputStream("userData.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			oos.writeObject(userData);
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * getSize() returns the size of the arraylist, utilized in building the
	 * account information table.
	 * 
	 * @return Returns size of ArrayList userData.
	 */
	public int getSize() {
		return userData.size();
	}

}
