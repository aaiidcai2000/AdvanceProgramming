/**
 * @author Yuan Hao (Alex) Liu s3583320
 * 
 * The class inherit Athlete class and implement the specific compete method,
 * which make Swimmers can only compete in swimming game and generate the compete result.
 * 
 */
public class Swimmer extends Athlete{
	
	//initialize Swimmer from superclass
	public Swimmer(String _ID, String _name, String _state, int _age) {
		super(_ID, _name, _state, _age);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	//compete in the game and return the competition result
	public double compete(String gameType) throws IllegalArgumentException {
		if(gameType!="swimming") 
			throw new IllegalArgumentException("Athlete can not compete in this type of game");
		return	100*(1+Math.random());
	}

}
