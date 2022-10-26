package com.uet.oop.bomberman.AI;

import com.uet.oop.bomberman.entities.Bomber;
import com.uet.oop.bomberman.entities.Enemy;

public class Smart extends AI {
    Bomber player;
    Enemy enemy;

    public Smart(Bomber player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
    }

    @Override
    public int Direction() {
        /**if(_player == null)
         return random.nextInt(4);*/
        int ver = random.nextInt(2);

        if (ver == 1) {
            int v = RowDirection();
            if (v != -1)
                return RowDirection();
            else
                return ColDirection();

        } else {
            int dir = ColDirection();
            if (dir != -1)
                return ColDirection();
            else
                return RowDirection();
        }
    }

    protected int ColDirection() {
        if (player.getXTile() > enemy.getXTile())
            return 1;
        else if (player.getXTile() < enemy.getXTile())
            return 3;

        return -1;
    }

    protected int RowDirection() {
        if (player.getYTile() > enemy.getYTile())
            return 2;
        else if (player.getYTile() < enemy.getYTile())
            return 0;
        return -1;
    }


}
