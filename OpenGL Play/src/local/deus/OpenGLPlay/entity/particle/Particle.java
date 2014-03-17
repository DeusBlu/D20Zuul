package local.deus.OpenGLPlay.entity.particle;

import java.util.ArrayList;
import java.util.List;

import local.deus.OpenGLPlay.entity.Entity;
import local.deus.OpenGLPlay.graphics.Sprite;

public class Particle extends Entity
{
	private List<Particle> particles;
	private Sprite sprite;
	
	private int life;
	
	public Particle(int x, int y, int life, int amount)
	{
		particles = new ArrayList<Particle>();
		sprite = Sprite.particle_normal;
	}

}
