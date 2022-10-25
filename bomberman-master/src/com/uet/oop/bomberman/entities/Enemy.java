package com.uet.oop.bomberman.entities;

import com.uet.oop.bomberman.Funcion.Board;
import com.uet.oop.bomberman.Funcion.Game;
import com.uet.oop.bomberman.AI.AI;
import com.uet.oop.bomberman.graphics.Screen;
import com.uet.oop.bomberman.graphics.Sprite;
import com.uet.oop.bomberman.Funcion.Coordinates;

import java.awt.*;

public abstract class Enemy extends Mob {

    protected int points;

    protected double speed;
    protected AI ai;

    //necessary to correct move
    protected final double MAX_STEPS;
    protected final double rest;
    protected double steps;

    protected int finalAnimation = 30;
    protected Sprite deadSprite;

    public Enemy(int x, int y, Board board, Sprite dead, double speed, int points) {
        super(x, y, board);

        this.points = points;
        this.speed = speed;

        MAX_STEPS = Game.TILES_SIZE / this.speed;
        rest = (MAX_STEPS - (int) MAX_STEPS) / MAX_STEPS;
        this.steps = MAX_STEPS;

        timeAfter = 20;
        deadSprite = dead;
    }

    /*
    |--------------------------------------------------------------------------
    | Mob Render & Update
    |--------------------------------------------------------------------------
     */
    @Override
    public void update() {
        animate();

        if (live == false) {
            afterKill();
            return;
        }

        if (live) calculateMove();
    }

    @Override
    public void render(Screen screen) {

        if (live)
            chooseSprite();
        else {
            if (timeAfter > 0) {
                _sprite = deadSprite;
                _animate = 0;
            } else {
                _sprite = Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3, _animate, 60);
            }

        }

        screen.renderEntity((int) _x, (int) _y - _sprite.SIZE, this);
    }

    /*
    |--------------------------------------------------------------------------
    | Mob Move
    |--------------------------------------------------------------------------
     */
    @Override
    public void calculateMove() {
        int xa = 0, ya = 0;
        if (steps <= 0) {
            dir = ai.Direction();
            steps = MAX_STEPS;
        }

        if (dir == 0) ya--;
        if (dir == 1) xa++;
        if (dir == 2) ya++;
        if (dir == 3) xa--;

        if (canMove(xa, ya)) {
            steps -= 1 + rest;
            move(xa * speed, ya * speed);
            moving = true;
        } else {
            steps = 0;
            moving = false;
        }
    }

    @Override
    public boolean canMove(double x, double y) {

        double xr = _x, yr = _y - 16;


        if (dir == 0) {
            yr += _sprite.getSize() - 1;
            xr += _sprite.getSize() / 2;
        }
        if (dir == 1) {
            yr += _sprite.getSize() / 2;
            xr += 1;
        }
        if (dir == 2) {
            xr += _sprite.getSize() / 2;
            yr += 1;
        }
        if (dir == 3) {
            xr += _sprite.getSize() - 1;
            yr += _sprite.getSize() / 2;
        }
        int xx = Coordinates.pixelToTile(xr) + (int) x;
        int yy = Coordinates.pixelToTile(yr) + (int) y;
        Entity a = board.getEntity(xx, yy, this);
        return a.collide(this);
    }

    @Override
    public void move(double xa, double ya) {
        if (!live) return;
        _x += xa;
        _y += ya;

    }

    @Override
    public boolean collide(Entity e) {
        if (e instanceof DirectionalExplosion) {
            kill();
            return false;
        }

        if (e instanceof Bomber) {
            ((Bomber) e).kill();
            return false;
        }

        return true;
    }

    @Override
    public void kill() {
        if (live == false) return;
        live = false;

        board.addPoints(points);

        Mess msg = new Mess("+" + points, getXMessage(), getYMessage(), 2, Color.white, 14);
        board.addMessage(msg);
    }


    @Override
    protected void afterKill() {
        if (timeAfter > 0) --timeAfter;
        else {
            if (finalAnimation > 0) --finalAnimation;
            else
                remove();
        }
    }

    protected abstract void chooseSprite();
}
