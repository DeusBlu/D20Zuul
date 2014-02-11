/**
 * provides an interface for a player to create their character
 * @author DeusBlu
 * @version 0.1_8
 *
 */
public class CreatePlayer 
{
	private final String[] STATS = {
			"Str", "Dex", "Con", "Int", "Wis", "Chr"
	};
    private static final String[] YES = {
    	"yes", "y"
    };
    private static final String[] NO = {
    	"no", "n"
    };
	public static final String LAND = "UNKNOWN";
	private InputReader reader;
	private String name;
	private int age;
	private int str;
	private int dex;
	private int con;
	private int intel;
	private int wis;
	private int chr;
	private int[] rolls;
	
	
	public CreatePlayer()
	{
		reader = new InputReader();
		rolls = new int[10];
		int age = 0;
		int str = 0;
		int dex = 0;
		int con = 0;
		int intel = 0;
		int wis = 0;
		int chr = 0;
	}
	
	public Player start()
	{
		System.out.println("Hello traveler, welcome to the land of "+LAND+"!");
		System.out.println("\nThese lands are dangerous and we need a name");
		System.out.println("to put on your Tombstone");
		getName();
		System.out.println();
		System.out.println("Help a poor blind man out and let me know your age");
		getAge();
		System.out.println("Welcome to "+LAND+" "+name);
		System.out.println("Lets see how the gods favor your prowess");
		System.out.println();
		System.out.println("Lets see what we have to work with");
		boolean done = false;
		setStats();
		while(!done){
			for(int i = 0; i < STATS.length; i++){
				printStatChoices();
				setStat(STATS[i]);
			}
			if(confirmStats()){
				done = true;
			}
			else{
				setStats();
			}
		}
		Player newPlayer = new Player(name, str, dex, con, intel, wis, chr, 1, 10, 1, 0, 0, age);
		return newPlayer;
	}
	
	private void getName()
	{
		boolean done = false;
		while(!done){
			System.out.print("Enter Name> ");
			String name = reader.readString();
			if(name!= null && !name.isEmpty()){
				this.name = name;
				done = true;
			}
			else{
				System.out.println("Your name cannot be empty");
			}
		}
	}
	
	private void getAge()
	{
		boolean done = false;
		while(!done){
			System.out.print("Enter Age> ");
			int age = reader.readInt();
			if(age >= Player.MIN_AGE && age <= Player.MAX_AGE){
				this.age = age;
				done = true;
			}
			else if(age < Player.MIN_AGE){
				System.out.println("Oh I think you sound a bit older than that ");
				System.out.println("you must be at least "+Player.MIN_AGE);
			}
			else if(age > Player.MAX_AGE){
				System.out.println("Oh you sound a good bit younger than that ");
				System.out.println("to me, you can't be a day older than "+Player.MAX_AGE);
			}
		}
	}
	
	private void setStats()
	{
		boolean has16 = false;
		boolean has13 = false;
		Dice dice = new Dice();
		for(int i = 0; i < rolls.length; i++){
			int tempRoll = dice.roll(3, 6);
			if(tempRoll >= 18){
				rolls[i] = 18;
				has16 = true;
			}
			else if(!has16){
				rolls[i] = 16;
				has16 = true;
			}
			else if(!has13){
				rolls[i] = 13;
				has13 = true;
			}
			else{
				rolls[i] = tempRoll;
			}
		}
	}
	
	private void setStat(String stat)
	{
		System.out.println("set "+stat+" to?");
		int input = 0;
		boolean set = false;
		while(!set){
			System.out.print("Enter #> ");
			input = reader.readInt();
			if(rolls[input-1] >= 0){
				if(stat.equalsIgnoreCase("str")){
					str = rolls[input-1];
					set = true;
				}
				else if(stat.equalsIgnoreCase("dex")){
					dex = rolls[input-1];
					set = true;
				}
				else if(stat.equalsIgnoreCase("con")){
					con = rolls[input-1];
					set = true;
				}
				else if(stat.equalsIgnoreCase("int")){
					intel = rolls[input-1];
					set = true;
				}
				else if(stat.equalsIgnoreCase("wis")){
					wis = rolls[input-1];
					set = true;
				}
				else if(stat.equalsIgnoreCase("chr")){
					chr = rolls[input-1];
					set = true;
				}
				else{
					System.out.println("Selection not valid");
					printStatChoices();
				}
			}
		}
		rolls[input-1] = 0;
	}
	
	private boolean confirmStats()
	{
		printCurrentStats();
		boolean keep = true;
		System.out.println();
		System.out.println("Keep these stats?");
		System.out.print("Enter Y/N> ");
		String input = null;
		boolean done = false;
		while(!done){
			input = reader.readString();
			for(int i=0; i < YES.length; i++){
                if(input.equalsIgnoreCase(YES[i])){
                	done = true;
                	keep = true;
                }
            }
            for(int i=0; i < NO.length; i++){
                if(input.equalsIgnoreCase(NO[i])){
                     done = true;
                     keep = false;
                }
            }
		}
		return keep;
	}
	
	private void printCurrentStats()
	{
		System.out.println("Str: "+str);
		System.out.println("Dex: "+dex);
		System.out.println("Con: "+con);
		System.out.println("Int: "+intel);
		System.out.println("Wis: "+wis);
		System.out.println("Chr: "+chr);
	}
	
	private void printStatChoices()
	{
		for(int i = 0; i < (rolls.length/2); i++){
			int add = rolls.length/2;
			printStat(i);
			System.out.print("\t");
			printStat(i+add);
			System.out.println();
		}
	}
	
	private void printStat(int stat)
	{
		System.out.print("# "+(stat+1)+"-"+rolls[stat]);
	}
}