package main.views;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The class control the first menu of GUI
 * It handles the three button events 
 * and style the Title
 * 
 * @author Yuan Hao (Alex) Liu s3583320
 */
public class MainController {
	
	@FXML 
	private Button goCreateGame;
	@FXML 
	private Button goDisplayResult;
	@FXML 
	private Button goDisplayPoint;
	
	@FXML 
	private Text bgTitle;
	
	@FXML
	/**
	 * Style the title to make it look better
	 */
    private void initialize() {
		Blend blend = new Blend();
		blend.setMode(BlendMode.MULTIPLY);

		DropShadow ds = new DropShadow();
		ds.setColor(Color.rgb(254, 235, 66, 0.3));
		ds.setOffsetX(5);
		ds.setOffsetY(5);
		ds.setRadius(5);
		ds.setSpread(0.2);

		blend.setBottomInput(ds);

		DropShadow ds1 = new DropShadow();
		ds1.setColor(Color.web("#f13a00"));
		ds1.setRadius(20);
		ds1.setSpread(0.2);

		Blend blend2 = new Blend();
		blend2.setMode(BlendMode.MULTIPLY);

		InnerShadow is = new InnerShadow();
		is.setColor(Color.web("#feeb42"));
		is.setRadius(9);
		is.setChoke(0.8);
		blend2.setBottomInput(is);

		InnerShadow is1 = new InnerShadow();
		is1.setColor(Color.web("#f13a00"));
		is1.setRadius(5);
		is1.setChoke(0.4);
		blend2.setTopInput(is1);

		Blend blend1 = new Blend();
		blend1.setMode(BlendMode.MULTIPLY);
		blend1.setBottomInput(ds1);
		blend1.setTopInput(blend2);

		blend.setTopInput(blend1);

		bgTitle.setEffect(blend);
		
		
    }
	
	@FXML
	/**
	 * handle the all the main menu buttons' events
	 * @param event
	 * @throws IOException
	 */
	private void handleButtonAction(ActionEvent event) throws IOException {
		
		Stage stage = null;
		Parent root = null;
		
		if(event.getSource() == goCreateGame) {
			stage = (Stage) goCreateGame.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("/main/views/CreateGame.fxml"));
		} else if (event.getSource() == goDisplayResult) {
			stage = (Stage) goDisplayResult.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("/main/views/DisplayResult.fxml"));
		} else if (event.getSource() == goDisplayPoint) {
			stage = (Stage) goDisplayPoint.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("/main/views/DisplayPoint.fxml"));
		}
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
	
}
