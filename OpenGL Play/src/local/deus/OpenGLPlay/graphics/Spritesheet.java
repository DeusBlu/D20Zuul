/**
 * 
 */
package local.deus.OpenGLPlay.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author desmond.jenkins
 * 
 */
public class Spritesheet
{

	private String path;
	public final int SIZE;
	public int[] pixels;

	public static Spritesheet tiles = new Spritesheet("/textures/spritesheet/spritesheet.png", 256);
	public static Spritesheet spawnTiles = new Spritesheet("/textures/spritesheet/spawn_level/spawntiles.png", 48);

	public Spritesheet(String path, int size)
	{
		this.path = path;
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		load();
	}

	private void load()
	{
		try {
			BufferedImage image = ImageIO.read(Spritesheet.class.getResource(path));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, pixels, 0, w);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}