package local.deus.OpenGLPlay.entity.projectile;

import local.deus.OpenGLPlay.graphics.Screen;
import local.deus.OpenGLPlay.graphics.Sprite;

public class WizardProjectile extends Projectile
{
	public static final int FIRE_RATE = 5; //higher is slower

	public WizardProjectile(int x, int y, double dir)
	{
		super(x, y, dir);
		range = random.nextInt(100) + 50;
		speed = 4;
		damage = 2;
		sprite = Sprite.wizard_projectile;
		newX = speed * Math.cos(angle);
		newY = speed * Math.sin(angle);
	}

	public void update()
	{
		if(level.tileCollision(xPos, yPos, newX, newY, 32)) remove();
		move();
	}

	protected void move()
	{
		xPos += newX;
		yPos += newY;
		if (distance() > range) remove();
	}

	private double distance()
	{
		double dist = 0;
		dist = Math.sqrt(Math.abs((xOrigin - xPos) * (xOrigin - xPos) + (yOrigin - yPos)
				* (yOrigin - yPos)));
		return dist;
	}

	public void render(Screen screen)
	{
		screen.renderProjectile((int) xPos, (int) yPos, this);
	}
}
