

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class PlayerCharacterTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class PlayerCharacterTest
{
    private PlayerCharacter playerCh1;
    private Item skullcap;
    private Item shortSword;
    private Item chainMail;
    private Item dagger;
    private Item battleAxe5;
    private Item spikeShoulders;
    private Item chainGloves;
    private Item leatherChaps;
    private Item spikedBoots;
    private Item potion;
    private Item jockStrap;
    private Monster monster1;
    private Combat combat1;
    private MonParty monParty1;
    private Party party1;
    private Encounter encounte1;
    private PlayerCharacter playerCh3;
    private PlayerCharacter playerCh4;
    private PlayerCharacter playerCh5;
    private Party party2;

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    
    
    
    
    
    
    
    
    
    
    
    
    
    

    
    
    
    
    
    
    
    
    
    
    
    
    
    

    
    
    
    
    
    
    
    
    
    
    
    

    
    
    
    
    
    
    
    

    
    
    
    
    
    
    
    
    
    
    

    
    

    /**
     * Default constructor for test class PlayerCharacterTest
     */
    public PlayerCharacterTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        playerCh1 = new PlayerCharacter();
        skullcap = new Item(0, 0, 4, "helm", "none", 0, "Skullcap");
        shortSword = new Item(3, 5, 0, "1hweapon", "none", 0, "Short Sword");
        chainMail = new Item(0, 0, 10, "chest", "none", 0, "Chain Mail");
        dagger = new Item(1, 3, 1, "1hweapon", "none", 0, "Dagger");
        potion = new Item(0, 0, 0, "misc", "healing", 10, "Potion of Healing");
        battleAxe5 = new Item(5, 10, 0, "2hweapon", "buff", 5, "Battle Axe +5");
        spikeShoulders = new Item(1, 2, 5, "shoulders", "none", 0, "Spiked Shoudlers");
        chainGloves = new Item(0, 0, 3, "gloves", "none", 0, "Chain Gloves");
        leatherChaps = new Item(1, 2, 1, "pants", "none", 0, "Leather Chaps");
        spikedBoots = new Item(2, 5, 3, "boots", "none", 0, "Spiked Boots");
        jockStrap = new Item(0, 0, 1, "pants", "none", 0, "Jock Strap");
        playerCh1.lootItem(shortSword);
        playerCh1.lootItem(battleAxe5);
        playerCh1.lootItem(chainMail);
        playerCh1.lootItem(dagger);
        playerCh1.lootItem(skullcap);
        playerCh1.lootItem(spikeShoulders);
        playerCh1.lootItem(chainGloves);
        playerCh1.lootItem(leatherChaps);
        playerCh1.lootItem(spikedBoots);
        playerCh1.lootItem(jockStrap);
        playerCh1.lootItem(potion);
        monster1 = new Monster();
        combat1 = new Combat(monster1, playerCh1);
        monParty1 = new MonParty();
        monParty1.join(monster1);
        playerCh3 = new PlayerCharacter("Player1", 18, 11, 11, 11, 11, 11, 11);
        playerCh4 = new PlayerCharacter("Player2", 18, 11, 11, 11, 11, 11, 11);
        playerCh5 = new PlayerCharacter("Player3", 18, 11, 11, 11, 11, 11, 11);
        party2 = new Party();
        party2.join(playerCh1);
        party2.join(playerCh3);
        party2.join(playerCh4);
        party2.join(playerCh5);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
}
