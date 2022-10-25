package com.uet.oop.bomberman.Map;


import com.uet.oop.bomberman.Funcion.exceptions.LoadLevelException;

public interface ILevel {
	public void loadLevel(String path) throws LoadLevelException;
}
