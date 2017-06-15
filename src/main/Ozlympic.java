package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 
 * It is a sport game called Ozlympic Game, which a player to select one of 3
 * different types of game: swimming, cycling, and running to play. After the
 * game started, this program will display the process of each game, and it will
 * show the result immediately as well.
 * 
 * 
 * @author Ying-Chieh Huang s3598781
 */

public class Ozlympic extends Application {

	/** initiate the GameDB */
	public static GameDB gamedb = new GameDB();

	private Stage primaryStage;

	public static void main(String[] args) {

		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Ozlympic Game");

		/** loading the main menu */
		Parent root = FXMLLoader.load(getClass().getResource("views/Main.fxml"));
		Scene scene = new Scene(root);
		this.primaryStage.setScene(scene);
		this.primaryStage.show();

	}

}