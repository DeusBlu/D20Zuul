package local.deus.OpenGLPlay.entity.mob;

import local.deus.OpenGLPlay.graphics.Screen;
import local.deus.OpenGLPlay.graphics.Sprite;
import local.deus.OpenGLPlay.input.Keyboard;

public class Player extends Mob
{
	private Keyboard keyInput;

	public Player(Keyboard keyInput)
	{
		this.keyInput = keyInput;
	}

	public Player(int xPos, int yPos, Keyboard keyInput)
	{
		this.xPos = xPos;
		this.yPos = yPos;
		this.keyInput = keyInput;
	}

	public void update()
	{
		int xChange = 0, yChange = 0;
		if (keyInput.up) yChange--;
		if (keyInput.down) yChange++;
		if (keyInput.left) xChange--;
		if (keyInput.right) xChange++;

		if (xChange != 0 || yChange != 0) move(xChange, yChange);
	}

	public void render(Screen screen)
	{
		screen.renderPlayer(xPos, yPos, Sprite.player);
	}
}
