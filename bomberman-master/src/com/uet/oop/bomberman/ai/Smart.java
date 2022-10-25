package com.uet.oop.bomberman.ai;

import com.uet.oop.bomberman.entities.Player;
import com.uet.oop.bomberman.enemy.Enemy;

public class Smart extends AI {
	Player _player;
	Enemy _e;
	
	public Smart(Player player, Enemy e) {
		_player = player;
		_e = e;
	}

	@Override
	public int calculateDirection() {
		/**if(_player == null)
			return random.nextInt(4);*/
		
		int vertical = random.nextInt(2);
		
		if(vertical == 1) {
			int v = calculateRowDirection();
			if(v != -1)
				return v;
			else
				return calculateColDirection();
			
		} else {
			int h = calculateColDirection();
			if(h != -1)
				return h;
			else
				return calculateRowDirection();
		}
	}
	
	protected int calculateColDirection() {
		if(_player.getXTile() < _e.getXTile())
			return 3;
		else if(_player.getXTile() > _e.getXTile())
			return 1;
		
		return -1;
	}
	
	protected int calculateRowDirection() {
		if(_player.getYTile() < _e.getYTile())
			return 0;
		else if(_player.getYTile() > _e.getYTile())
			return 2;
		return -1;
	}
	

}
