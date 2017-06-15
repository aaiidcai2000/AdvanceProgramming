package main;

/**
 * The class inherit Athlete class and implement the compete method,
 * which make superAthlete can compete in swimming,cycling and running game and generate the corresponding compete result.
 * 
 * @author Yuan Hao (Alex) Liu s3583320
 */
public class SuperAthlete extends Athlete{
	
	/**
	 * initialize SuperAthlete from superclass
	 * @param _ID
	 * @param _type
	 * @param _name
	 * @param _age
	 * @param _state
	 */
	public SuperAthlete(String _ID, String _type, String _name, int _age, String _state) {
		super(_ID, _type, _name, _age, _state);
	}

	
	/**
	 * compete in the game and return the competition result
	 */
	@Override
	public double compete(String gameType) throws IllegalArgumentException {
		if(gameType=="Swimming") return 100*(1+Math.random());
		if(gameType=="Cycling") return 500+300*Math.random();
		if(gameType=="Running") return 10*(1+Math.random());
		else
			throw new IllegalArgumentException(
					"No such sport game, only \"Swimming\",\"Cycling\",\"Running\" game are acceptable"
			);		
	}
}
