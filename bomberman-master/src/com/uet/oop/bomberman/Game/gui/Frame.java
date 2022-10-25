package com.uet.oop.bomberman.Game.gui;

import com.uet.oop.bomberman.Game.Game;
import com.uet.oop.bomberman.Game.gui.menu.Menu;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
	
	public GamePanel _gamepane;
	private JPanel _containerpane;
	private InfoPanel _infopanel;
	
	private Game _game;

	public Frame() {

		setJMenuBar(new Menu(this));
		ImageIcon img = new ImageIcon("E:\\BgameS\\music\\BomIcon.png");
		setIconImage(img.getImage());
		_containerpane = new JPanel(new BorderLayout());
		_gamepane = new GamePanel(this);
		_infopanel = new InfoPanel(_gamepane.getGame());
		
		_containerpane.add(_infopanel, BorderLayout.PAGE_START);
		_containerpane.add(_gamepane, BorderLayout.PAGE_END);
		
		_game = _gamepane.getGame();
		
		add(_containerpane);
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);

		_game.start();

	}

	public void newGame() {
		_game.getBoard().newGame();
	}
	
	public void changeLevel(int i) {
		_game.getBoard().changeLevel(i);
	}
	
	public void pauseGame() {
		_game.getBoard().gamePause();
	}
	
	public void resumeGame() {
		_game.getBoard().gameResume();
	}
	
	public boolean isRunning() {
		return _game.isRunning();
	}
	
	public void setTime(int time) {
		_infopanel.setTime(time);
	}
	

	
	public void setPoints(int points) {
		_infopanel.setPoints(points);
	}

	
}