package com.uet.oop.bomberman.entities.tile;

import com.uet.oop.bomberman.entities.Entity;
import com.uet.oop.bomberman.Game.graphics.Screen;
import com.uet.oop.bomberman.Game.graphics.Sprite;
import com.uet.oop.bomberman.Game.Coordinates;

public abstract class Tile extends Entity {
	
	
	public Tile(int x, int y, Sprite sprite) {
		_x = x;
		_y = y;
		_sprite = sprite;
	}
	
	@Override
	public boolean collide(Entity e) {
		return false;
	}
	
	@Override
	public void render(Screen screen) {
		screen.renderEntity( Coordinates.tileToPixel(_x), Coordinates.tileToPixel(_y), this);
	}
	
	@Override
	public void update() {}
}
