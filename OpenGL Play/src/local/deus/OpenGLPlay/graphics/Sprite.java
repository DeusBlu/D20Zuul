/**
 * 
 */
package local.deus.OpenGLPlay.graphics;

import local.deus.OpenGLPlay.level.tile.Tile;

/**
 * @author desmond.jenkins
 * 
 */
public class Sprite
{

	public final int SIZE;
	private int x, y;
	public int[] pixels;
	private Spritesheet sheet;

	public static Sprite grass = new Sprite(Tile.TILE_PX_SIZE, 0, 0, Spritesheet.tiles);
	public static Sprite stone = new Sprite(Tile.TILE_PX_SIZE, 1, 0, Spritesheet.tiles);
	public static Sprite templeStone = new Sprite(Tile.TILE_PX_SIZE, 2, 0,
			Spritesheet.tiles);
	public static Sprite voidSprite = new Sprite(Tile.TILE_PX_SIZE, 0x568bff);

	public static Sprite player_up = new Sprite(Tile.TILE_PX_SIZE, 0, 10, Spritesheet.tiles);
	public static Sprite player_right = new Sprite(Tile.TILE_PX_SIZE, 1, 10, Spritesheet.tiles);
	public static Sprite player_down = new Sprite(Tile.TILE_PX_SIZE, 2, 10, Spritesheet.tiles);
	public static Sprite player_left = new Sprite(Tile.TILE_PX_SIZE, 3, 10, Spritesheet.tiles);
	
	public static Sprite player_forward_1 = new Sprite(Tile.TILE_PX_SIZE, 0, 11, Spritesheet.tiles);
	public static Sprite player_forward_2 = new Sprite(Tile.TILE_PX_SIZE, 0, 12, Spritesheet.tiles);
	public static Sprite player_right_1 = new Sprite(Tile.TILE_PX_SIZE, 1, 11, Spritesheet.tiles);
	public static Sprite player_right_2 = new Sprite(Tile.TILE_PX_SIZE, 1, 12, Spritesheet.tiles);
	public static Sprite player_down_1 = new Sprite(Tile.TILE_PX_SIZE, 2, 11, Spritesheet.tiles);
	public static Sprite player_down_2 = new Sprite(Tile.TILE_PX_SIZE, 2, 12, Spritesheet.tiles);
	public static Sprite player_left_1 = new Sprite(Tile.TILE_PX_SIZE, 3, 11, Spritesheet.tiles);
	public static Sprite player_left_2 = new Sprite(Tile.TILE_PX_SIZE, 3, 12, Spritesheet.tiles);

	public Sprite(int size, int x, int y, Spritesheet sheet)
	{
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		load();
	}

	public Sprite(int size, int color)
	{
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		setColor(color);
	}

	private void setColor(int color)
	{
		for (int i = 0; i < SIZE * SIZE; i++) {
			pixels[i] = color;
		}
	}

	private void load()
	{
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y)
						* sheet.SIZE];
			}
		}
	}
}
