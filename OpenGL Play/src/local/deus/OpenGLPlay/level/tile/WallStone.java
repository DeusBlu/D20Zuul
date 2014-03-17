package local.deus.OpenGLPlay.level.tile;

import local.deus.OpenGLPlay.graphics.Screen;
import local.deus.OpenGLPlay.graphics.Sprite;

public class WallStone extends Tile
{

	public WallStone()
	{
		super(Sprite.wallStone);
	}

	public void render(int x, int y, Screen screen)
	{
		screen.renderTile(x << 4, y << 4, this.sprite);
	}

	public boolean solid()
	{
		return true;
	}

}
