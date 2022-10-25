package com.uet.oop.bomberman.entities.tile.destroyable;

import com.uet.oop.bomberman.entities.Entity;
import com.uet.oop.bomberman.entities.DirectionalExplosion;
import com.uet.oop.bomberman.entities.tile.stillObjects;
import com.uet.oop.bomberman.graphics.Sprite;

public class DestroyableTile extends stillObjects {

	private final int MAX_ANIMATE = 7500; //save the animation status and dont let this get too big
	private int animated = 0;
	protected boolean destroy = false;
	protected int TimeDisapear = 20;
	protected Sprite belowSprite = Sprite.grass; //default
	
	public DestroyableTile(int x, int y, Sprite sprite) {
		super(x, y, sprite);
	}
	
	@Override
	public void update() {
		if(destroy == true) {
			if(animated < MAX_ANIMATE) animated++; else animated = 0; //reset animation
			if(TimeDisapear > 0)
				TimeDisapear--;
			else
				remove();
		}
	}

	public boolean isDestroyed() {
		return destroy;
	}
	
	public void destroy() {
		destroy = true;
	}
	
	@Override
	public boolean collide(Entity e) {
		
		if(e instanceof DirectionalExplosion)
			destroy();
			
		return false;
	}
	
	public void addBelowSprite(Sprite sprite) {
		belowSprite = sprite;
	}

	protected Sprite movingSprite(Sprite normal, Sprite x1, Sprite x2) {
		int calc = animated % 30;
		
		if(calc < 10) {
			return normal;
		}
			
		if(calc < 20) {
			return x1;
		}
			
		return x2;
	}
	
}
