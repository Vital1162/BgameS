package com.uet.oop.bomberman.entities;

import com.uet.oop.bomberman.Funcion.Board;
import com.uet.oop.bomberman.Funcion.Game;
import com.uet.oop.bomberman.entities.tile.powerup.Powerup;
import com.uet.oop.bomberman.graphics.Screen;
import com.uet.oop.bomberman.graphics.Sprite;
import com.uet.oop.bomberman.Funcion.gui.menu.SoundPlayer;
import com.uet.oop.bomberman.Keypress.Keyboard;
import com.uet.oop.bomberman.Funcion.Coordinates;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Bomber extends Mob {

    private List<Bomb> _bombs;
    protected Keyboard _input;

    protected int _timeBetweenPutBombs = 0;

    public static List<Powerup> _powerups = new ArrayList<Powerup>();


    public Bomber(int x, int y, Board board) {
        super(x, y, board);
        _bombs = board.getBombs();
        _input = board.getInput();
        _sprite = Sprite.player_right;
    }


    @Override
    public void update() {
        clearBombs();
        if (live == false) {
            afterKill();
            return;
        }

        if (_timeBetweenPutBombs < -7500) _timeBetweenPutBombs = 0;
        else _timeBetweenPutBombs--;

        animate();

        calculateMove();

        detectPlaceBomb();
    }

    @Override
    public void render(Screen screen) {
        calculateXOffset();

        if (live)
            chooseSprite();
        else

            _sprite = Sprite.movingSprite(Sprite.player_dead1, Sprite.player_dead2, Sprite.player_dead3, _animate, 60);

        screen.renderEntity((int) _x, (int) _y - _sprite.SIZE, this);
    }

    public void calculateXOffset() {
        int xScroll = Screen.calculateXOffset(board, this);
        Screen.setOffset(xScroll, 0);
    }

    /*
    |--------------------------------------------------------------------------
    | Mob Unique
    |--------------------------------------------------------------------------
     */
    private void detectPlaceBomb() {
        if (_input.space && Game.getBombRate() > 0 && _timeBetweenPutBombs < 0) {

            int xt = Coordinates.pixelToTile(_x + _sprite.getSize() / 2);
            int yt = Coordinates.pixelToTile((_y + _sprite.getSize() / 2) - _sprite.getSize()); //subtract half player height and minus 1 y position

            placeBomb(xt, yt);
            Game.addBombRate(-1);

            _timeBetweenPutBombs = 30;
        }
    }

    protected void placeBomb(int x, int y) {
        Bomb b = new Bomb(x, y, board);
        board.addBomb(b);
    }

    private void clearBombs() {
        Iterator<Bomb> bs = _bombs.iterator();

        Bomb b;
        while (bs.hasNext()) {
            b = bs.next();
            if (b.isRemoved()) {
                bs.remove();
                Game.addBombRate(1);
            }
        }

    }

    @Override
    public void kill() {

        if (!live) {

            return;
        }
        SoundPlayer getugh;
        getugh = new SoundPlayer(new File("E:\\re\\music\\ugh.wav"));
        getugh.play();
        live = false;
        //_board.addLives(-1);
        Mess msg = new Mess("I'm dying", getXMessage() / 2, getYMessage() / 2, 2, Color.blue, 14);
        board.addMessage(msg);
    }

    @Override
    protected void afterKill() {

        if (timeAfter > 0) --timeAfter;
        else {
            if (_bombs.size() == 0) {

                /**if(_board.getLives() == 0)
                 _board.endGame();
                 else
                 _board.restartLevel();*/

                board.endGame();
            }
        }
    }

    /*
    |--------------------------------------------------------------------------
    | Mob Movement
    |--------------------------------------------------------------------------
     */
    @Override
    protected void calculateMove() {
        int xa = 0, ya = 0;
        if (_input.up) ya--;
        if (_input.down) ya++;
        if (_input.left) xa--;
        if (_input.right) xa++;

        if (xa != 0 || ya != 0) {
            move(xa * Game.getPlayerSpeed() / 2, ya * Game.getPlayerSpeed() / 2);
            moving = true;
        } else {
            moving = false;
        }

    }

    @Override
    public boolean canMove(double x, double y) {
        for (int c = 0; c < 4; c++) {
            double xt = ((_x + x) + c % 2 * 11) / Game.TILES_SIZE;
            double yt = ((_y + y) + c / 2 * 12 - 13) / Game.TILES_SIZE;

            Entity a = board.getEntity(xt, yt, this);

            if (!a.collide(this))
                return false;
        }

        return true;
    }

    @Override
    public void move(double xa, double ya) {
        if (xa > 0) dir = 1;
        if (xa < 0) dir = 3;
        if (ya > 0) dir = 2;
        if (ya < 0) dir = 0;

        if (canMove(0, ya)) {
            _y += ya;
        }

        if (canMove(xa, 0)) {
            _x += xa;
        }
    }

    @Override
    public boolean collide(Entity e) {
        if (e instanceof DirectionalExplosion) {
            kill();
            return false;
        }

        if (e instanceof Enemy) {
            kill();
            return true;
        }

        return true;
    }

    /*
    |--------------------------------------------------------------------------
    | Powerups
    |--------------------------------------------------------------------------
     */
    public void addPowerup(Powerup p) {
        if (p.isRemoved()) return;

        _powerups.add(p);

        p.setValues();
    }


    private void chooseSprite() {
        switch (dir) {
            case 0:
                _sprite = Sprite.player_up;
                if (moving) {
                    _sprite = Sprite.movingSprite(Sprite.player_up_1, Sprite.player_up_2, _animate, 20);
                }
                break;
            case 2:
                _sprite = Sprite.player_down;
                if (moving) {
                    _sprite = Sprite.movingSprite(Sprite.player_down_1, Sprite.player_down_2, _animate, 20);
                }
                break;
            case 3:
                _sprite = Sprite.player_left;
                if (moving) {
                    _sprite = Sprite.movingSprite(Sprite.player_left_1, Sprite.player_left_2, _animate, 20);
                }
                break;
            default:
                _sprite = Sprite.player_right;
                if (moving) {
                    _sprite = Sprite.movingSprite(Sprite.player_right_1, Sprite.player_right_2, _animate, 20);
                }
                break;
        }
    }

    public void clearUsedPowerups() {
        Powerup p;
        for (int i = 0; i < _powerups.size(); i++) {
            p = _powerups.get(i);
            if (p.isActive() == false)
                _powerups.remove(i);
        }
    }

    public void removePowerups() {
        for (int i = 0; i < _powerups.size(); i++) {
            _powerups.remove(i);
        }
    }
}
