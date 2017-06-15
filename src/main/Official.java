package main;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * The class inherit Participant class and provide specific method for Official to determine the game result.
 * Official can read and summarize all the results of athletes in one game, 
 * then declare who wins in the game.
 * 
 * @author Yuan Hao (Alex) Liu s3583320
 */
public class Official extends Participant {
	/** record Game competition result */
	private Map<Double,Integer> resultList;
	
	/** count number of athlete */
	private int AthleteCount;
	
	/** record the number of winning athletes */
	private int numberOfFirst;
	private int numberOfSecond;
	private int numberOfThird;

	/**
	 * initialize Official with ID,official type,name,nationality,age and the resultList of the game
	 * @param _ID
	 * @param _type
	 * @param _name
	 * @param _age
	 * @param _state
	 */
	public Official(String _ID, String _type, String _name, int _age, String _state) {
		super(_ID, _type, _name, _age, _state);
		resultList=new TreeMap<Double,Integer>();
		AthleteCount=0;
	}

	public Map<Double,Integer> getResultList(){
		return resultList;
	}
	
	/**
	 * the official prepares to read new results of the game
	 */
	public void startReadResult(){
		resultList=new TreeMap<Double,Integer>();
		AthleteCount=0;
	}
	
	/**
	 * the official read the competition result of the game
	 * @param res
	 *        athlete result
	 */
	public void readResult(double res){
		resultList.put(res, AthleteCount++);
	}
	
	
	/**
	 * the official determine the winner base on resultList
	 */
	public void summarizeResults() {
		Iterator<Double> iter = resultList.keySet().iterator();
		numberOfFirst = resultList.get(iter.next());
		numberOfSecond = resultList.get(iter.next());
		numberOfThird = resultList.get(iter.next());
	}

	/**
	 * return the first place athlete 
	 * @return numberOfFirst
	 */
	public int declareTheFirst() {
		return numberOfFirst;
	}
	
	/**
	 * return the second place athlete 
	 * @return numberOfSecond
	 */
	public int declareTheSecond() {
		return numberOfSecond;
	}
	
	/**
	 * return the third place athlete 
	 * @return numberOfThird
	 */
	public int declareTheThird() {
		return numberOfThird;
	}

	
}
