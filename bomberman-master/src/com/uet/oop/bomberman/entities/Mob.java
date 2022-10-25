package com.uet.oop.bomberman.entities;

import com.uet.oop.bomberman.Funcion.Board;
import com.uet.oop.bomberman.Funcion.Game;
import com.uet.oop.bomberman.graphics.Screen;

public abstract class Mob extends Animated {

    protected Board board;
    protected int dir = -1;
    protected boolean live = true;
    protected boolean moving = false;
    public int timeAfter = 80;

    public Mob(int x, int y, Board board) {
        _x = x;
        _y = y;
        this.board = board;
    }

    @Override
    public abstract void update();

    @Override
    public abstract void render(Screen screen);

    protected abstract void calculateMove();

    protected abstract void move(double xa, double ya);

    public abstract void kill();

    protected abstract void afterKill();

    protected abstract boolean canMove(double x, double y);

    public boolean isAlive() {
        return live;
    }

    public boolean isMoving() {
        return moving;
    }

    public int getDirection() {
        return dir;
    }

    protected double getXMessage() {
        return (_sprite.SIZE / 2 * Game.SCALE) + (_x * Game.SCALE);
    }

    protected double getYMessage() {
        return (_sprite.SIZE / 2 * Game.SCALE) - (_y * Game.SCALE);
    }

}
