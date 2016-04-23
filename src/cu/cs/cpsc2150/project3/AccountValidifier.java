package cu.cs.cpsc2150.project3;

public class AccountValidifier implements Validifier {

	private Account nwAccount;
	private Account origAccount;
	public String error;

	public AccountValidifier(Account nw, Account orig) {
		nwAccount = nw;
		origAccount = orig;
		if(origAccount == null)
			origAccount = new Account("Gj-'feBJRFA*4\2{", "", "", "", "", "", 1337);
	}

	@Override
	public boolean validate() {

		// check for blank username
		if (nwAccount.myUsername.equals("")) {
			error = "The username field cannot be left blank.";
			return false;
		}
		// make sure usernames are not resued
		if (!MainFrame.userData.checkUser(nwAccount.myUsername)) {
			// if the username is unchanged this is okay
			if (!nwAccount.myUsername.equals(origAccount.myUsername)) {
				error = "Account with this username already exists.";
				return false;
			}
		}

		// check password field
		if (nwAccount.myPassword.equals("")) {
			error = "The password field cannot be blank.";
			return false;
		}

		// check password length
		if (nwAccount.myPassword.length() < 6) {
			error = "Please use a password of at least 6 characters. "
					+ "It is also recommended to use numbers and special characters in your password.";
			return false;
		}

		// check for name
		if (nwAccount.myName.equals("")) {
			error = "The name field cannont be left blank.";
			return false;
		}

		// check for valid email
		String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		if (!nwAccount.myEmail.matches(EMAIL_REGEX)) {
			error = "The email entered is not a valid format";
			return false;
		}

		// check phone number
		String PHONE_REGEX = "^[0-9\\-]*$";
		if (!nwAccount.myPhone.matches(PHONE_REGEX)) {
			error = "The phone number entered is not in a valid format.";
			return false;
		}

		return true;
	}

}
