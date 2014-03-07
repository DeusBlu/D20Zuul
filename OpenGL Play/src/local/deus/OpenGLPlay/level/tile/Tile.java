package local.deus.OpenGLPlay.level.tile;

import local.deus.OpenGLPlay.graphics.Screen;
import local.deus.OpenGLPlay.graphics.Sprite;

public class Tile
{
	public static final int TILE_PX_SIZE = 16;
	public int x, y;
	public Sprite sprite;

	public static Tile grass = new GrassTile(Sprite.grass);
	public static Tile voidTile = new VoidTile(Sprite.voidSprite);

	public Tile(Sprite sprite)
	{
		this.sprite = sprite;
	}

	public void render(int x, int y, Screen screen)
	{
	}

	public boolean solid()
	{
		return false;
	}
}