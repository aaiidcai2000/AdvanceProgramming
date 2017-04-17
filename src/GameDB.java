import java.util.HashMap;

/**
 * @author Ying-Chieh Huang s3598781
 * 
 * GameDB class provides predefined game data and athletes information
 * We assuming there are 7 athletes in each sport game: swimming, cycling,
 * and running, and there are 3 super athletes could join all these sport
 * games. There are 10 officials for as referee in each game as well.
 * 
 */

public class GameDB {
	
	public static final int NUMBER_OF_GAMES = 6;
	public static final int MAX_PARTICIPANTS = 8;
	public static final int MIN_PARTICIPANTS = 5;
	public static final int NUMBER_OF_OFFICIALS = 10;
	
	// Assuming there are only 6 games in this Ozlympic Game
	public static String gamesIndex[] = {"S01", "C02", "R03", "S04", "C05", "R06"};
	
	// This HashMap stores gameID and its game type of each game
	public static HashMap<String, String> gamesType = new HashMap<String, String>();
	
	// There are four different type of athletes
	public Athlete[] superatheletes;
	public Athlete[] swimmers;
	public Athlete[] cyclists;
	public Athlete[] sprinters;
	
	public Official[] officials;
	
	public GameDB()
	{
		gamesType.put("S01", "swimming");
		gamesType.put("C02", "cycling");
		gamesType.put("R03", "running");
		gamesType.put("S04", "swimming");
		gamesType.put("C05", "cycling");
		gamesType.put("R06", "running");
		
		superatheletes = new SuperAthlete[3];
		superatheletes[0] = new SuperAthlete("sa01", "Ivan", "VIC", 20);
		superatheletes[1] = new SuperAthlete("sa02", "John", "QLD", 22);
		superatheletes[2] = new SuperAthlete("sa03", "Karen", "NSW", 21);
		
		swimmers = new Athlete[10];
		swimmers[0] = new Swimmer("sw01", "Adam", "VIC", 18);
		swimmers[1] = new Swimmer("sw02", "Zedd", "NSW", 28);
		swimmers[2] = new Swimmer("sw03", "Robin", "VIC", 22);
		swimmers[3] = new Swimmer("sw04", "Oscar", "VIC", 20);
		swimmers[4] = new Swimmer("sw05", "James", "QLD", 24);
		swimmers[5] = new Swimmer("sw06", "Frances", "WA", 23);
		swimmers[6] = new Swimmer("sw07", "Grace", "TAS", 19);
		swimmers[7] = superatheletes[0];
		swimmers[8] = superatheletes[1];
		swimmers[9] = superatheletes[2];
		
		cyclists = new Athlete[10];
		cyclists[0] = new Cyclist("cy01", "Jason", "QLD", 22);
		cyclists[1] = new Cyclist("cy02", "Betty", "NSW", 23);
		cyclists[2] = new Cyclist("cy03", "Carl", "QLD", 19);
		cyclists[3] = new Cyclist("cy04", "Bruno", "VIC", 18);
		cyclists[4] = new Cyclist("cy05", "Ellen", "WA", 20);
		cyclists[5] = new Cyclist("cy06", "Amy", "VIC", 21);
		cyclists[6] = new Cyclist("cy07", "Greg", "TAS", 24);
		cyclists[7] = superatheletes[0];
		cyclists[8] = superatheletes[1];
		cyclists[9] = superatheletes[2];
		
		sprinters = new Athlete[10];
		sprinters[0] = new Sprinter("sp01", "Andy", "NSW", 22);
		sprinters[1] = new Sprinter("sp02", "Hamish", "QLD", 28);
		sprinters[2] = new Sprinter("sp03", "Cara", "VIC", 21);
		sprinters[3] = new Sprinter("sp04", "Nick", "VIC", 19);
		sprinters[4] = new Sprinter("sp05", "Ed", "WA", 22);
		sprinters[5] = new Sprinter("sp06", "Justin", "TAS", 18);
		sprinters[6] = new Sprinter("sp07", "Calvin", "VIC", 19);
		sprinters[7] = superatheletes[0];
		sprinters[8] = superatheletes[1];
		sprinters[9] = superatheletes[2];
		
		officials = new Official[10];
		officials[0] = new Official("of01", "Sherry", "QLD", 35);
		officials[1] = new Official("of02", "Emily", "QLD", 24);
		officials[2] = new Official("of03", "Jason", "WA", 29);
		officials[3] = new Official("of04", "Shawn", "VIC", 20);
		officials[4] = new Official("of05", "Steve", "WA", 26);
		officials[5] = new Official("of06", "Tim", "TAS", 22);
		officials[6] = new Official("of07", "Alice", "wA", 33);
		officials[7] = new Official("of08", "Irene", "VIC", 25);
		officials[8] = new Official("of09", "Kenneth", "NSW", 28);
		officials[9] = new Official("of10", "Jane", "NSW", 30);
	}

}
