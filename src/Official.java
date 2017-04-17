/**
 * @author Yuan Hao (Alex) Liu s3583320
 * 
 * The class inherit Participant class and provide specific method for Official to determine the game result.
 * Official can read and summarize all the results of athletes in one game, 
 * then declare who wins in the game.
 * 
 */
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class Official extends Participant {
	//record Game competition result
	private Map<Double,Integer> resultList;
	//count number of athlete
	private int AthleteCount=0;
	//record the number of winning athletes
	private int numberOfFirst;
	private int numberOfSecond;
	private int numberOfThird;

	//initialize Official with ID,name,nationality,age and the resultList of the game
	public Official(String _ID, String _name, String _state, int _age) {
		super(_ID, _name, _state, _age);
		resultList=new TreeMap<Double,Integer>();
	}

	public Map<Double,Integer> getResultList(){
		return resultList;
	}
	
	//the official read the competition result of the game
	public void readResult(double res){
		resultList.put(res, AthleteCount++);
	}
	
	
	//the official determine the winner base on resultList
	public void summarizeResults() {
		Iterator<Double> iter = resultList.keySet().iterator();
		numberOfFirst = resultList.get(iter.next());
		numberOfSecond = resultList.get(iter.next());
		numberOfThird = resultList.get(iter.next());
	}

	//return the first place athlete 
	public int declareTheFirst() {
		return numberOfFirst;
	}
	
	//return the second place athlete 
	public int declareTheSecond() {
		return numberOfSecond;
	}
	
	//return the third place athlete 
	public int declareTheThird() {
		return numberOfThird;
	}

	
}
