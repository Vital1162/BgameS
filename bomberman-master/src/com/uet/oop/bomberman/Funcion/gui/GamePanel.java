package com.uet.oop.bomberman.Funcion.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import com.uet.oop.bomberman.Funcion.Game;
import com.uet.oop.bomberman.Funcion.exceptions.BombermanException;

public class GamePanel extends JPanel {

	private Game _game;
	
	public GamePanel(Frame frame) {
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(Game.WIDTH* 2, Game.HEIGHT* 2 ));
		
		try {
			_game = new Game(frame);
			
			add(_game);
			
			_game.setVisible(true);
			
		} catch (BombermanException e) {
			e.printStackTrace();
			//TODO: so we got a error hum..
			System.exit(0);
		}
		setVisible(true);
		setFocusable(true);
		
	}
	
	public void changeSize() {
		setPreferredSize(new Dimension(Game.WIDTH * 2, Game.HEIGHT * 2));
		revalidate();
		repaint();
	}
	
	public Game getGame() {
		return _game;
	}
	
}
