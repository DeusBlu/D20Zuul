/**
 * a base class for creating weapons
 * @author DeusBlu
 *
 */
public abstract class Weapon extends Gear 
{
	private DiceSet diceSet;
	private String weapProf;
	private int critMod;

	public Weapon() {
		super();
		diceSet = new DiceSet();
		setCritMod(1);
	}

	public Weapon(double weight, 
				  int value, 
				  String name, 
				  int dice, 
				  int sides, 
				  int modifier, 
				  int critMod, 
				  int defense, 
				  int damageMod,
				  int hitMod, 
				  String statToMod, 
				  int statMod, 
				  String type,
				  String weapProf) {
		super(weight, value, name, defense, damageMod, hitMod, statToMod, statMod, type);
		diceSet = new DiceSet(dice, sides, modifier);
		setWeapProf(weapProf);
		setCritMod(critMod);
	}
	
	private void setWeapProf(String weapProf)
	{
		for(int i = 0; i < Constant.WEAP_PROF.length; i++){
			if(weapProf.equals(Constant.WEAP_PROF[i])){
				this.weapProf = weapProf;
			}
		}
		if(this.weapProf == null){
			throw new IllegalArgumentException("weapProf was invalid");
		}
	}
	
	private void setCritMod(int critMod)
	{
		if(critMod >= 0){
			this.critMod = critMod;
		}
		else{
			throw new IllegalArgumentException(getName()+" crit mod out of bounds");
		}
	}
	
	/**
	 * rolls the damage for the weapon and returns it modified with the damage mod
	 * returns just the bonus if no damage range
	 * @return int
	 */
	public int getDamage()
	{
		return (diceSet.getRoll() + diceSet.getModifier());
	}
	
	public String getWeapProf()
	{
		return weapProf;
	}
	
	public int getCritMod()
	{
		return critMod;
	}
	
	/**
	 * returns the damage as a string for display
	 * @return String
	 */
	public String showDamage()
	{
		String damageString = "";
		if(diceSet != null){
			if(diceSet.getNumber() > 0 && diceSet.getSides() > 0){
				damageString += "" + diceSet.getNumber() + "d" + diceSet.getSides();
			}
			if(diceSet.getModifier() > 0 || getDamageMod() > 0){
				damageString += "+" + (diceSet.getModifier() + getDamageMod());
			}
		}
		return damageString;
	}
    
    /**
     * prints the details about the item
     */
	@Override
    public void printDetails()
    {
    	System.out.println("Item Name: " + getName());
        System.out.println("Item Type: " + getType());
        System.out.println("Item Weight: " + getWeight() + "lbs");
        if(diceSet != null){
        	System.out.println("Damage: " + showDamage());
        }
        if(getHitMod() > 0){
        	System.out.println("+" + getHitMod() + " to hit roll");
        }
        if(getDefense() > 0){
        	System.out.println("Defense: " + getDefense());
        }
        if(getStatToMod() != null && !getStatToMod().equalsIgnoreCase("none")){
        	System.out.println(getStatToMod() + " +" + getStatMod());
        }
        System.out.println("Value: " + printValue());
    }
    
    /**
     * prints a short detail of the item
     */
    @Override
    public void printShortDetail()
    {
    	System.out.print(getName() + "  ");
        if(getDamage() != 0){
            System.out.print("Dmg: " + showDamage() + "  ");
        }
        if(getDefense() != 0){
            System.out.print("Def: " + getDefense() + "  ");
        }
        if(!getStatToMod().equalsIgnoreCase("none")){
            System.out.print(getStatToMod() + " +" + getStatMod());
        }
        System.out.println();
    }
	

}
