package main.views;

import main.*;
import exceptions.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *  
 * Most user interations in this game creating class user need to choose the
 * type of game, 5~8 athletes, and 1 referee for running a game.
 * 
 * @author Ying-Chieh Huang s3598781
 */

public class CreateGameController implements Initializable {

	@FXML
	private Button btnBackMenu;
	@FXML
	private Button btnAddAth;
	@FXML
	private Button btnDelAth;
	@FXML
	private Button btnAddRef;
	@FXML
	private Button btnDelRef;
	@FXML
	private Button btnRunGame;
	@FXML
	private ToggleGroup gameType;
	@FXML
	private RadioButton rbtnSwi;
	@FXML
	private RadioButton rbtnCyc;
	@FXML
	private RadioButton rbtnRun;
	@FXML
	private TableView<Participant> tableView;
	@FXML
	private ListView<String> listAth;
	@FXML
	private ListView<String> listRef;
	@FXML
	private TableColumn<Participant, String> ID;
	@FXML
	private TableColumn<Participant, String> type;
	@FXML
	private TableColumn<Participant, String> name;
	@FXML
	private TableColumn<Participant, Integer> age;
	@FXML
	private TableColumn<Participant, String> state;

	/** for storing each game's data */
	public static ArrayList<Game> games = new ArrayList<>();

	private ObservableList<Participant> allParticipants = FXCollections
			.observableArrayList(Ozlympic.gamedb.participants);

	private ArrayList<Athlete> chosenAths = new ArrayList<Athlete>();

	private int finGamesNo = Ozlympic.gamedb.getFinGamesNo();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		/** Set tableView as multiple selection mode */
		tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		/** Set listAth as multiple selection mode */
		listAth.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		/** set cell value factories */
		setCellValueFactories();

		/** get All participants from Database for tableview */
		tableView.setItems(allParticipants);

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

	/**
	 * monitoring when game type changed, removes the selected athlete's which
	 * type is not matched
	 */
	@FXML
	private void handleRbtns(ActionEvent event) {

		getChosenAths();
		int counter = 0;

		if (rbtnSwi.isSelected()) {
			for (Participant p : chosenAths) {
				if (!p.getType().equals("swimmer")) {
					if (!p.getType().equals("super")) {
						listAth.getItems().remove(p.getID() + "  " + p.getType() + "  " + p.getName());
						counter++;
					}
				}
			}
		} else if (rbtnCyc.isSelected()) {
			for (Participant p : chosenAths) {
				if (!p.getType().equals("cyclist")) {
					if (!p.getType().equals("super")) {
						listAth.getItems().remove(p.getID() + "  " + p.getType() + "  " + p.getName());
						counter++;
					}
				}
			}
		} else if (rbtnRun.isSelected()) {
			for (Participant p : chosenAths) {
				if (!p.getType().equals("sprinter")) {
					if (!p.getType().equals("super")) {
						listAth.getItems().remove(p.getID() + "  " + p.getType() + "  " + p.getName());
						counter++;
					}
				}
			}
		}

		if (counter > 0) {
			try {
				System.out.println(
						counter + " registered athlete(s) which not match the new chosen game type were removed.");
				throw new WrongTypeException(
						counter + " registered athlete(s) which not match the new chosen game type were removed.");
			} catch (WrongTypeException e) {
				showAlert(e.getMessage());
			}
		}

	}

	/**
	 * handling the button actions when user want to select athletes and
	 * referee, and checking the number do not violate the boundary or wrong
	 * type assignment
	 */
	@FXML
	private void handleBtnAddDel(ActionEvent event) {

		ObservableList<Participant> selectedPtcps = tableView.getSelectionModel().getSelectedItems();
		ObservableList<String> registeredAths = listAth.getSelectionModel().getSelectedItems();

		if (event.getSource() == btnAddAth) {
			try {
				if (rbtnSwi.isSelected()) {
					for (Participant p : selectedPtcps) {
						if (!p.getType().equals("swimmer")) {
							if (!p.getType().equals("super")) {
								System.err.println("The participant you chose is not match the selected game type!!");
								throw new WrongTypeException(
										"The participant you chose is not match the selected game type!!");
							}
						}
					}
				} else if (rbtnCyc.isSelected()) {
					for (Participant p : selectedPtcps) {
						if (!p.getType().equals("cyclist")) {
							if (!p.getType().equals("super")) {
								System.err.println("The participant you chose is not match the selected game type!!");
								throw new WrongTypeException(
										"The participant you chose is not match the selected game type!!");
							}
						}
					}
				} else {
					for (Participant p : selectedPtcps) {
						if (!p.getType().equals("sprinter")) {
							if (!p.getType().equals("super")) {
								System.err.println("The participant you chose is not match the selected game type!!");
								throw new WrongTypeException(
										"The participant you chose is not match the selected game type!!");
							}
						}
					}
				}

				for (Participant p : selectedPtcps) {
					for (String string : listAth.getItems()) {
						String temp[] = string.split("\\s");
						if (p.getID().equals(temp[0])) {
							System.err.println("The participant you chose has been chosen already!!");
							throw new ClashPtcpsException("The participant you chose has been chosen already!!");
						}
					}
				}

				if ((selectedPtcps.size() + listAth.getItems().size()) <= 8) {
					for (Participant p : selectedPtcps)
						listAth.getItems().add(p.getID() + "  " + p.getType() + "  " + p.getName());
				} else {
					System.err.println("The game already has 8 athletes registered!!");
					throw new GameFullException("The game already has 8 athletes registered!!");
				}
			} catch (WrongTypeException e) {
				showAlert(e.getMessage());
			} catch (ClashPtcpsException e) {
				showAlert(e.getMessage());
			} catch (GameFullException e) {
				showAlert(e.getMessage());
			}
		} else if (event.getSource() == btnDelAth) {
			for (String s : registeredAths) {
				listAth.getItems().remove(s);
			}
		} else if (event.getSource() == btnAddRef) {
			try {
				if ((selectedPtcps.size() + listRef.getItems().size()) <= 1) {
					if (selectedPtcps.get(0).getType().equals("officer")) {
						listRef.getItems().add(selectedPtcps.get(0).getID() + "  " + selectedPtcps.get(0).getName());
					} else {
						System.err.println("Athlete can not be the referee!!");
						throw new WrongTypeException("Athlete can not be the referee!!");
					}
				} else {
					System.err.println("Please choose only 1 officer to be the referee!!");
					throw new WrongTypeException("Please choose only 1 officer to be the referee!!");
				}
			} catch (WrongTypeException e) {
				showAlert(e.getMessage());
			}
		} else if (event.getSource() == btnDelRef) {
			listRef.getItems().clear();
		}
	}

	/**
	 * handling the button actions when user want to run the game and check if
	 * the athletes is satisfied the required number.
	 */
	@FXML
	private void handleBtnRunGame(ActionEvent event) throws IOException {

		getChosenAths();

		try {
			if (!listRef.getItems().isEmpty()) {
				if (chosenAths.size() >= 5) {
					String ref[] = listRef.getItems().get(0).split("\\s");
					for (Participant p : allParticipants) {
						if (p.getID().equals(ref[0])) {
							if (rbtnSwi.isSelected()) {
								games.add(new Game(("S" + String.format("%02d", finGamesNo + 1)), chosenAths,
										(Official) p, "Swimming"));
								break;
							} else if (rbtnCyc.isSelected()) {
								games.add(new Game(("C" + String.format("%02d", finGamesNo + 1)), chosenAths,
										(Official) p, "Cycling"));
								break;
							} else {
								games.add(new Game(("R" + String.format("%02d", finGamesNo + 1)), chosenAths,
										(Official) p, "Running"));
								break;
							}
						}
					}

					Stage stage = (Stage) btnRunGame.getScene().getWindow();
					BorderPane showGameView = new BorderPane();
					games.get(games.size() - 1).start(showGameView);
					Scene scene = new Scene(showGameView);
					URL url = this.getClass().getResource("showGameStyle.css");
					if (url == null) {
						System.out.println("css not found.use default style");
					}
					String css = url.toExternalForm();
					scene.getStylesheets().add(css);
					stage.setScene(scene);
					stage.show();

				} else {
					System.err.println("Please choose at least 5 athletes to run the game!!");
					throw new TooFewAthleteException("Please choose at least 5 athletes to run the game!!");
				}
			} else {
				System.err.println("The referee has not assigned yet!!");
				throw new NoRefereeException("The referee has not assigned yet!!");
			}
		} catch (TooFewAthleteException e) {
			showAlert(e.getMessage());
		} catch (NoRefereeException e) {
			showAlert(e.getMessage());
		}

	}

	/** setting the tableview */
	private void setCellValueFactories() {
		ID.setCellValueFactory(new PropertyValueFactory<Participant, String>("ID"));
		type.setCellValueFactory(new PropertyValueFactory<Participant, String>("type"));
		name.setCellValueFactory(new PropertyValueFactory<Participant, String>("name"));
		age.setCellValueFactory(new PropertyValueFactory<Participant, Integer>("age"));
		state.setCellValueFactory(new PropertyValueFactory<Participant, String>("state"));
	}

	/** get the chosen athletes for create the game */
	private ArrayList<Athlete> getChosenAths() {
		chosenAths.clear();
		for (String string : listAth.getItems()) {
			String temp[] = string.split("\\s");
			for (Participant p : allParticipants) {
				if (p.getID().equals(temp[0]))
					chosenAths.add((Athlete) p);
			}
		}
		return chosenAths;
	}

	/** pop-out a alert window when user wrong clicks */
	private void showAlert(String errMsg) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error!");
		alert.setHeaderText(null);
		alert.setContentText(errMsg);
		alert.showAndWait();
	}

}
