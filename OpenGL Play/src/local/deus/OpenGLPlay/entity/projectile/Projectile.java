package local.deus.OpenGLPlay.entity.projectile;

import local.deus.OpenGLPlay.entity.Entity;
import local.deus.OpenGLPlay.graphics.Sprite;

public abstract class Projectile extends Entity
{
	protected final int xOrigin, yOrigin;
	protected double angle;
	protected Sprite sprite;
	protected double nx, ny;
	protected double speed, rateOfFire, range, damage;
	
	public Projectile(int x, int y, int dir)
	{
		xOrigin = x;
		yOrigin = y;
		angle = dir;
	}
}
