package main;

/**
 * 
 * For storing a athlete's record in a game.
 * 
 * @author Ying-Chieh Huang s3598781
 */

public class Record {

	private String gameID;
	private String refID;
	private String time;
	private String athID;
	private Double result;
	private int point;

	/**
	 * initialize a record
	 * 
	 * @param gameID
	 * @param refID
	 * @param time
	 * @param athID
	 * @param result
	 * @param point
	 */
	public Record(String gameID, String refID, String time, String athID, Double result, int point) {
		this.gameID = gameID;
		this.refID = refID;
		this.time = time;
		this.athID = athID;
		this.result = result;
		this.point = point;
	}

	public String getGameID() {
		return gameID;
	}

	public String getRefID() {
		return refID;
	}

	public String getTime() {
		return time;
	}

	public String getAthID() {
		return athID;
	}

	public Double getResult() {
		return result;
	}

	public int getPoint() {
		return point;
	}

}
