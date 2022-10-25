package com.uet.oop.bomberman.entities.tile;


import com.uet.oop.bomberman.entities.Entity;
import com.uet.oop.bomberman.graphics.Sprite;

public class Grass extends stillObjects {

	public Grass(int x, int y, Sprite sprite) {
		super(x, y, sprite);
	}
	
	@Override
	public boolean collide(Entity e) {
		return true;
	}
}
