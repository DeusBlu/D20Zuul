import java.util.ArrayList;
/**
 * an object for holding the players Proficiencies
 * @author DeusBlu
 * @version 0.1_8
 *
 */
public class Proficiencies 
{
	private int maxProf;
	private ArrayList<Proficiency> proficiencies;
	
	/**
	 * default constructor for Proficiencies objects
	 */
	public Proficiencies()
	{
		proficiencies = new ArrayList<Proficiency>();
		setMaxProf(1);
	}
	
	/**
	 * constructor that allows you to set your max prof level, sent in by the
	 * PlayerClass object
	 * @param maxProf
	 */
	public Proficiencies(int maxProf)
	{
		proficiencies = new ArrayList<Proficiency>();
		setMaxProf(maxProf);
	}
	
	/**
	 * returns the proficiency rating with the requested weapon
	 * @param proficiency
	 * @return proficiency ranks as int
	 */
	public int getProficiency(String proficiency)
	{
		for(Proficiency aProficiency : proficiencies){
			if(aProficiency.getType().equals(proficiency)){
				return aProficiency.getRank();
			}
		}
		return 0;
	}
	
	/**
	 * sets the max rank of a proficiency
	 * @param maxProf
	 */
	public void setMaxProf(int maxProf)
	{
		if(maxProf >= 1){
			this.maxProf = maxProf;
		}
		else{
			throw new IllegalArgumentException("maxProf was out of bounds");
		}
	}
	
	/**
	 * adds a new proficiency to the player
	 * @param proficiency
	 */
	public void learnProficiency(Proficiency proficiency)
	{
		if(proficiency != null){
			proficiencies.add(proficiency);
		}
	}
	
	/**
	 * increases the rank of the proficiency
	 * @param proficiency
	 */
	public void rankUp(String proficiency)
	{
		for(Proficiency aProficiency : proficiencies){
			if(aProficiency.getType().equals(proficiency)){
				if(aProficiency.getRank() < maxProf){
					aProficiency.rankUp();
				}
			}
		}
	}
	
	/**
	 * prints out the proficiencies
	 */
	public void listProficiencies()
	{
		for(Proficiency prof : proficiencies){
			System.out.println(prof.getType()+" rank "+prof.getRank());
		}
	}
}
