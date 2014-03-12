package local.deus.OpenGLPlay.level;

public class TileCoordinate
{
	private int xPos, yPos;
	private final int TILE_SIZE = 16;
	
	public TileCoordinate(int xPos, int yPos)
	{
		this.xPos = xPos * TILE_SIZE;
		this.yPos = yPos * TILE_SIZE;
	}
	
	public int getXPos()
	{
		return xPos;
	}
	
	public int getYPos()
	{
		return yPos;
	}
	
	public int[] getXYPos()
	{
		int[] xyPos = new int[2];
		xyPos[0] = xPos;
		xyPos[1] = yPos;
		return xyPos;
	}

}
