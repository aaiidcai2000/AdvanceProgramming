/**
 * @author Yuan Hao (Alex) Liu s3583320
 * 
 * The class inherit Athlete class and implement the specific compete method,
 * which make Sprinter can only compete in running game and generate the compete result.
 * 
 */
public class Sprinter extends Athlete{
	
	//initialize Sprinter from superclass
	public Sprinter(String _ID, String _name, String _state, int _age) {
		super(_ID, _name, _state, _age);
	}

	@Override
	//compete in the game and return the competition result
	public double compete(String gameType) throws IllegalArgumentException{
		if(gameType!="running") 
			throw new IllegalArgumentException("Athlete can not compete in this type of game");
		return 10*(1+Math.random());
	}

}
