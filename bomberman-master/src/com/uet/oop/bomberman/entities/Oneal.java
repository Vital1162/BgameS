package com.uet.oop.bomberman.entities;


import com.uet.oop.bomberman.AI.Smart;
import com.uet.oop.bomberman.Funcion.Board;
import com.uet.oop.bomberman.Funcion.Game;
import com.uet.oop.bomberman.graphics.Sprite;

public class Oneal extends Enemy {
	
	public Oneal(int x, int y, Board board) {
		super(x, y, board, Sprite.oneal_dead, Game.getPlayerSpeed()/4, 200);
		_sprite = Sprite.oneal_left1;
		
		ai = new Smart(board.getPlayer(), this);
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
					_sprite = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, _animate, 60);
				else
					_sprite = Sprite.oneal_left1;
				break;
			case 2:
			case 3:
				if(moving)
					_sprite = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, _animate, 60);
				else
					_sprite = Sprite.oneal_left1;
				break;
		}
	}
}
