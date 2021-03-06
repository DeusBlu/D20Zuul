/**
 * Write a description of class Combat here.
 * 
 * @author DeusBlu
 * @version 0.1_9
 */
public class Combat
{
	private int roll;
    private Dice dice;
    
    /**
     * default constructor for class Combat
     */
    public Combat()
    {
    	roll = 0;
        dice = new Dice();
    }
    
    public int setRoll()
    {
    	roll = dice.roll(1, 20);
    	return roll;
    }
    
    public int getRoll()
    {
    	return roll;
    }
    
    public boolean successHit(int hitMod, Entity target)
    {
    	if(roll == 20){
    		return true;
    	}
    	else if(roll == 1){
    		return false;
    	}
    	else if(hitMod + roll > target.getArmor()){
    		return true;
    	}
    	else{
    		return false;
    	}
    }
    
    /**
     * makes the object player melee attack the object opponent
     */
    public int attack(int hitMod, Entity player, Entity monster)
    {
        if(roll == 20){
        	return crit(player, monster);
        }
        else{
            if(roll + hitMod > monster.getArmor()){
              return hit(player, monster);
            }
        }
        return 0;
    }
    
    /**
     * makes the object player damage the object opponent
     */
    private int crit(Entity player, Entity opponent)
    {
        int delt = player.getDamage();
        int crit = player.getCritMod();
        opponent.takeDamage(delt*crit);
        return delt*crit;
    }
    
    /**
     * makes the player object swing and miss
     */
    private int hit(Entity player, Entity opponent)
    {
        int delt = player.getDamage();
        if(delt <= 0){
        	delt = 1;
        }
        opponent.takeDamage(delt);
        return delt;
    }
}
