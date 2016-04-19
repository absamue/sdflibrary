package cu.cs.cpsc2150.project3;

import java.io.*;
import java.util.ArrayList;

public class AccountData {
	private static ArrayList<Account> userData = new ArrayList<Account>();

	//load hashmap data of existing accounts
	@SuppressWarnings("unchecked")
	public AccountData() {
		try {
			//read from userData.ser to build userData
			FileInputStream fis = new FileInputStream("userData.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);

			userData = (ArrayList<Account>) ois.readObject();
			ois.close();
			fis.close();
		} catch (IOException e) {
			//account data not found, add admin account
			Account admin = new Account("admin", "", "Staff", "Andrew Samuels", 
					"absamue@g.clemson.edu", "843-737-3461", userData.size());
			userData.add(admin);
			Account test = new Account("asdf", "asdf", "Member", "test user", 
					"test@test.com", "123-456-7891", userData.size());
			userData.add(test);
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
		}
	}

	//add user to database
	public void addUser(Account in) {
		//set id to be 1 + last id
		in.myId = userData.get(userData.size()-1).myId+1;
		userData.add(in);
		this.save();
	}
	

	public boolean checkUser(String uname){
		boolean check = true;
		
		for(Account acc : userData){
			if(acc.myUsername.equals(uname))
				check = false;
		}
		
		return check;
	}
	
	
	// return account of requested user
	public static Account getUser(String uname) {
		Account find = null;
		for(Account acc : userData){
			if(acc.myUsername.equals(uname)){
				find = acc;
				break;
			}
		}
		
		return find;
	}

	public Account getAccount(int id){
		return userData.get(id);
	}
	
	public void removeAccount(Account rem){
		userData.remove(rem);
		this.save();
	}
	
	// verify password of given username
	public static boolean verify(String uname, String pass) {
		//get account from username
		Account check = null;
		for(Account acc : userData){
			if(acc.myUsername.equals(uname)){
				check = acc;
				break;
			}
		}
		
		//check if password matches
		if(check != null)
			if (check.myPassword.equals(pass))
				return true;
		
		return false;

	}
	
	public void update(Account old, Account nw){
		userData.set(old.myId, nw);
		this.save();
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

	public int getSize(){
		return userData.size();
	}
	
	public void clearData(){
		userData.clear();
	}
	
}
