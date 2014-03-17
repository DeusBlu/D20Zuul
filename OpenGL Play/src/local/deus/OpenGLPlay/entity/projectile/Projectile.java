package local.deus.OpenGLPlay.entity.projectile;

import java.util.Random;

import local.deus.OpenGLPlay.entity.Entity;
import local.deus.OpenGLPlay.graphics.Sprite;

public abstract class Projectile extends Entity
{
	protected final int xOrigin, yOrigin;
	protected double angle;
	protected Sprite sprite;
	protected double xPos, yPos;
	protected double newX, newY;
	protected double speed, range, damage;
	
	protected final Random random = new Random();
	
	public Projectile(int x, int y, double dir)
	{
		xOrigin = x;
		yOrigin = y;
		angle = dir;
		xPos = x;
		yPos = y;
	}
	
	public Sprite getSprite()
	{
		return sprite;
	}
	
	public int getSpriteSize()
	{
		return sprite.SIZE;
	}
	
	protected void move()
	{
		
	}
}
