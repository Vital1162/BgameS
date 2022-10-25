package com.uet.oop.bomberman.entities.tile;

import com.uet.oop.bomberman.entities.Entity;
import com.uet.oop.bomberman.graphics.Screen;
import com.uet.oop.bomberman.graphics.Sprite;
import com.uet.oop.bomberman.Funcion.Coordinates;

public abstract class stillObjects extends Entity {
	
	
	public stillObjects(int x, int y, Sprite sprite) {
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
