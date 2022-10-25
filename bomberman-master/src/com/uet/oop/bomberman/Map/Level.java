package com.uet.oop.bomberman.Map;

import com.uet.oop.bomberman.Funcion.Board;
import com.uet.oop.bomberman.Funcion.exceptions.LoadLevelException;

public abstract class Level implements ILevel {

    protected int width, height, level;
    protected String[] line;
    protected Board board;


    public Level(String path, Board board) throws LoadLevelException {
        loadLevel(path);
        this.board = board;
    }

    @Override
    public abstract void loadLevel(String path) throws LoadLevelException;

    public abstract void createEntities();

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getLevel() {
        return level;
    }

}
