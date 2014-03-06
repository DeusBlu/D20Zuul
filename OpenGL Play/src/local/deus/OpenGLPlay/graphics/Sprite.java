/**
 * 
 */
package local.deus.OpenGLPlay.graphics;

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

	public static Sprite grass = new Sprite(16, 0, 0, Spritesheet.tiles);
	public static Sprite stone = new Sprite(16, 1, 0, Spritesheet.tiles);
	public static Sprite templeStone = new Sprite(16, 2, 0, Spritesheet.tiles);
	public static Sprite voidSprite = new Sprite(16, 0x568bff);
	
	public static Sprite player = new Sprite(16, 0, 10, Spritesheet.tiles);

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
