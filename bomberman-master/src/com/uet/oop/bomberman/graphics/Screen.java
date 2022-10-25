package com.uet.oop.bomberman.graphics;

import com.uet.oop.bomberman.Funcion.Board;
import com.uet.oop.bomberman.Funcion.Game;
import com.uet.oop.bomberman.entities.Entity;
import com.uet.oop.bomberman.entities.Bomber;

import java.awt.*;

public class Screen {
	protected int _width, _height;
	public int[] _pixels;
	private int HBColor = 0xffff00ff;//0xffff00ff; //pink with alpha channel (ff in the begining)
	
	public static int xOffset = 0, yOffset = 0;
	
	public Screen(int width, int height) {
		_width = width;
		_height = height;
		
		_pixels = new int[width * height];
		
	}
	
	public void clear() {
		for (int i = 0; i < _pixels.length; i++) {
			_pixels[i] = 0;
		}
	}
	
	public void renderEntity(int xpos, int ypos, Entity entity) {
		xpos -= xOffset;
		ypos -= yOffset;
		for(int i = 0;i<entity.getSprite().getSize();i++){
			int  xa = i + xpos;
			for(int j = 0 ;j<entity.getSprite().getSize();j++){
				int ya = j + ypos;
				if(xa < -entity.getSprite().getSize() || xa >= _width
				|| ya < 0 || ya >= _height){
					break;
				}
				if(xa<0) xa = 0;
				int color = entity.getSprite().getPixel(i+j*entity.getSprite().getSize());
				if(color != HBColor) _pixels[xa + ya *_width] = color;
			}
		}
	}
	//chaging this
	
	public void renderEntityWithBelowSprite(int xpos, int ypos, Entity entity, Sprite below) {
		xpos -= xOffset;
		ypos -= yOffset;
		for (int i = 0; i < entity.getSprite().getSize(); i++) {
			int xa = i+xpos;
			for (int j = 0; j < entity.getSprite().getSize(); j++) {
				int  ya = ypos + j;
				if(xa < -entity.getSprite().getSize() || xa >= _width || ya < 0 || ya >= _height) break;
				if(xa < 0) xa = 0;
				int color = entity.getSprite().getPixel(i + j * entity.getSprite().getSize());
				if(color != HBColor)
					_pixels[xa + ya * _width] = color;
				else
					_pixels[xa + ya * _width] = below.getPixel(i + j * below.getSize());
			}
		}
	}
	
	public static void setOffset(int xO, int yO) {
		xOffset = xO;
		yOffset = yO;
	}
	
	public static int calculateXOffset(Board board, Bomber player) {
		if(player == null) return 0;
		int temp = xOffset;
		
		double playerX = player.getX() / 16;
		double complement = 0.5;
		int firstBreakpoint = board.getWidth() / 4;
		int lastBreakpoint = board.getWidth() - firstBreakpoint;
		
		if( playerX > firstBreakpoint + complement && playerX < lastBreakpoint - complement) {
			temp = (int)player.getX()  - (Game.WIDTH / 2);
		}
		
		return temp;
	}

	public void drawEndGame(Graphics g, int points) {
		g.setColor(Color.black);
		g.fillRect(0, 0, getRealWidth(), getRealHeight());
		
		Font font = new Font(null, Font.BOLD, 20 * Game.SCALE);

		g.setFont(font);
		g.setColor(Color.white);
		drawCenteredString("GAME OVER", getRealWidth()/2 + 120, getRealHeight()/2+100, g);
		
		font = new Font(null, Font.BOLD, 10 * Game.SCALE);
		g.setFont(font);
		g.setColor(Color.yellow);
		drawCenteredString("POINTS: " + points, getRealWidth()/2 + 120, getRealHeight() + (Game.TILES_SIZE * 2) * Game.SCALE, g);
		
		
		font = new Font(null, Font.BOLD, 10 * Game.SCALE);
		g.setFont(font);
		g.setColor(Color.GRAY);
	}

	public void drawChangeLevel(Graphics g, int level) {
		g.setColor(Color.black);
		g.fillRect(0, 0, getRealWidth(), getRealHeight());
		
		Font font = new Font("Arial", Font.BOLD, 10 * Game.SCALE);
		g.setFont(font);
		g.setColor(Color.white);
		drawCenteredString("GAME " + level, getRealWidth()/2 + 120, getRealHeight()/2+100, g);
		
	}
	
	public void drawPaused(Graphics g) {
		Font font = new Font("Arial", Font.BOLD, 10 * Game.SCALE);
		g.setFont(font);
		g.setColor(Color.white);
		drawCenteredString("PAUSED", getRealWidth()/2 + 120, getRealHeight()/2+100, g);
		
	}
	
	
	
	public void drawCenteredString(String s, int w, int h, Graphics g) {
	    FontMetrics fm = g.getFontMetrics();
	    int x = (w - fm.stringWidth(s)) / 2;
	    int y = (fm.getAscent() + (h - (fm.getAscent() + fm.getDescent())) / 2);
	    g.drawString(s, x, y);
	 }
	
	public int getWidth() {
		return _width;
	}
	
	public int getHeight() {
		return _height;
	}
	
	public int getRealWidth() {
		return _width * Game.SCALE;
	}
	
	public int getRealHeight() {
		return _height * Game.SCALE;
	}
}
