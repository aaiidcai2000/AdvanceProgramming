import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class Official extends Participant {
	private Map<Double,Integer> resultList;
	private int AthleteNumber=0;
	private int numberOfFirst;
	private int numberOfSecond;
	private int numberOfThird;

	public Official(String _ID, String _name, String _state, int _age) {
		super(_ID, _name, _state, _age);
		resultList=new TreeMap<Double,Integer>();
	}

	
	public void readResult(double res){
		resultList.put(res, AthleteNumber++);
	}
	
	public void summarizeResults() {
		Iterator<Double> iter = resultList.keySet().iterator();
		numberOfFirst = resultList.get(iter.next());
		numberOfSecond = resultList.get(iter.next());
		numberOfThird = resultList.get(iter.next());
	}

	public int declareTheFirst() {
		return numberOfFirst;
	}
	
	public int declareTheSecond() {
		return numberOfSecond;
	}
	
	public int declareTheThird() {
		return numberOfThird;
	}

	
}
