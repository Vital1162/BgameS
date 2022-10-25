package com.uet.oop.bomberman.Game;

import com.uet.oop.bomberman.Game.exceptions.LoadLevelException;

public abstract class Level implements ILevel {

	protected int _width, _height, _level;
	protected String[] _lineTiles;
	protected Board _board;


	public Level(String path, Board board) throws LoadLevelException {
		loadLevel(path);
		_board = board;
	}

	@Override
	public abstract void loadLevel(String path) throws LoadLevelException;
	
	public abstract void createEntities();

	/*
	|--------------------------------------------------------------------------
	| Codes
	|--------------------------------------------------------------------------
	 */


	public int getWidth() {
		return _width;
	}

	public int getHeight() {
		return _height;
	}

	public int getLevel() {
		return _level;
	}

}
