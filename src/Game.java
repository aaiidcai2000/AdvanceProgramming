/**
 * @author Yuan Hao (Alex) Liu s3583320
 * 
 * The class provide all the function needed in one game.
 * It including the game detail,such as game ID, game type(swimming or cycling..)..
 * And it has its own official and athletes related in the game.
 * It also can show the game results, award the winner of the game and record the result.
 * It also can control the function of showing the game detail/progress.
 * 
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class Game {
	public static final int FIRST_PLACE_POINT=5;
	public static final int SECOND_PLACE_POINT=2;
	public static final int THIRD_PLACE_POINT=1;
	
	private static final int OUTPUT_DIGITS_AFTER_DECIMAL_POINT=2;
	private ArrayList<Athlete> athletes;
	private Official official;
	private ShowGame showGame;
	
	//the type of the game, only "swimming","running","cycling" are acceptable
	private String gameType;
	private String ID;
	
	private Athlete firstPlaceWinner;
	private Athlete secondPlaceWinner;
	private Athlete thirdPlaceWinner;
	
	//initialize game with ID, athletes, official, gameType
	public Game(String _ID,ArrayList<Athlete> _athletes,Official _official,String _gameType){
		ID=_ID;
		athletes=_athletes;
		setOfficial(_official);
		setGameType(_gameType);
		showGame= new ShowGame(athletes,gameType,ID);
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
	
	//return the first place winner
	public Athlete getFirstPlaceWinner() {
		return firstPlaceWinner;
	}

	//return the second place winner
	public Athlete getSecondPlaceWinner() {
		return secondPlaceWinner;
	}
	
	//return the third place winner
	public Athlete getThirdPlaceWinner() {
		return thirdPlaceWinner;
	}
	
	//round number to which digit after decimal point
	private static double keepPlaceAfterDecimalPoint(double num,int place){
		double scaler = Math.pow(10,place);
		return Math.round(num*scaler)/scaler;
	}
	
	//start the game  
	public void start(){
		compete(gameType);
		official.summarizeResults();
		announceWinner();
		awardWinner();
		showGame.start();
		printResult();
	}
	
	//show the game result
	public void printResult(){
		
		System.out.print("\n\nGame "+ID+" - "+gameType+" result:7\n");
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
	
	//let each athletes compete in the game
	private void compete(String type){
		for(int i=0;i<athletes.size();++i){
			double athleteResult = athletes.get(i).compete(type);
			showGame.addResult(athleteResult);
			official.readResult(athleteResult);
		}
	}
	
	//determine the winner of the game
	private void announceWinner(){
		firstPlaceWinner = athletes.get( official.declareTheFirst( ) );
		secondPlaceWinner = athletes.get( official.declareTheSecond( ) );
		thirdPlaceWinner = athletes.get( official.declareTheThird( ) );
		showGame.addWinAthlete(official.declareTheFirst( ) ,1);
		showGame.addWinAthlete(official.declareTheSecond( ) ,2);
		showGame.addWinAthlete(official.declareTheThird( ) ,3);
	}
	
	//award winner and add point to them
	private void awardWinner(){
		firstPlaceWinner.addPoints(FIRST_PLACE_POINT);
		secondPlaceWinner.addPoints(SECOND_PLACE_POINT);
		thirdPlaceWinner.addPoints(THIRD_PLACE_POINT);	
	}
	

}
