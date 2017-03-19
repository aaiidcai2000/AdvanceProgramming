
public class SuperAthlete extends Athlete{

	public SuperAthlete(String _ID, String _name, String _state, int _age) {
		super(_ID, _name, _state, _age);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double compete(String gameType) {
		if(gameType=="swimming") return 100*(1+Math.random());
		if(gameType=="cycling") return 500+300*Math.random();
		if(gameType=="swimming") return 10*(1+Math.random());
		else
			System.out.println("No such sport game,Error!");
			return 0;
	}

}
