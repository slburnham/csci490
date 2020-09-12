import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * POS - Point of sale for golf course
 * 
 * @author Team 4
 *
 */
public class POS
{
	public static void display(TeeTime teeTime, Login login)
	{

		Stage window = new Stage();

		window.setTitle("Point of Sale");
		window.setMinWidth(500);

		// Top Section Items
		Text topText = new Text("Point of Sale");
		topText.setFont(Font.font("Tahoma", FontWeight.BOLD, 32));

		Image image = new Image("file:TeamFourLogo.png");
		ImageView theImage = new ImageView(image);
		theImage.setFitWidth(85);
		theImage.setPreserveRatio(true);
		theImage.setSmooth(true);
		theImage.setCache(true);

		// Middle Section items
		Text name = new Text("Name");
		name.setFont(Font.font("Tahoma", FontWeight.BOLD, 16));

		Text teeName = new Text(teeTime.getName());
		teeName.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));

		Text golfers = new Text("Golfers");
		golfers.setFont(Font.font("Tahoma", FontWeight.BOLD, 16));

		Text golfersNum = new Text(teeTime.getGolfers() + "");
		golfersNum.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));

		Text rate = new Text("Rate");
		rate.setFont(Font.font("Tahoma", FontWeight.BOLD, 16));

		Text rateNum = new Text(teeTime.getRate());
		rateNum.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));

		Text price = new Text("Price");
		price.setFont(Font.font("Tahoma", FontWeight.BOLD, 16));

		Text priceNum = new Text(TeeSheetFX.rateToCost(teeTime.getRate()));
		priceNum.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));

		// Middle section layout
		GridPane middleGrid = new GridPane();
		middleGrid.setAlignment(Pos.TOP_LEFT);
		middleGrid.setHgap(10);
		middleGrid.setVgap(10);
		middleGrid.setPadding(new Insets(5, 25, 5, 25));
		middleGrid.add(name, 0, 0);
		middleGrid.add(teeName, 0, 1);
		middleGrid.add(golfers, 3, 0);
		middleGrid.add(golfersNum, 3, 1);
		middleGrid.add(rate, 6, 0);
		middleGrid.add(rateNum, 6, 1);
		middleGrid.add(price, 9, 0);
		middleGrid.add(priceNum, 9, 1);

		// Right Section items
		Text rightText = new Text("Total Price");
		rightText.setFont(Font.font("Tahoma", FontWeight.BOLD, 16));

		Text rightPrice = new Text("$" + rateToInt(teeTime.getRate()) * teeTime.getGolfers());
		rightPrice.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));

		// Right Section
		GridPane rightGrid = new GridPane();
		rightGrid.setAlignment(Pos.TOP_LEFT);
		rightGrid.setHgap(10);
		rightGrid.setVgap(10);
		rightGrid.setPadding(new Insets(5, 25, 5, 25));
		rightGrid.add(rightText, 0, 0);
		rightGrid.add(rightPrice, 0, 1);


		// Bottom Section Items
		Button cash = new Button("Cash");
		cash.setMinWidth(100);
		cash.setOnAction(e -> 
		{
			AlertBox.display("Cash transction complete!");
			window.close();
			TeeSheetFX.teeSheet(login, teeTime.getDay());
		});
		
		Button creditCard = new Button("Credit Card");
		creditCard.setMinWidth(100);
		creditCard.setOnAction(e -> 
		{
			AlertBox.display("Credit Card transction complete!");
			window.close();
			TeeSheetFX.teeSheet(login, teeTime.getDay());

		});
		
		Button exit = new Button("Exit");
		exit.setMinWidth(100);
		exit.setOnAction(e -> 
		{
			window.close();
			AlertBox.display("Transaction Cancelled!");
			TeeSheetFX.teeSheet(login, teeTime.getDay());
		});


		// The top section
		HBox top = new HBox(10);
		top.setPadding(new Insets(25, 25, 5, 25));
		top.setAlignment(Pos.TOP_CENTER);
		top.getChildren().addAll(topText, theImage);

		// Bottom section
		HBox bottom = new HBox(10);
		bottom.setPadding(new Insets(25, 25, 5, 25));
		bottom.setAlignment(Pos.TOP_CENTER);
		bottom.getChildren().addAll(cash, creditCard, exit);

		// The outside sections of the POS
		BorderPane borderPane = new BorderPane();
		borderPane.setTop(top);
		borderPane.setCenter(middleGrid);
		borderPane.setBottom(bottom);
		borderPane.setRight(rightGrid);

		// Setting up the scene
		Scene scene = new Scene(borderPane, 600, 500);
		scene.getStylesheets();
		window.setScene(scene);
		window.show();
	} // End display

	private static double rateToInt(String rate)
	{
		if (rate.equals("Regular"))
			return 100.00;
		if (rate.equals("Hotel"))
			return 80.00;
		if (rate.equals("Internet"))
			return 90.00;

		return 0;
	}

} // End class
