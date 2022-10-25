package com.uet.oop.bomberman.entities;


import com.uet.oop.bomberman.Funcion.Board;
import com.uet.oop.bomberman.Funcion.Game;
import com.uet.oop.bomberman.AI.Stupid;
import com.uet.oop.bomberman.graphics.Sprite;

public class Balloom extends Enemy {
	
	
	public Balloom(int x, int y, Board board) {
		super(x, y, board, Sprite.balloom_dead, Game.getPlayerSpeed() / 4, 50);
		
		_sprite = Sprite.balloom_left1;
		
		ai = new Stupid();
		dir = ai.Direction();
	}
	

	@Override
	protected void chooseSprite() {
		switch(dir) {
			case 0:
			case 1:
					_sprite = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, _animate, 60);
				break;
			case 2:
			case 3:
					_sprite = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, _animate, 60);
				break;
		}
	}
}
