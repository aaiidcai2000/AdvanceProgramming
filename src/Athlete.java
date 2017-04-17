/**
 * @author Yuan Hao (Alex) Liu s3583320
 * 
 * The class inherit Participant class and provide the compete method ( athlete can compete in the game)
 * And if the athletes win, they could show how many points they get.
 * It's mainly use for provide the common methods and attributes for polymorphism.
 * 
 */
public abstract class Athlete extends Participant {
	private int points;
	
	//initialize Athlete with ID,name,nationality,age and personal points(score)
	public Athlete(String _ID, String _name, String _state, int _age) {
		super(_ID, _name, _state, _age);
		points =0;
	}
	
	public int getPoints() {
		return points;
	}
	
	//compete in the game and return the competition result
	public abstract double compete(String gameType) throws IllegalArgumentException;

	//award athlete if he or she wins
	public boolean addPoints(int points){
		this.points += points;
		return true;
	}
}
