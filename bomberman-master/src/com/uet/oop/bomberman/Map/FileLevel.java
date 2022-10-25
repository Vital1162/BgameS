package com.uet.oop.bomberman.Map;

import com.uet.oop.bomberman.Funcion.Board;
import com.uet.oop.bomberman.Funcion.Coordinates;
import com.uet.oop.bomberman.Funcion.Game;
import com.uet.oop.bomberman.Funcion.exceptions.LoadLevelException;
import com.uet.oop.bomberman.entities.*;
import com.uet.oop.bomberman.entities.tile.Grass;
import com.uet.oop.bomberman.entities.tile.Portal;
import com.uet.oop.bomberman.entities.tile.Wall;
import com.uet.oop.bomberman.entities.tile.destroyable.Brick;
import com.uet.oop.bomberman.entities.tile.powerup.PowerupBombs;
import com.uet.oop.bomberman.entities.tile.powerup.PowerupFlames;
import com.uet.oop.bomberman.entities.tile.powerup.PowerupSpeed;
import com.uet.oop.bomberman.graphics.Screen;
import com.uet.oop.bomberman.graphics.Sprite;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.StringTokenizer;

public class FileLevel extends Level {

    public FileLevel(String path, Board board) throws LoadLevelException {
        super(path, board);
    }

    @Override
    public void loadLevel(String path) throws LoadLevelException {
        try {
            URL absPath = FileLevel.class.getResource("/" + path);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(absPath.openStream()));

            String data = in.readLine();
            StringTokenizer tokens = new StringTokenizer(data);

            level = Integer.parseInt(tokens.nextToken());
            height = Integer.parseInt(tokens.nextToken());
            width= Integer.parseInt(tokens.nextToken());

            line = new String[height];

            for (int i = 0; i < height; ++i) {
                line[i] = in.readLine().substring(0, width);
            }

            in.close();
        } catch (IOException e) {
        }
    }

    @Override
    public void createEntities() {
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                addLevelEntity(line[y].charAt(x), x, y);
            }
        }
    }

    public void addLevelEntity(char c, int x, int y) {
        int pos = x + y * getWidth();

        switch (c) { // TODO: minimize this method
            case '#':
                board.addEntitie(pos, new Wall(x, y, Sprite.wall));
                break;
            case 'b':
                ManyE layer = new ManyE(x, y,
                        new Grass(x, y, Sprite.grass),
                        new Brick(x, y, Sprite.brick));

                if (board.isPowerupUsed(x, y, level) == false) {
                    layer.addBeforeTop(new PowerupBombs(x, y, level, Sprite.powerup_bombs));
                }

                board.addEntitie(pos, layer);
                break;
            case 's':
                layer = new ManyE(x, y,
                        new Grass(x, y, Sprite.grass),
                        new Brick(x, y, Sprite.brick));

                if (board.isPowerupUsed(x, y, level) == false) {
                    layer.addBeforeTop(new PowerupSpeed(x, y, level, Sprite.powerup_speed));
                }

                board.addEntitie(pos, layer);
                break;
            case 'f':
                layer = new ManyE(x, y,
                        new Grass(x, y, Sprite.grass),
                        new Brick(x, y, Sprite.brick));


                if (board.isPowerupUsed(x, y, level) == false) {
                    layer.addBeforeTop(new PowerupFlames(x, y, level, Sprite.powerup_flames));
                }

                board.addEntitie(pos, layer);
                break;
            case '*':
                board.addEntitie(pos, new ManyE(x, y,
                        new Grass(x, y, Sprite.grass),
                        new Brick(x, y, Sprite.brick)));
                break;
            case 'x':
                board.addEntitie(pos, new ManyE(x, y,
                        new Grass(x, y, Sprite.grass),
                        new Portal(x, y, board, Sprite.portal),
                        new Brick(x, y, Sprite.brick)));
                break;
            case ' ':
                board.addEntitie(pos, new Grass(x, y, Sprite.grass));
                break;
            case 'p':
                board.addMob(new Bomber(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, board));
                Screen.setOffset(0, 0);

                board.addEntitie(pos, new Grass(x, y, Sprite.grass));
                break;
            //Enemies
            case '1':
                board.addMob(new Balloom(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, board));
                board.addEntitie(pos, new Grass(x, y, Sprite.grass));
                break;
            case '2':
                board.addMob(new Oneal(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, board));
                board.addEntitie(pos, new Grass(x, y, Sprite.grass));
                break;
            case '3':
                board.addMob(new Doll(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, board));
                board.addEntitie(pos, new Grass(x, y, Sprite.grass));
                break;
            case '4':
                board.addMob(new Minvo(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, board));
                board.addEntitie(pos, new Grass(x, y, Sprite.grass));
                break;
            case '5':
                board.addMob(new Kondoria(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, board));
                board.addEntitie(pos, new Grass(x, y, Sprite.grass));
                break;
            default:
                board.addEntitie(pos, new Grass(x, y, Sprite.grass));
                break;
        }
    }
}
