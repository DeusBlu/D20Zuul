package local.deus.OpenGLPlay.level;

import local.deus.OpenGLPlay.graphics.Screen;
import local.deus.OpenGLPlay.level.tile.Tile;

public class Level
{
	protected int width, height;
	protected int[] tileInt;
	protected int[] tiles;
	public static Level spawn = new SpawnLevel("/textures/levels/spawnLevel.png");

	public Level(int width, int height)
	{
		this.width = width;
		this.height = height;
		tileInt = new int[width * height];
		generateLevel();
	}

	public Level(String path)
	{
		loadLevel(path);
		generateLevel();
	}

	protected void generateLevel()
	{

	}

	protected void loadLevel(String path)
	{

	}

	public void update()
	{

	}

	@SuppressWarnings("unused")
	private void time()
	{

	}

	public void render(int xScroll, int yScroll, Screen screen)
	{
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll >> 4;
		int x1 = (xScroll + screen.width + Tile.TILE_PX_SIZE) >> 4;
		int y0 = yScroll >> 4;
		int y1 = (yScroll + screen.height + Tile.TILE_PX_SIZE) >> 4;

		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getTile(x, y).render(x, y, screen);
			}
		}
	}

	// Grass = 0xFF00FF00
	// Stone = 0xFFFFFF00
	// templeStone = 0xFF7F7F00
	public Tile getTile(int x, int y)
	{
		if (x < 0 || y < 0 || x >= width || y >= height) return Tile.VOID_TILE;
		if (tiles[x + y * width] == Tile.COL_SPAWN_FLOOR) return Tile.SPAWN_FLOOR;
		if (tiles[x + y * width] == Tile.COL_SPAWN_GRASS) return Tile.SPAWN_GRASS;
		if (tiles[x + y * width] == Tile.COL_SPAWN_ROCK) return Tile.SPAWN_ROCK;
		if (tiles[x + y * width] == Tile.COL_SPAWN_TREE) return Tile.SPAWN_TREE;
		if (tiles[x + y * width] == Tile.COL_SPAWN_WALL) return Tile.SPAWN_WALL;
		if (tiles[x + y * width] == Tile.COL_SPAWN_WATER) return Tile.SPAWN_WATER;
		return Tile.VOID_TILE;
	}

}