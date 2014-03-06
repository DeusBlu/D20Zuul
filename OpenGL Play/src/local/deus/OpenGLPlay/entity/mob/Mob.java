package local.deus.OpenGLPlay.entity.mob;

import local.deus.OpenGLPlay.entity.Entity;
import local.deus.OpenGLPlay.graphics.Sprite;

public abstract class Mob extends Entity
{
	protected Sprite sprite;
	protected int dir = 0; // 0 north, 1 east, 2 south, 3 west
	protected boolean moving = false;

	public void move(int xChange, int yChange)
	{
		if (xChange > 0) dir = 1;
		if (xChange < 0) dir = 3;
		if (yChange > 0) dir = 2;
		if (yChange < 0) dir = 0;

		// requires -1, 0 or 1
		if (!collision()) {
			xPos += xChange;
			yPos += yChange;
		}
	}

	public void update()
	{

	}

	private boolean collision()
	{
		return false;
	}

	public void render()
	{

	}
}
