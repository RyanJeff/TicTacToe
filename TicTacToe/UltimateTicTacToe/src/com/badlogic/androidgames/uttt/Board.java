package com.badlogic.androidgames.uttt;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;

import android.graphics.Rect;

public class Board
{
	static final int WORLD_WIDTH = 10;
    static final int WORLD_HEIGHT = 13;
    static final int GRID_WIDTH = 96;
    static final int GRID_HEIGHT = 96;
    static final int GRID_START_X = 16;
    static final int GRID_START_Y = 128;
    static final int SQUARE_SIZE = 32;
    TicTacToeGrid grids[][] = new TicTacToeGrid[3][3];
    public boolean gameOver = false;
    public X x;
    public O o;
    public Game game;
    
    public Board(Game game)
    {
    	this.game = game;
    	grids = new TicTacToeGrid[3][3];
    	for(int i = 0; i < 3; ++i)
    	{
    		grids[i] = new TicTacToeGrid[3];
    		for(int j = 0; j < 3; ++j)
    		{
    			grids[i][j] = new TicTacToeGrid();
    		}
    	}
    }
    
    public void placeX(int x, int y)
    {
    	
		for (int i = 0; i < WORLD_WIDTH; i++) 
        {
            for (int j = 0; j < WORLD_HEIGHT; j++) 
            {
            //    fields[i][j] = false;
                
            }
        }

    }
    
    public void update(float deltaTime) 
    {
    	
    }
    
    public void present()
    {
    	Graphics g = game.getGraphics();
    	//g.drawPixmap(Assets.x, 0, 0);
    	for(int row = 0; row < 3; ++row)
    	{
    		for(int col = 0; col < 3; ++col)
    		{
    			for(int i= 0; i < 9; ++i)
    			{
    				//if the grid square is x
    				if(grids[row][col].grid[i] == 1)
    				{
    					float xLoc = GRID_START_X + col * GRID_WIDTH + (i % 3) * SQUARE_SIZE;
    					float yLoc = GRID_START_Y + row * GRID_HEIGHT + (i / 3) * SQUARE_SIZE;
    					g.drawPixmap(Assets.x, (int)xLoc, (int)yLoc);
    				}
    				
    				else if(grids[row][col].grid[i] == 2)
    				{
    					float xLoc = GRID_START_X + col * GRID_WIDTH + (i % 3) * SQUARE_SIZE;
    					float yLoc = GRID_START_Y + row * GRID_HEIGHT + (i / 3) * SQUARE_SIZE;
    					g.drawPixmap(Assets.x, (int)xLoc, (int)yLoc);
    				}
    			}
    		}
    	}
    }
    
    public void destroy()
    {
    	
    }
}
