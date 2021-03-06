/**
 * 
 */
package local.deus.OpenGLPlay.graphics;

import java.util.Random;

import local.deus.OpenGLPlay.entity.projectile.Projectile;
import local.deus.OpenGLPlay.level.tile.Tile;

/**
 * @author Deus
 */
public class Screen
{

	public int width, height;
	public int[] pixels;
	public final int MAP_SIZE = 64;
	public final int MAP_SIZE_MASK = MAP_SIZE - 1;
	public int[] tiles = new int[MAP_SIZE * MAP_SIZE];
	public int xOffset, yOffset;
	private Random random = new Random();

	public Screen(int width, int height)
	{
		this.width = width;
		this.height = height;
		pixels = new int[width * height];

		for (int i = 0; i < MAP_SIZE * MAP_SIZE; i++) {
			tiles[i] = random.nextInt(0xffffff);
		}
	}

	public void clear()
	{
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}
	
	public void renderSprite(int xPos, int yPos, Sprite sprite, boolean fixed)
	{
		if(fixed) {
			xPos -= xOffset;
			yPos -= yOffset;
		}
		for(int y = 0; y < sprite.getHeight(); y++){
			int ya = y + yPos;
			for (int x = 0; x < sprite.getWidth(); x++){
				int xa = x + xPos;
				if(xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
				pixels[x + y * width] = sprite.pixels[x + y * sprite.getWidth()];
			}
		}
	}

	public void renderTile(int xPos, int yPos, Sprite sprite)
	{
		xPos -= xOffset;
		yPos -= yOffset;
		for (int y = 0; y < sprite.SIZE; y++) {
			int ya = y + yPos;
			for (int x = 0; x < sprite.SIZE; x++) {
				int xa = x + xPos;
				if (xa < -sprite.SIZE || xa >= width || ya < 0 || ya >= height)
					break;
				if (xa < 0) xa = 0;
				pixels[xa + ya * width] = sprite.pixels[x + y * sprite.SIZE];
			}
		}
	}

	public void renderProjectile(int xPos, int yPos, Projectile p)
	{
		xPos -= xOffset;
		yPos -= yOffset;
		for (int y = 0; y < p.getSpriteSize(); y++) {
			int ya = y + yPos;
			int ySprite = y;
			for (int x = 0; x < p.getSpriteSize(); x++) {
				int xa = x + xPos;
				int xSprite = 15 - x;
				if (xa < -p.getSpriteSize() || xa >= width || ya < 0 || ya >= height)
					break;
				if (xa < 0) xa = 0;
				int color = p.getSprite().pixels[xSprite + ySprite * p.getSpriteSize()];
				if (color != 0xffff00ff) pixels[xa + ya * width] = color;
			}
		}

	}

	public void renderPlayer(int xPos, int yPos, Sprite sprite)
	{
		xPos -= xOffset;
		yPos -= yOffset;
		for (int y = 0; y < Tile.TILE_PX_SIZE; y++) {
			int ya = y + yPos;
			int ySprite = y;
			for (int x = 0; x < Tile.TILE_PX_SIZE; x++) {
				int xa = x + xPos;
				int xSprite = 15 - x;
				if (xa < -Tile.TILE_PX_SIZE || xa >= width || ya < 0 || ya >= height)
					break;
				if (xa < 0) xa = 0;
				int color = sprite.pixels[xSprite + ySprite * Tile.TILE_PX_SIZE];
				if (color != 0xffff00ff) pixels[xa + ya * width] = color;
			}
		}

	}

	public void setOffset(int xOffset, int yOffset)
	{
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
}
