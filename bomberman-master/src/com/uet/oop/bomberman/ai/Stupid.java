package com.uet.oop.bomberman.ai;

public class Stupid extends AI {

	@Override
	public int calculateDirection() {
		return random.nextInt(4);
	}

}
