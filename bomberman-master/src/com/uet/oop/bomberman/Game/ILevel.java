package com.uet.oop.bomberman.Game;


import com.uet.oop.bomberman.Game.exceptions.LoadLevelException;

public interface ILevel {

	public void loadLevel(String path) throws LoadLevelException;
}
