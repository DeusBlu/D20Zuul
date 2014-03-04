package local.deus.OpenGLPlay.level.tile;

import local.deus.OpenGLPlay.graphics.Screen;
import local.deus.OpenGLPlay.graphics.Sprite;

public class GrassTile extends Tile
{

	public GrassTile(Sprite sprite)
	{
		super(sprite);
	}

	public void render(int x, int y, Screen screen)
	{
		screen.renderTile(x, y, this);
	}

}