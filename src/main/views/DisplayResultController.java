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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import main.Ozlympic;
import main.Rank;
import main.Record;

/**
 * 
 * Displaying all the game result including its details.
 * 
 * @author Ying-Chieh Huang s3598781
 */

public class DisplayResultController implements Initializable {

	@FXML
	private Button btnBackMenu;
	@FXML
	private TableView<Rank> tableView;
	@FXML
	private TableView<Record> tableView2;
	@FXML
	private TableColumn<Rank, String> gameID;
	@FXML
	private TableColumn<Rank, String> referee;
	@FXML
	private TableColumn<Rank, String> first;
	@FXML
	private TableColumn<Rank, String> second;
	@FXML
	private TableColumn<Rank, String> third;
	@FXML
	private TableColumn<Record, String> athID;
	@FXML
	private TableColumn<Record, Double> result;
	@FXML
	private TableColumn<Record, Integer> point;

	private ObservableList<Rank> mainDisplay = FXCollections.observableArrayList(Ozlympic.gamedb.top3);

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		/** set cell value factories */
		setCellValueFactories();

		/** get all records from Database for tableview */
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

	/** display the detailed game result when user clicking on a brief record */
	@FXML
	private void handleMousePressed(MouseEvent evert) throws IOException {
		ObservableList<Rank> selectedGame = tableView.getSelectionModel().getSelectedItems();
		ObservableList<Record> gameRecord = FXCollections
				.observableArrayList(Ozlympic.gamedb.getGameRecord(selectedGame.get(0).getGameID()));
		System.out.println("\nGame " + selectedGame.get(0).getGameID() + " rerords:");
		for (Record gr : gameRecord)
			System.out.println(gr.getAthID() + " " + gr.getResult() + " " + gr.getPoint());
		tableView2.setItems(gameRecord);
	}

	/** setting the tableview */
	private void setCellValueFactories() {
		gameID.setCellValueFactory(new PropertyValueFactory<Rank, String>("gameID"));
		referee.setCellValueFactory(new PropertyValueFactory<Rank, String>("referee"));
		first.setCellValueFactory(new PropertyValueFactory<Rank, String>("first"));
		second.setCellValueFactory(new PropertyValueFactory<Rank, String>("second"));
		third.setCellValueFactory(new PropertyValueFactory<Rank, String>("third"));
		athID.setCellValueFactory(new PropertyValueFactory<Record, String>("athID"));
		result.setCellValueFactory(new PropertyValueFactory<Record, Double>("result"));
		point.setCellValueFactory(new PropertyValueFactory<Record, Integer>("point"));

	}

}
