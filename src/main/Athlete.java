package main;

/**
 * The class inherit Participant class and provide the compete method ( athlete can compete in the game)
 * And if the athletes win, they could show how many points they get.
 * It's mainly use for provide the common methods and attributes for polymorphism.
 * 
 * @author Yuan Hao (Alex) Liu s3583320
 */
public abstract class Athlete extends Participant {
	/** athlete's points gain in games */
	private int points;
	
	/**
	 * initialize Athlete with ID,athlete type,name,nationality,age and personal points(score)
	 * @param _ID
	 * @param _type
	 * @param _name
	 * @param _age
	 * @param _state
	 */
	public Athlete(String _ID, String _type, String _name, int _age, String _state) {
		super(_ID, _type, _name, _age, _state);
		points =0;
	}
	
	public int getPoints() {
		return points;
	}
	
	/**
	 * compete in the game and return the competition result
	 */
	public abstract double compete(String gameType) throws IllegalArgumentException;

	/**
	 * award athlete if he or she wins
	 * @param points
	 * @return true or false
	 */
	public boolean addPoints(int points){
		this.points += points;
		return true;
	}
}
