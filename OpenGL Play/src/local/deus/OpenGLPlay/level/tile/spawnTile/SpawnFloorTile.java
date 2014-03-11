package local.deus.OpenGLPlay.level.tile.spawnTile;

import local.deus.OpenGLPlay.graphics.Screen;
import local.deus.OpenGLPlay.graphics.Sprite;
import local.deus.OpenGLPlay.level.tile.Tile;


public class SpawnFloorTile extends Tile
{

	public SpawnFloorTile(Sprite sprite)
	{
		super(sprite);
	}

	public void render(int x, int y, Screen screen)
	{
		screen.renderTile(x << 4, y << 4, this);
	}
}
