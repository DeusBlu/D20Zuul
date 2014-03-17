package local.deus.OpenGLPlay.entity.mob;

import local.deus.OpenGLPlay.Game;
import local.deus.OpenGLPlay.entity.projectile.Projectile;
import local.deus.OpenGLPlay.entity.projectile.WizardProjectile;
import local.deus.OpenGLPlay.graphics.Screen;
import local.deus.OpenGLPlay.graphics.Sprite;
import local.deus.OpenGLPlay.input.Keyboard;
import local.deus.OpenGLPlay.input.Mouse;

public class Player extends Mob
{
	private Keyboard keyInput;
	private Sprite sprite;
	int animate = 0;
	private boolean walking = false;
	
	private int fireRate = 0;

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
		fireRate = WizardProjectile.FIRE_RATE;
	}

	public void update()
	{
		if(fireRate > 0) fireRate --;
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
		clear();
		updateShooting();
	}
	
	private void clear()
	{
		for(int i = 0; i < level.getProjectiles().size(); i++){
			Projectile p = level.getProjectiles().get(i);
			if(p.isRemoved()){
				level.getProjectiles().remove(i);
			}
		}
	}

	private void updateShooting()
	{
		if (Mouse.getButton() == 1 && fireRate <= 0) {
			double dx = Mouse.getX() - Game.getWindowWidth() / 2;
			double dy = Mouse.getY() - Game.getWindowHeight() / 2;
			double dir = Math.atan2(dy, dx);
			shoot(xPos, yPos, dir);
			fireRate = WizardProjectile.FIRE_RATE;
		}
	}

	public void render(Screen screen)
	{
		if (dir == 0) {
			sprite = Sprite.player_up;
			if (walking) {
				if (animate % 20 > 10) {
					sprite = Sprite.player_forward_1;
				}
				else {
					sprite = Sprite.player_forward_2;
				}
			}
		}
		if (dir == 1) {
			sprite = Sprite.player_left;
			if (walking) {
				if (animate % 20 > 10) {
					sprite = Sprite.player_left_1;
				}
				else {
					sprite = Sprite.player_left_2;
				}
			}
		}
		if (dir == 2) {
			sprite = Sprite.player_down;
			if (walking) {
				if (animate % 20 > 10) {
					sprite = Sprite.player_down_1;
				}
				else {
					sprite = Sprite.player_down_2;
				}
			}
		}
		if (dir == 3) {
			sprite = Sprite.player_right;
			if (walking) {
				if (animate % 20 > 10) {
					sprite = Sprite.player_right_1;
				}
				else {
					sprite = Sprite.player_right_2;
				}
			}
		}
		screen.renderPlayer(xPos, yPos, sprite);
	}
}
