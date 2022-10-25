package com.uet.oop.bomberman.entities.tile;

import com.uet.oop.bomberman.Funcion.Board;
import com.uet.oop.bomberman.entities.Entity;
import com.uet.oop.bomberman.entities.Bomber;
import com.uet.oop.bomberman.graphics.Sprite;

public class Portal extends stillObjects {

    protected Board board;

    public Portal(int x, int y, Board board, Sprite sprite) {
        super(x, y, sprite);
        board = board;
    }

    @Override
    public boolean collide(Entity e) {

        if (e instanceof Bomber) {

            if (board.detectNoEnemies() == false)
                return false;

            if (e.getXTile() == getX() && e.getYTile() == getY()) {
                if (board.detectNoEnemies())
                    board.nextLevel();
            }

            return true;
        }

        return false;
    }

}
