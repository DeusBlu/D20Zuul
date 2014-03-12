package local.deus.OpenGLPlay.entity;

import java.util.Random;

import local.deus.OpenGLPlay.graphics.Screen;
import local.deus.OpenGLPlay.level.Level;

public abstract class Entity
{
	public int xPos, yPos;
	private boolean removed = false;
	protected Level level;
	protected final Random random = new Random();

	public void update()
	{

	}

	public void render(Screen screen)
	{

	}

	public void remove()
	{
		// remove from level
		removed = true;
	}

	public boolean isRemoved()
	{
		return removed;
	}
	
	public void init(Level level)
	{
		this.level = level;
	}
}