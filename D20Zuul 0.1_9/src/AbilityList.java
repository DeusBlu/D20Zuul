import java.util.ArrayList;
/**
 * a class to hold Ability objects and perform prints and retreives
 * @author DeusBlu
 * @version 0.1_8
 *
 */
public class AbilityList 
{
	private ArrayList<Ability> abilityList;
	
	/**
	 * constructor for AbilityList
	 */
	public AbilityList()
	{
		abilityList = new ArrayList<Ability>();
	}
	
	/**
	 * adds a ability to the AbilityList of the player
	 * @param ability
	 */
	public void addAbility(Ability ability)
	{
		if(ability != null){
			abilityList.add(ability);
		}
	}
	
	/**
	 * returns an array of abilitys
	 * @return ArrayList<Ability>
	 */
	public ArrayList<Ability> getAbilitys()
	{
		return abilityList;
	}
	
	/**
	 * prints out all the active abilitys contained within the ability list
	 */
	public void printActiveAbilitys()
	{
		for(Ability ability : abilityList){
			if(ability.getActive()){
				System.out.println(ability.getName());
				System.out.println("----------------");
				System.out.println(ability.getDescription());
				
					if(ability.getOffensive()){
						System.out.print("Damage: ");
					}
					else{
						System.out.print("Healing: ");
					}
					System.out.println(ability.getEffectValueString());
				
				System.out.println(ability.getDescription());
			}
		}
	}
	
	/**
	 * prints out all the passive abilitys contained within the ability list
	 */
	public void printPassiveAbilitys()
	{
		for(Ability ability : abilityList){
			if(!ability.getActive()){
				System.out.println(ability.getName());
				System.out.println("----------------");
				System.out.println(ability.getDescription());
				if(ability.getActive()){
					if(ability.getOffensive()){
						System.out.print("Damage: ");
					}
					else{
						System.out.print("Healing: ");
					}
					System.out.println(ability.getEffectValueString());
				}
				System.out.println(ability.getDescription());
			}
		}
	}
}
