
/*
 * AlertBox is used for all error messages
 */

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * AlertBox - A window that pops up to display a message
 * @author Team 4
 */
public class AlertBox
{
	/**
	 * display - Method to display the alert box
	 * 
	 * @param message - The message to be displayed in the window
	 */
	public static void display(String message)
	{
		Stage window = new Stage();

		window.initModality(Modality.APPLICATION_MODAL);

		window.setTitle("ALERT!");
		window.setMinWidth(300);

		Label sceneTitle = new Label(message);

		Button exit = new Button("Exit");
		exit.setOnAction(e -> window.close());

		VBox layout = new VBox(10);
		layout.getChildren().addAll(sceneTitle, exit);
		layout.setAlignment(Pos.CENTER);

		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();

	}// End display

} // End class