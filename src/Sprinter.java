
public class Sprinter extends Athlete{

	public Sprinter(String _ID, String _name, String _state, int _age) {
		super(_ID, _name, _state, _age);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double compete(String gameSport) {
		// TODO Auto-generated method stub
		return 10*(1+Math.random());
	}

}
