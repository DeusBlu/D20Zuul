package local.deus.OpenGLPlay.entity.mob;

import java.util.ArrayList;
import java.util.List;

import local.deus.OpenGLPlay.entity.Entity;
import local.deus.OpenGLPlay.entity.projectile.Projectile;
import local.deus.OpenGLPlay.entity.projectile.WizardProjectile;
import local.deus.OpenGLPlay.graphics.Sprite;

public abstract class Mob extends Entity
{
	protected Sprite sprite;
	protected int dir = 0; // 0 north, 1 east, 2 south, 3 west
	protected boolean moving = false;

	public void move(int xChange, int yChange)
	{
		if (xChange != 0 && yChange != 0) {
			move(xChange, 0);
			move(0, yChange);
			return;
		}
		if (xChange > 0) dir = 1;
		if (xChange < 0) dir = 3;
		if (yChange > 0) dir = 2;
		if (yChange < 0) dir = 0;

		// requires -1, 0 or 1
		if (!collision(xChange, yChange)) {
			xPos += xChange;
			yPos += yChange;
		}
	}

	public void update()
	{
		
	}

	protected void shoot(int xPos, int yPos, double dir)
	{
		// dir *= 180 / Math.PI;
		Projectile p = new WizardProjectile(xPos, yPos, dir);
		level.addProjectile(p);
	}

	private boolean collision(int xChange, int yChange)
	{
		boolean solid = false;
		for (int corner = 0; corner < 4; corner++) {
			int xt = ((xPos + xChange) + corner % 2 * 10 + 2) / 16;
			int yt = ((yPos + yChange) + corner / 2 * 10 + 5) / 16;
			if (level.getTile(xt, yt).solid()) solid = true;
		}
		return solid;
	}

	public void render()
	{

	}
}
