/**
 * holds all of the stats and does jobs to check modifiers and increase stats
 * @author DeusBlu
 * @version 0.1_8
 *
 */
public class Stats 
{
	private int str;
	private int dex;
	private int con;
	private int intel;
	private int wis;
	private int chr;
	
	/**
	 * default constructor for stats
	 */
	public Stats()
	{
		setStr(0);
		setDex(0);
		setCon(0);
		setIntel(0);
		setWis(0);
		setChr(0);
	}
	
	/**
	 * constructor for Stats object
	 * @param str
	 * @param dex
	 * @param con
	 * @param intel
	 * @param wis
	 * @param chr
	 */
	public Stats(int str, int dex, int con, int intel, int wis, int chr)
	{
		setStr(str);
		setDex(dex);
		setCon(con);
		setIntel(intel);
		setWis(wis);
		setChr(chr);
	}
	/**
	 * sets the str
	 * @param str
	 */
	private void setStr(int str)
	{
		if(str >= 3){
			this.str = str;
		}
		else{
			this.str = 3;
		}
	}
	
	/**
	 * sets the dex
	 * @param dex
	 */
	private void setDex(int dex)
	{
		if(dex >= 3){
			this.dex = dex;
		}
		else{
			this.dex = 3;
		}
	}
	
	/**
	 * sets the Con
	 * @param con
	 */
	private void setCon(int con)
	{
		if(con >= 3){
			this.con = con;
		}
		else{
			this.con = 3;
		}
	}
	
	/**
	 * sets the Intel
	 * @param intel
	 */
	private void setIntel(int intel)
	{
		if(intel >= 3){
			this.intel = intel;
		}
		else{
			this.intel = 3;
		}
	}
	
	/**
	 * sets the Wis
	 * @param wis
	 */
	private void setWis(int wis)
	{
		if(wis >= 3){
			this.wis = wis;
		}
		else{
			this.wis = 3;
		}
	}
	
	/**
	 * sets the Chr
	 * @param chr
	 */
	private void setChr(int chr)
	{
		if(chr >= 3){
			this.chr = chr;
		}
		else{
			this.chr = 3;
		}
	}
	
	public int getStat(String stat)
	{
		if(stat.equalsIgnoreCase("str")){
			return str;
		}
		if(stat.equalsIgnoreCase("dex")){
			return dex;
		}
		if(stat.equalsIgnoreCase("con")){
			return con;
		}
		if(stat.equalsIgnoreCase("intel")){
			return intel;
		}
		if(stat.equalsIgnoreCase("wis")){
			return wis;
		}
		if(stat.equalsIgnoreCase("chr")){
			return chr;
		}
		else{
			throw new IllegalArgumentException("Unrecognized Stat");
		}
	}
	
	/**
	 * increases the strength
	 */
	public void increaseStr()
	{
		str++;
	}
	
	/**
	 * increases the dexterity
	 */
	public void increaseDex()
	{
		dex++;
	}
	
	/**
	 * increases the constitution
	 */
	public void increaseCon()
	{
		con++;
	}
	
	/**
	 * increases the intelligence
	 */
	public void increaseIntel()
	{
		intel++;
	}
	
	/**
	 * increases the wisdom
	 */
	public void increaseWis()
	{
		wis++;
	}
	
	/**
	 * increases the charisma
	 */
	public void increaseChr()
	{
		chr++;
	}
}
