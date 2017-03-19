
public abstract class Athlete extends Participant {
	private int points;

	public Athlete(String _ID, String _name, String _state, int _age) {
		super(_ID, _name, _state, _age);
		points =0;
	}
	
	public int getPoints() {
		return points;
	}
	
	public abstract double compete(String gameType);

	public boolean addPoints(int points){
		this.points += points;
		return true;
	}
}
