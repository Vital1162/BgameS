package com.uet.oop.bomberman;


import com.uet.oop.bomberman.entities.Entity;

public abstract class Animated extends Entity {

	protected int _animate = 0;
	protected final int MAX_ANIMATE = 7500; //save the animation status and dont let this get too big
	
	protected void animate() {
		if(_animate < MAX_ANIMATE) _animate++; else _animate = 0; //reset animation
	}

}
