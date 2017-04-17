/**
 * @author Yuan Hao (Alex) Liu s3583320
 * 
 * The class provide all the functions of showing game detail/progress
 * It determine the showing speed of progress and showing format.
 * It can also determine the speed of the athletes when showing to audiences to control the tension in the game.
 * It also equip with the capability of dealing with accuracy calculation and scaling the competition result of each 
 * athletes to make showing smoothly.
 * 
 */
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShowGame {
	//change ratio of athletes' speed 
	private static final double PROGRESS_ENTROPY=0.9;
	//determine athletes' speed variance
	private static final int SHUFFLING_PROGRESS_TIME=200;
	//total speed segments for whole game
	private static final int FRAME_SEGMENTS=50;
	// show progress's total length
	private static final int PROGRESS_BAR_LENGTH = 40;
	
	//athletes result
	private ArrayList<Double> results;
	private ArrayList<String> athletesNames;
	//record athletes speed segments
	private ArrayList<ArrayList<Double>> athletesProgress;
	private ArrayList<Double> athletePositions;
	private double scaleResultNumber;
	//how many athletes finish
	private int finishedCount;
	//indicator of the progress of the game showing  
	private int progressIter;
	//record athletes who win
	private Map<Integer,String> winAthletes ;
	private String gameType;
	private String gameID;
	
	
	//Initialize ShowGame with athlete, get showing game ready and initialize each parameters 
	public ShowGame(ArrayList<Athlete> athletes,String _gameType,String _gameID){
		gameType=_gameType;
		gameID=_gameID;
		results = new ArrayList<Double>();
		athletesProgress= new ArrayList<ArrayList<Double>>();
		athletePositions = new ArrayList<Double>();
		athletesNames = new ArrayList<String>();
		winAthletes = new HashMap<Integer,String>();
		for(int i=0;i<athletes.size();++i){
			athletesProgress.add(new ArrayList<Double>());
			athletePositions.add(0.0);
			athletesNames.add( fixedLength(athletes.get(i).getName(), 10) );
		}
		
		scaleResultNumber=1;
		finishedCount=0;
		progressIter=0;
		
	}

	
	//let athletes' name show on left hand site and fix output format
	public static String fixedLength(String string, int length) {
	    return String.format("%1$-"+length+ "s", string);
	}
	
	//to fix inaccuracy of double subtraction
	private static double doubleSub(double v1,double v2) {
		 BigDecimal b1 = new BigDecimal(Double.toString(v1));
		 BigDecimal b2 = new BigDecimal(Double.toString(v2));
		 return b1.subtract(b2).doubleValue();
	}
	
	//to fix inaccuracy of double addition
	private static double doubleAdd(double v1,double v2) {
		 BigDecimal b1 = new BigDecimal(Double.toString(v1));
		 BigDecimal b2 = new BigDecimal(Double.toString(v2));
		 return b1.add(b2).doubleValue();
	}
	
	private static void pauseScreen(int millisec){
		try {
			Thread.sleep(millisec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//read game compete results
	public void addResult(Double result) {
		results.add(result);
	}
	
	//add winner of the game
	public void addWinAthlete(int index,int place){
		switch(place){
			case 1:
				winAthletes.put(index,"The First!!");
				break;
			case 2:
				winAthletes.put(index,"The Second!!");
				break;
			case 3:
				winAthletes.put(index,"The Third!!");
				break;
			default:
				throw new IllegalArgumentException("Illegal win athlete input.");
		}
	}	
	
	//start showing game procedure
	public void start(){
		
		determineResultScale();
		
		generateathletesProgress();
		
		startShow();	
		
	}
	
	//start printing progress on screen/console
	private void startShow() {
		System.out.println("Start Competition...");
		
		printCompeteProgress();
		nextProgress();
		pauseScreen(1000);
		
		while(!CompeteShowFinished()){
			printDelimiter();
			printCompeteProgress();
			nextProgress();
			
			pauseScreen(150);
			printDelimiter();
		}
		
		printDelimiter();
		printCompeteProgress();
		pauseScreen(300);
	}
	
	//delimit each progress
	private void printDelimiter(){
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	}
	
	//print athletes progress
	private void printCompeteProgress(){
		System.out.println("Game "+gameID+" - "+gameType);
		for(int i=0;i<athletesNames.size();++i){
			System.out.print(athletesNames.get(i)+": ");
			printProgressBarFor(i);
			if(athletePositions.get(i)==1 && winAthletes.containsKey(i)){
				System.out.print(" "+ winAthletes.get(i));
			}
			System.out.print("\n");
		}
	}
	
	//print the athlete's progress bar
	private void printProgressBarFor(int Athlete){
		int finished = (int) Math.floor(athletePositions.get(Athlete)*(PROGRESS_BAR_LENGTH-1));
		
		
		System.out.print('|');
		for(int i=0; i < finished ;++i)
			System.out.print('=');
		
		System.out.print('*');
		
		for(int i=PROGRESS_BAR_LENGTH-finished-1;i>0;--i){
			System.out.print('-');
		}
		
		System.out.print("|");
	}

	//calculate each athletes speed segments 
	private void generateathletesProgress(){
		
		for(int i=0;i<athletesNames.size();++i){
			double progressUnit = (1/results.get(i))*scaleResultNumber;
		
			for(int j=0;j<1/progressUnit;++j){
				athletesProgress.get(i).add(progressUnit);
			}
			
			suffleProgress(i);
			
		}
		
		
	}
	
	//vary athletes' speed segments
	private void suffleProgress(int Athlete){
		
		for(int i=0;i<SHUFFLING_PROGRESS_TIME;++i){
			int indexAmount = athletesProgress.get(Athlete).size()-1;
			int addedIndex =(int) Math.floor( Math.random() * indexAmount );
			int deductedIndex=(int) Math.floor( Math.random() * indexAmount  );
			while(addedIndex==deductedIndex){
				addedIndex =(int) Math.floor( Math.random() * indexAmount );
				deductedIndex=(int) Math.floor( Math.random() * indexAmount  );
			}
			
			double deductedOriAmount = athletesProgress.get(Athlete).get(deductedIndex);
			double addedOriAmount = athletesProgress.get(Athlete).get(addedIndex);
			
			if(deductedOriAmount>addedOriAmount){
				//swap
				int temp1 = addedIndex;
				addedIndex = deductedIndex;
				deductedIndex = temp1;
				
				double temp2 = deductedOriAmount;
				deductedOriAmount = addedOriAmount ;
				addedOriAmount  = temp2;
					
			}
			
			
			double changeAmount = deductedOriAmount*Math.random()*PROGRESS_ENTROPY;
			
			double deductedNewAmount=doubleSub(deductedOriAmount,changeAmount);
			double addedNewAmount=doubleAdd(addedOriAmount,changeAmount);
					
			athletesProgress.get(Athlete).set(deductedIndex,deductedNewAmount);
			athletesProgress.get(Athlete).set(addedIndex,addedNewAmount);
		}
	}
	
	//proceed progress
	private void nextProgress(){
		for(int i=0;i<athletePositions.size();++i){
			if(athletesProgress.get(i).size()>progressIter){
				double ori = athletePositions.get(i);
				double nextPosition = doubleAdd(ori,athletesProgress.get(i).get(progressIter));
				if(nextPosition>=1.0){
					nextPosition=1.0;
					++finishedCount;
				}
				athletePositions.set(i, nextPosition);
			}
		}
		++progressIter;
	}

	//scale each compete result in certain range
	private void determineResultScale(){
		double progressUnit = 1.0/results.get(0);
		while(progressUnit*scaleResultNumber > 1.0/FRAME_SEGMENTS){
			scaleResultNumber/=2;
		}
		while(progressUnit*scaleResultNumber *2 < 1.0/FRAME_SEGMENTS){
			scaleResultNumber*=2;
		}
	}
	
	//determine if showing game is finished
	private boolean CompeteShowFinished(){
		return finishedCount==athletesNames.size();
	}
	
	
}
