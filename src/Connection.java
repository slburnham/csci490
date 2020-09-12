import java.sql.*;
import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/*
 * Class to connect to the MySQL Database 
 * Similar to the facade class that has a singleton connection that is established 
 * at the beginning of the program. 
 */
public class Connection
{
	private java.sql.Connection connection;
	private static Connection singleton;

	private Connection()
	{
		Stage window = new Stage();

		window.setTitle("Connecting...");
		window.setMinWidth(300);

		Text sceneTitle = new Text("Connecting to database...");
		sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 32));

		VBox layout = new VBox(10);
		layout.setMinWidth(100);
		layout.getChildren().add(sceneTitle);
		layout.setAlignment(Pos.CENTER);

		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.show();
		
		String host = "jdbc:mysql://sql9.freesqldatabase.com/sql9255339";
		String dbuser = "sql9255339";
		String dbpass = "S8EkeFyZuD";
		try
		{
			this.connection = DriverManager.getConnection(host, dbuser, dbpass);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		window.close();
	}

	public static Connection getInstance()
	{
		if (singleton == null)
		{
			singleton = new Connection();
		}
		return singleton;
	}

	public java.sql.Connection getConnection()
	{
		return this.connection;
	}

	public ArrayList<Login> getLogin()
	{
		try
		{
			java.sql.Connection con = this.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from user");
			// Create an array of all usernames
			ArrayList<Login> userArray = new ArrayList<>();
			while (rs.next())
			{
				String userName = rs.getString("username");
				String password = rs.getString("password");
				String uid = rs.getString("uid");
				Login tempLogin = new Login(userName, password, uid);
				userArray.add(tempLogin);
			}
			System.out.println(userArray);
			return userArray;
		} catch (SQLException error)
		{
			System.out.println(error.getMessage());
			return null;
		}
	}

	public ArrayList<TeeTime> getTeeTimes(int dayToGet)
	{
		try
		{
			java.sql.Connection con = this.getConnection();
			// Prepared statement
			// Select tee times from database
			PreparedStatement stmt = con.prepareStatement("select * from teetime where day = ?");
			stmt.setInt(1, dayToGet);
			ResultSet rs = stmt.executeQuery();
			// Create an array of all tee times
			ArrayList<TeeTime> teeTimeArray = new ArrayList<>();
			while (rs.next())
			{
				String uid = rs.getString("uid");
				String name = rs.getString("name");
				int golfers = rs.getInt("golfers");
				int time = rs.getInt("time");
				String rate = rs.getString("rate");
				int day = rs.getInt("day");
				TeeTime temp = new TeeTime(name, golfers, time, rate, day, uid);
				teeTimeArray.add(temp);
			}
			return teeTimeArray;
		} catch (SQLException error)
		{
			System.out.println(error.getMessage());
			return null;
		}
	}

	public void addTeeTime(TeeTime teeTime)
	{
		try
		{
			java.sql.Connection con = this.getConnection();
			// Prepared statement
			// Insert teeTime into database
			PreparedStatement stmt = con.prepareStatement("insert into teetime values(\"" + teeTime.getUid() + "\", \""
					+ teeTime.getName() + "\", " + teeTime.getGolfers() + ", " + teeTime.getTime() + ", "
					+ teeTime.getDay() + ",\"" + teeTime.getRate() + "\");");
			stmt.executeUpdate();
		} catch (SQLException error)
		{
			System.out.println(error.getMessage());
		}
	}

	public void deleteTeeTime(TeeTime teeTime)
	{
		try
		{
			java.sql.Connection con = this.getConnection();
			// Prepared statement
			// Delete tee time from database
			PreparedStatement stmt = con.prepareStatement(
					"delete from teetime where uid = ? and" + " golfers = ? and time = ? and day = ?");
			stmt.setString(1, teeTime.getUid());
			stmt.setInt(2, teeTime.getGolfers());
			stmt.setInt(3, teeTime.getTime());
			stmt.setInt(4, teeTime.getDay());
			stmt.executeUpdate();
		} catch (SQLException error)
		{
			System.out.println(error.getMessage());
		}
	}

	public void editTeeTime(TeeTime oldTime, TeeTime newTime)
	{
		try
		{
			java.sql.Connection con = this.getConnection();
			// Prepared statement
			// update a previously stored teetime
			PreparedStatement stmt = con.prepareStatement(
					"update teetime set name = ?, golfers = ?, time = ?, rate = ? where name = ? and time = ? and day = ?");
			stmt.setString(1, newTime.getName());
			stmt.setInt(2, newTime.getGolfers());
			stmt.setInt(3, newTime.getTime());
			stmt.setString(4, newTime.getRate());
			stmt.setString(5, oldTime.getName());
			stmt.setInt(6, oldTime.getTime());
			stmt.setInt(7, oldTime.getDay());
			stmt.executeUpdate();
		} catch (SQLException error)
		{
			System.out.println(error.getMessage());
		}
	}

}
// End class
