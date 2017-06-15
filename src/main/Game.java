package main;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * The class provide all the function needed in one game.
 * It including the game detail,such as game ID, game type(swimming or cycling..)..
 * And it has its own official and athletes related in the game.
 * It also can show the game results, award the winner of the game and record the result.
 * It also can control the function of showing the game detail/progress.
 * 
 * @author Yuan Hao (Alex) Liu s3583320
 */
public class Game {
	
	/** define point the winner will get */
	public static final int FIRST_PLACE_POINT=5;
	public static final int SECOND_PLACE_POINT=2;
	public static final int THIRD_PLACE_POINT=1;
	
	/** for storing result in DB */
	public static final ArrayList<Integer> AWARD_POINTS = new ArrayList<Integer>(Arrays.asList(5,2,1,0,0,0,0,0));
	
	/** round the result to which level */
	private static final int OUTPUT_DIGITS_AFTER_DECIMAL_POINT=2;
	
	/** for storing result in DB */
	public LinkedHashMap<String,Double> rank;
	
	private ArrayList<Athlete> athletes;
	private Official official;
	private ShowGame showGame;
	
	/** the type of the game, only "swimming","running","cycling" are acceptable */
	private String gameType;
	private String ID;
	
	private Athlete firstPlaceWinner;
	private Athlete secondPlaceWinner;
	private Athlete thirdPlaceWinner;
	
	/**
	 * initialize game with ID, athletes, official, gameType
	 * @param _ID
	 * @param _athletes
	 * @param _official
	 * @param _gameType
	 */
	public Game(String _ID,ArrayList<Athlete> _athletes,Official _official,String _gameType){
		ID=_ID;
		athletes=_athletes;
		setOfficial(_official);
		setGameType(_gameType);
		showGame= new ShowGame(athletes,gameType,ID);
		rank = new LinkedHashMap<String,Double>();
	}
	
	public ArrayList<Athlete> getAthletes(){
		return athletes;
	}
	
	public Official getOfficial() {
		return official;
	}
	
	public void setOfficial(Official official) {
		this.official = official;
	}
	
	public String getGameType() {
		return gameType;
	}

	public void setGameType(String gameType) {
		this.gameType = gameType;
	}
	
	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}
	
	/**
	 *  the first place winner
	 * @return Athlete
	 */
	public Athlete getFirstPlaceWinner() {
		return firstPlaceWinner;
	}

	/**
	 * return the second place winner
	 * @return Athlete
	 */
	public Athlete getSecondPlaceWinner() {
		return secondPlaceWinner;
	}
	
	/**
	 * return the third place winner
	 * @return Athlete
	 */
	public Athlete getThirdPlaceWinner() {
		return thirdPlaceWinner;
	}
	
	/**
	 * round number to which digit after decimal point
	 * @param num
	 * @param place
	 * @return double
	 */
	private static double keepPlaceAfterDecimalPoint(double num,int place){
		double scaler = Math.pow(10,place);
		return Math.round(num*scaler)/scaler;
	}
	
	/**
	 * start the game  
	 * @param showPane
	 * 	      the pane that will show the game in GUI
	 */
	public void start(BorderPane showPane){
		compete(gameType);
		official.summarizeResults();
		announceWinner();
		awardWinner();
		showGame.setPane(showPane);
		showGame.start();
		printResult();
		generateRank();
		// write to DB
		Ozlympic.gamedb.addGameResult(ID, official.getID(), rank);
		//write to textfile
		outputResult("gameResults.txt");
		
	}
	
	/**
	 * show the game result in console
	 */
	public void printResult(){
		
		System.out.print("\n\nGame "+ID+" - "+gameType+" result:\n");
		Map<Double,Integer> resultList = official.getResultList();
		Iterator<Map.Entry<Double, Integer>> iter = resultList.entrySet().iterator();
		Map.Entry<Double, Integer> entries = iter.next();
		System.out.println( 
				getFirstPlaceWinner().getName() 
				+ ": " 
				+ keepPlaceAfterDecimalPoint(entries.getKey(),OUTPUT_DIGITS_AFTER_DECIMAL_POINT) 
				+ " seconds, First place");
		entries=iter.next();
		System.out.println( 
				getSecondPlaceWinner().getName() 
				+ ": " 
				+ keepPlaceAfterDecimalPoint(entries.getKey(),OUTPUT_DIGITS_AFTER_DECIMAL_POINT) 
				+ " seconds, Second place");
		entries=iter.next();
		System.out.println( 
				getThirdPlaceWinner().getName() 
				+ ": " 
				+ keepPlaceAfterDecimalPoint(entries.getKey(),OUTPUT_DIGITS_AFTER_DECIMAL_POINT) 
				+ " seconds, Third place");
		
		
		
		for(int i=0;i<resultList.size()-3;++i){
			entries=iter.next();
			System.out.println( 
					athletes.get(entries.getValue()).getName() 
					+ ": " 
					+ keepPlaceAfterDecimalPoint(entries.getKey(),OUTPUT_DIGITS_AFTER_DECIMAL_POINT) 
					+ " seconds");
		}
		
	}
	
	/**
	 * output the game result to file
	 * @param fileName
	 */
	public void outputResult(String fileName){
		String data="";
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
		
		data+="\n\n"+ID+", "+official.getID()+", "+df.format(date);
		data=data.substring(0, data.length()-1);
		data+="\n";
		
		Map<Double,Integer> resultList = official.getResultList();
		Iterator<Map.Entry<Double, Integer>> iter = resultList.entrySet().iterator();
		Map.Entry<Double, Integer> entries = iter.next();
		data+= getFirstPlaceWinner().getID()
				+ ", " 
				+ keepPlaceAfterDecimalPoint(entries.getKey(),OUTPUT_DIGITS_AFTER_DECIMAL_POINT) 
				+ ", "+ FIRST_PLACE_POINT+"\n";
		entries=iter.next();
		data+= getSecondPlaceWinner().getID()
				+ ", " 
				+ keepPlaceAfterDecimalPoint(entries.getKey(),OUTPUT_DIGITS_AFTER_DECIMAL_POINT) 
				+ ", "+ SECOND_PLACE_POINT+"\n";
		entries=iter.next();
		data+= getThirdPlaceWinner().getID()
				+ ", " 
				+ keepPlaceAfterDecimalPoint(entries.getKey(),OUTPUT_DIGITS_AFTER_DECIMAL_POINT) 
				+ ", "+ THIRD_PLACE_POINT+"\n";
		
		
		
		for(int i=0;i<resultList.size()-3;++i){
			entries=iter.next();
			data+= athletes.get(entries.getValue()).getID()
					+ ", " 
					+ keepPlaceAfterDecimalPoint(entries.getKey(),OUTPUT_DIGITS_AFTER_DECIMAL_POINT) 
					+ ", 0\n";
		}
		
		try {
			FileTool.write(fileName, data, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * let each athletes compete in the game
	 * @param type
	 * 		  gameType
	 */
	private void compete(String type){
		official.startReadResult();
		for(int i=0;i<athletes.size();++i){
			double athleteResult = athletes.get(i).compete(type);
			showGame.addResult(athleteResult);
			official.readResult(athleteResult);
		}
	}
	
	/**
	 * determine the winner of the game
	 */
	private void announceWinner(){
		firstPlaceWinner = athletes.get( official.declareTheFirst( ) );
		secondPlaceWinner = athletes.get( official.declareTheSecond( ) );
		thirdPlaceWinner = athletes.get( official.declareTheThird( ) );
	}
	
	/**
	 * award winner and add point to them
	 */
	private void awardWinner(){
		firstPlaceWinner.addPoints(FIRST_PLACE_POINT);
		secondPlaceWinner.addPoints(SECOND_PLACE_POINT);
		thirdPlaceWinner.addPoints(THIRD_PLACE_POINT);	
	}
	
	/**
	 * reorganize the game result for storing to database
	 */
	private void generateRank(){
		Map<Double,Integer> resultList = official.getResultList();
		Iterator<Map.Entry<Double, Integer>> iter = resultList.entrySet().iterator();
		Map.Entry<Double, Integer> entries;
		for(int i=0;i<resultList.size();i++){
			entries=iter.next();
			rank.put(athletes.get(entries.getValue()).getID(), keepPlaceAfterDecimalPoint(entries.getKey(),OUTPUT_DIGITS_AFTER_DECIMAL_POINT));
		}
	}

}
