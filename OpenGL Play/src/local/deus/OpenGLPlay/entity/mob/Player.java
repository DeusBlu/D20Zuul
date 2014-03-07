package local.deus.OpenGLPlay.entity.mob;

import local.deus.OpenGLPlay.graphics.Screen;
import local.deus.OpenGLPlay.graphics.Sprite;
import local.deus.OpenGLPlay.input.Keyboard;

public class Player extends Mob
{
	private Keyboard keyInput;
	private Sprite sprite;
	int animate = 0;
	private boolean walking = false;

	public Player(Keyboard keyInput)
	{
		this.keyInput = keyInput;
	}

	public Player(int xPos, int yPos, Keyboard keyInput)
	{
		this.xPos = xPos;
		this.yPos = yPos;
		this.keyInput = keyInput;
		sprite = Sprite.player_up;
	}

	public void update()
	{
		int xChange = 0, yChange = 0;
		if (animate < Integer.MAX_VALUE) animate++;
		else animate = 0;
		if (keyInput.up) yChange--;
		if (keyInput.down) yChange++;
		if (keyInput.left) xChange--;
		if (keyInput.right) xChange++;

		if (xChange != 0 || yChange != 0) {
			move(xChange, yChange);
			walking = true;
		}
		else {
			walking = false;
		}
	}

	public void render(Screen screen)
	{
		if (dir == 0) {
			sprite = Sprite.player_up;
			if (walking) {
				if(animate % 20 > 10){
					sprite = Sprite.player_forward_1;
				}
				else{
					sprite = Sprite.player_forward_2;
				}
			}
		}
		if (dir == 1) {
			sprite = Sprite.player_left;
			if (walking) {
				if(animate % 20 > 10){
					sprite = Sprite.player_left_1;
				}
				else{
					sprite = Sprite.player_left_2;
				}
			}
		}
		if (dir == 2) {
			sprite = Sprite.player_down;
			if (walking) {
				if(animate % 20 > 10){
					sprite = Sprite.player_down_1;
				}
				else{
					sprite = Sprite.player_down_2;
				}
			}
		}
		if (dir == 3){
			sprite = Sprite.player_right;
			if (walking) {
				if(animate % 20 > 10){
					sprite = Sprite.player_right_1;
				}
				else{
					sprite = Sprite.player_right_2;
				}
			}
		}
		screen.renderPlayer(xPos, yPos, sprite);
	}
}
