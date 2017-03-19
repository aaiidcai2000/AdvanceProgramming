import java.util.ArrayList;

public class Game {
	public static final int firstPlacePoint=5;
	public static final int secondPlacePoint=2;
	public static final int thirdPlacePoint=1;
	
	private ArrayList<Athlete> athletes;
	private Official official;
	private String gameType;
	private String ID;
	
	private Athlete firstPlaceWinner;
	private Athlete secondPlaceWinner;
	private Athlete thirdPlaceWinner;
	
	
	public Game(String _ID,ArrayList<Athlete> _athletes,Official _official,String _gameType){
		ID=_ID;
		athletes=_athletes;
		setOfficial(_official);
		setGameType(_gameType);
	}
	
	public Official getOfficial() {
		return official;
	}
	
	public void setOfficial(Official official) {
		this.official = official;
	}
	
	public String getGameType() {
		return gameType;
	}

	public void setGameType(String gameType) {
		this.gameType = gameType;
	}
	
	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}
	
	public Athlete getFirstPlaceWinner() {
		return firstPlaceWinner;
	}

	public Athlete getSecondPlaceWinner() {
		return secondPlaceWinner;
	}

	public Athlete getThirdPlaceWinner() {
		return thirdPlaceWinner;
	}
	
	public void start(){
		compete(gameType);
		official.summarizeResults();
		announceWinner();
		awardWinner();
	}
	
	public void printResult(){
		System.out.println( 
			   "First place athlete: "+ getFirstPlaceWinner().getName() + 
			   " Second place athlete: "+ getSecondPlaceWinner().getName() +
			   " Third place athlete: "+ getThirdPlaceWinner().getName()
		);
	}
	
	private void compete(String type){
		for(int i=0;i<athletes.size();++i){
			double athleteResult = athletes.get(i).compete(type);
			System.out.println( athleteResult);
			official.readResult(athleteResult);
		}
	}
	
	private void announceWinner(){
		firstPlaceWinner = athletes.get( official.declareTheFirst( ) );
		secondPlaceWinner = athletes.get( official.declareTheSecond( ) );
		thirdPlaceWinner = athletes.get( official.declareTheThird( ) );
	}
	
	private void awardWinner(){
		firstPlaceWinner.addPoints(firstPlacePoint);
		secondPlaceWinner.addPoints(secondPlacePoint);
		thirdPlaceWinner.addPoints(thirdPlacePoint);
	}
	
	
	
	
	
	public static void main(String[] args) {
		// get data from input file
		ArrayList<Athlete> athletes = new ArrayList<Athlete>();
		Official official1=new Official("s0","Ven","Au",33);
		
		athletes.add( new Swimmer("s1","AAAsss","Au",19));
		athletes.add( new SuperAthlete("s2","BBBss","Au",23));
		athletes.add( new Swimmer("s3","CCCww","Au",49));
		athletes.add( new SuperAthlete("s4","DDDDee","Au",39));
		athletes.add( new Swimmer("s5","EEEii","Au",29));
		
		
		Game game1 = new Game("asdasd",athletes,official1,"swimming");
		game1.start();
		game1.printResult();
		
		for(int i=0;i<5;++i){
			System.out.println(athletes.get(i).getPoints());
		}
	}
	

}
