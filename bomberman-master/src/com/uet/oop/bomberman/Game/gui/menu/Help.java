package com.uet.oop.bomberman.Game.gui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import com.uet.oop.bomberman.Game.gui.Frame;
import com.uet.oop.bomberman.Game.gui.InfoDialog;

public class Help extends JMenu {

	public Help(Frame frame)  {
		super("Help");
		
		/*
		 * How to play
		 */
		JMenuItem instructions = new JMenuItem("Don't know how to play ?");
		instructions.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
		instructions.addActionListener(new MenuActionListener(frame));
		add(instructions);
		
		/*
		 * Credits
		 */

		
	}
	
	class MenuActionListener implements ActionListener {
		public Frame _frame;
		public MenuActionListener(Frame frame) {
			_frame = frame;
		}
		
		  @Override
		public void actionPerformed(ActionEvent e) {
			  
			  if(e.getActionCommand().equals("How to play")) {
				  new InfoDialog(_frame, "How to Play", "MOVE: W,A,S,D or click the arrow button \nCreate Bomb: SPACE or X", JOptionPane.NO_OPTION);
			  }

			  
		  }
	}
}
