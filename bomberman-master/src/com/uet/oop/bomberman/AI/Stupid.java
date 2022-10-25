package com.uet.oop.bomberman.AI;

public class Stupid extends AI {

	@Override
	public int Direction() {
		return random.nextInt(4);
	}

}
