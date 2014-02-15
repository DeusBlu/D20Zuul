/**
 * an object that holds information about proficiencies with weapons
 * @author DeusBlu
 *
 */
public class Proficiency 
{
	private String type;
	private int rank;
	
	public Proficiency()
	{
		setType("unknown");
		setRank(1);
	}
	
	public Proficiency(String type, int rank)
	{
		setType(type);
		setRank(rank);
	}

	/**
	 * sets the type for the proficiency
	 * @param type the type to set
	 */
	private void setType(String type) 
	{
		for(int i = 0; i < Constant.WEAP_PROF.length; i++){
			if(type.equalsIgnoreCase(Constant.WEAP_PROF[i])){
				this.type = type;
			}
		}
		if(this.type == null){
			throw new IllegalArgumentException("Weapon Proficiency was unknown type");
		}
	}

	/**
	 * @param rank the rank to set
	 */
	private void setRank(int rank) {
		this.rank = rank;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return the rank
	 */
	public int getRank() {
		return rank;
	}
}
