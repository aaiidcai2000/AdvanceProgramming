/**
 * @author Yuan Hao (Alex) Liu s3583320
 * 
 * The class inherit Athlete class and implement the compete method,
 * which make superAthlete can compete in swimming,cycling and running game and generate the corresponding compete result.
 * 
 */
public class SuperAthlete extends Athlete{
	
	//initialize SuperAthlete from superclass
	public SuperAthlete(String _ID, String _name, String _state, int _age) {
		super(_ID, _name, _state, _age);
	}

	@Override
	//compete in the game and return the competition result
	public double compete(String gameType) throws IllegalArgumentException {
		if(gameType=="swimming") return 100*(1+Math.random());
		if(gameType=="cycling") return 500+300*Math.random();
		if(gameType=="running") return 10*(1+Math.random());
		else
			throw new IllegalArgumentException(
					"No such sport game, only \"swimming\",\"running\",\"cycling\" game are acceptable"
			);		
	}
}
