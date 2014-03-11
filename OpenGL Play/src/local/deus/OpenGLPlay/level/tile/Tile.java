package local.deus.OpenGLPlay.level.tile;

import local.deus.OpenGLPlay.graphics.Screen;
import local.deus.OpenGLPlay.graphics.Sprite;
import local.deus.OpenGLPlay.level.tile.spawnTile.SpawnFloorTile;
import local.deus.OpenGLPlay.level.tile.spawnTile.SpawnGrassTile;
import local.deus.OpenGLPlay.level.tile.spawnTile.SpawnRockTile;
import local.deus.OpenGLPlay.level.tile.spawnTile.SpawnTreeTile;
import local.deus.OpenGLPlay.level.tile.spawnTile.SpawnWallTile;
import local.deus.OpenGLPlay.level.tile.spawnTile.SpawnWaterTile;

public class Tile
{
	public static final int TILE_PX_SIZE = 16;
	public int x, y;
	public Sprite sprite;

	// basic tiles
	public static Tile GRASS = new GrassTile(Sprite.grass);
	public static Tile STONE = new StoneTile(Sprite.stone);
	public static Tile WALL_STONE = new WallTile(Sprite.wallStone);
	public static Tile VOID_TILE = new VoidTile(Sprite.voidSprite);

	// spawnlevel tiles
	public static Tile SPAWN_GRASS = new SpawnGrassTile(Sprite.spawnGrass);
	public static Tile SPAWN_ROCK = new SpawnRockTile(Sprite.spawnRock);
	public static Tile SPAWN_TREE = new SpawnTreeTile(Sprite.spawnTree);
	public static Tile SPAWN_WALL = new SpawnWallTile(Sprite.spawnWall);
	public static Tile SPAWN_WATER = new SpawnWaterTile(Sprite.spawnWater);
	public static Tile SPAWN_FLOOR = new SpawnFloorTile(Sprite.spawnFloor);
	
	public final static int COL_SPAWN_GRASS = 0xff00ff00;
	public final static int COL_SPAWN_ROCK = 0xffbb8800;
	public final static int COL_SPAWN_TREE = 0xff008800;
	public final static int COL_SPAWN_FLOOR = 0xffff0000;
	public final static int COL_SPAWN_WATER = 0xff0000ff;
	public final static int COL_SPAWN_WALL = 0xff000000;

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