package com.badlogic.androidgames.uttt;

import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Pixmap;
import com.badlogic.androidgames.uttt.X;
import com.badlogic.androidgames.framework.Game;

public class X 
{
	public int x, y;
	public int width, height;
	public Pixmap _x; 
	 
	Game game;
	
	public X(int x, int y, int width, int height, Pixmap _x) 
	{     
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this._x = _x;
	}
	
	public void drawXOPixmap(Pixmap pixmap, int x, int y)
	{
		Graphics g = game.getGraphics();
        //g.drawPixmap(Assets.x, 0, 0);
	}
	
	public void update(float deltaTime)
	{
		
	}
	
	public void dispose()
	{
		
	}
}
