/**
 * TeeTime - A tee time for a tee sheet
 * 
 * @author Team 4
 */
public class TeeTime
{
	// Attributes
	private String name;
	private int golfers;
	private int time;
	private String rate;
	private int day;
	private String uid;

	// Constructors
	/**
	 * TeeTime - Default constructor(DO NOT USE!!!)
	 */
	@SuppressWarnings("unused")
	private TeeTime()
	{
		name = "";
		golfers = 0;
		time = 0;
		rate = "";
		day = 0;
		uid = "";
	} // End default constructor

	/**
	 * TeeTime - Constructor for a tee time
	 * 
	 * @param name    - Name of the golfer as a String
	 * @param golfers - Number of golfers in that tee time as an int
	 * @param time    - The tee time as an int
	 * @param rate    - The rate the golfers booked as a String
	 * @param day     - The day the golfers are playing on as an int
	 * @param uid     - The user id of the person booking the tee time as a String
	 */
	public TeeTime(String name, int golfers, int time, String rate, int day, String uid)
	{
		this.name = name;
		this.golfers = golfers;
		this.time = time;
		this.rate = rate;
		this.day = day;
		this.uid = uid;
	} // End constructor

	// Setters
	public void setName(String name)
	{
		this.name = name;
	}

	public void setGolfers(int golfers)
	{
		this.golfers = golfers;
	}

	public void setTime(int time)
	{
		this.time = time;
	}

	public void setRate(String rate)
	{
		this.rate = rate;
	}

	public void setDay(int day)
	{
		this.day = day;
	}

	public void setUid(String uid)
	{
		this.uid = uid;
	}

	// Getters
	public String getName()
	{
		return name;
	}

	public int getGolfers()
	{
		return golfers;
	}

	public int getTime()
	{
		return time;
	}

	public String getRate()
	{
		return rate;
	}

	public int getDay()
	{
		return day;
	}

	public String getUid()
	{
		return uid;
	}

	// toString
	/**
	 * toString - Displays the attributes of the Tee Time object
	 */
	public String toString()
	{
		return "Day: " + day + "   Time: " + time + "   Name: " + name + "   Golfers: " + golfers + "   Rate: " + rate
				+ "    User ID: " + uid;
	}

} // End Class
