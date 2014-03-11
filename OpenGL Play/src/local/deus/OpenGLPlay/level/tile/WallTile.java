package local.deus.OpenGLPlay.level.tile;

import local.deus.OpenGLPlay.graphics.Screen;
import local.deus.OpenGLPlay.graphics.Sprite;

public class WallTile extends Tile
{

	public WallTile(Sprite sprite)
	{
		super(sprite);
		
	}

	public void render(int x, int y, Screen screen)
	{
		screen.renderTile(x << 4, y << 4, this);
	}

}
