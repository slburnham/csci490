/**
 * Login - An object class for passing login information
 * 
 * @author Team 4
 */
public class Login
{
	private String userName;
	private String password;
	private String uid;

	/**
	 * Default Constructor with invalid credentials (DO NOT USE!!!)
	 */
	public Login()
	{
		// Send this to Translator somehow
		userName = "";
		password = "";
		uid = "";
	} // Default constructor

	/**
	 * Login - The constructor to use to create a valid login
	 * 
	 * @param userName - The user name as a string
	 * @param password - The password as a string
	 */
	public Login(String userName, String password, String uid)
	{
		this.userName = userName;
		this.password = password;
		this.uid = uid;
	}

	// Getters
	/**
	 * getUserName - Gets the name of the user
	 * 
	 * @return the name as a String
	 */
	public String getUserName()
	{
		return userName;
	}

	/**
	 * getPassword - Gets the password of the user
	 * 
	 * @return the password as a String
	 */
	public String getPassword()
	{
		return password;
	}

	/**
	 * getUID - Gets the User ID of the user
	 * 
	 * @return the uid of the user as a String
	 */
	public String getUID()
	{
		return uid;
	}

	public String toString()
	{
		return "username: " + userName + " -- password: " + password + " -- uid: " + uid;
	}

} // End class