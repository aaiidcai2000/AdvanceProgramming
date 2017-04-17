import java.util.*;

/**
 * @author Ying-Chieh Huang s3598781
 * 
 * It is a text-based game called Ozlympic Game, which a player to select one
 * of 3 different types of game: swimming, cycling, and running to play.
 * In each game, the player can make a prediction of who will be the winner.
 * After all game started, this program will display the process of each game,
 * and it will display the result of your predictions when games finished.
 * 
 * This Ozlympic class provides:
 * - a private method menu(), which provides options for user to execute the 
 *   game functions.
 * - a private method selectGame(), which provides predefined games for user
 * 	 to select one of them to generate athletes for competition in that game.
 * - a private method predictWinner(), which provides a list of joined athletes
 * 	 for user to predict who will be the winner of the game which have been
 *   selected before.
 * 	 a private method displayUsersPrediction(), which could display the games
 *   that user selected and the predictions of each game.
 * - a private method startGame(), which will start all the games are selected
 *   before, and also display the process during competition. It will display
 *   the result of user's predictions when games are all finished.
 * - a private method displayResult(), which will display the competition result
 *   of every athletes in each game.
 * - a private method displayPointsOfAthletes(), which will display the points of
 *   every athletes they got after all games finished.
 */

public class Ozlympic {
	
	public final int MENU_BEGIN = 1;
	public final int MAIN_MANU_END = 7;
	
	// Initiate the GameDB
	private static GameDB gamedb = new GameDB();
	
	// Store the game that user selected
	private static ArrayList<Game> games = new ArrayList<>();
	
	// Store the prediction that user made, which including gameID and the name of athlete
	private static HashMap<String, String> userPrediction = new HashMap<String, String>();
	private static int countOfficial = 0;
	private static boolean gameStarted = false;

	private Scanner reader = new Scanner(System.in);
	private int userInput = 0;

	public static void main(String[] args) {
		
		Ozlympic ozlympic = new Ozlympic();
		ozlympic.menu();

	}
	
	// Provide options for user to execute the game functions
	private void menu(){
		
		boolean userExited = false;
		
		while(!userExited) {
			
			displayMenu();
			userInput = getUserInput();
			
			if(checkMenuInput(userInput)){
				switch(userInput)
				{
				case 1:
					selectGame();
					break;
				case 2:
					predictWinner();
					break;
				case 3:
					displayUsersPrediction();
					break;
				case 4:
					startGame();
					break;
				case 5:
					displayResult();
					break;
				case 6:
					displayPointsOfAthletes();
					break;
				case 7:
					userExited = true;
					break;
				}
			}
		}
		
		System.out.println("Good Bye!");
		System.exit(0);
		
	}
	
	// Display different options
	private void displayMenu(){
		System.out.println("\nOzlympic Game");
		System.out.println("=========================================");
		System.out.println("1. Select a game to generate athletes");
		System.out.println("2. Predict the winner of the game");
		System.out.println("3. Display my predictions");
		System.out.println("4. Start all games");
		System.out.println("5. Display the final results of all games");
		System.out.println("6. Display the points of all athletes");
		System.out.println("7. Exit");
		System.out.println("=========================================");
		System.out.println("Please enter a number of options: ");
	}
	
	// Check user's input is an integer
	private int getUserInput(){
		
		Boolean validInput = false;
		int input = 0;
		
		do{
			try{
				input = reader.nextInt();
				validInput = true;
			} catch (InputMismatchException ime) {
				System.out.println("Please enter the correct number from the menu again!");
				reader = new Scanner(System.in);
			}
		} while(!validInput);
		reader= new Scanner(System.in);
		
		return input;
	}
	
	// Check whether user's input is out of the range of the main menu
	private boolean checkMenuInput(int input)
	{
		if(input < MENU_BEGIN || input > MAIN_MANU_END)
		{
			System.out.println("Please enter number " + MENU_BEGIN + " to " + MAIN_MANU_END + "\n");
			return false;
		}
		return true;
	}
	
	// Check whether user's input is out of the range of the selected games
	private boolean checkNumOfGamesInput(int input)
	{
		if(input < MENU_BEGIN || input > GameDB.NUMBER_OF_GAMES)
		{
			System.out.println("Invalid input!! Return to the main menu now.");
			pause();
			return false;
		}
		return true;
	}
	
	// Check whether user's input is out of the range of the selected games
	private boolean checkPredictInput(int input)
	{
		if(input < MENU_BEGIN || input > games.size())
		{
			System.out.println("Invalid input!! Return to the main menu now.");
			pause();
			return false;
		}
		return true;
	}
	
	// Check whether user's input is out of the range of the list of athletes of that selected game
	private boolean checkAthleteInput(int selectedAthlete)
	{
		if(selectedAthlete < MENU_BEGIN || selectedAthlete > games.get(userInput - 1).getAthletes().size())
		{
			System.out.println("Invalid input!! Return to the main menu now.");
			pause();
			return false;
		}
		return true;
	}
	
	// Pause a while for user to receive the information displayed
	private void pause(){ 
		
		System.out.println("Please press Enter to continue..\n");
		reader.nextLine();
		
	}
	
	// Let user to choose one of the predefined games to generate athletes for competition
	private void selectGame(){
		
		if(gameStarted) {
			System.out.println("\nAll competitions were finished!\n");
			pause();
		} else {
			Boolean validInput = false;
			
			while(!validInput){
				
				System.out.println("\nPlease select a game to generate athletes!");
				System.out.println("=========================================");
				for(int i=1;i<=GameDB.NUMBER_OF_GAMES;i++){
					System.out.println(i + ". " + GameDB.gamesIndex[i - 1] + " - " + GameDB.gamesType.get(GameDB.gamesIndex[i - 1]) + " game");
				}
				System.out.println("=========================================");
				System.out.println("Please enter a number of options: ");
				
				userInput = getUserInput();
				validInput = checkNumOfGamesInput(userInput);
				
				if(!validInput)	break;
				
				/* Generate a new game depends on the game type of the selected gameID from user,
				   then retrieve the athlete data from its game type array in gamedb */
				Game newGame = null;
				if(GameDB.gamesIndex[userInput - 1].startsWith("S")) {
					newGame = new Game(GameDB.gamesIndex[userInput - 1], generateAthleteList(gamedb.swimmers)
							, generateOfficial(), GameDB.gamesType.get(GameDB.gamesIndex[userInput - 1]));
				} else if(GameDB.gamesIndex[userInput - 1].startsWith("C")) {
					newGame = new Game(GameDB.gamesIndex[userInput - 1], generateAthleteList(gamedb.cyclists)
							, generateOfficial(), GameDB.gamesType.get(GameDB.gamesIndex[userInput - 1]));
				} else {
					newGame = new Game(GameDB.gamesIndex[userInput - 1], generateAthleteList(gamedb.sprinters)
							, generateOfficial(), GameDB.gamesType.get(GameDB.gamesIndex[userInput - 1]));
				}
				
				// Check if the new game has been selected already
				Boolean gameExist = false;
				for(Game tmp: games){
					if(tmp.getID() == newGame.getID())
						gameExist = true;
				}
				
				// Check If the generated athletes less than 5 then the game won't be selected successfully
				if(newGame.getAthletes().size() >= GameDB.MIN_PARTICIPANTS){
					if(gameExist){
						for(int i=0;i<games.size();i++){
							if(games.get(i).getID() == newGame.getID()){
								games.set(i, newGame);
							}
						}
					} else {
						games.add(newGame);
					}
					for(int i=0;i<games.size();i++){
						if(userPrediction.containsKey(newGame.getID()))
							userPrediction.remove(newGame.getID());
					}
					System.out.println("\n==============================");
					System.out.println("Game " + newGame.getID() + " created successfully!");
					System.out.println("==============================\n");
				}
				else {
					System.out.println("\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
					System.out.println("Game " + newGame.getID() + " ran failed due to less than 5 athletes were willing to join this game."
							+ "\nyou could select this game again to generate new athletes.\n\n");
					pause();
				}
			}
		}
		
	}
	
	// Predict who will be the winner of the game which have been selected before
	private void predictWinner(){
		
		if(gameStarted) {
			System.out.println("\nAll competitions were finished!\n");
			pause();
		} else if(games.isEmpty()){
			System.out.println("\nPlease run a game first!\n");
			pause();
		} else {
			Boolean validInput = false;
			
			while(!validInput){
				
				System.out.println("\nWhich game do you want to predict the winner?");
				System.out.println("=============================================");
				for(int i=1;i<=games.size();i++)
					System.out.println(i + ". " + games.get(i - 1).getID() + " - " + GameDB.gamesType.get(games.get(i - 1).getID()) + " game");
				System.out.println("=============================================");
				System.out.println("Please enter a number of options: ");
				
				userInput = getUserInput();
				validInput = checkPredictInput(userInput);
				if(!validInput) break;
				
				System.out.println("\nWhich athlete will be the winner?");
				System.out.println("=================================");
				for(int i=1;i<=games.get(userInput - 1).getAthletes().size();i++)
					System.out.println(i + ". " + games.get(userInput - 1).getAthletes().get(i - 1).getName());
				System.out.println("=================================");
				System.out.println("Please enter a number of options: ");
				
				int selectedAthlete = getUserInput();
				validInput = checkAthleteInput(selectedAthlete);
				if(!validInput) break;
				
				// Store gameID mapping with the name of selected athlete
				if(!userPrediction.containsKey(games.get(userInput - 1).getID())){
					userPrediction.put(games.get(userInput - 1).getID()
							, games.get(userInput - 1).getAthletes().get(selectedAthlete - 1).getName());
				} else {
					userPrediction.replace(games.get(userInput - 1).getID()
							, games.get(userInput - 1).getAthletes().get(selectedAthlete - 1).getName());
				}
			}
		}
	}
	
	// Display the games that user selected the and predictions of each game.
	private void displayUsersPrediction(){
		
		if(games.isEmpty()){
			System.out.println("\nThere is no prediction, please run a game first!\n");
			pause();
		} else {
			System.out.println("\nYour predictions: ");
			for(int i=0;i<games.size();i++){
				System.out.print("\nIn game " + games.get(i).getID() + " - " + GameDB.gamesType.get(games.get(i).getID()) + " game");
				if(userPrediction.containsKey(games.get(i).getID())){
					System.out.print(" you guess " + userPrediction.get(games.get(i).getID()) + " will be the winner.\n");
				} else { 
					System.out.print(" you haven't guessed will be the winner.\n");
				}
			}
			System.out.println();
			pause();
		}
	}
	
	/* Start all the games are selected before, and also display the process during competition.
	   It will display the result of user's predictions when games are all finished */
	private void startGame(){
		
		Iterator<Game> iter;
		
		if(gameStarted) {
			System.out.println("\nAll competitions were finished!\n");
			pause();
		} else if(games.isEmpty()){
			System.out.println("\nThere is no game ready to start, please run a game first!\n");
			pause();
		} else {
			iter = games.iterator();
			while(iter.hasNext()){
				iter.next().start();
				pause();
			}
			
			gameStarted = true;
			
			// Compare the value stored in usersPrediction array list to the result generated in FirstPlaceWinner
			System.out.println("The result of your predictions: ");
			for(int i=0;i<games.size();i++){
				if(userPrediction.containsKey(games.get(i).getID())){
					if(userPrediction.get(games.get(i).getID()) == games.get(i).getFirstPlaceWinner().getName()){
						System.out.println("\nCongrats! You made a right prediction of the game: " + games.get(i).getID() 
								+ " - " + GameDB.gamesType.get(games.get(i).getID()) + " game" 
								+ ", the winner is " + games.get(i).getFirstPlaceWinner().getName());
					} else {
						System.out.println("\nToo bad.. You guessed wrong of the game: " + games.get(i).getID() 
								+ " - " + GameDB.gamesType.get(games.get(i).getID()) + " game" 
								+ ", the winner is " + games.get(i).getFirstPlaceWinner().getName());
					}
				} else {
					System.out.println("\nYou didn't make a prediction of the game: " + games.get(i).getID() 
							+ " - " + GameDB.gamesType.get(games.get(i).getID()) + " game");
				}
			}
			System.out.println();
			pause();
		}
		
	}
	
	// Display the competition result of every athletes in each game
	private void displayResult(){
		
		if(!gameStarted) {
			System.out.println("\nThere is no result of any games, please run a game first!\n");
			pause();
		} else {
			for(int i=0;i<games.size();i++){
				games.get(i).printResult();
				System.out.println();
				pause();
			}
		}
		
	}
	
	// Display the points of every athletes they got after all games finished
	private void displayPointsOfAthletes(){
		
		Iterator<Athlete> iter;
		
		if(!gameStarted) {
			System.out.println("\nThere is no result of any games, please run a game first!\n");
			pause();
		} else {
			// Using HashSet to avoid duplicated super athlete data
			HashSet<Athlete> hset = new HashSet<>();
			for(int i=0;i<games.size();i++){
				iter = games.get(i).getAthletes().iterator();
				while(iter.hasNext())
					hset.add(iter.next());
			}
			Athlete[] pointsArray = hset.toArray(new Athlete[hset.size()]);
			
			System.out.println("\nThe points of all athletes: \n");
			for (int i = 0; i < pointsArray.length; i++) {
			  System.out.println(pointsArray[i].getName() + " : " + pointsArray[i].getPoints() + " points.");
			}
			System.out.println();
			pause();
		}
		
	}
	
	// generate 3 to 8 athletes randomly
	private static ArrayList<Athlete> generateAthleteList(Athlete[] input){
		
		int randNumOfParticipants = (int)(Math.random()*(GameDB.MAX_PARTICIPANTS - 3 + 1)) + 3;
		int rand;
		HashSet<Athlete> hset = new HashSet<>();
		
		while(hset.size() < randNumOfParticipants){
			rand = (int)(Math.random()*input.length);
			hset.add(input[rand]);
		}
		
		return new ArrayList<Athlete>(hset);
	}
	
	/* generate 1 official from the beginning in the officials array
	   assuming that each official can only participates in one game */
	private static Official generateOfficial(){
		
		if(countOfficial >= GameDB.NUMBER_OF_OFFICIALS)
			countOfficial = 0;
		
		return gamedb.officials[countOfficial++];
	}

}
