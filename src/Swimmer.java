
public class Swimmer extends Athlete{

	public Swimmer(String _ID, String _name, String _state, int _age) {
		super(_ID, _name, _state, _age);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public double compete(String gameType){
		return	100*(1+Math.random());
	}

}
