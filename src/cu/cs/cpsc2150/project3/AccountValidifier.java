package cu.cs.cpsc2150.project3;

/**
 * The AccountValidifer class verifies that the information used to create or
 * update an account satisfies the restraints of the prompted information.
 * 
 * @author Andrew
 *
 */
public class AccountValidifier implements Validifier {

	/**
	 * New account created from input fields.
	 */
	private Account nwAccount;
	/**
	 * Old account to be updated in case of editing an account.
	 */
	private Account origAccount;
	/**
	 * Error string, initialized when a test in validate() fails.
	 */
	public String error;

	public AccountValidifier(Account nw, Account orig) {
		nwAccount = nw;
		origAccount = orig;
		// called from new account dialog, fill orig with dummy information
		if (origAccount == null)
			origAccount = new Account("Gj-'feBJRFA*4\2{", "", "", "", "", "", 1337);
	}
	
	/**
	 * validate() runs a series of procedural tests to determine if the information of an account is valid.
	 * @return returns false if any of the tests fail. Returns true if all tests complete successfully.
	 */
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
