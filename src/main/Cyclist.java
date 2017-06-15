package main;

/**
 * The class inherit Athlete class and implement the specific compete method,
 * which make Cyclists can only compete in cycling game and generate the compete result.
 * 
 * @author Yuan Hao (Alex) Liu s3583320
 */
public class Cyclist extends Athlete{
	
	/**
	 * initialize Cyclist from superclass
	 * @param _ID
	 * @param _type
	 * @param _name
	 * @param _age
	 * @param _state
	 */
	public Cyclist(String _ID, String _type , String _name, int _age, String _state) {
		super(_ID, _type, _name, _age, _state);
	}

	
	/**
	 * compete in the game and return the competition result
	 */
	@Override
	public double compete(String gameType) throws IllegalArgumentException{
		if(gameType!="Cycling") 
			throw new IllegalArgumentException("Athlete can not compete in this type of game");
		return 500+300*Math.random();
	}

}
