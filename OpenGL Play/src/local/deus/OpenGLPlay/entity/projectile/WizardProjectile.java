package local.deus.OpenGLPlay.entity.projectile;

public class WizardProjectile extends Projectile
{

	public WizardProjectile(int x, int y, int dir)
	{
		super(x, y, dir);
		range = 200;
		damage = 20;
		rateOfFire = 15;
		
	}

}
