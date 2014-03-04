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

	public Sprite(int size, int x, int y, Spritesheet sheet)
	{
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		load();
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
