package main;

/**
 * The class inherit Athlete class and implement the specific compete method,
 * which make Sprinter can only compete in running game and generate the compete result.
 * 
 * @author Yuan Hao (Alex) Liu s3583320
 */
public class Sprinter extends Athlete{
	
	/**
	 * initialize Sprinter from superclass
	 * @param _ID
	 * @param _type
	 * @param _name
	 * @param _age
	 * @param _state
	 */
	public Sprinter(String _ID, String _type , String _name, int _age, String _state) {
		super(_ID, _type, _name, _age, _state);
	}
	
	/**
	 * compete in the game and return the competition result
	 */
	@Override
	public double compete(String gameType) throws IllegalArgumentException{
		if(gameType!="Running") 
			throw new IllegalArgumentException("Athlete can not compete in this type of game");
		return 10*(1+Math.random());
	}

}
