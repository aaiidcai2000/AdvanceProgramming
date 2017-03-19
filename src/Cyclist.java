
public class Cyclist extends Athlete{

	public Cyclist(String _ID, String _name, String _state, int _age) {
		super(_ID, _name, _state, _age);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double compete(String gameType) {
		// TODO Auto-generated method stub
		return 500+300*Math.random();
	}

}
