/**
 * 
 * @author Zephron
 * @version 0.1_8
 * Takes the responsibility of the creation of rooms and a map away from the Game class.
 * Makes map creation much more flexible.
 * 
 *
 */
public class Map {
	
	private Room currentRoom;
	
	public Map(){
		createRooms();
	}
	private void createRooms()
    {
        Room outside, theatre, pub, lab, office;//Main level
        Room cellar, basement;//Lower level
      
        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theatre = new Room("in a lecture theatre");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        cellar = new Room("in the cellar below the office");
        basement = new Room("in the basement of the lab");
        
        // initialise room exits
        outside.setExits("east", theatre);
        outside.setExits("west", pub);
        outside.setExits("south", lab);
        theatre.setExits("west", outside);
        pub.setExits("east", outside);
        lab.setExits("north", outside);
        lab.setExits("east", office);
        lab.setExits("down", basement);
        office.setExits("west", lab);
        office.setExits("down", cellar);
        cellar.setExits("up", office);
        basement.setExits("up", lab);
        

        currentRoom = outside;  // start game outside
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
