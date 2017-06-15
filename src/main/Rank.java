package main;

import javafx.beans.property.SimpleStringProperty;

/**
 * 
 * For storing a brief result of a game.
 * 
 * @author Ying-Chieh Huang s3598781
 */

public class Rank {

	private SimpleStringProperty gameID;
	private SimpleStringProperty referee;
	private SimpleStringProperty first;
	private SimpleStringProperty second;
	private SimpleStringProperty third;

	/**
	 * initialize a rank
	 * 
	 * @param gameID
	 * @param referee
	 * @param first
	 * @param second
	 * @param third
	 */
	public Rank(String gameID, String referee, String first, String second, String third) {
		this.gameID = new SimpleStringProperty(gameID);
		this.referee = new SimpleStringProperty(referee);
		this.first = new SimpleStringProperty(first);
		this.second = new SimpleStringProperty(second);
		this.third = new SimpleStringProperty(third);
	}

	public String getGameID() {
		return gameID.get();
	}

	public String getReferee() {
		return referee.get();
	}

	public String getFirst() {
		return first.get();
	}

	public String getSecond() {
		return second.get();
	}

	public String getThird() {
		return third.get();
	}

}
