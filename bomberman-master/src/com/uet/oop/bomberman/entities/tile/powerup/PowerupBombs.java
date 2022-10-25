package com.uet.oop.bomberman.entities.tile.powerup;

import com.uet.oop.bomberman.Game.Game;
import com.uet.oop.bomberman.entities.Entity;
import com.uet.oop.bomberman.entities.Player;
import com.uet.oop.bomberman.Game.graphics.Sprite;

public class PowerupBombs extends Powerup {

	public PowerupBombs(int x, int y, int level, Sprite sprite) {
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
		Game.addBombRate(1);
	}
	


}
