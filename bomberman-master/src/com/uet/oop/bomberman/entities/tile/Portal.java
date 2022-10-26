package com.uet.oop.bomberman.entities.tile;

import com.uet.oop.bomberman.Funcion.Board;
import com.uet.oop.bomberman.entities.Entity;
import com.uet.oop.bomberman.entities.Bomber;
import com.uet.oop.bomberman.graphics.Sprite;

public class Portal extends stillObjects {

    protected Board jboard;

    public Portal(int x, int y, Board board, Sprite sprite) {
        super(x, y, sprite);
        jboard = board;
    }

    @Override
    public boolean collide(Entity e) {

        if (e instanceof Bomber) {

            if (jboard.detectNoEnemies() == false)
                return false;

            else if (e.getXTile() == getX() && e.getYTile() == getY()) {
                if (jboard.detectNoEnemies())
                    jboard.nextLevel();
            }


            else  return true;
        }

        return false;
    }

}
