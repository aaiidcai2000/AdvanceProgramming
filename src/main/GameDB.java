package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import main.views.CreateGameController;

import java.io.File;
import java.sql.*;
import java.text.SimpleDateFormat;

/**
 * 
 * GameDB class provides database initialization and store the obtained data in
 * ArrayList as cache.
 * 
 * @author Ying-Chieh Huang s3598781
 */

public class GameDB {

	/** For storing all participant data */
	public static ArrayList<Participant> participants = new ArrayList<>();
	private String ID;
	private String type;
	private String name;
	private Integer age;
	private String state;

	/** For storing all result data */
	public static ArrayList<Record> records = new ArrayList<>();
	private String gameID;
	private String refID;
	private String time;
	private String athID;
	private Double result;
	private int point;

	/** for storing gameID, referee, and top 3 athletes' name */
	public static ArrayList<Rank> top3 = new ArrayList<>();

	/** for storing each game's detail result */
	private ArrayList<Record> gameRecord = new ArrayList<>();

	/** for storing each athlete's accumulated point */
	public static ArrayList<Record> scoreboard = new ArrayList<>();

	/** for transfer ID to the Name */
	private HashMap<String, String> IDMappingName = new HashMap<String, String>();

	private static Connection con;
	private boolean dbExist = false;

	/**
	 * initialize GameDB, including get all participant data and history results
	 */
	public GameDB() {

		/** checking the DB file is exist or not */
		File db = new File("GameDB.sqlite");
		if (db.exists()) {
			dbExist = true;
		}

		/** connecting to SQLite driver and DB file */
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:GameDB.sqlite");
		} catch (ClassNotFoundException e) {
			System.err.println("SQLite Driver not found!");
		} catch (SQLException e) {
			System.err.println("Game Database not found!");
		}

		participants = getAllParticipants();
		setIDMappingName();
		records = getAllRecords();
		setTop3();
		setPoint();

	}

	/**
	 * checking the DB file is exist or not
	 * 
	 * @return ArrayList<Participant>
	 */
	private ArrayList<Participant> getFromFile() {

		System.out.println("Use file input instead!");
		participants = FileTool.readFrom("participants.txt");

		return participants;
	}

	/**
	 * get all participant data from the DB or text file
	 * 
	 * @return ArrayList<Participant>
	 */
	private ArrayList<Participant> getAllParticipants() {

		if (dbExist) {
			try {

				Statement stmt = con.createStatement();
				ResultSet res = stmt.executeQuery("SELECT * FROM participant");

				while (res.next()) {
					boolean incorrectInputData = false;
					try {
						ID = res.getString(1);
						type = res.getString(2);
						name = res.getString(3);
						age = res.getInt(4);
						state = res.getString(5);
					} catch (Exception e) {
						incorrectInputData = true;
						System.err.println("1 entry ignored due to incorrect input data.");
					}

					boolean isDuplicated = false;
					for (Participant p : participants) {
						if (p.getID().equals(ID)) {
							isDuplicated = true;
							System.err.println("1 entry ignored due to " + p.getID() + " is duplcated.");
							break;
						}
					}

					/**
					 * checking input data are not missing or duplicated of
					 * attribute
					 */
					if (!incorrectInputData && !isDuplicated) {
						if (type.equals("super")) {
							participants.add(new SuperAthlete(ID, type, name, age, state));
						} else if (type.equals("swimmer")) {
							participants.add(new Swimmer(ID, type, name, age, state));
						} else if (type.equals("cyclist")) {
							participants.add(new Cyclist(ID, type, name, age, state));
						} else if (type.equals("sprinter")) {
							participants.add(new Sprinter(ID, type, name, age, state));
						} else if (type.equals("officer")) {
							participants.add(new Official(ID, type, name, age, state));
						} else {
							System.err.println("not match any accepted types");
						}
					}
				}
			} catch (SQLException sqle) {
				System.err.println("SQL query executed failed!");
			}
		} else {
			return getFromFile();
		}
		return participants;
	}

	/**
	 * put game result into database
	 * 
	 * @param gid
	 * @param pid
	 * @param rank
	 */
	public void addGameResult(String gid, String pid, Map<String, Double> rank) {

		if (dbExist) {
			try {

				Date timeStamp = new Date();
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
				Iterator<Integer> iter = Game.AWARD_POINTS.iterator();

				for (Map.Entry<String, Double> entry : rank.entrySet()) {
					con.prepareStatement(
							"INSERT INTO gameresult VALUES('" + gid + "', '" + pid + "', '" + df.format(timeStamp)
									+ "', '" + entry.getKey() + "', " + entry.getValue() + ", " + iter.next() + ")")
							.execute();
				}

				System.out.println("\nThe results stored into database successfully.");
				updateCache();

			} catch (SQLException sqle) {
				System.err.println("SQL query executed failed!");
			}
		} else {
			System.out.println("\nDue to DB connection failed, the results will store into text file only.");
			updateCache();
		}
	}

	/** update the cache data after database had new data */
	private void updateCache() {
		records.clear();
		records = getAllRecords();
		setTop3();
		setPoint();
	}

	/**
	 * get all participant data from the DB or text file
	 * 
	 * @return int
	 */
	public int getFinGamesNo() {

		if (dbExist) {
			try {

				Statement stmt = con.createStatement();
				ResultSet res = stmt.executeQuery("SELECT COUNT(DISTINCT gid) FROM gameresult");

				return res.getInt(1);

			} catch (SQLException sqle) {
				System.err.println("SQL query executed failed!");
			}
		} else {
			System.out.println("No Database connection, game ID will begin from 0");
		}
		return 0;
	}

	/**
	 * get all game result data from the DB
	 * 
	 * @return ArrayList<Participant>
	 */
	private ArrayList<Record> getAllRecords() {

		if (dbExist) {
			try {

				Statement stmt = con.createStatement();
				ResultSet res = stmt.executeQuery("SELECT * FROM gameresult");

				boolean isEmpty = true;
				while (res.next()) {
					gameID = res.getString(1);
					refID = res.getString(2);
					time = res.getString(3);
					athID = res.getString(4);
					result = res.getDouble(5);
					point = res.getInt(6);
					records.add(new Record(gameID, refID, time, athID, result, point));
					isEmpty = false;
				}
				if (isEmpty) {
					System.out.println("There is no history record.");
					records = getCurrentResult();
				}

			} catch (SQLException sqle) {
				System.err.println("SQL query executed failed!");
			}
		} else {
			return getCurrentResult();
		}
		return records;
	}

	/**
	 * get game result data from the current games when DB is not available
	 * 
	 * @return ArrayList<Record>
	 */
	private ArrayList<Record> getCurrentResult() {
		records.clear();
		System.out.println("\nTry to load current game result instead.");
		if (!CreateGameController.games.isEmpty()) {
			for (Game g : CreateGameController.games) {
				Map<String, Double> resultList = g.rank;
				Iterator<Map.Entry<String, Double>> iter = resultList.entrySet().iterator();
				Map.Entry<String, Double> entries;
				Date timeStamp = new Date();
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
				Iterator<Integer> iterPoint = Game.AWARD_POINTS.iterator();
				for (int i = 0; i < resultList.size(); i++) {
					entries = iter.next();
					records.add(new Record(g.getID(), g.getOfficial().getID(), df.format(timeStamp), entries.getKey(),
							entries.getValue(), iterPoint.next()));
				}
			}
		} else {
			System.out.println("No game ran yet!");
		}
		return records;
	}

	/**
	 * get a specific game detailed result
	 * 
	 * @param gid
	 * @return ArrayList<Record>
	 */
	public ArrayList<Record> getGameRecord(String gid) {
		gameRecord.clear();
		if (!records.isEmpty()) {
			for (Record r : records) {
				if (r.getGameID().equals(gid)) {
					gameRecord.add(new Record(r.getGameID(), r.getRefID(), r.getTime(), IDMappingName.get(r.getAthID()),
							r.getResult(), r.getPoint()));
				}
			}
		}
		return gameRecord;
	}

	/** sorted out top 3 athlete's data of each game result */
	private void setTop3() {
		top3.clear();
		if (!records.isEmpty()) {
			String gid = records.get(0).getGameID();
			String rid = records.get(0).getRefID();
			ArrayList<String> top3Aths = new ArrayList<>();
			for (Record r : records) {
				if (r.getGameID().equals(gid)) {
					for (Record gr : getGameRecord(r.getGameID())) {
						top3Aths.add(gr.getAthID());
					}
				} else {
					top3.add(new Rank(gid, IDMappingName.get(rid), top3Aths.get(0), top3Aths.get(1), top3Aths.get(2)));
					top3Aths.clear();
					gid = r.getGameID();
					rid = r.getRefID();
				}
			}
			for (Record gr : getGameRecord(gid)) {
				top3Aths.add(gr.getAthID());
			}
			top3.add(new Rank(gid, IDMappingName.get(rid), top3Aths.get(0), top3Aths.get(1), top3Aths.get(2)));
		}
	}

	/**
	 * sorted out each athlete's accumulated point and sorting in descending
	 * order
	 */
	private void setPoint() {
		scoreboard.clear();
		if (!records.isEmpty()) {
			HashMap<String, Integer> scores = new HashMap<String, Integer>();
			for (Record r : records) {
				if (scores.keySet().contains(r.getAthID())) {
					scores.replace(r.getAthID(), scores.get(r.getAthID()) + r.getPoint());
				} else {
					scores.put(r.getAthID(), r.getPoint());
				}
			}
			List<Map.Entry> entryList = new ArrayList<>(scores.entrySet());
			Comparator<Map.Entry> sortByValue = (e1, e2) -> {
				return ((Integer) e2.getValue()).compareTo((Integer) e1.getValue());
			};
			Collections.sort(entryList, sortByValue);
			Map<String, Integer> sortedScores = new LinkedHashMap<>();
			for (Map.Entry e : entryList)
				sortedScores.put((String) e.getKey(), (Integer) e.getValue());

			Iterator<Map.Entry<String, Integer>> iter = sortedScores.entrySet().iterator();
			Map.Entry<String, Integer> entries;
			for (int i = 0; i < scores.size(); i++) {
				entries = iter.next();
				scoreboard.add(new Record("GameID", "RefID", "Time", IDMappingName.get(entries.getKey()), 0.0,
						entries.getValue()));
			}
		}
	}

	/** sorted out the ID mapping to its name */
	private void setIDMappingName() {
		for (Participant p : participants) {
			IDMappingName.put(p.getID(), p.getName());
		}
	}

}