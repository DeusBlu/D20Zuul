import java.util.ArrayList;
/**
 * an object for holding the players Proficiencies
 * @author DeusBlu
 * @version 0.1_8
 *
 */
public class Proficiencies 
{
	private ArrayList<Proficiency> proficiencies;
	
	public Proficiencies()
	{
		proficiencies = new ArrayList<Proficiency>();
	}
	
	public ArrayList<Proficiency> getProficiencies()
	{
		return proficiencies;
	}
	
	public void learnProficiency(Proficiency proficiency)
	{
		if(proficiency != null){
			proficiencies.add(proficiency);
		}
	}
	
	public void listProficiencies()
	{
		for(Proficiency prof : proficiencies){
			System.out.println(prof.getType()+" rank "+prof.getRank());
		}
	}
}
