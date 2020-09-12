import java.util.*;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

/**
 * The tee sheet in JavaFX to be displayed. Also includes the main for the
 * program to be run.
 * 
 * @author Team 4
 */
public class TeeSheetFX extends Application
{
	static Button[] editButtons = new Button[100];
	static Button[] deleteButtons = new Button[100];
	static Button[] posButtons = new Button[100];
	static TeeTime[] teeTimes = new TeeTime[100];

	public static void main(String[] args)
	{
		//String name, int golfers, int time, String rate, int day, String uid
		System.out.println("Started");
		launch(args);
		System.out.println("Ended");
	} // End main

	@Override
	public void start(Stage primaryStage)
	{
		LoginFX.display();
	} // Start end

	/**
	 * teeSheet - The tee sheet to be displayed given a valid login and day
	 * 
	 * @param login - The login credentials of the user
	 * @param day   - The day to login
	 */
	static void teeSheet(Login login, int day)
	{
		System.out.println("Tee sheet Displayed");

		Stage window = new Stage();
		window.setMinWidth(725);
		window.setTitle("Golf Course Tee Sheet");
		Scene teeSheetScene;

		// Set up the top section of the tee sheet
		GridPane topDisplay = new GridPane();
		topDisplay.setAlignment(Pos.TOP_LEFT);
		topDisplay.setHgap(10);
		topDisplay.setVgap(10);
		topDisplay.setPadding(new Insets(25, 25, 5, 25));

		// Image for the Tee sheet
		Image image = new Image("file:TeamFourLogo.png");
		ImageView theImage = new ImageView(image);
		theImage.setFitWidth(85);
		theImage.setPreserveRatio(true);
		theImage.setSmooth(true);
		theImage.setCache(true);
		topDisplay.add(theImage, 27, 0, 1, 3);

		// Text for the tee sheet
		Text scenetitle2 = new Text("America Country Club");
		scenetitle2.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		topDisplay.add(scenetitle2, 0, 0, 15, 1);

		Text dayText = new Text("January");
		dayText.setFont(Font.font("Tahoma", FontWeight.BOLD, 12));
		topDisplay.add(dayText, 9, 1, 4, 1);

		ChoiceBox<Integer> dayEntry = new ChoiceBox<>();
		dayEntry.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23,
				24, 25, 26, 27, 28, 29, 30, 31);
		dayEntry.setValue(day);
		topDisplay.add(dayEntry, 13, 1, 4, 1);

		Text welcomeText = new Text("User: " + login.getUserName());
		welcomeText.setFont(Font.font("Tahoma", FontWeight.BOLD, 12));
		topDisplay.add(welcomeText, 0, 1, 3, 1);

		Text timeText = new Text("Time");
		timeText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
		topDisplay.add(timeText, 0, 2, 1, 1);

		Text groupNameText = new Text("Group Name");
		groupNameText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
		topDisplay.add(groupNameText, 4, 2, 1, 1);

		Text groupSizeText = new Text("Golfers");
		groupSizeText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
		topDisplay.add(groupSizeText, 9, 2, 1, 1);

		Text groupRateText = new Text("Rate");
		groupRateText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
		topDisplay.add(groupRateText, 14, 2, 1, 1);

		Text groupCostText = new Text("Cost");
		groupCostText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
		topDisplay.add(groupCostText, 19, 2, 1, 1);

		// Set up the middle of the tee sheet
		GridPane teeGrid = new GridPane();
		teeGrid.setAlignment(Pos.TOP_LEFT);
		teeGrid.setHgap(10);
		teeGrid.setVgap(10);
		teeGrid.setPadding(new Insets(5, 25, 5, 25));

		// Add the tee times here through a loop
		String tempTime = "";
		int row = 0;

		// Retrieve the tee times for the day being displayed
		ArrayList<TeeTime> teeTimesTemp = new ArrayList<>();
		teeTimesTemp = Translator.getTeeTimes(day);

		// Display the array
		System.out.println("------------");
		for (int i = 0; i < teeTimesTemp.size(); i++)
		{
			System.out.println(teeTimesTemp.get(i).toString());
		}
		System.out.println("------------");

		int time = 700;
		int prevTime = 650;
		while (time <= 1400) // To generate all the tee times
		{
			// If there are tee times in the ArrayList
			if (teeTimesTemp.size() != 0)
			{

				// If the previous time is equal to the current time
				if (teeTimesTemp.get(0).getTime() == prevTime)
				{
					if ((time / 10) % 10 == 0)
					{
						time -= 50;
					} else
					{
						time -= 10;
					}
				}

				// If the tee times match
				if (time == teeTimesTemp.get(0).getTime())
				{
					teeTimes[row] = teeTimesTemp.get(0);

					Text teeName = new Text(teeTimesTemp.get(0).getName());
					teeName.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
					teeGrid.add(teeName, 2, row);

					Text teeGolfers = new Text(teeTimesTemp.get(0).getGolfers() + "");
					teeGolfers.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
					teeGrid.add(teeGolfers, 10, row);

					Text teeRate = new Text(teeTimesTemp.get(0).getRate());
					teeRate.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
					teeGrid.add(teeRate, 16, row);

					Text teeCost = new Text(rateToCost(teeTimesTemp.get(0).getRate()));
					teeCost.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
					teeGrid.add(teeCost, 20, row);

					// Edit - delete buttons
					Button edit = new Button("Edit");
					editButtons[row] = edit;
					teeGrid.add(editButtons[row], 22, row);
					editButtons[row].setOnAction(e ->
					{
						Button tempEB = (Button) e.getSource();
						int tempRow = 0;
						for (int i = 0; i < editButtons.length; i++) // To find a match for the button to find the row
						{
							if (tempEB.equals(editButtons[i]))
							{
								tempRow = i;
							}
						}
						window.close();
						EntryFormFX.display(login, day, teeTimes[tempRow], true);
					});

					Button delete = new Button("Delete");
					deleteButtons[row] = delete;
					teeGrid.add(delete, 23, row);
					deleteButtons[row].setOnAction(e ->
					{
						Button tempEB = (Button) e.getSource();
						int tempRow = 0;
						for (int i = 0; i < deleteButtons.length; i++) // To find a match for the button to find the row
						{
							if (tempEB.equals(deleteButtons[i]))
							{
								tempRow = i;
							}
						}
						Translator.deleteTeeTime(teeTimes[tempRow]);

						AlertBox.display("Tee Time Deleted!");
						window.close();
						TeeSheetFX.teeSheet(login, dayEntry.getValue());
					});
					
					Button pos = new Button("POS");
					posButtons[row] = pos;
					teeGrid.add(pos, 24, row);
					posButtons[row].setOnAction(e ->
					{
						Button tempEB = (Button) e.getSource();
						int tempRow = 0;
						for (int i = 0; i < posButtons.length; i++) // To find a match for the button to find the row
						{
							if (tempEB.equals(posButtons[i]))
							{
								tempRow = i;
							}
						}
						window.close();
						POS.display(teeTimes[tempRow], login);
						
					});
					teeTimesTemp.remove(0);

				} else // If the tee times do not match
				{
					teeTimes[row] = new TeeTime("PlaceHolder", 0, 0, "None", day, "Tester");
				}

			} // End if times are in ArrayList

			if (time < 1200)
			{
				tempTime = time + " AM";
			} else if (time < 1300)
			{
				tempTime = time + " PM";
			} else
			{
				tempTime = (time - 1200) + " PM";
			}

			tempTime = tempTime.substring(0, tempTime.length() - 5) + ":" + tempTime.substring(tempTime.length() - 5);

			if (time != prevTime)
			{
				Text teeTime = new Text(tempTime);
				teeTime.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
				teeGrid.add(teeTime, 0, row);
			}

			prevTime = time;
			// Increment time only if there is not a skip

			if ((time / 10) % 10 == 5)
			{
				time += 50;
			} else
			{
				time += 10;
			}

			row++;

		} // End loop

		BorderPane borderPane = new BorderPane();

		// Buttons and their actions when pressed
		Button addTeeTime = new Button("Add a Tee Time");
		addTeeTime.setOnAction(e ->
		{
			window.close();
			EntryFormFX.display(login, dayEntry.getValue(), new TeeTime("PlaceHolder", 0, 0, "None", day, "Tester"),
					false);
		});

		Button logOut = new Button("Log Out");
		logOut.setOnAction(e ->
		{
			window.close();
			LoginFX.display();
		});

		Button changeDay = new Button("Change Day");
		topDisplay.add(changeDay, 19, 1, 9, 1);
		changeDay.setOnAction(e ->
		{
			window.close();
			TeeSheetFX.teeSheet(login, dayEntry.getValue());
		});

		HBox bottomOptions = new HBox(10);
		bottomOptions.setPadding(new Insets(5, 25, 25, 25));
		bottomOptions.setAlignment(Pos.BOTTOM_RIGHT);
		bottomOptions.getChildren().addAll(addTeeTime, logOut);

		ScrollPane scroll = new ScrollPane(teeGrid);

		borderPane.setTop(topDisplay);
		borderPane.setCenter(scroll);
		borderPane.setBottom(bottomOptions);

		teeSheetScene = new Scene(borderPane, 600, 500);
		teeSheetScene.getStylesheets();
		window.setScene(teeSheetScene);
		window.show();
	} // End teeSheet

	/**
	 * rateToCost - Helper method to change the rate to money
	 * 
	 * @param rate - The rate to convert
	 * @return the dollar amount that the rate converts to as a String to include
	 *         "$"
	 */
	public static String rateToCost(String rate)
	{
		if (rate.equals("Regular"))
			return "$100.00";
		if (rate.equals("Hotel"))
			return "$80.00";
		if (rate.equals("Internet"))
			return "$90.00";

		return "Bad Rate";
	} // End rateToCost

} // End class
