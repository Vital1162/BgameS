package com.uet.oop.bomberman.entities.tile.powerup;

import com.uet.oop.bomberman.Game;
import com.uet.oop.bomberman.entities.Entity;
import com.uet.oop.bomberman.entities.mob.Player;
import com.uet.oop.bomberman.graphics.Sprite;

public class PowerupSpeed extends Powerup {

	public PowerupSpeed(int x, int y, int level, Sprite sprite) {
		super(x, y, level, sprite);
	}
	
	@Override
	public boolean collide(Entity e) {
		
		if(e instanceof Player) {
			((Player) e).addPowerup(this);
			remove();
			return true;
		}
		
		return false;
	}
	
	@Override
	public void setValues() {
		_active = true;
		Game.addPlayerSpeed(0.1);
	}
	


}
