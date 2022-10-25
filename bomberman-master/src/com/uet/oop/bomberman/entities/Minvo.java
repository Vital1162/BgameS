package com.uet.oop.bomberman.entities;


import com.uet.oop.bomberman.AI.Stupid;
import com.uet.oop.bomberman.Funcion.Board;
import com.uet.oop.bomberman.Funcion.Game;
import com.uet.oop.bomberman.graphics.Sprite;

public class Minvo extends Enemy {
	
	
	public Minvo(int x, int y, Board board) {
		super(x, y, board, Sprite.minvo_dead, Game.getPlayerSpeed()/4, 800);
		
		_sprite = Sprite.minvo_right1;
		ai = new Stupid();
		dir  = ai.Direction();
	}
	/*
	|--------------------------------------------------------------------------
	| Mob Sprite
	|--------------------------------------------------------------------------
	 */
	@Override
	protected void chooseSprite() {
		switch(dir) {
			case 0:
			case 1:
				if(moving)
					_sprite = Sprite.movingSprite(Sprite.minvo_right1, Sprite.minvo_right2, Sprite.minvo_right3, _animate, 60);
				else
					_sprite = Sprite.minvo_left1;
				break;
			case 2:
			case 3:
				if(moving)
					_sprite = Sprite.movingSprite(Sprite.minvo_left1, Sprite.minvo_left2, Sprite.minvo_left3, _animate, 60);
				else
					_sprite = Sprite.minvo_left1;
				break;
		}
	}
}
