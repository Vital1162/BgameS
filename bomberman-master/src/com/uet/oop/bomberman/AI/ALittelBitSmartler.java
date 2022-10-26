package com.uet.oop.bomberman.AI;

import com.uet.oop.bomberman.Funcion.Board;
import com.uet.oop.bomberman.entities.Bomber;
import com.uet.oop.bomberman.entities.Enemy;
import com.uet.oop.bomberman.entities.Bomb;
import javafx.scene.layout.Pane;

import java.util.List;
public class ALittelBitSmartler extends AI {
    Bomber bomber;
    Enemy enemy;

    List<Bomb> bombList;

    public ALittelBitSmartler(Bomber bomber, Enemy enemy, Board board) {
        this.bomber = bomber;
        this.enemy = enemy;
        bombList = board.getBombs();
    }

    public int Direction() {
        /**if(_player == null)
         return random.nextInt(4);*/
        int ver = random.nextInt(2);

        for(int i = 0; i <bombList.size(); i++) {
            if(bombList.get(i).getXTile() > enemy.getXTile()) return 3;
            else if(bombList.get(i).getXTile() < enemy.getXTile()) return 1;
            else if(bombList.get(i).getYTile() < enemy.getYTile()) return 2;
            else
                return -1;
        }
        return random.nextInt(4);
    }

    protected int ColDirection() {
        if (bomber.getXTile() > enemy.getXTile())
            return 1;
        else if (bomber.getXTile() < enemy.getXTile())
            return 3;

        return -1;
    }

    protected int RowDirection() {
        if (bomber.getYTile() > enemy.getYTile())
            return 2;
        else if (bomber.getYTile() < enemy.getYTile())
            return 0;
        return -1;
    }

}
