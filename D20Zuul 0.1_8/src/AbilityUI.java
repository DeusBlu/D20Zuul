import java.util.ArrayList;
import java.util.Stack;
/**
 * 
 */

/**
 * @author Nicole
 *
 */
public class AbilityUI 
{
	private ArrayList<Ability> abilities;
	private Party monsters;
	private Party players;
	private Player player;
	private Ability ability;
	private Entity target;
	private InputReader reader;
	private Stack<Integer> attacks;
	private Combat combat;
	
	public AbilityUI()
	{
		reader = new InputReader();
		target = null;
		ability = null;
		combat = new Combat();
		setPlayer(new Player());
		setPlayers(new Party());
		setMonsters(new Party());
		setAbilities();
	}

	public AbilityUI(Party players, 
					 Party monsters,
					 Player player,
					 Stack<Integer> attacks)
	{
		reader = new InputReader();
		combat = new Combat();
		setPlayers(players);
		setMonsters(monsters);
		setPlayer(player);
		setAbilities();
		setAttacks(attacks);
	}
	
	private void setMonsters(Party monsters) 
	{
		if(monsters != null){
			this.monsters = monsters;
		}
		else{
			throw new IllegalArgumentException("monsters was null");
		}
	}

	private void setPlayers(Party players) 
	{
		if(players != null){
			this.players = players;
		}
		else{
			throw new IllegalArgumentException("players was null");
		}
	}

	private void setPlayer(Player player) 
	{
		if(player != null){
			this.player = player;
		}
		else{
			throw new IllegalArgumentException("player was null");
		}
	}
	
	private void setAbilities()
	{
		abilities = player.getAbilitys().getAbilitys();
	}
	
	private void setAttacks(Stack<Integer> attacks)
	{
		if(attacks != null && !attacks.empty()){
			this.attacks = attacks;
		}
		else{
			throw new IllegalArgumentException("attacks stack was null or empty");
		}
	}
	
	public boolean useAbility()
	{
		String selectAbility = null;
		while(selectAbility == null){
			if(player.getPlayerClass() instanceof Fighter){
				printFighterAbility();
			}
			selectAbility = selectAbility();
			if(selectAbility != null && selectAbility.equalsIgnoreCase("end")){
				break;
			}
		}
		ability = getAbility(selectAbility);
		String selectTarget = null;
		while(selectTarget == null){
			if(ability.getOffensive()){
				selectTarget = selectOffTarget();
				if(selectTarget.equalsIgnoreCase("end")){
					break;
				}
			}
		}
		target = getTarget(selectTarget);
		if(confirm()){
			useStOffAbility();
			return true;
		}
		return false;
	}
	
	private void printFighterAbility()
	{
		for(Ability ability : abilities){
			if(((FighterAbility)ability).getProfType().equals(player.getWeapon().getWeapProf())){
				if(ability.getActive()){
					System.out.println(ability.getName());
				}
			}
		}
	}
	
	private String selectAbility()
	{
		System.out.println("Use which ability?");
		System.out.print("Name> ");
		String input = reader.readString();
		for(Ability ability : abilities){
			if(ability.getName().equalsIgnoreCase(input)
					&& ability.getActive()){
				return input;
			}
			else if(input.equalsIgnoreCase("end")){
				return input;
			}
		}
		System.out.println("That is not a valid ability please enter the name");
		System.out.println("of the ability or type end to leave the menu");
		return null;
	}
	
	private Ability getAbility(String selectAbility)
	{
		for(Ability ability : abilities){
			if(ability.getName().equalsIgnoreCase(selectAbility)){
				return ability;
			}
		}
		return null;
	}
	
	private String selectOffTarget()
	{
		monsters.shortStatus();
		System.out.println("Use on who?");
		System.out.print("#/Name> ");
		String input = reader.readString();
		if(input.equalsIgnoreCase("end")){
			return input;
		}
		else if(input.equals("1") && monsters.getPlayers()[0] != null){
			if(!monsters.getPlayers()[0].isDead()){
				return input;
			}
		}
		else if(input.equals("2") && monsters.getPlayers()[1] != null){
			if(!monsters.getPlayers()[1].isDead()){
				return input;
			}
		}
		else if(input.equals("3") && monsters.getPlayers()[2] != null){
			if(!monsters.getPlayers()[2].isDead()){
				return input;
			}
		}
		else if(input.equals("4") && monsters.getPlayers()[3] != null){
			if(!monsters.getPlayers()[3].isDead()){
				return input;
			}
		}
		else if(input.equals("5") && monsters.getPlayers()[4] != null){
			if(!monsters.getPlayers()[4].isDead()){
				return input;
			}
		}
		else if(input.equals("6") && monsters.getPlayers()[5] != null){
			if(!monsters.getPlayers()[5].isDead()){
				return input;
			}
		}
		for(int i = 0; i < monsters.getPlayers().length; i++){
			if(input.equalsIgnoreCase(monsters.getPlayers()[i].getName())){
				return input;
			}
		}
		return null;
	}
	
	private Entity getTarget(String selectTarget)
	{
		if(selectTarget.equals("1") && monsters.getPlayers()[0] != null){
			if(!monsters.getPlayers()[0].isDead()){
				return monsters.getPlayers()[0];
			}
		}
		else if(selectTarget.equals("2") && monsters.getPlayers()[1] != null){
			if(!monsters.getPlayers()[1].isDead()){
				return monsters.getPlayers()[1];
			}
		}
		else if(selectTarget.equals("3") && monsters.getPlayers()[2] != null){
			if(!monsters.getPlayers()[2].isDead()){
				return monsters.getPlayers()[2];
			}
		}
		else if(selectTarget.equals("4") && monsters.getPlayers()[3] != null){
			if(!monsters.getPlayers()[3].isDead()){
				return monsters.getPlayers()[3];
			}
		}
		else if(selectTarget.equals("5") && monsters.getPlayers()[4] != null){
			if(!monsters.getPlayers()[4].isDead()){
				return monsters.getPlayers()[4];
			}
		}
		else if(selectTarget.equals("6") && monsters.getPlayers()[5] != null){
			if(!monsters.getPlayers()[5].isDead()){
				return monsters.getPlayers()[5];
			}
		}
		for(int i = 0; i < monsters.getPlayers().length; i++){
			if(selectTarget.equalsIgnoreCase(monsters.getPlayers()[i].getName())){
				return monsters.getPlayers()[i];
			}
		}
		return null;
	}
	
	private boolean confirm()
	{
		boolean confirm = false;
		boolean ask = true;
		while(ask){
			System.out.println("Use "+ability.getName()+" on "+target.getName()+"?");
			System.out.print("Y/N> ");
			String input = reader.readString();
			for(int i=0; i < Constant.YES.length; i++){
                if(input.equalsIgnoreCase(Constant.YES[i])){
                	ask = false;
                	confirm = true;
                }
            }
            for(int i=0; i < Constant.NO.length; i++){
            	if(input.equalsIgnoreCase(Constant.NO[i])){
            		ask = false;
                }
            }
            if(ask){
                System.out.println("use 'yes' or 'no'");
            }
		}
		return confirm;
	}
	
	private void useStOffAbility()
	{
		System.out.println(player.getName()+" used "+ability.getName()+
				" on "+target.getName());
		reader.readString();
		int attack = attacks.peek();
		if(combat.successHit(attack+ability.getHitMod(), target)){
			int roll = combat.getRoll();
			System.out.println(player.getName()+" rolls "+roll);
			if(attack+ability.getHitMod()+roll > target.getArmor()){
				int damage = player.getDamage()+ability.getDmgMod();
				System.out.println(player.getName()+" hits "+target.getName()+
						" for "+damage+" damage!");
				target.takeDamage(damage);
				printDefeated(target);
			}
		}
		else{
			System.out.println(player.getName()+" missed");
		}
	}
	
	private void printDefeated(Entity target)
	{
    	if(target.isDead()){
    		System.out.println(target.getName() + 
    				" has been defeated!");
    		reader.readString();
            Console.clearConsole();
    	}
	}
}
