/**
 * Write a description of class Combat here.
 * 
 * @author (DeusBlu) 
 * @version (0.1_5)
 */
public class Combat
{
    private Dice dice;
    
    /**
     * default constructor for class Combat
     */
    public Combat()
    {
        dice = new Dice();
    }
    
    /**
     * makes the object player melee attack the object opponent
     */
    public void attack(Entity player, Entity opponent)
    {
        int roll = dice.roll(1, 20);
        roll++;
        if(roll == 20){
            hit(player, opponent);
        }
        else if(roll == 1){
            miss(player, opponent);
        }
        else{
            if(roll + player.getMeleeAttackMod() > opponent.getArmor()){
                hit(player, opponent);
            }
            else{
                miss(player, opponent);
            }
        }
    }
    
    /**
     * makes the object player damage the object opponent
     */
    private void hit(Entity player, Entity opponent)
    {
        int delt = player.getDamage();
        System.out.println(player.getName() + " hit " + opponent.getName());
        System.out.println(opponent.getName() + " takes " + delt + " damage!");
        opponent.takeDamage(delt);
    }
    
    /**
     * makes the player object swing and miss
     */
    private void miss(Entity player, Entity opponent)
    {
        System.out.println(player.getName() + " missed the " + opponent.getName());
    }
}
