package com.uet.oop.bomberman.level;


import com.uet.oop.bomberman.exceptions.LoadLevelException;

public interface ILevel {

	public void loadLevel(String path) throws LoadLevelException;
}
