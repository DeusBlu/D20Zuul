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

	public AbilityUI(Encounter encounter)
	{
		reader = new InputReader();
		combat = new Combat();
		setPlayers(encounter.getPlayers());
		setMonsters(encounter.getMonsters());
		setPlayer((Player)encounter.getCurrentTurn());
		setAbilities();
		setAttacks(encounter.getAttacks());
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
				return false;
			}
		}
		ability = getAbility(selectAbility);
		String selectTarget = null;
		Party targets = null;
		while(selectTarget == null){
			if(ability.getOffensive()){
				targets = monsters;
			}
			else{
				targets = players;
			}
			selectTarget = selectTarget(targets);
			if(selectTarget.equalsIgnoreCase("end")){
				return false;
			}
		}
		target = getTarget(selectTarget, targets);
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
					System.out.println(ability.getName()+" -- "+ability.getDescription());
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
		reader.readString();
		Console.clearConsole();
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
	
	private String selectTarget(Party targets)
	{
		targets.shortStatus();
		System.out.println("Use on who?");
		System.out.print("#/Name> ");
		String selectTarget = reader.readString();
		for(int i = 1; i <= targets.getPlayers().length; i++){
			if(selectTarget.equals(((Integer)i).toString()) && 
			targets.getPlayers()[i-1] != null && 
			!targets.getPlayers()[i-1].isDead()){
				return selectTarget;
			}
		}
		for(int i = 0; i < targets.getPlayers().length; i++){
			if(targets.getPlayers()[i] != null &&
					!targets.getPlayers()[i].isDead() &&
					targets.getPlayers()[i].getName().equalsIgnoreCase(selectTarget)){
				return selectTarget;
			}
		}
		System.out.println("That was not a valid target, please enter the name");
		System.out.println("or the # of the target or type end to exit");
		reader.readString();
		return null;
	}
	
	private Entity getTarget(String target, Party targets)
	{
		for(int i = 1; i <= targets.getPlayers().length; i++){
			if(target.equals(((Integer)i).toString()) && 
			targets.getPlayers()[i-1] != null){
				return targets.getPlayers()[i-1];
			}
		}
		for(int i = 0; i < targets.getPlayers().length; i++){
			if(targets.getPlayers()[i] != null &&
					targets.getPlayers()[i].getName().equalsIgnoreCase(target)){
				return targets.getPlayers()[i];
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
