import java.util.Random;
/**
 * Write a description of class Combat here.
 * 
 * @author (DeusBlu) 
 * @version (0.1_5)
 */
public class Combat
{
    private Random rand;
    private Dice dice;
    private Monster monster;
    private PlayerCharacter player;
    /**
     * Constructor for Combat
     * @param Monster - the monster for the combat
     * @param PlayerCharacter - the character for the combat
     */
    public Combat(Monster monster, PlayerCharacter player)
    {
        rand = new Random();
        dice = new Dice();
        usePlayer(player);
        useMonster(monster);
    }
    
    /**
     * loads a PlayerCharacter into the combat
     * @param player - the player character to use for combat
     */
    private void usePlayer(PlayerCharacter player)
    {
        if(player != null){
            this.player = player;
        }
        else{
            System.out.println("There was an error in Combat loading the player");
        }
    }
    
    /**
     * load a Monster into the combat
     * @param monster - the monster to use for combat
     */
    private void useMonster(Monster monster)
    {
        if(monster != null){
            this.monster = monster;
        }
        else{
            System.out.println("There was an error in Combat loading a monster");
        }
    }
    
    /**
     * returns the player character that is being used for combat
     * @return PlayerCharacter
     */
    public PlayerCharacter getPlayer()
    {
        if(player != null){
            return player;
        }
        else{
            return null;
        }
    }
    
    /**
     * returns the monster that is being used for combat
     * @return Monster
     */
    public Monster getMonster()
    {
        if(monster != null){
            return monster;
        }
        else{
            return null;
        }
    }
    
    /**
     * makes the object player melee attack the object monster
     */
    public void playerAttack()
    {
        int roll = dice.roll(1, 20);
        roll++;
        if(roll == 20){
            playerHit();
        }
        else if(roll == 1){
            playerMiss();
        }
        else{
            if(roll + player.getAttackBonus() > monster.getArmor()){
                playerHit();
            }
            else{
                playerMiss();
            }
        }
    }
    
    /**
     * makes the object player damage the object monster
     */
    private void playerHit()
    {
        int[] damage = player.getDamage();
        int delt = (rand.nextInt(damage[1]) + damage[0]);
        System.out.println(player.getName() + " hit " + monster.getName());
        System.out.println(monster.getName() + " takes " + delt + " damage!");
        monster.damage(delt);
    }
    
    /**
     * makes the player object swing and miss
     */
    private void playerMiss()
    {
        System.out.println(player.getName() + " missed the " + monster.getName());
    }
    
    /**
     * makes the monster attack the player
     */
    public void monsterAttack()
    {
        int roll = dice.roll(1, 20);
        roll++;
        if(roll == 20){
            monsterHit();
        }
        else if(roll == 1){
            monsterMiss();
        }
        else{
            if(roll + monster.getHitBonus() > player.getArmorClass()){
                monsterHit();
            }
            else{
                monsterMiss();
            }
        }
    }
    
    /**
     * makes the monster hit the player
     */
    private void monsterHit()
    {
        int[] damage = monster.getDamage();
        int delt = (rand.nextInt(damage[1]) + damage[0]);
        System.out.println(monster.getName() + " hit " + player.getName());
        System.out.println(player.getName() + " takes " + delt + " damage!");
        player.damage(delt);
    }
    
    /**
     * makes the monster miss the player
     */
    private void monsterMiss()
    {
        System.out.println(monster.getName() + " missed the " + player.getName());
    }
}
