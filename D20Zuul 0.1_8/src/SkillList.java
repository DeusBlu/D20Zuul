import java.util.ArrayList;
/**
 * a class to hold Skill objects and perform prints and retreives
 * @author DeusBlu
 * @version 0.1_8
 *
 */
public class SkillList 
{
	private ArrayList<Skill> skillList;
	
	/**
	 * constructor for SkillList
	 */
	public SkillList()
	{
		skillList = new ArrayList<Skill>();
	}
	
	/**
	 * adds a skill to the SkillList of the player
	 * @param skill
	 */
	public void addSkill(Skill skill)
	{
		if(skill != null){
			skillList.add(skill);
		}
	}
	
	/**
	 * returns an array of skills
	 * @return ArrayList<Skill>
	 */
	public ArrayList<Skill> getSkills()
	{
		return skillList;
	}
	
	/**
	 * prints out all the active skills contained within the skill list
	 */
	public void printActiveSkills()
	{
		for(Skill skill : skillList){
			if(skill.getActive()){
				System.out.println(skill.getName());
				System.out.println("----------------");
				System.out.println(skill.getDescription());
				
					if(skill.getOffensive()){
						System.out.print("Damage: ");
					}
					else{
						System.out.print("Healing: ");
					}
					System.out.println(skill.getEffectValueString());
				
				System.out.println(skill.getDescription());
			}
		}
	}
	
	/**
	 * prints out all the passive skills contained within the skill list
	 */
	public void printPassiveSkills()
	{
		for(Skill skill : skillList){
			if(!skill.getActive()){
				System.out.println(skill.getName());
				System.out.println("----------------");
				System.out.println(skill.getDescription());
				if(skill.getActive()){
					if(skill.getOffensive()){
						System.out.print("Damage: ");
					}
					else{
						System.out.print("Healing: ");
					}
					System.out.println(skill.getEffectValueString());
				}
				System.out.println(skill.getDescription());
			}
		}
	}
}
