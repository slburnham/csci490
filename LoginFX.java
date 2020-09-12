import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * LoginFX - The class that displays the JavaFX login screen
 * 
 * @author Team 4
 */
public class LoginFX
{
	static Login login;

	/**
	 * display - Displays the Login screen
	 * 
	 * @return - Returns Login credentials, if valid.
	 */
	public static Login display()
	{
		Stage window = new Stage();
		Scene loginScene;

		window.initModality(Modality.APPLICATION_MODAL);

		window.setMinWidth(500);
		window.setTitle("Golf Course Login");

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Text scenetitle = new Text("Welcome");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 0, 0, 2, 1);

		Label userName = new Label("User Name:");
		grid.add(userName, 0, 1, 2, 1);

		TextField userTextField = new TextField(); // Text field for user name
		grid.add(userTextField, 2, 1, 6, 1);

		Label pw = new Label("Password:");
		grid.add(pw, 0, 2, 2, 1);

		PasswordField pwBox = new PasswordField(); // Password field for password
		grid.add(pwBox, 2, 2, 6, 1);

		Button btn = new Button("Sign in");
		btn.setOnAction(e ->
		{
			String tempUserName = userTextField.getText();
			String tempPassword = pwBox.getText();
			boolean goodLogin = Translator.goodLogin(tempUserName, tempPassword);
			if (goodLogin)
			{

				login = new Login(tempUserName, tempPassword, Translator.getUid(tempUserName, tempPassword));
				TeeSheetFX.teeSheet(login, 1);
				window.close();
			} else
			{
				AlertBox.display("Login not allowed!");
			}
		});
		btn.setDefaultButton(true);

		// Image Adding
		Image image = new Image("file:TeamFourLogo.png");
		ImageView theImage = new ImageView(image);
		grid.add(theImage, 9, 0, 1, 9);

		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(btn);
		grid.add(hbBtn, 1, 4);

		final Text actiontarget = new Text();
		grid.add(actiontarget, 1, 6);

		loginScene = new Scene(grid, 300, 275);

		window.setScene(loginScene);
		window.showAndWait();

		return login;

	} // End display

} // End class