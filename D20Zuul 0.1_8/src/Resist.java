/**
 * tracks and returns the resistances of the player
 * @author Deus
 * @version 0.1_8
 *
 */
public class Resist 
{
	private int fort;
	private int reflex;
	private int will;
	
	/**
	 * default constructor for Resist
	 */
	public Resist()
	{
		setFort(0);
		setReflex(0);
		setWill(0);
	}
	
	public Resist(int fort, int reflex, int will)
	{
		setFort(fort);
		setReflex(reflex);
		setWill(will);
	}
	
	/**
	 * sets the fortitude
	 * @param fort
	 */
	private void setFort(int fort)
	{
		if(fort >= 0){
			this.fort = fort;
		}
		else{
			throw new IllegalArgumentException("Fortitiude was negative");
		}
	}
	
	/**
	 * sets the reflex
	 * @param reflex
	 */
	private void setReflex(int reflex)
	{
		if(reflex >= 0){
			this.reflex = reflex;
		}
		else{
			throw new IllegalArgumentException("Reflex was negative");
		}
	}
	
	/**
	 * sets the will
	 * @param will
	 */
	private void setWill(int will)
	{
		if(will >= 0){
			this.will = will;
		}
		else{
			throw new IllegalArgumentException("Will was negative");
		}
	}
	
	/**
	 * returns the requested resist
	 * @param resist
	 * @return fort as int
	 */
	public int getResist(String resist)
	{
		if(resist.equalsIgnoreCase("fort")){
			return fort;
		}
		if(resist.equalsIgnoreCase("reflex")){
			return reflex;
		}
		if(resist.equalsIgnoreCase("will")){
			return will;
		}
		throw new IllegalArgumentException("Resist not recognized");
	}
}
