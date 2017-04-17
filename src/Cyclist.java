/**
 * @author Yuan Hao (Alex) Liu s3583320
 * 
 * The class inherit Athlete class and implement the specific compete method,
 * which make Cyclists can only compete in cycling game and generate the compete result.
 * 
 */
public class Cyclist extends Athlete{
	
	//initialize Cyclist from superclass
	public Cyclist(String _ID, String _name, String _state, int _age) {
		super(_ID, _name, _state, _age);
		// TODO Auto-generated constructor stub
	}

	@Override
	//compete in the game and return the competition result
	public double compete(String gameType) throws IllegalArgumentException{
		if(gameType!="cycling") 
			throw new IllegalArgumentException("Athlete can not compete in this type of game");
		return 500+300*Math.random();
	}

}
