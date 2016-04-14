package cu.cs.cpsc2150.project3;

import java.io.*;
import java.util.HashMap;

public class AccountData {
	private static HashMap<String, Account> userData = new HashMap<String, Account>();

	//load hashmap data of existing accounts
	@SuppressWarnings("unchecked")
	public AccountData() {
		try {
			//read from userData.ser to build userData
			FileInputStream fis = new FileInputStream("userData.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);

			userData = (HashMap<String, Account>) ois.readObject();
			ois.close();
			fis.close();
		} catch (IOException e) {
			//account data not found, add admin account
			Account admin = new Account("admin", "");
			admin.staff = true;
			userData.put("admin", admin);
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
		}
	}

	public void addUser(Account in) {
		userData.put(in.username, in);
	}

	// return account of requested user
	public Account getUser(String username) {
		return userData.get(username);
	}

	// verify password of given username
	public static boolean verify(String username, String pass) {
		Account check = userData.get(username);

		if(check != null)
			if (check.password.equals(pass))
				return true;
		
		return false;

	}

	//save the userData hashmap as to save all of the account files
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
}
