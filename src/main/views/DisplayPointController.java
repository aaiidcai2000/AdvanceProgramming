package main.views;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.Ozlympic;
import main.Record;

/**
 * 
 * Displaying all the athlete accumulated points.
 * 
 * @author Ying-Chieh Huang s3598781
 */

public class DisplayPointController implements Initializable {

	@FXML
	private Button btnBackMenu;
	@FXML
	private TableView<Record> tableView;
	@FXML
	private TableColumn<Record, String> athID;
	@FXML
	private TableColumn<Record, Integer> point;

	private ObservableList<Record> mainDisplay = FXCollections.observableArrayList(Ozlympic.gamedb.scoreboard);

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		/** setting the tableview */
		athID.setCellValueFactory(new PropertyValueFactory<Record, String>("athID"));
		point.setCellValueFactory(new PropertyValueFactory<Record, Integer>("point"));

		/** get all athlete point data from Database for tableview */
		tableView.setItems(mainDisplay);

	}

	/** handling the button action to back to main menu */
	@FXML
	private void handleBtnBackMenu(ActionEvent event) throws IOException {
		Stage stage = (Stage) btnBackMenu.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("/main/views/Main.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

}
